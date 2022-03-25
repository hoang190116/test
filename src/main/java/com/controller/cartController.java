/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.cartDAO;
import com.model.cart;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
public class cartController {
    
    @Autowired
    private cartDAO cart;
    
    @RequestMapping(value = "/myCart", method = RequestMethod.GET)
    public ModelAndView search(ModelAndView model) {
         try {
            List<cart> list = cart.list(0);
            model.addObject("listProduct", list);    
        } catch (Exception e) {
        }       
        model.setViewName("product_cart");
        return model;
    }
}
