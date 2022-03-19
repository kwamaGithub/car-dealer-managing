/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.restcontroller;

import com.agency.service.ManagingService;
import com.agency.utils.AppConstant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kwamaGithub
 * @Created: 17/03/2022
 */
@RestController
@RequestMapping(path = AppConstant.MANAGING_REST_PATH_API)
public class ListingRestController {

    @Autowired
    private ManagingService managingService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getAllDealer", method = RequestMethod.GET)
    public Object loadFonction() {
        Map<String, Object> p = new HashMap<>();
        p.put("dealer", this.managingService.getAllDealer());
        return p;
    }

}
