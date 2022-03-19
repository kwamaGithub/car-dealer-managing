/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.repository;

import com.agency.DTO.Item;
import com.agency.entity.utils.ParameterValue;
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
public interface ParameterValueRepository extends JpaRepository<ParameterValue, String> {

    @Query("SELECT new com.agency.DTO.Item(p) FROM ParameterValue p  "
            + " WHERE p.parameter.code=:parameterCode")
    public List<Item> getParamterValues(@Param("parameterCode") String parameterCode);

    @Query("SELECT p FROM ParameterValue p  "
            + " WHERE p.parameter.code=:parameterCode AND p.id=:valueCode")
    public Optional<ParameterValue> getParamterValue(@Param("parameterCode") String parameterCode,
            @Param("valueCode") String valueCode);
}
