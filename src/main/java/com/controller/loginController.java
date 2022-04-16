/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.accountDAO;
import com.dao.cartDAO;
import com.dao.commentDAO;
import com.dao.productDAO;
import com.model.Product;
import com.model.account;
import com.model.comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping(value = {""})
public class loginController {
    @Autowired
    private accountDAO acc;
    @Autowired
    private productDAO product;
    @Autowired
    private commentDAO cmtDAO;
    @Autowired
    private cartDAO cartDao;
    
    public static boolean isValid(String pass) {
        boolean flag = true;
        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        if (pass.length() < 8) {
            flag = false;
        }
        if (!specailCharPatten.matcher(pass).find()) {
            flag = false;
        }
        if (!UpperCasePatten.matcher(pass).find()) {
            flag = false;
        }
        if (!lowerCasePatten.matcher(pass).find()) {
            flag = false;
        }
        if (!digitCasePatten.matcher(pass).find()) {
            flag = false;
        }

        return flag;
    }
    
    public ModelAndView checkLogin(HttpServletRequest request, ModelAndView model){
        account a = checkCookie(request);
        boolean login = false;
        Integer num = 0;
        if(a!=null){
            login = true;
        }
        model.addObject("login", login);
        model.addObject("numberCart", num);
        return model;
    }
    public int getCountOfCart(account a){
        try{
            Integer num = 0;
            num = cartDao.getCountCart(a.getId());
            return num;
        }catch(Exception e){
            System.out.println("ERROR WHEN GET COUNT FOR CART");
        }
        return 0;
    }
    
    @RequestMapping("/loginForm")
    public ModelAndView loginForm(HttpServletRequest rq, ModelAndView model) {
        account a = checkCookie(rq);
        if(a != null){
            List<Product> list = product.list();
            model.addObject("listProduct", list);
            list = product.hotList();
            model.addObject("hotList", list);
            model.addObject("login", true);
            model.addObject("numberCart", getCountOfCart(a));
            model.setViewName("redirect:/index");
        }else{
            model.addObject("account", new account());
            model.setViewName("login");
        }
        return model;
    }
    @RequestMapping("/registerForm")
    public ModelAndView registerForm(HttpServletRequest rq, ModelAndView model){
        account a = checkCookie(rq);
        if(a != null){
            List<Product> list = product.list();
            model.addObject("listProduct", list);
            list = product.hotList();
            model.addObject("hotList", list);
            model.addObject("login", true);
            model.addObject("numberCart", getCountOfCart(a));
            model.setViewName("redirect:/index");
        }else{
            model.addObject("account", new account());
            model.setViewName("register");
        }
        return model;
    }
    @RequestMapping("/usernameCheck")
    @ResponseBody
    public void checkName(@RequestParam("writeName") String name, HttpServletResponse response) throws IOException{
        boolean a = acc.getName(name);
        if(a){
            response.getWriter().println(1);
        }else{
            response.getWriter().println(0);
        }
    }
    @RequestMapping("/passwordCheck")
    @ResponseBody
    public void checkwordPass(@RequestParam("writePass") String pass, HttpServletResponse response) throws IOException{
        boolean a = isValid(pass);
        if(a){
            response.getWriter().println(0);
        }else{
            response.getWriter().println(1);
        }
    }
    @RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@ModelAttribute(value = "account") account account, ModelMap modelMap, HttpSession session, 
                HttpServletRequest request, HttpServletResponse response) {
		if(account.getFname() != null && account.getUname() != null && account.getPass() != null){
                    System.out.println("Join not null");
                    try{
                        account.setRole("user");
                        acc.register(account);
                        account a = acc.login(account);
                        if(a != null){
                            if (a.getUname()!= null && a.getPass()!=null) {
    //			session.setAttribute("username", a.getUname());
    //if (request.getParameter("remember") != null)
                                Cookie ckUsername = new Cookie("username", a.getUname());
                                ckUsername.setMaxAge(3600);
                                response.addCookie(ckUsername);
                                Cookie ckPassword = new Cookie("password", account.getPass());
                                ckPassword.setMaxAge(3600);
                                response.addCookie(ckPassword);
                                Cookie ckID = new Cookie("userID", String.valueOf(a.getId()));
                                ckID.setMaxAge(3600);
                                response.addCookie(ckID);
                                Cookie ckFname = new Cookie("fullname", String.valueOf(a.getFname()));
                                ckFname.setMaxAge(3600);
                                response.addCookie(ckFname);
                                String login = null;
                                modelMap.put("login", login="in");
                                login = null;

                                List<Product> list = product.list();
                                modelMap.put("listProduct", list); 
                                list = product.hotList();
                                modelMap.put("hotList", list);
                                modelMap.put("login", true);
                                modelMap.put("numberCart", getCountOfCart(a));
                            }
                        }
                    }catch(Exception e){
                        System.out.println("Error Register login");
                    }
                }
            return "index";
	}
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/login";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(ModelMap modelMap, HttpSession session, HttpServletRequest request) {
		account account = checkCookie(request);
		if (account == null) {
			return "redirect:/index";
		} else {
			account a = acc.login(account);
			if(a!=null){
                            if (a.getUname()!= null && a.getPass()!=null) {
                                if(a.getRole().equals("admin")){
                                    return "admin/adminIndex";
                                }else if(a.getRole().equals("staff")){
                                    return "redirect:/emp/index";
                                }
//				session.setAttribute("username", a.getUname());
                                List<Product> list = product.list();
                                modelMap.put("listProduct", list);
                                list = product.hotList();
                                modelMap.put("hotList", list);
				modelMap.put("login", true);
                                modelMap.put("numberCart", getCountOfCart(a));
                                return "index";
                            }
                        }
                        modelMap.put("account", new account());
                        modelMap.put("error", "Account's Invalid");
                        return "login";
		}
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute(value = "account") account account, ModelMap modelMap, HttpSession session, 
                HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "idP", required=false) Integer idP ) {
		account a = acc.login(account);
		if(a != null){
                        if (a.getUname()!= null && a.getPass()!=null) {
    //			session.setAttribute("username", a.getUname());
    //if (request.getParameter("remember") != null)
                            Cookie ckUsername = new Cookie("username", a.getUname());
                            ckUsername.setMaxAge(3600);
                            response.addCookie(ckUsername);
                            Cookie ckPassword = new Cookie("password", account.getPass());
                            ckPassword.setMaxAge(3600);
                            response.addCookie(ckPassword);
                            Cookie ckID = new Cookie("userID", String.valueOf(a.getId()));
                            ckID.setMaxAge(3600);
                            response.addCookie(ckID);
                            Cookie ckFname = new Cookie("fullname", String.valueOf(a.getFname()));
                            ckFname.setMaxAge(3600);
                            response.addCookie(ckFname);
                            
                            if(a.getRole().equals("admin")){
                                return "admin/adminIndex";
                            }else if(a.getRole().equals("staff")){
                                return "redirect:/emp/index";
                            }
                            
                            if(idP != null){
                                product.updateView(idP);
                                Product p = product.get(idP);
                                List<Product> list = new ArrayList<>();
                                list.add(p);
                                List<comment> listCmt = cmtDAO.list(idP);
                                modelMap.put("cmtList", listCmt);
                                modelMap.put("productDetail", list);
                                modelMap.put("mssdetail", p.getDetail());
                                
                                modelMap.put("login", true);
                                modelMap.put("numberCart", getCountOfCart(a));
                                
                                
                                list = new ArrayList<>();
                                list = product.randomList(p.getGenre());
                                modelMap.put("randomList", list);
                                
                                return "productDetail";
                            }
                            
                            List<Product> list = product.list();
                            modelMap.put("listProduct", list); 
                            list = product.hotList();
                            modelMap.put("hotList", list);
                            modelMap.put("login", true);
                            modelMap.put("numberCart", getCountOfCart(a));
                            return "index";
                    }
                }
                if(idP != null){
                    modelMap.put("idP", idP);
                }
                modelMap.put("account", new account());
                modelMap.put("error", "Account's Invalid");
                return "login";
	}
