/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.repository;

import com.agency.DTO.Item;
import com.agency.entity.Dealer;
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
public interface DealerRepository extends JpaRepository<Dealer, String>{
    
    @Query("SELECT new com.agency.DTO.Item(d) FROM Dealer d")
    public List<Item> getAllDealer();
    
    @Query("SELECT d.tierLimit FROM Dealer d Where d.id=:dealerId")
    public Optional<Integer> getDealerTierLimit(@Param("dealerId") String dealerId);
    
    

    
}
