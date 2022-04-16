/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.accountDAO;
import com.dao.cartDAO;
import com.dao.productDAO;
import com.model.Product;
import com.model.account;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Admin
 */
@Controller
public class mainController {
//    private static mainController unit;
//    private mainController(){
//    }
//    public static mainController getIns(){
//        if(unit == null){
//            unit = new mainController();
//        }
//        return unit;
//    }
    
    @Autowired
    private accountDAO acc;
    @Autowired
    private productDAO product;
    @Autowired
    private cartDAO cartD;
    //index page
    
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public ModelAndView index2(HttpServletRequest request) {
        loginController loginC=new loginController();
        ModelAndView model = new ModelAndView();
        model=loginC.checkLogin(request, model);
        account a = loginC.checkCookie(request);
        if(a!= null){
            int number = 0;
            number = cartD.getCountCart(a.getId());
            model.addObject("numberCart", number);
        }
        List<Product> list = product.list();
        model.addObject("listProduct", list); 
        
        list = product.hotList();
        model.addObject("hotList", list);
        
        list = product.slideBar();
        model.addObject("slideBar", list);
        
        model.setViewName("index");
        return model;
    }

    //==============================================================================Test
    @RequestMapping("/testing")
    @ResponseBody
    public void testing(@RequestParam("id") int id, HttpServletResponse response) throws IOException{
//        for (Product product : list()) {
//            if(product.getProduct_id() == id){
////                product.setQuantity(product.getQuantity()+1);
//            }
//        }
//        response.getWriter().println(id);
    }
}