//        @RequestMapping(value="goLoginIn/{idP}", method = RequestMethod.POST)    
//        public ModelAndView loginProduct(@ModelAttribute(value = "account") account account, HttpServletResponse response, @PathVariable Integer idP){
//            ModelAndView model = new ModelAndView();
//            if(idP >0 && idP != null){
//                    account ac = acc.login(account);
//                    if(ac != null){
//                        if (ac.getUname()!= null && ac.getPass()!=null) {
//                            Cookie ckUsername = new Cookie("username", ac.getUname());
//                            ckUsername.setMaxAge(3600);
//                            response.addCookie(ckUsername);
//                            Cookie ckPassword = new Cookie("password", account.getPass());
//                            ckPassword.setMaxAge(3600);
//                            response.addCookie(ckPassword);
//                            Cookie ckID = new Cookie("userID", String.valueOf(ac.getId()));
//                            ckID.setMaxAge(3600);
//                            response.addCookie(ckID);
//                            Cookie ckFname = new Cookie("fullname", String.valueOf(ac.getFname()));
//                            ckFname.setMaxAge(3600);
//                            response.addCookie(ckFname);
//                            
//                            Product p = product.get(idP);
//                            List<Product> list = new ArrayList<>();
//                            list.add(p);
//                            List<comment> listCmt = cmtDAO.list(idP);
//                            String login = "";
//                            model.addObject("login", login = "in");
//                            login = null;
//                            model.addObject("cmtList", listCmt);
//                            model.setViewName("productDetail");
//                            model.addObject("productDetail", list);
//                            model.addObject("mssdetail", p.getDetail());
//                            
//                            return model;
//                        }
//                    }
//                    model.addObject("account", new account());
//                    model.addObject("error", "Account's Invalid");
//                    model.addObject("idP", idP);
//                    model.setViewName("goLoginForm");
//                    return model;
//                }
//            model.setViewName("redirect:/index");
//            return model;
//        }

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		// Remove session
//		session.removeAttribute("username");
		// Remove cookie
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equalsIgnoreCase("username")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			if (cookie.getName().equalsIgnoreCase("password")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
                        if (cookie.getName().equalsIgnoreCase("userID")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		return "redirect:/index";
	}

	public account checkCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		account account = null;
		String username = "", password = "", fullname="";
                String userID = "";
                if(cookies == null){
                    return account;
                }
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase("username")) {
				username = cookie.getValue();
			}
			if (cookie.getName().equalsIgnoreCase("password")) {
				password = cookie.getValue();
			}
                        if (cookie.getName().equalsIgnoreCase("userID")) {
				userID = cookie.getValue();
			}
                        if (cookie.getName().equalsIgnoreCase("fullname")) {
				fullname = cookie.getValue();
			}
		}
		if (!username.isEmpty() && !password.isEmpty()) {
                        account = new account();
                        account.setUname(username);
                        account.setPass(password);
                        account.setId(Integer.valueOf(userID));
                        account.setFname(fullname);
		}
		return account;
	}
}
