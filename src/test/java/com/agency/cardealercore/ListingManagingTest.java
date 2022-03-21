/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.cardealercore;

import com.agency.DTO.Item;
import com.agency.DTO.ListingDTO;
import com.agency.repository.DealerRepository;
import com.agency.repository.ListingRepository;
import com.agency.repository.ParameterValueRepository;
import com.agency.restcontroller.ListingRestController;
import com.agency.service.ManagingService;
import com.agency.utils.AppConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author kwamaGithub
 * @Created: 20/03/2022
 */
//@WebMvcTest(ListingRestController.class)
@WebMvcTest
public class ListingManagingTest {

    private static final Integer STATUS_OK = 200;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

   
    @MockBean
    ManagingService managingService;

    //CAR DEALER
    Item DEALER_RECORD_1 = new Item("HDAI", "Hyundai", "20");
    Item DEALER_RECORD_2 = new Item("HD", "Honda", "5");
    Item DEALER_RECORD_3 = new Item("GC", "GMC", "0");
    Item DEALER_RECORD_4 = new Item("JP", "Jeep", "2");

    //DEALER HONDA LISTING RECORD
    ListingDTO LISTING_RECORD_1 = new ListingDTO("1L", DEALER_RECORD_2.getId(),
            "HONDA VH 002", BigDecimal.valueOf(2300), AppConstant.DEFAULT_STATE);
    ListingDTO LISTING_RECORD_2 = new ListingDTO("2L", DEALER_RECORD_2.getId(),
            "HONDA VH 030", BigDecimal.valueOf(2000), AppConstant.DEFAULT_STATE);
    ListingDTO LISTING_RECORD_3 = new ListingDTO("3L", DEALER_RECORD_2.getId(),
            "HONDA VH 00DS", BigDecimal.valueOf(3400), AppConstant.DEFAULT_STATE);

    //LISTING RECORD FOR CREATION TEST
    ListingDTO LISTING_RECORD_4 = new ListingDTO("4L", DEALER_RECORD_2.getId(),
            "HONDA VH 002", BigDecimal.valueOf(2300), AppConstant.DEFAULT_STATE);

    @Test
    public void getAllDealer() throws Exception {
        List<Item> dealers = new ArrayList<>(Arrays.asList(DEALER_RECORD_1, DEALER_RECORD_2,
                DEALER_RECORD_3, DEALER_RECORD_4));
        Mockito.when(this.managingService.getAllDealer()).thenReturn(dealers);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/managing/getAllDealer")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        JSONObject responseExcpeted = new JSONObject(result.getResponse().getContentAsString());
        JSONArray array = (JSONArray) responseExcpeted.getJSONArray("dealers");
        assertEquals(STATUS_OK, result.getResponse().getStatus());
        assertEquals(4, array.length());
        assertEquals(array.getJSONObject(0).get("label"), "Hyundai");
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getDealerListingByStateTest() throws Exception {

        List<ListingDTO> listings = new ArrayList<>(Arrays.asList(LISTING_RECORD_1, LISTING_RECORD_2,
                LISTING_RECORD_3));

        Mockito.when(this.managingService.getDealerListingByState(DEALER_RECORD_2.getId(),
                AppConstant.DEFAULT_STATE)).thenReturn(listings);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/managing/getDealerListing/" + DEALER_RECORD_2.getId() + "/" + AppConstant.DEFAULT_STATE)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        JSONObject responseExcpeted = new JSONObject(result.getResponse().getContentAsString());
        JSONArray array = (JSONArray) responseExcpeted.getJSONArray("listings");
        assertEquals(STATUS_OK, result.getResponse().getStatus());
        assertEquals(3, array.length());

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void createListingTest() throws Exception {
        Mockito.when(this.managingService.createListing(LISTING_RECORD_4)).thenReturn(LISTING_RECORD_4);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/managing/saveListing")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(LISTING_RECORD_4));

        MvcResult result = mockMvc.perform(mockRequest).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void updateListingTest() throws Exception {
        LISTING_RECORD_4.setVehicle("HONDA NEW VERSION");
        LISTING_RECORD_4.setCreatedAt(Instant.now().toString());

        Mockito.when(this.managingService.updateListing(LISTING_RECORD_4)).thenReturn(LISTING_RECORD_4);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/managing/updateListing")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(LISTING_RECORD_4));

        MvcResult result = mockMvc.perform(mockRequest).andReturn();
        JSONObject responseExcpeted = new JSONObject(result.getResponse().getContentAsString());

        assertEquals(STATUS_OK, result.getResponse().getStatus());
        assertEquals(new JSONObject(responseExcpeted.get("listingDTO").toString())
                .getString("vehicle"), "HONDA NEW VERSION");
        assertEquals(true, responseExcpeted.get("listingEdited"));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void publishListingTest() throws Exception {
        LISTING_RECORD_4.setStateCode(AppConstant.PUBLISHED_STATE);

        Mockito.when(this.managingService.publishOrUnpublishListing(LISTING_RECORD_4)).thenReturn(LISTING_RECORD_4);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/managing/publishedOrUnpublish")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(LISTING_RECORD_4));

        MvcResult result = mockMvc.perform(mockRequest).andReturn();
        assertEquals(STATUS_OK, result.getResponse().getStatus());
    }

}
