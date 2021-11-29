/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.config;

//import com.dao.categoryDAO;
//import com.dao.categoryDAOImp;
//import com.dao.courseDAO;
//import com.dao.courseDAOImp;
//import com.dao.topicDAO;
//import com.dao.topicDAOImp;
//import com.dao.userDAO;
//import com.dao.userDAOImp;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 *
 * @author Admin
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.*")
@SpringBootApplication
//@EnableTransactionManagement
public class SpringMvcConfig {
    @Bean
    public DataSource getData(){
        DriverManagerDataSource dt = new DriverManagerDataSource();
        dt.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dt.setUrl("jdbc:sqlserver://13.212.250.128:1433;databaseName=training");
        dt.setUsername("sa");
        dt.setPassword("admin@123");
        
        return dt;
    }

    @Bean
    public ViewResolver getView(){
        InternalResourceViewResolver reso = new InternalResourceViewResolver();
        reso.setPrefix("/");
        reso.setSuffix(".jsp");
        
        return reso;
    }
    
//    @Bean
//    public categoryDAO getDAO(){
//        return new categoryDAOImp(getData());   
//    }
//    
//    @Bean
//    public courseDAO getCourseDAO(){
//        return new courseDAOImp(getData()); 
//    }
//    
//    @Bean
//    public topicDAO getTopicDAO(){
//        return new topicDAOImp(getData());
//    }
//    
//    
//    @Bean
//    public userDAO getUserDAO(){
//        return new userDAOImp(getData());
//    }
    
}
