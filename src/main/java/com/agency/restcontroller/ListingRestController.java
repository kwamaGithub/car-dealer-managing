/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.restcontroller;

import com.agency.DTO.ListingDTO;
import com.agency.service.ManagingService;
import com.agency.utils.AppConstant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kwamaGithub
 * @Created: 17/03/2022
 */
@RestController
@RequestMapping(path = AppConstant.MANAGING_REST_PATH_API)
public class ListingRestController {

    private static final String DEALERS = "dealers";
    private static final String LISTINGS = "listings";
    private static final String LISTING_DTO = "listingDTO";
    private static final String LISTING_EDITED = "listingEdited";
    private static final String LISTING_ID = "listingId";

    @Autowired
    private ManagingService managingService;

    /**
     *
     * @return
     */
    @GetMapping(value = "/getAllDealer")
    public Object getDealers() {
        Map<String, Object> p = new HashMap<>();
        p.put(DEALERS, this.managingService.getAllDealer());
        return p;
    }

    /**
     *
     * @param dealerId
     * @param stateId
     * @return
     */
    @GetMapping(value = "/getDealerListing/{dealerId}/{stateId}")
    public Object getDealerListingByState(@PathVariable("dealerId") String dealerId,
            @PathVariable("stateId") String stateId) {
        Map<String, Object> p = new HashMap<>();
        p.put(LISTINGS, this.managingService.getDealerListingByState(dealerId, stateId));
        return p;
    }

    /**
     *
     * @param listingDTO
     * @return
     */
    @PutMapping(value = "/updateListing")
    public Object updateListing(@RequestBody() ListingDTO listingDTO) {
        Map<String, Object> p = new HashMap<>();
        this.managingService.updateListing(listingDTO);
        p.put(LISTING_EDITED, true);
        p.put(LISTING_ID, listingDTO.getId());
        return p;
    }

    /**
     *
     * @param listingDTO
     * @return
     */
    @PostMapping(value = "/saveListing")
    public Object saveListing(@RequestBody() ListingDTO listingDTO) {
        Map<String, Object> p = new HashMap<>();
        p.put(LISTING_DTO, this.managingService.createListing(listingDTO));
        return p;
    }

    /**
     *
     * @param listingId
     * @return
     */
    @PutMapping(value = "/publishedOrUnpublish/{listingId}")
    public Object updateListing(@PathVariable("listingId") String listingId) {
        Map<String, Object> p = new HashMap<>();
        p.put(LISTING_DTO, this.managingService.publishOrUnpublishListing(listingId));
        return p;
    }
}
