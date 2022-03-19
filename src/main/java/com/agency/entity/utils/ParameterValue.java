/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.entity.utils;

import com.agency.utils.AppConstant;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author kwamaGithub
 * @Created: 16/03/2022
 */
@Entity
@Table(name = "values", schema = AppConstant.SCHEMA_NAME)
public class ParameterValue implements Serializable {

    @Id
    @Column(name = "id", length = 100)
    private String id;

    @ManyToOne
    @JoinColumn(name = "parameter_code", referencedColumnName = "code")
    private AppParameter parameter;

    @Column(name = "label", length = 100)
    private String label;
    
    @Column(name = "value", length = 100)
    private String value;

    public ParameterValue() {
    }

    public ParameterValue(String id, AppParameter parameter, String value) {
        this.id = id;
        this.parameter = parameter;
        this.value = value;
    }

    public ParameterValue(String id, AppParameter parameter, String label, String value) {
        this.id = id;
        this.parameter = parameter;
        this.label = label;
        this.value = value;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppParameter getParameter() {
        return parameter;
    }

    public void setParameter(AppParameter parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    
    @Override
    public String toString() {
        return "ParameterValue{" + "id=" + id + ", parameter=" + parameter + ", value=" + value + '}';
    }

}
