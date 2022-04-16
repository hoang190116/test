/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.accountDAO;
import com.dao.billDAO;
import com.dao.genreDAO;
import com.dao.productDAO;
import com.model.Product;
import com.model.account;
import com.model.bill;
import com.model.genre;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping(value = {"adm"})
public class adminController {
    @Autowired
    private productDAO product;
    @Autowired
    private accountDAO accDao;
    @Autowired
    private genreDAO genreDao;
    @Autowired
    private billDAO billDao;
    
    @RequestMapping(value="index", method = RequestMethod.GET)
    public String index(HttpServletRequest rq) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("admin")){
                return "admin/adminIndex";
            }
        }
        return "redirect:/index";
    }
    
    @RequestMapping(value="product/{pageid}")    
    public ModelAndView viewP(@PathVariable Integer pageid,ModelAndView m, HttpServletRequest rq){
        if(pageid>0 && pageid != null){
            loginController loginC=new loginController();
            account a = loginC.checkCookie(rq);
            if(a!=null){
                a = accDao.login(a);
                if(a.getRole().equals("admin")){
                    int total=8;
                    int currentP = 1;
                    currentP = pageid;
                    Integer count = product.getCountProductPage();
                    float countPage = (float) count / total;
                    countPage = (float) Math.ceil(countPage);
                    if(pageid!=1){    
                        pageid=(pageid-1)*total+1;
                    }    
                    List<Product> list=product.getProductByPage(pageid,total);
                    m.addObject("countP", countPage);
                    m.addObject("productList", list);
                    m.addObject("currentP", currentP);
                    m.setViewName("admin/ad_productList");
                    return m;
                }
            }
        }
        m.setViewName("redirect:/index");
        return m;
    }
    
    @RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest rq) {
        
        ModelAndView model = new ModelAndView();
         try {
             loginController loginC=new loginController();
             account a = loginC.checkCookie(rq);
             if(a!=null){
                 a = accDao.login(a);
                if(a.getRole().equals("admin")){
                   String s = rq.getParameter("search");
                   int total = 8;
                   Integer count = 1;
                   count = product.getCountSearchPage(s);
                   float num = (float) count/8;
                   num = (float) Math.ceil(num);
                   List<Product> list = product.search(s, 1, total);
                   model.addObject("searchName", s);
                   model.addObject("countP", num);
                   model.addObject("productList", list);
                   model.addObject("currentP", 1);
                   model.setViewName("admin/ad_searchProduct");
                   return model;
                }
             }
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value = "/searchProduct/{pageid}", method = RequestMethod.GET)
    public ModelAndView searchPage(HttpServletRequest rq, @PathVariable Integer pageid) {
        ModelAndView model = new ModelAndView();
        if(pageid > 0 && pageid != null){
             try {
                String s = rq.getParameter("search");
                loginController loginC=new loginController();
                account a = loginC.checkCookie(rq);
                if(a!=null){
                    a = accDao.login(a);
                    if(a.getRole().equals("admin")){
                        Integer currentP = pageid;
                        int total = 8;
                        if(pageid != 1){
                            pageid = (pageid - 1)*total+1;
                        }
                        Integer count = 1;
                        count = product.getCountSearchPage(s);
                        float num = (float) count/8;
                        num = (float) Math.ceil(num);
                        List<Product> list = product.search(s, pageid, total);
                        model.addObject("searchName", s);
                        model.addObject("countP", num);
                        model.addObject("productList", list);
                        model.addObject("currentP", currentP);
                        model.setViewName("admin/ad_searchProduct");
                        return model;
                    }
                }
            } catch (Exception e) {
                model.setViewName("redirect:/index");
                return model;
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
    
    @RequestMapping(value="addProduct", method = RequestMethod.GET)
    public ModelAndView addProduct(HttpServletRequest rq, ModelAndView model) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("admin")){
                Product p = new Product();
                model.addObject("p", p);
                try {
                    List<genre> list = genreDao.list();
                    model.addObject("listGenre", list);
                } catch (Exception e) {

                }
                model.setViewName("admin/ad_productAdd");
            }
        }
        return model;
    }
    //==========================================================================Check Unique Name
    @RequestMapping("/pnameCheck")
    @ResponseBody
    public void checkName(@RequestParam("writeName") String name, HttpServletResponse response) throws IOException{
        boolean a = product.selectProductName(name);
        if(a){
            response.getWriter().println(1);
        }else{
            response.getWriter().println(0);
        }
    }
    //==========================================================================Delete Image
    @RequestMapping("/rmImg")
    @ResponseBody
    public void rmImg(@RequestParam("name") String name, @RequestParam("idP") Integer idP, HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            loginController loginC = new loginController();
            account a = loginC.checkCookie(request);
            a = accDao.login(a);
            if(a.getRole().equals("admin")){
                product.deleteImg(name);
                
                Path path = Paths.get("./img/1/"+name);
                Files.delete(path);
            }
        } catch (Exception e) {
            response.getWriter().println(0);
        }
    }
    @RequestMapping(value = "/addnewProduct", method = RequestMethod.POST)
    public ModelAndView addnewProduct(@RequestParam("fileImage") MultipartFile[] multi, @ModelAttribute Product p, HttpServletRequest request) throws IOException {
        ModelAndView mol = new ModelAndView();
        loginController loginC = new loginController();
        account a = loginC.checkCookie(request);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("admin")){
                ArrayList<String> list = new ArrayList<>();
                String uploadDir = "./img/1/";
                if(multi != null){
                for (MultipartFile multi2 : multi) {
                    String date = new SimpleDateFormat("yyyyMMddHHmmss.").format(new Date());
                    String imgName = StringUtils.cleanPath(multi2.getOriginalFilename());
//                    if(imgName.length() > 10){
                        imgName = imgName.toLowerCase().replaceAll(" ", "-").substring(imgName.length() - 10);
//                    }
                    imgName = date+imgName;
                    list.add(imgName);
                    System.out.println(imgName);

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
                }}
                p.setPhotos(list);
                try{
                    product.save(p);
                    String success = "Successful added product!";
                    mol.addObject("success", success);
                    success = null;
                    p = new Product();
                    mol.addObject("p", p);
                    try {
                        List<genre> list2 = genreDao.list();
                        mol.addObject("listGenre", list2);
                        mol.setViewName("admin/ad_productAdd");
                        return mol;
                    } catch (Exception e) {

                    }
                }catch(Exception e){

                }
                p = new Product();
                mol.addObject("p", p);
                List<genre> list2 = genreDao.list();
                mol.addObject("listGenre", list2);
                mol.setViewName("admin/ad_productAdd");
                return mol;
            }
        }
        mol.setViewName("redirect:/index");
        return mol;
    }
    @RequestMapping(value = "/product/editProduct", method = RequestMethod.GET)
    public ModelAndView editProduct(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
         try {
            Integer id = Integer.parseInt(rq.getParameter("id"));
            String success = rq.getParameter("success");
            String error = rq.getParameter("error");
             loginController loginC=new loginController();
             account a = loginC.checkCookie(rq);
             if(a!=null){
                 a = accDao.login(a);
                if(a.getRole().equals("admin")){
                   Product p = product.get(id);
                    model.addObject("p", p);
                    try {
                        List<genre> list = genreDao.list();
                        model.addObject("listGenre", list);
                    } catch (Exception e) {

                    }
                   if(success !=null){
                       model.addObject("success", "Successful updated product!");
                   }
                   if(error !=null){
                       model.addObject("error", "Error to update product!!!");
                   }
                   model.setViewName("admin/ad_productUpdate");
                   return model;
                }
             }
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value = "/product/edittingProduct", method = RequestMethod.POST)
    public ModelAndView edittingProduct(@RequestParam("fileImage") MultipartFile[] multi, @ModelAttribute Product p, HttpServletRequest request) throws IOException {
        ModelAndView mol = new ModelAndView();
        loginController loginC = new loginController();
        account a = loginC.checkCookie(request);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("admin")){
                ArrayList<String> list = new ArrayList<>();
                String uploadDir = "./img/1/";
                if(multi != null){
                for (MultipartFile multi2 : multi) {
                    System.out.println("multi2: "+multi2);
                    String date = new SimpleDateFormat("yyyyMMddHHmmss.").format(new Date());
                    String imgName = StringUtils.cleanPath(multi2.getOriginalFilename());
//                    if(imgName.length() > 10){
                        imgName = imgName.toLowerCase().replaceAll(" ", "-").substring(imgName.length() - 10);
//                    }
                    imgName = date+imgName;
                    list.add(imgName);

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
                }}
                p.setPhotos(list);
                try{
                    product.update(p);
                    product.addImg(p);
                    mol.addObject("success", "success");
                    mol.setViewName("redirect:/adm/product/editProduct?id="+p.getProduct_id());
                    return mol;
                }catch(Exception e){

                }
                mol.addObject("error", "error");
                mol.setViewName("redirect:/adm/product/editProduct?id="+p.getProduct_id());
                return mol;
            }
        }
        mol.setViewName("redirect:/index");
        return mol;
    }
    @RequestMapping(value = "/product/delProduct", method = RequestMethod.GET)
    public ModelAndView deleteProduct(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
         try {
             Integer id = Integer.parseInt(rq.getParameter("id"));
             loginController loginC=new loginController();
             account a = loginC.checkCookie(rq);
             if(a!=null){
                 a = accDao.login(a);
                if(a.getRole().equals("admin")){
                    try {
                        Product p = product.get(id);
                        product.delete(id);
                        
                        if(!p.getPhotos().get(0).equals("G3default.png")){
                            for (String photo : p.getPhotos()) {
                                Path path = Paths.get("./img/1/"+photo);
                                Files.delete(path);
                            }
                        }
                        
                        model.setViewName("redirect:/adm/product/1");
                        return model;
                    } catch (Exception e) {
                    }
                }
             }
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value="user", method = RequestMethod.GET)
    public ModelAndView user(HttpServletRequest rq, ModelAndView model) {
        loginController loginC=new loginController();
        account a = loginC.checkCookie(rq);
        if(a!=null){
            a = accDao.login(a);
            if(a.getRole().equals("admin")){
                try {
                    
                    int total = 8;
                    Integer count = 1;
                    count = accDao.getCountAccountPage();
                    float num = (float) count/8;
                    num = (float) Math.ceil(num);
                    List<account> list = accDao.getAccountByPage(1, total);
                    model.addObject("countP", num);
                    model.addObject("accountList", list);
                    model.addObject("currentP", 1);
                    
                    model.setViewName("admin/ad_userList");
                    return model;
                } catch (Exception e) {
                    model.setViewName("redirect:/index");
                }
            }
        }
        return model;
    }
    @RequestMapping(value="user/{pageid}")    
    public ModelAndView viewUser(@PathVariable Integer pageid,ModelAndView m, HttpServletRequest rq){
        if(pageid>0 && pageid != null){
            loginController loginC=new loginController();
            account a = loginC.checkCookie(rq);
            if(a!=null){
                a = accDao.login(a);
                if(a.getRole().equals("admin")){
                    try{
                    int total=8;
                    int currentP = 1;
                    currentP = pageid;
                    Integer count = accDao.getCountAccountPage();
                    float countPage = (float) count / total;
                    countPage = (float) Math.ceil(countPage);
                    if(pageid!=1){    
                        pageid=(pageid-1)*total+1;
                    }    
                    List<account> list=accDao.getAccountByPage(pageid, total);
                    m.addObject("countP", countPage);
                    m.addObject("accountList", list);
                    m.addObject("currentP", currentP);
                    m.setViewName("admin/ad_userList");
                    return m;
                    }catch(Exception e){
                        m.setViewName("redirect:/adm/index");
                    }
                    return m;
                }
            }
        }
        m.setViewName("redirect:/index");
        return m;
    }
    @RequestMapping(value = "/searchUser", method = RequestMethod.GET)
    public ModelAndView searchUser(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
         try {
             String s = rq.getParameter("search");
             loginController loginC=new loginController();
             account a = loginC.checkCookie(rq);
             if(a!=null){
                 a = accDao.login(a);
                if(a.getRole().equals("admin")){
                    try{
                   int total = 8;
                   Integer count = 1;
                   count = accDao.getCountSearchAccountPage(s);
                   float num = (float) count/8;
                   num = (float) Math.ceil(num);
                   List<account> list = accDao.searchAccount(s, 1, total);
                   model.addObject("searchName", s);
                   model.addObject("countP", num);
                   model.addObject("accountList", list);
                   model.addObject("currentP", 1);
                   model.setViewName("admin/ad_searchUser");
                   return model;
                    }catch(Exception e){
                        model.setViewName("redirect:/adm/index");
                        return model;
                    }
                }
             }
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value = "/searchUser/{pageid}", method = RequestMethod.GET)
    public ModelAndView searchPageUser(HttpServletRequest rq, @PathVariable Integer pageid) {
        ModelAndView model = new ModelAndView();
        if(pageid > 0 && pageid != null){
             try {
                String s = rq.getParameter("search");
                loginController loginC=new loginController();
                account a = loginC.checkCookie(rq);
                if(a!=null){
                    a = accDao.login(a);
                    if(a.getRole().equals("admin")){
                        try{
                        Integer currentP = pageid;
                        int total = 8;
                        if(pageid != 1){
                            pageid = (pageid - 1)*total+1;
                        }
                        Integer count = 1;
                        count = accDao.getCountSearchAccountPage(s);
                        float num = (float) count/8;
                        num = (float) Math.ceil(num);
                        List<account> list = accDao.searchAccount(s, pageid, total);
                        model.addObject("searchName", s);
                        model.addObject("countP", num);
                        model.addObject("accountList", list);
                        model.addObject("currentP", currentP);
                        model.setViewName("admin/ad_searchUser");
                        return model;
                        }catch(Exception e){
                            model.setViewName("redirect:/adm/index");
                            return model;
                        }
                    }
                }
            } catch (Exception e) {
                model.setViewName("redirect:/index");
                return model;
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView addUser(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
         try {
             loginController loginC=new loginController();
             account a = loginC.checkCookie(rq);
             if(a!=null){
                 a = accDao.login(a);
                if(a.getRole().equals("admin")){
                    try {
                        String success = rq.getParameter("success");
                        String error = rq.getParameter("error");
                        if(success != null){
                            model.addObject("success", "Sucessful!");
                        }
                        if(error != null){
                            model.addObject("error", "Error!");
                        }
                        a = new account();
                        model.addObject("a", a);
                        model.setViewName("admin/ad_userAdd");
                        return model;
                    } catch (Exception e) {
                        model.setViewName("redirect:/adm/index");
                        return model;
                    }
                }
             }
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value = "/user/saveAccount", method = RequestMethod.POST)
    public ModelAndView saveAccount(@ModelAttribute account a, HttpServletRequest request) throws IOException {
        ModelAndView mol = new ModelAndView();
        loginController loginC = new loginController();
        account a2 = loginC.checkCookie(request);
        if(a2!=null){
            a2 = accDao.login(a2);
            if(a2.getRole().equals("admin")){
                try{
                    if(a.getId() == null){
                        accDao.save(a);
                        mol.addObject("success", "success");
                        mol.setViewName("redirect:/adm/addUser");
                        return mol;
                    }else{
                        accDao.update(a, a.getId());
                        mol.addObject("success", "success");
                        mol.setViewName("redirect:/adm/user/editAccount?id="+a.getId());
                        return mol;
                    }
                }catch(Exception e){
                    mol.addObject("error", "error");
                    if(a.getId() == null){
                        mol.setViewName("redirect:/adm/addUser");
                    }else{
                        mol.setViewName("redirect:/adm/user/editAccount?id="+a.getId());
                    }
                    return mol;
                }
            }
        }
        mol.setViewName("redirect:/index");
        return mol;
    }
    @RequestMapping(value = "/user/editAccount", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
         try {
             loginController loginC=new loginController();
             account a = loginC.checkCookie(rq);
             Integer id = Integer.parseInt(rq.getParameter("id"));
             if(a!=null){
                 a = accDao.login(a);
                if(a.getRole().equals("admin")){
                    try {
                        String success = rq.getParameter("success");
                        String error = rq.getParameter("error");
                        if(success != null){
                            model.addObject("success", "Sucessful!");
                        }
                        if(error != null){
                            model.addObject("error", "Error!");
                        }
                        account a2 = accDao.get(id);
                        a2.setPass(null);
                        model.addObject("a", a2);
                        model.addObject("username", a2.getUname());
                        model.setViewName("admin/ad_userUpdate");
                        return model;
                    } catch (Exception e) {
                        model.setViewName("redirect:/adm/index");
                        return model;
                    }
                }
             }
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value = "/user/deleteAccount", method = RequestMethod.GET)
    public ModelAndView deleteAccount(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
         try {
             Integer id = Integer.parseInt(rq.getParameter("id"));
             loginController loginC=new loginController();
             account a = loginC.checkCookie(rq);
             if(a!=null){
                 a = accDao.login(a);
                if(a.getRole().equals("admin")){
                    try {
                        accDao.delete(id);
                        model.setViewName("redirect:/adm/user");
                        return model;
                    } catch (Exception e) {
                    }
                }
             }
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value="order/{pageid}")    
    public ModelAndView viewOrder(@PathVariable Integer pageid, ModelAndView m, HttpServletRequest rq){
        if(pageid>0 && pageid != null){
            loginController loginC=new loginController();
            account a = loginC.checkCookie(rq);
            if(a!=null){
                a = accDao.login(a);
                if(a.getRole().equals("admin")){
                    try{
                    int total=8;
                    int currentP = 1;
                    currentP = pageid;
                    Integer count = billDao.getCountBillPage();
                    float countPage = (float) count / total;
                    countPage = (float) Math.ceil(countPage);
                    if(pageid!=1){    
                        pageid=(pageid-1)*total+1;
                    }    
                    List<bill> list=billDao.getBillByPage(pageid, total);
                    m.addObject("countP", countPage);
                    m.addObject("billList", list);
                    m.addObject("currentP", currentP);
                    m.setViewName("admin/ad_orderList");
                    return m;
                    }catch(Exception e){
                        m.setViewName("redirect:/adm/index");
                    }
                    return m;
                }
            }
        }
        m.setViewName("redirect:/index");
        return m;
    }
    @RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
    public ModelAndView searchOrder(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
         try {
             loginController loginC=new loginController();
             account a = loginC.checkCookie(rq);
             if(a!=null){
                 a = accDao.login(a);
                if(a.getRole().equals("admin")){
                    try {
                        Integer s = Integer.parseInt(rq.getParameter("search"));
                        List<bill> list = billDao.search(s);
                        model.addObject("billList", list);
                        if(list.isEmpty()){
                            String nobill = "No Bill";
                            model.addObject("noBill", nobill);
                            nobill = null;
                        }
                        model.setViewName("admin/ad_searchBill");
                        return model;
                    } catch (Exception e) {
                        String nobill = "Search Error";
                        model.addObject("noBill", nobill);
                        nobill = null;
                        model.setViewName("admin/ad_searchBill");
                        return model;
                    }
                }
             }
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
    @RequestMapping(value = "/order/orderDetail", method = RequestMethod.GET)
    public ModelAndView detailOrder(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
         try {
             loginController loginC=new loginController();
             account a = loginC.checkCookie(rq);
             if(a!=null){
                 a = accDao.login(a);
                if(a.getRole().equals("admin")){
                    try {
                        Integer id = Integer.parseInt(rq.getParameter("id"));
                        bill bill = billDao.get(id);
                        
                        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
                        String date = dateFormat.format(bill.getDate());
                        bill.setDateString(date);
                        
                        List<bill> list = new ArrayList<>();
                        list.add(bill);
                        model.addObject("billDetail", list);
                        model.addObject("cash", true);
                        if(bill.getCard_number() != null){
                            model.addObject("cash", false);
                        }
                        model.setViewName("admin/ad_orderDetail");
                        return model;
                    } catch (Exception e) {
                        model.setViewName("admin/ad_orderList");
                        return model;
                    }
                }
             }
        } catch (Exception e) {
        }       
        model.setViewName("redirect:/index");
        return model;
    }
}
