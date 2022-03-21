/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.DTO;

import com.agency.entity.Dealer;
import com.agency.entity.utils.ParameterValue;

/**
 *
 * @author kwamaGithub
 * @Created: 17/03/2022
 */
public class Item {

    private String id;
    private String code;
    private String label;
    private String value;

    public Item() {
    }

    public Item(String id, String code, String label, String value) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.value = value;
    }
    public Item(String id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    public Item(Dealer dealer) {
        this.id = dealer.getId();
        this.label = dealer.getName();
    }

    public Item(ParameterValue pValue) {
        this.id = pValue.getId();
        this.code=pValue.getParameter().getCode();
        this.label = pValue.getParameter().getLabel();
        this.value = pValue.getValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", code=" + code + ", label=" + label + ", value=" + value + '}';
    }
}
