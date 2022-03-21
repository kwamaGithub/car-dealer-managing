/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.DTO;

import com.agency.entity.Listing;
import com.agency.utils.AppConstant;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kwamaGithub
 * @Created: 17/03/2022
 */
public class ListingDTO {

    private String id;
    @NotNull
    private String dealerId;

    private String vehicle;
    @NotNull
    private BigDecimal price = BigDecimal.ZERO;

    private String createdAt;
    @NotNull
    private String stateCode =AppConstant.DEFAULT_STATE;

    private String stateLabel;

//    public ListingDTO() {
//        if (this.disabledUpdate) {
//            this.stateCode = AppConstant.DEFAULT_STATE;
//        }
//    }
    public ListingDTO() {
    }

    public ListingDTO(Listing listing) {
        this.id = listing.getId();
        this.dealerId = listing.getDealerID().getId();
        this.vehicle = listing.getVehicle();
        this.price= listing.getPrice();
//        this.createdAt = FOMATER.format(listing.getCreatedAt());
        this.stateCode = listing.getState().getId();
        this.stateLabel = listing.getState().getLabel();
    }

    public ListingDTO(String id, String dealerId, String vehicle, BigDecimal price,
            String stateCode) {
        this.id = id;
        this.dealerId = dealerId;
        this.vehicle = vehicle;
        this.price = price;
        this.stateCode = stateCode;
    }

    public ListingDTO(String dealerId, String vehicle, BigDecimal price,
            String stateCode) {
        this.dealerId = dealerId;
        this.vehicle = vehicle;
        this.price = price;
        this.stateCode = stateCode;
    }

    public ListingDTO(String id, String dealerId, String vehicle, String createdAt, String stateCode, String stateLabel) {
        this.id = id;
        this.dealerId = dealerId;
        this.vehicle = vehicle;
        this.createdAt = createdAt;
        this.stateCode = stateCode;
        this.stateLabel = stateLabel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateLabel() {
        return stateLabel;
    }

    public void setStateLabel(String stateLabel) {
        this.stateLabel = stateLabel;
    }


    @Override
    public String toString() {
        return "ListingDTO{" + "id=" + id + ", dealerId=" + dealerId
                + ", vehicle=" + vehicle + ", price=" + price
                + ", createdAt=" + createdAt + ", stateCode=" + stateCode
                + ", stateLabel=" + stateLabel + '}';
    }

}
