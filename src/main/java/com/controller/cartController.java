/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.billDAO;
import com.dao.cartDAO;
import com.dao.paymentDAO;
import com.model.account;
import com.model.bill;
import com.model.cart;
import com.model.payment;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class cartController {
    
    @Autowired
    private cartDAO cartDao;
    @Autowired
    private paymentDAO payDao;
    @Autowired
    private billDAO billDao;
    @Autowired
    private paymentDAO paymentDao;
    
    @RequestMapping(value = "/myCart", method = RequestMethod.GET)
    public ModelAndView search(ModelAndView model, HttpServletRequest rq) {
         try {
            loginController loginC=new loginController();
            model=loginC.checkLogin(rq, model);
            account a = loginC.checkCookie(rq);
            if(a!= null){
                model.addObject("numberCart", cartDao.getCountCart(a.getId()));
                List<cart> list = cartDao.list(a.getId());
                if(list != null){
                    model.addObject("cartList", list);
                }
                model.setViewName("product_cart");
                return model;
            }  
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    //========================================================================== ADD TO CART FROM AJAX
    @RequestMapping("/addToCart")
    @ResponseBody
    public void addToCart(@RequestParam("idP") Integer idP, HttpServletResponse response, HttpServletRequest request) throws Exception{
        ModelAndView model = new ModelAndView();
        try{
            loginController loginC=new loginController();
            account a = loginC.checkCookie(request);
            if(a != null){
                cart c = new cart();
                c.setProduct_id(idP);
                cartDao.save(c, a.getId());
                response.getWriter().println(1);
            }else{
                response.getWriter().println(2);
            }
        }catch(IOException e){
            throw new Exception("ERROR UPLOAD CMT!!");
        }
    }
    //==========================================================================Plus button
    @RequestMapping("/plusButton")
    @ResponseBody
    public void plusButton(@RequestParam("idP") Integer idP, @RequestParam("idC") Integer idC, HttpServletRequest request) throws Exception{
        loginController loginC=new loginController();
        account a = loginC.checkCookie(request);
        if(a != null){
            try{
                cart c = new cart();
                c.setId(idC);
                c.setProduct_id(idP);
                cartDao.update(c);
            }catch(Exception e){
                System.out.println("ERROR Plus BUtton");
            }
        }
    }
    //==========================================================================Minus button
    @RequestMapping("/minusButton")
    @ResponseBody
    public void minusButton(@RequestParam("idP") Integer idP, @RequestParam("idC") Integer idC, HttpServletRequest request) throws Exception{
        loginController loginC=new loginController();
        account a = loginC.checkCookie(request);
        if(a != null){
            try{
                cart c = new cart();
                c.setId(idC);
                c.setProduct_id(idP);
                cartDao.updateMinus(c);
            }catch(Exception e){
                System.out.println("ERROR Minus Button");
            }
        }
    }
    //==========================================================================Remove Cart button
    @RequestMapping("/rmButton")
    @ResponseBody
    public void rmButton(@RequestParam("idP") Integer idP, @RequestParam("idC") Integer idC, HttpServletResponse response, HttpServletRequest request) throws Exception{
        loginController loginC=new loginController();
        account a = loginC.checkCookie(request);
        if(a != null){
            try{
                cart c = new cart();
                c.setId(idC);
                c.setProduct_id(idP);
                cartDao.delete(c);
            }catch(Exception e){
                System.out.println("ERROR RM BUTTON");
            }
        }
    }
    //==========================================================================Checkout
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView checkOut(ModelAndView model, HttpServletRequest rq) {
         try {
            loginController loginC=new loginController();
            model=loginC.checkLogin(rq, model);
            account a = loginC.checkCookie(rq);
            if(a!= null){
                model.addObject("numberCart", cartDao.getCountCart(a.getId()));
                List<cart> list = cartDao.list(a.getId());
                if(list != null){
                    cart c = list.get(0);
                    if(!c.getName().isEmpty()){
                        model.addObject("cartList", list);
                        List<payment> listPay = payDao.list(a.getId());
                        model.addObject("payment", true);
                        model.addObject("paymentList", listPay);
                        model.setViewName("checkout");
                    }else{
                        model.setViewName("redirect:/index");
                    }
                }else{
                    model.setViewName("redirect:/index");
                }
                return model;
            }  
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    //==========================================================================Paying by Card
    @RequestMapping("/paying")
    @ResponseBody
    public void paying(@RequestParam("fname") String fname, @RequestParam("email") String email, @RequestParam("adres") String adres,
            @RequestParam("country") String country, @RequestParam("nameCard") String nameCard, 
            @RequestParam("numberCard") String numberCard, @RequestParam("mmCard") Integer mmCard,
            @RequestParam("yyCard") Integer yyCard, @RequestParam("cvvCard") Integer cvvCard,
            @RequestParam("bankName") String bankName, @RequestParam("total") Integer total, 
            @RequestParam("idP") Integer idP, HttpServletResponse response, HttpServletRequest request) throws Exception{
        
        loginController loginC=new loginController();
        account a = loginC.checkCookie(request);
        if(a != null){
            try{
                if(idP != null && idP > 0){
                    cart c = new cart();
                    c.setProduct_id(idP);
                    cartDao.save(c, a.getId());
                }
                
                bill b = new bill();
                b.setTotal(total);
                b.setPay_type(bankName);
                b.setPay_status("Paid");
                b.setStatus("Waiting");
                b.setFname(fname);
                b.setEmail(email);
                b.setCountry(country);
                b.setAddress(adres);
                b.setCard_number(new BigDecimal(numberCard).divide(new BigDecimal("100")));
                b.setFname_card(nameCard);
                
                String date2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(date2);
                
                b.setDate(date);
                
                billDao.save(b, a.getId());
                
            }catch(Exception e){
                System.out.println("ERROR Paying");
            }
        }
    }
    //==========================================================================Paying by Card and Save information
    @RequestMapping("/savingpaying")
    @ResponseBody
    public void savingpaying(@RequestParam("fname") String fname, @RequestParam("email") String email, @RequestParam("adres") String adres,
            @RequestParam("country") String country, @RequestParam("nameCard") String nameCard, 
            @RequestParam("numberCard") String numberCard, @RequestParam("mmCard") Integer mmCard,
            @RequestParam("yyCard") Integer yyCard, @RequestParam("cvvCard") Integer cvvCard,
            @RequestParam("bankName") String bankName, @RequestParam("total") Integer total, 
            @RequestParam("idP") Integer idP, HttpServletResponse response, HttpServletRequest request) throws Exception{
        
        loginController loginC=new loginController();
        account a = loginC.checkCookie(request);
        if(a != null){
            try{
                if(idP != null && idP > 0){
                    cart c = new cart();
                    c.setProduct_id(idP);
                    cartDao.save(c, a.getId());
                }
                
                bill b = new bill();
                b.setTotal(total);
                b.setPay_type(bankName);
                b.setPay_status("Paid");
                b.setStatus("Waiting");
                b.setFname(fname);
                b.setEmail(email);
                b.setCountry(country);
                b.setAddress(adres);
                b.setCard_number(new BigDecimal(numberCard).divide(new BigDecimal("100")));
                b.setFname_card(nameCard);
                
                String date2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(date2);
                
                b.setDate(date);
                
                b.setBank_name(bankName);
                b.setMM(mmCard);
                b.setYY(yyCard);
                paymentDao.save(b, a.getId());
                
                billDao.save(b, a.getId());
                
            }catch(Exception e){
                System.out.println("ERROR Paying");
            }
        }
    }
    //==========================================================================Checkout by Cash
    @RequestMapping(value = "/Cashcheckout", method = RequestMethod.GET)
    public ModelAndView CashcheckOut(ModelAndView model, HttpServletRequest rq) {
         try {
            loginController loginC=new loginController();
            model=loginC.checkLogin(rq, model);
            account a = loginC.checkCookie(rq);
            if(a!= null){
                model.addObject("numberCart", cartDao.getCountCart(a.getId()));
                List<cart> list = cartDao.list(a.getId());
                if(list != null){
                    cart c = list.get(0);
                    if(!c.getName().isEmpty()){
                        model.addObject("payment", false);
                        model.addObject("cartList", list);

                        model.setViewName("checkout");
                    }else{
                        model.setViewName("redirect:/index");
                    }
                }else{
                    model.setViewName("redirect:/index");
                }
                return model;
            }  
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    //==========================================================================Paying by Cash
    @RequestMapping("/Cashpaying")
    @ResponseBody
    public void Cashpaying(@RequestParam("fname") String fname, @RequestParam("email") String email, @RequestParam("adres") String adres,
            @RequestParam("country") String country, @RequestParam("total") Integer total, @RequestParam("idP") Integer idP,
            HttpServletResponse response, HttpServletRequest request) throws Exception{
        
        loginController loginC=new loginController();
        account a = loginC.checkCookie(request);
        if(a != null){
            try{
                if(idP != null && idP > 0){
                    cart c = new cart();
                    c.setProduct_id(idP);
                    cartDao.save(c, a.getId());
                }
                
                bill b = new bill();
                b.setTotal(total);
                b.setPay_type("Cash payment");
                b.setPay_status("Unpaid");
                b.setStatus("Waiting");
                b.setFname(fname);
                b.setEmail(email);
                b.setCountry(country);
                b.setAddress(adres);
                
                String date2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(date2);
                
                b.setDate(date);
                
                billDao.save_cash(b, a.getId());
            }catch(Exception e){
                System.out.println("ERROR Cash Paying");
            }
        }
    }
    //==========================================================================Cash Buy Now
    @RequestMapping(value = "/cashCheckout", method = RequestMethod.GET)
    public ModelAndView cashCheckout(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
        try{
            Integer id = Integer.parseInt(rq.getParameter("id"));
            
            loginController loginC=new loginController();
            model=loginC.checkLogin(rq, model);
            account a = loginC.checkCookie(rq);
            if(a!= null){
                model.addObject("numberCart", cartDao.getCountCart(a.getId()));
                cart c = cartDao.get(id);
                List<cart> list = new ArrayList<>();
                list.add(c);
                    cart c2 = list.get(0);
                    if(!c2.getName().isEmpty()){
                        model.addObject("payment", false);
                        model.addObject("cartList", list);
                        model.addObject("idP", id);
                        model.setViewName("checkout");
                    }
                return model;
            }else{
                model.addObject("idP", id);
                model.addObject("account", new account());
                model.setViewName("login");
            }
        }catch(Exception e){
            model.setViewName("redirect:/index");
        }
        return model;
    }
    //==========================================================================Cash Buy Now
    @RequestMapping(value = "/cardCheckout", method = RequestMethod.GET)
    public ModelAndView cardCheckout(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
        try{
            Integer id = Integer.parseInt(rq.getParameter("id"));
            
            loginController loginC=new loginController();
            model=loginC.checkLogin(rq, model);
            account a = loginC.checkCookie(rq);
            if(a!= null){
                model.addObject("numberCart", cartDao.getCountCart(a.getId()));
                cart c = cartDao.get(id);
                List<cart> list = new ArrayList<>();
                list.add(c);
                    cart c2 = list.get(0);
                    if(!c2.getName().isEmpty()){
                        model.addObject("payment", true);
                        model.addObject("cartList", list);
                        List<payment> listPay = payDao.list(a.getId());
                        model.addObject("paymentList", listPay);
                        model.addObject("idP", id);
                        model.setViewName("checkout");
                    }
                return model;
            }else{
                model.addObject("idP", id);
                model.addObject("account", new account());
                model.setViewName("login");
            }
        }catch(Exception e){
            model.setViewName("redirect:/index");
        }
        return model;
    }
}
