/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.productDAO;
import com.model.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class productController {
    @Autowired
    private productDAO product;
    
    @RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest rq) {
        String s = rq.getParameter("search");
        ModelAndView model = new ModelAndView();
         try {
            List<Product> list = product.search(s);
            model.addObject("listProduct", list);    
        } catch (Exception e) {
            }       
        model.setViewName("product_list");
        return model;
    }
}
