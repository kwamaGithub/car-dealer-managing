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
import com.agency.restcontroller.ListingRestController;
import com.agency.service.ManagingService;
import com.agency.utils.AppConstant;
import java.math.BigDecimal;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author kwamaGithub
 * @Created: 20/03/2022
 */
@WebMvcTest(ListingRestController.class)
public class ListingManagingTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DealerRepository dealerRepository;

    @MockBean
    ListingRepository listingRepository;

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

    @Test
    public void getAllDealer() throws Exception {
        List<Item> dealers = new ArrayList<>(Arrays.asList(DEALER_RECORD_1, DEALER_RECORD_2,
                DEALER_RECORD_3, DEALER_RECORD_4));
        Mockito.when(this.managingService.getAllDealer()).thenReturn(dealers);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/managing/getAllDealer")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        JSONObject responseExcpeted = new JSONObject(result.getResponse().getContentAsString());
        Integer statusExcepted = 200;
        JSONArray array = (JSONArray) responseExcpeted.getJSONArray("dealers");
        assertEquals(statusExcepted, result.getResponse().getStatus());
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
        Integer statusExcepted = 200;
        JSONArray array = (JSONArray) responseExcpeted.getJSONArray("listings");
        assertEquals(statusExcepted, result.getResponse().getStatus());
        assertEquals(3, array.length());

    }

}
