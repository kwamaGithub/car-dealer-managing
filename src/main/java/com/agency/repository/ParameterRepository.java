/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.repository;

import com.agency.entity.utils.AppParameter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kwamaGithub
 * @Created: 17/03/2022
 */
public interface ParameterRepository extends JpaRepository<AppParameter, String>{
    
}
