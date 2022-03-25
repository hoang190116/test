/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.genreDAO;
import com.model.AccountModel;
import com.model.Accountt;
import com.model.Product;
import com.model.account;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author Admin
 */
@Controller
@RequestMapping(value = { "", "account" })
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
    private genreDAO genre;
    
    
    
    //index page
//    @RequestMapping(value = {"/index", "/"})
//    public ModelAndView index(ModelAndView model) {
//        Integer a = loginController.getID();
//        String login = null;
//        if(a!=null){
//            model.addObject("login", login="in");
//        }
//        model.setViewName("index");
//        return model;
//    }
    //==============================================================================================
    @RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "redirect:/account/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(ModelMap modelMap, HttpSession session, HttpServletRequest request) {
		Accountt account = checkCookie(request);
		if (account == null) {
			modelMap.put("account", new Accountt());
			return "indexTest";
		} else {
			AccountModel accountModel = new AccountModel();
			if (accountModel.login(account.getUsername(), account.getPassword())) {
				session.setAttribute("username", account.getUsername());
				return "welcome";
			} else {
				modelMap.put("error", "Account's Invalid");
				return "indexTest";
			}
		}
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute(value = "account") Accountt account, ModelMap modelMap, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		AccountModel accountModel = new AccountModel();
		if (accountModel.login(account.getUsername(), account.getPassword())) {
			session.setAttribute("username", account.getUsername());
			if (request.getParameter("remember") != null) {
				Cookie ckUsername = new Cookie("username", account.getUsername());
				ckUsername.setMaxAge(3600);
				response.addCookie(ckUsername);
				Cookie ckPassword = new Cookie("password", account.getPassword());
				ckPassword.setMaxAge(3600);
				response.addCookie(ckPassword);
			}
			return "welcome";
		} else {
			modelMap.put("error", "Account's Invalid");
			return "indexTest";
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		// Remove session
		session.removeAttribute("username");
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
		}
		return "redirect:/account/login";
	}

	public Accountt checkCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Accountt account = null;
		String username = "", password = "";
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
		}
		if (!username.isEmpty() && !password.isEmpty()) {
			account = new Accountt(username, password);
		}
		return account;
	}
    
    //==============================================================================Test
//    @RequestMapping(value = {"/index", "/"})
//    public ModelAndView index(ModelAndView model) {
//        Product p = new Product();
//        model.addObject("p", p);
//        try {
//            List<genre> list = genre.list();
//            model.addObject("index", list);
//        } catch (Exception e) {
//
//        }
//        model.setViewName("productAdd");
//        return model;
//    }
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ModelAndView saveCate(@RequestParam("fileImage") MultipartFile[] multi, @ModelAttribute Product p) {
        ModelAndView mol = new ModelAndView();
        ArrayList<String> list = new ArrayList<String>();
        for (MultipartFile multi2 : multi) {
            String date = new SimpleDateFormat("yyyyMMddHHmmss.").format(new Date());
            String imgName = StringUtils.cleanPath(multi2.getOriginalFilename());
            imgName = imgName.toLowerCase().replaceAll(" ", "-").substring(imgName.length() - 10);
            imgName = date+imgName;
            list.add(imgName);
            System.out.println(imgName);
        }
        p.setPhotos(list);
        if(p.getPhotos() == null){
            System.out.println("photos null");
        }
        else{
            System.out.println("not null");
        }
        System.out.println(p.getName());
        System.out.println(p.getPrice());
        System.out.println(p.getReleaseDate());
        System.out.println(p.getDetail());
        System.out.println(p.getGenre());
//        if (p.getProduct_id() == null) {
////            product.save(p);
//            mol.setViewName("redirect:/");
//        } else {
////            product.update(p);
//            mol.setViewName("redirect:/");
//        }
        mol.setViewName("redirect:/");
        return mol;
    }
    private List<Product> list(){
        List<Product> list = new ArrayList<Product>();
        Product p2 = new Product();
        p2.setName("abc");
        p2.setPrice(345);
        p2.setProduct_id(1);
//        p2.setQuantity(3);
        list.add(p2);
        p2 = new Product();
        p2.setName("awd");
        p2.setPrice(567);
        p2.setProduct_id(4);
//        p2.setQuantity(2);
        list.add(p2);
        return list;
    }
    @PostMapping("/uploadFileeee")
    public String saveFile(@ModelAttribute(name = "product") Product product, RedirectAttributes ra, 
            @RequestParam("fileImage") MultipartFile[] multi) throws IOException{
        
//        Product saveFile = imgd.insert(product);//***************
        String uploadDir = "./img/1/";
        
        for (MultipartFile multi2 : multi) {
            String date = new SimpleDateFormat("yyyyMMddHHmmss.").format(new Date());
            String imgName = StringUtils.cleanPath(multi2.getOriginalFilename());
            imgName = imgName.toLowerCase().replaceAll(" ", "-").substring(imgName.length() - 10);
            imgName = date+imgName;
            System.out.println(imgName);
            
//            product.setPhotos(imgName);//********

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try {
                InputStream strem = multi2.getInputStream();
                Path filePath = uploadPath.resolve(imgName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(strem, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("COuld notsave" + imgName);
            }
            
        }
        return "redirect:/index";
    }
    
    @PostMapping("/rm")
    public String rmFile()throws IOException{
        Path path = Paths.get("./img/1/873162.png");
        Files.delete(path);
        return "redirect:/index";
    }
    
    @RequestMapping("/unameCheck")
    @ResponseBody
    public void checkName(@RequestParam("myuser") String myuser, HttpServletResponse response) throws IOException{
        response.getWriter().println(myuser);
    }
    
    @RequestMapping("/checkAjax")
    public String checkAjax(@RequestParam("id") int id){
        System.out.println(id);
        return "redirect:/";
    }
    
    @RequestMapping("/testing")
    @ResponseBody
    public void testing(@RequestParam("id") int id, HttpServletResponse response) throws IOException{
        for (Product product : list()) {
            if(product.getProduct_id() == id){
//                product.setQuantity(product.getQuantity()+1);
            }
        }
        response.getWriter().println(id);
    }
}
