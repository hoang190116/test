/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.accountDAO;
import com.model.account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
public class loginController {
    private static Integer id;
    
    public static Integer getID(){
        return loginController.id;
    }
    
    @Autowired
    private accountDAO acc;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model) {
        account ac = new account();
        model.addObject("account", ac);
        model.setViewName("login");
        return model;
    }
    
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ModelAndView loginIn(@ModelAttribute account account) {
        ModelAndView model = new ModelAndView();
        String checkLogin = null;
        try {
            account ac = acc.login(account);
            if (ac.getRole().equals("admin")) {
                model.setViewName("");
                try {
                    this.id = ac.getId();
                    model.addObject("checkLogin", null);
                    model.setViewName("index");
//                    List<user> list = daoUser.list();
//                    model.addObject("listUser", list);
//                    this.checking = "in";
                } catch (Exception e) {
                }
            } else if (ac.getRole().equals("staff")) {
                model.setViewName("");
                try {
//                    List<user> list = daoUser.list2();
//                    model.addObject("listUser", list);
                } catch (Exception e) {
                }
            } else if (ac.getRole().equals("user")) {
                try {
                    this.id = ac.getId();
                    model.addObject("checkLogin", null);
                    model.setViewName("index.jsp");
                } catch (Exception e) {
                }
            } else {
                checkLogin = "false";
                model.addObject("checkLogin", checkLogin);
                model.setViewName("login");
            }
        } catch (Exception e) {
            checkLogin = "false";
            model.addObject("checkLogin", checkLogin);
            model.setViewName("login");
        }

        return model;
    }
}
