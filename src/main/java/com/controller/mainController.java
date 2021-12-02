/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller; 
import com.dao.categoryDAO;
import com.dao.courseDAO;
import com.dao.topicDAO;
import com.dao.userDAO;
import com.model.category;
import com.model.course;
import com.model.topic;
import com.model.user;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
//@RequestMapping(value="/")
public class mainController {
    private String checking;
    private boolean checkPass;
    private String checkRole;
    private boolean staff;
    private boolean FK = false;
    private Integer id;
            
    @Autowired
    private categoryDAO Catedao;
    @Autowired
    private courseDAO daoCourse;
    @Autowired
    private topicDAO daoTopic;
    @Autowired
    private userDAO daoUser;
    
    @RequestMapping(value = {"/index", "/"})
    public ModelAndView list(ModelAndView model){
        this.checking = null;
        staff = false;
        id = null;
        model.setViewName("index");
        return model;
    }

    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model){
        user u = new user();
        model.addObject("user", u);
        model.setViewName("login");
        return model;
    }
    
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
    
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView addUser(ModelAndView model){
        user u = new user();
        model.addObject("u", u);
        model.setViewName("admin_user_add");
        if(staff){
            String a = "in";
            model.addObject("check", a);
        }
        if(checkRole!=null && checkRole.equals("in")){
            String a = "You must choose User Role!";
            model.addObject("mes", a);
            checkRole = null;
        }
        if(checkPass){
            String checkp = "Password lenght must have atleast 8 character, 1 specail character, 1 uppercase character, 1 lowercase and 1 digit character";
            model.addObject("checkPass", checkp);
            checkPass = false;
        }
        return model;
    }
    
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute user u){
        ModelAndView mol = new ModelAndView();
        if(u.getU_id() == null) {
            if(staff){
                u.setRole_name("Student");
            }
            if(u.getRole_name()==null){
                checkRole = "in";
                mol.setViewName("redirect:/addUser");
            }
            else{
                if (isValid(u.getU_pass())) {
                    daoUser.save(u);
                    mol.setViewName("redirect:/userList");
                } else {
                    checkPass = true;
                    mol.setViewName("redirect:/addUser");
                }
            }
        }
        else{
            if (staff) {
                u.setRole_name("Student");
            }
            if (isValid(u.getU_pass())) {
                daoUser.update(u);
                mol.setViewName("redirect:/userList");
            } else {
                checkPass = true;
                this.id = u.getU_id();
                mol.setViewName("redirect:/editUserLoad");
            }
        }
        checking = "in";
        return mol;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest rq){
        String sr = rq.getParameter("search");
        ModelAndView model = new ModelAndView();
        model.addObject("a", sr);
        if(staff){
                try {
                    String a = "in";
                    model.addObject("check", a);
                    List<user> list = daoUser.search2(sr);
                    model.addObject("listUser", list);
            } catch (Exception e) {
            }
        }
        else{
            try {
                List<user> list = daoUser.search(sr);
                model.addObject("listUser", list);
            } catch (Exception e) {
            }
        }
        model.setViewName("admin_user_list");
        return model;
    }
    
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public ModelAndView userList(ModelAndView model){
        if(this.checking != null && this.checking.equals("in")){
            if(staff){
                String a = "in";
                model.addObject("check", a);
                List<user> list = daoUser.list2();
                model.addObject("listUser", list);
                model.setViewName("admin_user_list");
            }
            else
            {
                List<user> list = daoUser.list();
                model.addObject("listUser", list);
                model.setViewName("admin_user_list");
            }
        }
        else{
            model.setViewName("index");
        }
        return model;
    }
    @RequestMapping(value = "/editUserLoad", method = RequestMethod.GET)
    public ModelAndView editUser2(){
        user u = daoUser.get(this.id);
        this.id = null;
        ModelAndView model = new ModelAndView("admin_user_add");
        model.addObject("u", u);
        if(staff){
            String a = "in";
            model.addObject("check", a);
        }
        if(checkPass){
            String checkp = "Password lenght must have atleast 8 character, 1 specail character, 1 uppercase character, 1 lowercase and 1 digit character";
            model.addObject("checkPass", checkp);
            checkPass = false;
        }
        return model;
    }
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(HttpServletRequest rq){
        Integer id = Integer.parseInt(rq.getParameter("id"));
        user u = daoUser.get(id);
        ModelAndView model = new ModelAndView("admin_user_add");
        model.addObject("u", u);
        if(staff){
            String a = "in";
            model.addObject("check", a);
        }
        return model;
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ModelAndView deleteUser(@RequestParam Integer id){
        daoUser.delete(id);
        return new ModelAndView("redirect:/userList");
    }
    
    /////////////////////////////////////////////////////////Login//////////////////////////////////////////////////////
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView loginIn(@ModelAttribute user u){
        ModelAndView model = new ModelAndView();
        try{
            user u2 = daoUser.login(u);
            if (u2.getRole_name().equals("Admin")) {
                model.setViewName("admin_user_list");
                try {
                    List<user> list = daoUser.list();
                    model.addObject("listUser", list);
                    this.checking = "in";
                } catch (Exception e) {
                }
            }
            else if (u2.getRole_name().equals("Staff")) {
                model.setViewName("admin_user_list");
                String a = "in";
                model.addObject("check", a);
                try {
                    staff = true;
                    List<user> list = daoUser.list2();
                    model.addObject("listUser", list);
                    this.checking = "in";
                } catch (Exception e) {
                }
            }
            else if(u2.getRole_name().equals("Teacher")){
                try {
                    checking = "in";
                    this.id = u2.getU_id();
                    List<topic> list = daoTopic.list4Teacher(u2.getU_id());
                    model.addObject("listTopic", list);
                    model.setViewName("trainer_home");
                    model.addObject("id", id);
                } catch (Exception e) {
                }
            }
            else if (u2.getRole_name().equals("Student")) {
                try {
                    this.id = u2.getU_id();
                    List<course> list = daoCourse.list4Student(u2.getU_id());
                    model.addObject("listCourse", list);
                    model.setViewName("trainee_home");
                } catch (Exception e) {
                }
            }
            else {
                String a = "Wrong ";
                model.addObject("mes", a);
                model.setViewName("login");
            }
        }catch(Exception e){
            String a = "Wrong user name or pass word!";
            model.addObject("mes", a);
            model.setViewName("login");
        }
        
        return model;
    }
    
    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public ModelAndView logOut(ModelAndView model){
        this.checking = null;
        staff = false;
        id = null;
        model.setViewName("redirect:/index");
        return model;
    }
    
    
    /////////////////////////////////////////////////////Category/////////////////////////////////////////////
    
    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    public ModelAndView categoryList(ModelAndView model){
        if (this.checking != null && this.checking.equals("in")) {
                String a = "in";
                model.addObject("check", a);
            List<category> list = Catedao.list();
            model.addObject("listCate", list);
            model.setViewName("admin_category_list");
            if(FK){
                    String b = "This Category already have course! You must delete these Course first!";
                    model.addObject("fk",b);
                }
        }
        else{
            model.setViewName("index");
        }
        FK = false;
        return model;
    }
    
    @RequestMapping(value = "/addCate", method = RequestMethod.GET)
    public ModelAndView addCate(ModelAndView model){
        category cate = new category();
        model.addObject("cate", cate);
        model.setViewName("admin_category_add");
            String a = "in";
            model.addObject("check", a);
        return model;
    }
    
    @RequestMapping(value = "/saveCate", method = RequestMethod.POST)
    public ModelAndView saveCate(@ModelAttribute category cate){
        ModelAndView mol = new ModelAndView();
        if(cate.getCategory_id() == null) {
                Catedao.save(cate);
                mol.setViewName("redirect:/categoryList");
        }
        else{
            Catedao.update(cate);
            mol.setViewName("redirect:/categoryList");
        }
        checking = "in";
        return mol;
    }
    
    @RequestMapping(value = "/searchCate", method = RequestMethod.GET)
    public ModelAndView searchCate(HttpServletRequest rq){
        String sr = rq.getParameter("search");
        ModelAndView model = new ModelAndView();
        model.addObject("a", sr);

        String a = "in";
        model.addObject("check", a);

        try {
            List<category> list = Catedao.search(sr);
            model.addObject("listCate", list);
        } catch (Exception e) {
        }
        model.setViewName("admin_category_list");
        return model;
    }
    
    @RequestMapping(value = "/deleteCate", method = RequestMethod.POST)
    public ModelAndView deleteCate(@RequestParam Integer id){
        try{
            Catedao.delete(id);
            FK = false;
        }catch(Exception e){
            FK = true;
        }
        return new ModelAndView("redirect:/categoryList");
    }
    @RequestMapping(value = "/editCate", method = RequestMethod.POST)
    public ModelAndView editCate(HttpServletRequest rq){
        Integer id = Integer.parseInt(rq.getParameter("id"));
        category cate = Catedao.get(id);
        ModelAndView model = new ModelAndView("admin_category_add");
        model.addObject("cate", cate);
        String a = "in";
        model.addObject("check", a);
        return model;
    }
    ///////////////////////////////////////////////////Course/////////////////////////////////////////////////
    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    public ModelAndView courseList(ModelAndView model){
        if (this.checking != null && this.checking.equals("in")) {
                String a = "in";
                model.addObject("check", a);
            List<course> list = daoCourse.list();
            model.addObject("listCourse", list);
            model.setViewName("admin_course_list");
            if(FK){
                    String b = "This Course already have topics or students!";
                    model.addObject("fk2",b);
                }
        }
        else{
            model.setViewName("index");
        }
        FK = false;
        return model;
    }
    
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public ModelAndView addCourse(ModelAndView model){
        course c = new course();
        model.addObject("c", c);
        model.setViewName("admin_course_add");
            String a = "in";
            model.addObject("check", a);
        try{
            List<category> list = Catedao.list();
            model.addObject("listCategory", list);
        }catch(Exception e){
            
        }
        return model;
    }
    
    @RequestMapping(value = "/saveCourse", method = RequestMethod.POST)
    public ModelAndView saveCourse(@ModelAttribute course c){
        ModelAndView mol = new ModelAndView();
        if(c.getC_id() == null) {
                daoCourse.save(c);
                mol.setViewName("redirect:/courseList");
        }
        else{
            daoCourse.update(c);
            mol.setViewName("redirect:/courseList");
        }
        checking = "in";
        return mol;
    }
    
    @RequestMapping(value = "/searchCourse", method = RequestMethod.GET)
    public ModelAndView searchCourse(HttpServletRequest rq){
        String sr = rq.getParameter("search");
        ModelAndView model = new ModelAndView();
        model.addObject("a", sr);

        String a = "in";
        model.addObject("check", a);

        try {
            List<course> list = daoCourse.search(sr);
            model.addObject("listCourse", list);
        } catch (Exception e) {
        }
        model.setViewName("admin_course_list");
        return model;
    }
    @RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
    public ModelAndView deleteCourse(@RequestParam Integer id){
        try{
            daoCourse.delete(id);
        }catch(Exception e){
            FK = true;
        }
        return new ModelAndView("redirect:/courseList");
    }
    @RequestMapping(value = "/editCourse", method = RequestMethod.POST)
    public ModelAndView editCourse(HttpServletRequest rq){
        Integer id = Integer.parseInt(rq.getParameter("id"));
        course c = daoCourse.get(id);
        ModelAndView model = new ModelAndView("admin_course_add");
        
        model.addObject("c", c);
        String a = "in";
        model.addObject("check", a);
        
        try{
            List<category> list = Catedao.list();
            model.addObject("listCategory", list);
        }catch(Exception e){
            
        }
        return model;
    }
    
    @RequestMapping(value = "/listStudent", method = RequestMethod.GET)
    public ModelAndView addStudent(HttpServletRequest rq){
        Integer id = Integer.parseInt(rq.getParameter("id"));
        course c = daoCourse.get(id);
        this.id = id;
        ModelAndView model = new ModelAndView("admin_course_add_student");
        model.addObject("name", c.getC_name());
        String a = "in";
        model.addObject("check", a);
        
        try{
            List<user> list = daoUser.list3(id);
            model.addObject("listUser", list);
        }catch(Exception e){
            
        }
        
        try{
            List<user> list = daoUser.list4(id);
            model.addObject("listUserCourse", list);
        }catch(Exception e){
            
        }
        
        return model;
    }
    
    @RequestMapping(value = "/searchStudent", method = RequestMethod.GET)
    public ModelAndView searchStudent(HttpServletRequest rq){
        String sr = rq.getParameter("search");
        ModelAndView model = new ModelAndView();
        model.addObject("a", sr);

        String a = "in";
        model.addObject("check", a);
        
        course c = daoCourse.get(this.id);
        model.addObject("name", c.getC_name());
        
        model.setViewName("admin_course_add_student");
        
        
        try{
            List<user> list = daoUser.search3(sr, this.id);
            model.addObject("listUser", list);
        }catch(Exception e){
            
        }
        
        try{
            List<user> list = daoUser.list4(this.id);
            model.addObject("listUserCourse", list);
        }catch(Exception e){
            
        }
        
        return model;
    }
    
    @RequestMapping(value = "/searchCourseStudent", method = RequestMethod.GET)
    public ModelAndView searchCourseStudent(HttpServletRequest rq){
        String sr = rq.getParameter("search");
        ModelAndView model = new ModelAndView();
        model.addObject("a", sr);

        String a = "in";
        model.addObject("check", a);
        
        course c = daoCourse.get(this.id);
        model.addObject("name", c.getC_name());
        
        model.setViewName("admin_course_add_student");
        
        
        try{
            List<user> list = daoUser.list3(this.id);
            model.addObject("listUser", list);
        }catch(Exception e){
            
        }
        
        try{
            List<user> list = daoUser.search4(sr, this.id);
            model.addObject("listUserCourse", list);
        }catch(Exception e){
            
        }
        
        return model;
    }
    @RequestMapping(value = "/addStu", method = RequestMethod.POST)
    public ModelAndView addStu(HttpServletRequest rq){
        Integer id = Integer.parseInt(rq.getParameter("id"));
        ModelAndView model = new ModelAndView("redirect:/listStudent?id="+this.id);
        
        try{
            daoUser.save2(id, this.id);
        }catch(Exception e){
            
        }
        return model;
    }
    @RequestMapping(value = "/deleteStu", method = RequestMethod.POST)
    public ModelAndView deleteStu(HttpServletRequest rq){
        Integer id = Integer.parseInt(rq.getParameter("id"));
        ModelAndView model = new ModelAndView("redirect:/listStudent?id="+this.id);
        
        try{
            daoUser.delete2(id, this.id);
        }catch(Exception e){
            
        }
        return model;
    }
    /////////////////////////////////////////////////////////////Topic////////////////////////////////////////////
    @RequestMapping(value = "/topicList", method = RequestMethod.GET)
    public ModelAndView topicList(ModelAndView model){
        if (this.checking != null && this.checking.equals("in")) {
            String a = "in";
            model.addObject("check", a);
            List<topic> list = daoTopic.list();
            model.addObject("listTopic", list);
            model.setViewName("admin_topic_list");
        } else {
            model.setViewName("index");
        }
        return model;
    }
    @RequestMapping(value = "/searchTopic", method = RequestMethod.GET)
    public ModelAndView searchTopic(HttpServletRequest rq){
        String sr = rq.getParameter("search");
        ModelAndView model = new ModelAndView();
        model.addObject("a", sr);

        String a = "in";
        model.addObject("check", a);

        try {
            List<topic> list = daoTopic.search(sr);
            model.addObject("listTopic", list);
        } catch (Exception e) {
        }
        model.setViewName("admin_topic_list");
        return model;
    }
    
    @RequestMapping(value = "/addTopic", method = RequestMethod.GET)
    public ModelAndView addTopic(ModelAndView model){
        topic to = new topic();
        model.addObject("to", to);
        
        try{
            List<course> list = daoCourse.list();
            model.addObject("listCourse", list);
        }catch(Exception e){
            
        }
        
        try{
            List<user> list = daoUser.listTeacher();
            model.addObject("listUser", list);
        }catch(Exception e){
            
        }
        
        model.setViewName("admin_topic_add");
            String a = "in";
            model.addObject("check", a);
        return model;
    }
    
    @RequestMapping(value = "/saveTopic", method = RequestMethod.POST)
    public ModelAndView saveTopic(@ModelAttribute topic to){
        ModelAndView mol = new ModelAndView();
        if(to.getTopic_id() == null) {
                daoTopic.save(to);
                mol.setViewName("redirect:/topicList");
        }
        else{
            daoTopic.update(to);
            mol.setViewName("redirect:/topicList");
        }
        checking = "in";
        return mol;
    }
    
    @RequestMapping(value = "/deleteTopic", method = RequestMethod.POST)
    public ModelAndView deleteTopic(@RequestParam Integer id){
        try{
            daoTopic.delete(id);
        }catch(Exception e){
            FK = true;
        }
        return new ModelAndView("redirect:/topicList");
    }
    @RequestMapping(value = "/editTopic", method = RequestMethod.POST)
    public ModelAndView editTopic(HttpServletRequest rq){
        Integer id = Integer.parseInt(rq.getParameter("id"));
        topic to = daoTopic.get(id);
        ModelAndView model = new ModelAndView("admin_topic_add");
        
        model.addObject("to", to);
        String a = "in";
        model.addObject("check", a);
        
        try{
            List<course> list = daoCourse.list();
            model.addObject("listCourse", list);
        }catch(Exception e){
            
        }
        
        try{
            List<user> list = daoUser.listTeacher();
            model.addObject("listUser", list);
        }catch(Exception e){
            
        }
        return model;
    }
    
    /////////////////////////////////////////////////////Student Home///////////////////////////////////////
    @RequestMapping(value = "/Course", method = RequestMethod.GET)
    public ModelAndView Course(HttpServletRequest rq){      
        ModelAndView model = new ModelAndView("trainee_course");
        
        try{
            Integer id = Integer.parseInt(rq.getParameter("id"));
            List<topic> list = daoTopic.listTopic2(id);
            model.addObject("listTopic", list);
            
            course c = daoCourse.get(id);
            model.addObject("name",c.getC_name());
        }catch(Exception e){
            model.setViewName("redirect:/");
        }

        return model;
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView model){
        if (checking != null) {
            try {
                List<topic> list = daoTopic.list4Teacher(this.id);
                model.addObject("listTopic", list);
                model.setViewName("trainer_home");
            } catch (Exception e) {
                model.setViewName("index");
            }
        }
        else {
            try {
                List<course> list = daoCourse.list4Student(this.id);
                model.addObject("listCourse", list);
                model.setViewName("trainee_home");
            } catch (Exception e) {
                model.setViewName("index");
            }
        }
        return model;
    }
    
    @RequestMapping(value = "/searchBar", method = RequestMethod.GET)
    public ModelAndView searchBar(HttpServletRequest rq){
        String sr = rq.getParameter("search");
        ModelAndView model = new ModelAndView();
        if (checking != null) {
            try {
                List<topic> list = daoTopic.searchById(sr, this.id);
                model.addObject("listTopic", list);
            } catch (Exception e) {
            }
            model.setViewName("trainer_home");
        }
        else {
            try {
                List<course> list = daoCourse.searchById(sr, this.id);
                model.addObject("listCourse", list);
            } catch (Exception e) {
            }
            model.setViewName("trainee_home");
        }
        return model;
    }
    
    @RequestMapping(value = "/studentProfile", method = RequestMethod.GET)
    public ModelAndView studentProfile(ModelAndView model){
        try{
        user u = daoUser.get(this.id);
        model.setViewName("trainer_profile");
        model.addObject("u", u);
        }catch(Exception e){
            model.setViewName("index");
        }
        return model;
    }
    
    @RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
    public ModelAndView saveProfile(@ModelAttribute user u){
        ModelAndView mol = new ModelAndView();
        try{
            daoUser.update2(u);
            mol.setViewName("redirect:/home");
        }catch(Exception e){
            mol.setViewName("index");
        }
        return mol;
    }
    
    @RequestMapping(value = "/Category", method = RequestMethod.GET)
    public ModelAndView Category(HttpServletRequest rq){      
        ModelAndView model = new ModelAndView("trainer_course");
        
        try{
            Integer id = Integer.parseInt(rq.getParameter("id"));
            List<topic> list = daoTopic.list4Course(this.id, id);
            model.addObject("listTopic", list);
            
            category cate = Catedao.get(id);
            model.addObject("name",cate.getCategory_name());
        }catch(Exception e){
            model.setViewName("redirect:/");
        }

        return model;
    }
    
    @RequestMapping(value = "/CourseT", method = RequestMethod.GET)
    public ModelAndView CourseT(HttpServletRequest rq){      
        ModelAndView model = new ModelAndView("trainer_topic");
        
        try{
            Integer id = Integer.parseInt(rq.getParameter("id"));
            List<topic> list = daoTopic.list4Topic(this.id, id);
            model.addObject("listTopic", list);
            
            course c = daoCourse.get(id);
            model.addObject("name",c.getC_name());
        }catch(Exception e){
            model.setViewName("redirect:/");
        }

        return model;
    }
}
