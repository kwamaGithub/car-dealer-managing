/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.interface_naming;

import com.agency.DTO.Item;
import com.agency.DTO.ListingDTO;
import java.util.List;

/**
 *
 * @author kwamaGithub
 * @Created: 17/03/2022
 */
public interface ListingInterface {
    
    public List<Item> getAllDealer();
    
    public ListingDTO createListing(ListingDTO listingDTO);
    public ListingDTO updateListing(ListingDTO listingDTO);
    public List<ListingDTO> getAllListing();
    public List<ListingDTO> getDealerListingByState(String dealerCode,String stateCode);
    public List<ListingDTO> getDealerListing(String dealerCode);
    public ListingDTO publishOrUnpublishListing(ListingDTO listingDTO);
    
    public ListingDTO getDealerOldestListing(String dealerId, String stateId);
    
}
