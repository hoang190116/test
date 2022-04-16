/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.accountDAO;
import com.dao.billDAO;
import com.model.account;
import com.model.bill;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value = {"emp"})
public class employeeController {
    @Autowired
    private accountDAO accDao;
    @Autowired
    private billDAO billDao;
    
    @RequestMapping(value="index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest rq, ModelAndView model) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("staff")){
                try{
                    List<bill> list = billDao.listWaiting();
                    model.addObject("orderList", list);
                    model.setViewName("employee/empIndex");
                    return model;
                }catch(Exception e){
                    
                }
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value="/receive", method = RequestMethod.GET)
    public ModelAndView receive(HttpServletRequest rq, ModelAndView model) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("staff")){
                try {
                    Integer id = Integer.parseInt(rq.getParameter("id"));
                    bill b = new bill();
                    b.setBill_id(id);
                    b.setStatus("In processing");
                    billDao.update(b);
                    model.setViewName("redirect:/emp/index");
                    return model;
                } catch (Exception e) {
                    
                }
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value="orderProcess", method = RequestMethod.GET)
    public ModelAndView orderProcess(HttpServletRequest rq, ModelAndView model) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("staff")){
                try {
                    List<bill> list = billDao.listProcess();
                    model.addObject("orderList", list);
                    model.setViewName("employee/em_orderP");
                    return model;
                } catch (Exception e) {
                    
                }
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value="updateOrder", method = RequestMethod.GET)
    public ModelAndView orderUpdate(HttpServletRequest rq, ModelAndView model) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("staff")){
                try {
                    Integer id = Integer.parseInt(rq.getParameter("id"));
                    bill b = billDao.get(id);
                    
                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
                    String date = dateFormat.format(b.getDate());
                    b.setDateString(date);
                    boolean cash = true;
                    if(b.getCard_number() != null){
                        cash = false;
                    }
                    model.addObject("cash", cash);
                    model.addObject("b", b);
                    model.setViewName("employee/em_orderUpdate");
                    return model;
                } catch (Exception e) {
                    
                }
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value="/saveOrder", method = RequestMethod.POST)
    public ModelAndView orderSave(HttpServletRequest rq, @ModelAttribute bill b) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        ModelAndView model = new ModelAndView(); 
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("staff")){
                try {
                    billDao.update(b);
                    model.setViewName("redirect:/emp/updateOrder?id="+b.getBill_id());
                    return model;
                } catch (Exception e) {
                    
                }
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value="deliver", method = RequestMethod.GET)
    public ModelAndView orderDeliver(HttpServletRequest rq, ModelAndView model) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("staff")){
                try {
                    List<bill> list = billDao.listDeliver();
                    model.addObject("orderList", list);
                    model.setViewName("employee/em_delivered");
                    return model;
                } catch (Exception e) {
                    
                }
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value="history", method = RequestMethod.GET)
    public ModelAndView orderHistory(HttpServletRequest rq, ModelAndView model) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("staff")){
                try {
                    List<bill> list = billDao.listFinish();
                    model.addObject("orderList", list);
                    model.setViewName("employee/em_history");
                    return model;
                } catch (Exception e) {
                    
                }
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value="orderDetail", method = RequestMethod.GET)
    public ModelAndView orderDetail(HttpServletRequest rq, ModelAndView model) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("staff")){
                try {
                    Integer id = Integer.parseInt(rq.getParameter("id"));
                    bill b = billDao.get(id);
                    
                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
                    String date = dateFormat.format(b.getDate());
                    b.setDateString(date);
                    boolean cash = true;
                    if(b.getCard_number() != null){
                        cash = false;
                    }
                    model.addObject("cash", cash);
                    
                    List<bill> list = new ArrayList<>();
                    list.add(b);
                    model.addObject("billDetail", list);
                    model.setViewName("employee/em_orderDetail");
                    return model;
                } catch (Exception e) {
                    
                }
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
}
