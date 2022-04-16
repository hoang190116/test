/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.billDAO;
import com.dao.cartDAO;
import com.model.account;
import com.model.bill;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class billController {
    @Autowired
    private cartDAO cartD;
    @Autowired
    private billDAO billD;
    
    @RequestMapping(value = "/myBill", method = RequestMethod.GET)
    public ModelAndView productView(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
        try{
            loginController loginC=new loginController();
            model=loginC.checkLogin(rq, model);
            account a = loginC.checkCookie(rq);
            if(a!= null){
                model.addObject("numberCart", cartD.getCountCart(a.getId()));
                List<bill> list = billD.listBill(a.getId());
                model.addObject("myBilling", list);
                model.setViewName("my_bill");
            }else{
                model.setViewName("redirect:/index");
            }
        }catch(Exception e){
            model.setViewName("redirect:/index");
        }
        return model;
    }
    //==========================================================================View Detail Bill
    @RequestMapping(value = "/billDetail", method = RequestMethod.GET)
    public ModelAndView billDetail(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
        try{
            Integer id = Integer.parseInt(rq.getParameter("id"));
            if(id > 0){
                loginController loginC=new loginController();
                model=loginC.checkLogin(rq, model);
                account a = loginC.checkCookie(rq);
                if(a!= null){
                    model.addObject("numberCart", cartD.getCountCart(a.getId()));
                    bill b = billD.get(id);
                    List<bill> list = new ArrayList<>();
                    list.add(b);
                    model.addObject("billDetail", list);
                    model.setViewName("bill_detail");
                }
            }else{
                model.setViewName("redirect:/index");
            }
        }catch(Exception e){
            model.setViewName("redirect:/index");
        }
        return model;
    }
}
