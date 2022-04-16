/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.accountDAO;
import com.dao.cartDAO;
import com.dao.paymentDAO;
import com.model.account;
import com.model.payment;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
public class accountController {
    @Autowired
    private accountDAO aDao;
    @Autowired
    private cartDAO cartD;
    @Autowired
    private paymentDAO payD;
    
    @RequestMapping("/myProfile")
    public ModelAndView profile(HttpServletRequest request) throws Exception{
        try{
            loginController loginC=new loginController();
            account a = loginC.checkCookie(request);
            if(a != null){
                ModelAndView model = new ModelAndView();
                model=loginC.checkLogin(request, model);
                model.addObject("numberCart", cartD.getCountCart(a.getId()));
                account a2 = aDao.get(a.getId());
                a2.setPass(null);
                model.addObject("user", a2);
                model.setViewName("profile");
                return model;
            }
        }catch(Exception e){
            throw new Exception("ERROR Profile");
        }
        return new ModelAndView("redirect:/index");
    }
    @RequestMapping(value = "/saveInfor", method = RequestMethod.POST)
    public ModelAndView updateInfor(HttpServletRequest rq, @ModelAttribute account user) throws Exception {
        try{
            loginController loginC=new loginController();
            account a = loginC.checkCookie(rq);
            if(a != null){
                ModelAndView model = new ModelAndView();
                model=loginC.checkLogin(rq, model);
                model.addObject("numberCart", cartD.getCountCart(a.getId()));
                user.setRole("user");
                aDao.update(user, a.getId());
                account a2 = aDao.get(a.getId());
                a2.setPass(null);
                model.addObject("user", a2);
                model.setViewName("profile");
                return model;
            }
        }catch(Exception e){
            throw new Exception("ERROR Update Profile");
        }
        return new ModelAndView("redirect:/index");
    }
    @RequestMapping(value = "/deletePayment", method = RequestMethod.GET)
    public ModelAndView deletePayment(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
        try{
            Integer id = Integer.parseInt(rq.getParameter("id"));
            if(id > 0){
                loginController loginC=new loginController();
                model=loginC.checkLogin(rq, model);
                account a = loginC.checkCookie(rq);
                if(a!= null){
                    model.addObject("numberCart", cartD.getCountCart(a.getId()));
                    payD.delete(id);
                    List<payment> list = payD.list(a.getId());
                    for (payment object : list) {
                        long divi = object.getCard_number().longValue();
                        divi = divi % 10000;
                        object.setCard_number(new BigDecimal(divi));
                    }
                    model.addObject("myPayment", list);
                    model.setViewName("my_payment");
                }
            }else{
                model.setViewName("redirect:/index");
            }
        }catch(Exception e){
            model.setViewName("redirect:/index");
        }
        return model;
    }
    @RequestMapping("/myPayment")
    public ModelAndView myPayment(HttpServletRequest request) throws Exception{
        try{
            loginController loginC=new loginController();
            account a = loginC.checkCookie(request);
            if(a != null){
                ModelAndView model = new ModelAndView();
                model=loginC.checkLogin(request, model);
                model.addObject("numberCart", cartD.getCountCart(a.getId()));
                List<payment> list = payD.list(a.getId());
                for (payment object : list) {
                        long divi = object.getCard_number().longValue();
                        divi = divi % 10000;
                        object.setCard_number(new BigDecimal(divi));
                    }
                model.addObject("myPayment", list);
                model.setViewName("my_payment");
                return model;
            }
        }catch(Exception e){
            throw new Exception("ERROR Profile");
        }
        return new ModelAndView("redirect:/index");
    }
    //==========================================================================ADD PAMENT FORM
    @RequestMapping(value = "/addPayment", method = RequestMethod.GET)
    public ModelAndView addPayment(HttpServletRequest rq) throws Exception {
        try{
            loginController loginC=new loginController();
            account a = loginC.checkCookie(rq);
            if(a != null){
                ModelAndView model = new ModelAndView();
                model=loginC.checkLogin(rq, model);
                model.addObject("numberCart", cartD.getCountCart(a.getId()));
                payment p = new payment();
                model.addObject("payment", p);
                model.setViewName("add_payment");
                return model;
            }
        }catch(Exception e){
            throw new Exception("ERROR FORM ADD PAYMENT");
        }
        return new ModelAndView("redirect:/index");
    }
    @RequestMapping(value = "/addnewPayment", method = RequestMethod.POST)
    public ModelAndView addnewPayment(HttpServletRequest rq, @ModelAttribute payment payment) {
        ModelAndView model = new ModelAndView();
        try{
                loginController loginC=new loginController();
                model=loginC.checkLogin(rq, model);
                account a = loginC.checkCookie(rq);
                if(a!= null){
                    model.addObject("numberCart", cartD.getCountCart(a.getId()));
                    payD.save(payment, a.getId());
                    List<payment> list = payD.list(a.getId());
                    for (payment object : list) {
                        long divi = object.getCard_number().longValue();
                        divi = divi % 10000;
                        object.setCard_number(new BigDecimal(divi));
                    }
                    model.addObject("myPayment", list);
                    model.setViewName("my_payment");
                }
        }catch(Exception e){
            model.setViewName("redirect:/index");
        }
        return model;
    }
}
