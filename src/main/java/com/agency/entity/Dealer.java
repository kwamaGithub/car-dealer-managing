/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.entity;

import com.agency.utils.AppConstant;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author kwamaGithub
 * @Created: 16/03/2022
 */
@Entity
@Table(name = "dealer", schema = AppConstant.SCHEMA_NAME)
public class Dealer implements Serializable {

    @Id
    @Column(name = "id", length = 40)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name", length = 100,nullable = false)
    private String name;
    
    @Column(name = "tier_limit")
    private Integer tierLimit;

    public Dealer() {
    }
    
    public Dealer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dealer(String id, String name, Integer tierLimit) {
        this.id = id;
        this.name = name;
        this.tierLimit = tierLimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTierLimit() {
        return tierLimit;
    }

    public void setTierLimit(Integer tierLimit) {
        this.tierLimit = tierLimit;
    }

    @Override
    public String toString() {
        return "Dealer{" + "id=" + id + ", name=" + name + ", tierLimit=" + tierLimit + '}';
    }
}
