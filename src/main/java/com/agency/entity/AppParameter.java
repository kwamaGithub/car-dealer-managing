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
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author kwamaGithub
 * @Created: 16/03/2022
 */
@Entity
@Table(name = "parameters", schema = AppConstant.SCHEMA_NAME)
public class AppParameter implements Serializable {

    @Id
    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "label", length = 100)
    private String label;

    public AppParameter() {
    }

    public AppParameter(String code, String label) {
        this.code = code;
        this.label = label;
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

    @Override
    public String toString() {
        return "AppParameter{" + "code=" + code + ", label=" + label + '}';
    }
}
