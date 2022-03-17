/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.entity;

import com.agency.entity.utils.ParameterValue;
import com.agency.utils.AppConstant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author kwamaGithub
 * @Created: 16/03/2022
 */
@Entity
@Table(name = "listing", schema = AppConstant.SCHEMA_NAME)
public class Listing implements Serializable {

    @Id
    @Column(name = "id", length = 40)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "dealer_id", referencedColumnName = "id")
    private Dealer dealerID;

    @Column(name = "vehicle", length = 100)
    private String vehicle;

    @Column(name = "price", nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name = "create_at")
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private ParameterValue state;

    public Listing() {
    }

    public Listing(String id, Dealer dealerID, String vehicle, Instant createdAt, ParameterValue state) {
        this.id = id;
        this.dealerID = dealerID;
        this.vehicle = vehicle;
        this.createdAt = createdAt;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Dealer getDealerID() {
        return dealerID;
    }

    public void setDealerID(Dealer dealerID) {
        this.dealerID = dealerID;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public ParameterValue getState() {
        return state;
    }

    public void setState(ParameterValue state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Listing{" + "id=" + id + ", dealerID=" + dealerID + ", vehicle=" + vehicle + ", price=" + price + ", createdAt=" + createdAt + ", state=" + state + '}';
    }
    
    
}
