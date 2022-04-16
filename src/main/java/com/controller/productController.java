/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.cartDAO;
import com.dao.commentDAO;
import com.dao.genreDAO;
import com.dao.productDAO;
import com.model.Product;
import com.model.account;
import com.model.comment;
import com.model.genre;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class productController {
    @Autowired
    private genreDAO genre;
    @Autowired
    private productDAO product;
    @Autowired
    private commentDAO cmtDAO;
    @Autowired
    private cartDAO cartD;
    //=======================================================================SEARCH PRODUCT
    @RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest rq) {
        String s = rq.getParameter("search");
        ModelAndView model = new ModelAndView();
         try {
             loginController loginC=new loginController();
             model=loginC.checkLogin(rq, model);
             account a = loginC.checkCookie(rq);
            if(a!= null){
                model.addObject("numberCart", cartD.getCountCart(a.getId()));
            }
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
        } catch (Exception e) {
            }       
        model.setViewName("product_search");
        return model;
    }
    ////////////////////////////////////////////////////////////////////////////Result search perPage
    @RequestMapping(value = "/searchProduct/{pageid}", method = RequestMethod.GET)
    public ModelAndView searchPage(HttpServletRequest rq, @PathVariable Integer pageid) {
        ModelAndView model = new ModelAndView();
        if(pageid > 0 && pageid != null){
            String s = rq.getParameter("search");
             try {
                 loginController loginC=new loginController();
                 model=loginC.checkLogin(rq, model);
                 account a = loginC.checkCookie(rq);
                if(a!= null){
                    model.addObject("numberCart", cartD.getCountCart(a.getId()));
                }
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
                model.setViewName("product_search");
                return model;
            } catch (Exception e) {
                model.setViewName("redirect:/index");
                return model;
            }
        }
        model.setViewName("redirect:/index");
        return model;
    }
    //==========================================================================PRODUCT ADD PAGE
    @RequestMapping(value="/productAdd")
    public ModelAndView addProduct(){
        ModelAndView model = new ModelAndView();
        Product p = new Product();
        model.addObject("p", p);
        
        try {
            List<genre> list = genre.list();
            model.addObject("listGenre", list);
        } catch (Exception e) {

        }
        
        model.setViewName("productAdd");
        return model;
    }
    //==========================================================================ADD A PRODUCT
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ModelAndView saveProduct(@RequestParam("fileImage") MultipartFile[] multi, @ModelAttribute Product p) throws IOException {
        ModelAndView mol = new ModelAndView();
        ArrayList<String> list = new ArrayList<>();
        String uploadDir = "./img/1/";
        if(multi != null){
        for (MultipartFile multi2 : multi) {
            String date = new SimpleDateFormat("yyyyMMddHHmmss.").format(new Date());
            String imgName = StringUtils.cleanPath(multi2.getOriginalFilename());
            imgName = imgName.toLowerCase().replaceAll(" ", "-").substring(imgName.length() - 10);
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
        if (p.getProduct_id() == null) {
            product.save(p);
            mol.setViewName("redirect:/productAdd");
        } else {
//            product.update(p);
            mol.setViewName("redirect:/productAdd");
        }
        mol.setViewName("redirect:/productAdd");
        return mol;
    }
    //==========================================================================PageView - Pagination
    @RequestMapping(value="/view/{pageid}")    
    public ModelAndView viewP(@PathVariable Integer pageid,ModelAndView m, HttpServletRequest rq){
        if(pageid>0 && pageid != null){
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
            loginController loginC=new loginController();
            m=loginC.checkLogin(rq, m);
            account a = loginC.checkCookie(rq);
            if(a!= null){
                m.addObject("numberCart", cartD.getCountCart(a.getId()));
            }
            m.addObject("countP", countPage);
            m.addObject("productList", list);
            m.addObject("currentP", currentP);
            m.setViewName("product_list");
            return m;
        }
        m.setViewName("redirect:/index");
        return m;
    }
    //==========================================================================View2 for hot list
    @RequestMapping(value="/view2/{pageid}")    
    public ModelAndView view2p(@PathVariable Integer pageid,ModelAndView m, HttpServletRequest rq){
        if(pageid>0 && pageid != null){
            int total=8;
            int currentP = 1;
            currentP = pageid;
            Integer count = product.getCountProductPage();
            float countPage = (float) count / total;
            countPage = (float) Math.ceil(countPage);
            if(pageid!=1){    
                pageid=(pageid-1)*total+1;
            }    
            List<Product> list=product.getHotProductByPage(pageid,total);
            loginController loginC=new loginController();
            m=loginC.checkLogin(rq, m);
            account a = loginC.checkCookie(rq);
            if(a!= null){
                m.addObject("numberCart", cartD.getCountCart(a.getId()));
            }
            m.addObject("countP", countPage);
            m.addObject("productList", list);
            m.addObject("currentP", currentP);
            m.setViewName("product_list");
            return m;
        }
        m.setViewName("redirect:/index");
        return m;
    }
    //==========================================================================View Detail PRODUCT
    @RequestMapping(value = "/productView", method = RequestMethod.GET)
    public ModelAndView productView(HttpServletRequest rq) {
        ModelAndView model = new ModelAndView();
        try{
            Integer id = Integer.parseInt(rq.getParameter("id"));
            
            loginController loginC=new loginController();
            model=loginC.checkLogin(rq, model);
            account a = loginC.checkCookie(rq);
            if(a!= null){
                model.addObject("numberCart", cartD.getCountCart(a.getId()));
                product.updateView(id);
            }
            
            Product p = product.get(id);
            List<Product> list = new ArrayList<>();
            list.add(p);
            model.addObject("productDetail", list);
            
            List<comment> listCmt = cmtDAO.list(id);
            model.addObject("cmtList", listCmt);
            
            list = new ArrayList<>();
            list = product.randomList(p.getGenre());
            model.addObject("randomList", list);
            
            model.setViewName("productDetail");
            model.addObject("mssdetail", p.getDetail());
        }catch(Exception e){
            model.setViewName("redirect:/index");
        }
        return model;
    }
    //==========================================================================POST COMMENT
    @RequestMapping("/uploadCmt")
    @ResponseBody
    public void uploadCmt(@RequestParam("idP") Integer idP, @RequestParam("cmt") String cmt, HttpServletResponse response, HttpServletRequest request) throws Exception{
        loginController loginC=new loginController();
        account a = loginC.checkCookie(request);
        if(a != null){
            try{
                String date2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(date2);
                cmtDAO.save(cmt, a.getId(), idP, date);
            }catch(Exception e){
                System.out.println("ERROR UPLOAD CMT");
            }
        }
    }
    //==========================================================================Login From this product
    @RequestMapping("/goLogin/{idP}")
    public ModelAndView goLogin(@PathVariable Integer idP, HttpServletRequest request) throws Exception{
        try{
            loginController loginC=new loginController();
            account a = loginC.checkCookie(request);
            if(a == null && idP != null){
                ModelAndView model = new ModelAndView();
                model.addObject("idP", idP);
                model.addObject("account", new account());
                model.setViewName("login");
                return model;   
            }
        }catch(Exception e){
            throw new Exception("ERROR GO LOGIN FROM PRODUCT");
        }
        return new ModelAndView("redirect:/index");
    }
}
