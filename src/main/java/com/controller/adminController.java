/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
public class adminController {
    @RequestMapping("/adminDashboard")
    public ModelAndView adminDashb(ModelAndView model) {
        model.setViewName("adminDashboard");
        return model;
    }
    @RequestMapping("/adminUser")
    public ModelAndView adminUser(ModelAndView model) {
        model.setViewName("adminUser");
        return model;
    }
    @RequestMapping("/adminTable")
    public ModelAndView adminTable(ModelAndView model) {
        model.setViewName("adminTable");
        return model;
    }
    @RequestMapping("/adminBlank")
    public ModelAndView adminBlank(ModelAndView model) {
        model.setViewName("adminBlank");
        return model;
    }
}
