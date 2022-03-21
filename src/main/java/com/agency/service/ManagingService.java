/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.service;

import com.agency.DTO.Item;
import com.agency.DTO.ListingDTO;
import com.agency.entity.Listing;
import com.agency.exceptionHandler.InterneExpection;
import com.agency.interface_naming.ListingInterface;
import com.agency.repository.DealerRepository;
import com.agency.repository.ListingRepository;
import com.agency.repository.ParameterValueRepository;
import com.agency.utils.AppConstant;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kwamaGithub
 * @Created: 17/03/2022
 */
@Service
@Transactional
public class ManagingService implements ListingInterface {

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ParameterValueRepository parameterValueRepository;

    DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("yyyy/MM/dd").withZone(ZoneId.systemDefault());

    ;

    @Override
    public List<Item> getAllDealer() {
        return dealerRepository.getAllDealer();
    }

    /**
     *
     * Create a listing. All the created listings should have state `draft` by
     * default;
     *
     * @param listingDTO
     * @return
     */
    @Override
    public ListingDTO createListing(ListingDTO listingDTO) {
        this.listingValidationBeforeCreationOrUpdate(listingDTO);
        Listing listing = new Listing();
        listing.setDealerID(this.dealerRepository.getById(listingDTO.getDealerId()));
        listing.setVehicle(listingDTO.getVehicle());
        listing.setPrice(listingDTO.getPrice());
        listing.setState(this.parameterValueRepository.getParamterValue(AppConstant.LISTING_STATE_CODE, AppConstant.DEFAULT_STATE).get());
        listing = this.listingRepository.save(listing);
        return this.mappedListingToDTO(listing);
    }


    /**
     * Update a listing
     *
     * @param listingDTO
     * @return 
     */
    @Override
    public ListingDTO updateListing(ListingDTO listingDTO) {
        this.listingValidationBeforeCreationOrUpdate(listingDTO);
        Listing listing = new Listing();
        if (listingDTO.getId() != null) {
            listing = this.listingRepository.getById(listingDTO.getId());
            listing.setId(listingDTO.getId());
            listing.setDealerID(this.dealerRepository.getById(listingDTO.getDealerId()));
            listing.setVehicle(listingDTO.getVehicle());
            listing.setPrice(listingDTO.getPrice());
//            listing.setCreatedAt(FORMATER.format(listing.getCreatedAt()));
            listing.setState(this.parameterValueRepository.getParamterValue(AppConstant.LISTING_STATE_CODE, listingDTO.getStateCode()).get());
        } else {
            throw new InterneExpection("Listing uuid is requiered", null);
        }
        return this.mappedListingToDTO(this.listingRepository.
                save(listing));
    }

    /**
     *
     * @return
     */
    @Override
    public List<ListingDTO> getAllListing() {
        return listingRepository.getDealerListings();
    }

    /**
     *
     * @param dealerCode
     * @param stateCode
     * @return
     */
    @Override
    public List<ListingDTO> getDealerListingByState(String dealerCode, String stateCode) {
        List<ListingDTO> listingDTOs = new ArrayList<>();
        listingRepository.getDealerListingsByDealerAndState(dealerCode, stateCode).stream().forEach(listing -> {
            listing.setCreatedAt(FORMATER.format(listingRepository.getById(listing.getId()).getCreatedAt()));
            listingDTOs.add(listing);
        });
        return listingDTOs;
    }

    /**
     *
     * @param listingDTO
     * @return
     */
    @Override
    public ListingDTO publishOrUnpublishListing(ListingDTO listingDTO) {
        Listing listing = this.listingRepository.getById(listingDTO.getId());
        if (listing != null) {
            if (listing.getState().getId().equals(AppConstant.DEFAULT_STATE)) {
                listing.setState(this.parameterValueRepository.getParamterValue(AppConstant.LISTING_STATE_CODE,
                        AppConstant.PUBLISHED_STATE).get());
            } else {
                listing.setState(this.parameterValueRepository.getParamterValue(AppConstant.LISTING_STATE_CODE,
                        AppConstant.DEFAULT_STATE).get());
            }
        } else {
            return listingDTO;
        }
        return this.mappedListingToDTO(this.listingRepository.save(listing));
    }

    /**
     *
     * @param dealerCode
     * @return
     */
    @Override
    public List<ListingDTO> getDealerListing(String dealerCode) {
        return this.listingRepository.getDealerListings(dealerCode);
    }

    /**
     *
     * @param dealerId
     * @param stateId
     * @return
     */
    @Override
    public ListingDTO getDealerOldestListing(String dealerId, String stateId) {
        List<ListingDTO> listingDTOs = this.listingRepository.getDealerListingsByCreatedDateAscending(dealerId,
                stateId);
        if (!listingDTOs.isEmpty()) {
            return listingDTOs.get(0);
        } else {
            throw new InterneExpection("This dealer have not listings ", null);
        }
    }

    /**
     *
     *
     * @param l
     * @return
     */
    public ListingDTO mappedListingToDTO(Listing l) {
        System.out.println(" LISTING l " + l.toString());
        ListingDTO listingDTO = new ListingDTO();
        listingDTO.setId(l.getId());
        listingDTO.setDealerId(l.getDealerID().getId());
        listingDTO.setCreatedAt(FORMATER.format(l.getCreatedAt()));
        listingDTO.setStateCode(l.getState().getId());
        listingDTO.setStateLabel(l.getState().getLabel());
        listingDTO.setVehicle(l.getVehicle());
        listingDTO.setPrice(l.getPrice());
        return listingDTO;
    }

    /**
     *
     *
     * @param listingDTO
     */
    public void listingValidationBeforeCreationOrUpdate(ListingDTO listingDTO) {
        //Before listing creation or updating
        if (listingDTO.getDealerId() == null) {
            throw new InterneExpection("Dealer is requiered", null);
        }
        if (listingDTO.getPrice() == null
                | (listingDTO.getPrice() != null && listingDTO.getPrice().compareTo(BigDecimal.ZERO) == 0)) {
            throw new InterneExpection("The vehicle price is requiered", null);
        }
        if (listingDTO.getStateCode() == null) {
            throw new InterneExpection("A state is requiered", null);
        }

        //Only Before Listing creation
        if (listingDTO.getId() == null) {
            if (!dealerRepository.getDealerTierLimit(listingDTO.getDealerId()).isPresent()) {
                throw new InterneExpection("This dealer have not autorization to published listings ", null);
            } else if (dealerRepository.getDealerTierLimit(listingDTO.getDealerId()).isPresent()) {
                if (listingRepository.countDealerListingByState(listingDTO.getDealerId(), AppConstant.PUBLISHED_STATE).get().
                        equals(dealerRepository.getDealerTierLimit(listingDTO.getDealerId()).get())) {
                    throw new InterneExpection("The tier limit to the dealer is rached. you have a choice to unpublish the oldest listing of a dealer", null);
                }
            }

        }
    }

}
