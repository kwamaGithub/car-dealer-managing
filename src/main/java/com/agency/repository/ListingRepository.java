/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.repository;

import com.agency.DTO.ListingDTO;
import com.agency.entity.Listing;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author kwamaGithub
 * @Created: 17/03/2022
 */
public interface ListingRepository extends JpaRepository<Listing, String> {

    @Query("SELECT new com.agency.DTO.ListingDTO(l) FROM Listing l "
            + " WHERE l.dealerID.id=:dealerId")
    public List<ListingDTO> getDealerListings(@Param("dealerId") String dealerId);
    
    @Query("SELECT new com.agency.DTO.ListingDTO(l) FROM Listing l ")
    public List<ListingDTO> getDealerListings();
    
    @Query("SELECT count(l) FROM Listing l "
            + " WHERE l.dealerID.id=:dealerId ")
    public Optional<Integer> countDealerListing(@Param("dealerId") String dealerId);
    
    @Query("SELECT count(l) FROM Listing l "
            + " WHERE l.dealerID.id=:dealerId AND l.state.id=:stateId")
    public Optional<Integer> countDealerListingByState(@Param("dealerId") String dealerId,
            @Param("stateId") String stateId);

    @Query("SELECT new com.agency.DTO.ListingDTO(l) FROM Listing l "
            + " WHERE l.dealerID.id=:dealerId ORDER BY l.createdAt DESC")
    public List<ListingDTO> getDealerListingsByCreatedDateDescending(@Param("dealerId") String dealerId);

    @Query("SELECT new com.agency.DTO.ListingDTO(l) FROM Listing l "
            + " WHERE l.state.id=:stateId")
    public List<ListingDTO> getDealerListingsByState(@Param("stateId") 
            String stateId);

    
     @Query("SELECT new com.agency.DTO.ListingDTO(l) FROM Listing l "
            + " WHERE l.dealerID.id=:dealerId AND l.state.id=:stateId")
    public List<ListingDTO> getDealerListingsByDealerAndState(@Param("dealerId") String dealerId,
            @Param("stateId") String stateId);


}
