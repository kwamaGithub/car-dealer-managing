/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.DTO;

import com.agency.utils.AppConstant;
import java.math.BigDecimal;

/**
 *
 * @author kwamaGithub
 * @Created: 17/03/2022
 */
public class ListingDTO {

    private String id;
    private String dealerId;
    private String vehicle;
    private BigDecimal price = BigDecimal.ZERO;
    private String createdAt;
    private String stateCode;
    private String stateLabel;
    private boolean disabledUpdate = true;

    public ListingDTO() {
        if (this.disabledUpdate) {
            this.stateCode = AppConstant.DEFAULT_STATE;
        }
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

    public boolean isDisabledUpdate() {
        return disabledUpdate;
    }

    public void setDisabledUpdate(boolean disabledUpdate) {
        this.disabledUpdate = disabledUpdate;
    }

    @Override
    public String toString() {
        return "ListingDTO{" + "id=" + id + ", dealerId=" + dealerId +
                ", vehicle=" + vehicle + ", price=" + price +
                ", createdAt=" + createdAt + ", stateCode=" + stateCode + 
                ", stateLabel=" + stateLabel + ", disabledUpdate=" + disabledUpdate + '}';
    }
    
    

    
}
