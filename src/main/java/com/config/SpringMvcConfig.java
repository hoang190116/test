/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.config;

import com.dao.accountDAO;
import com.dao.accountDAOImp;
import com.dao.billDAO;
import com.dao.billDAOImp;
import com.dao.cartDAO;
import com.dao.cartDAOImp;
import com.dao.commentDAO;
import com.dao.commentDAOImp;
import com.dao.genreDAO;
import com.dao.genreDAOImp;
import com.dao.paymentDAO;
import com.dao.paymentDAOImp;
import com.dao.productDAO;
import com.dao.productDAOImp;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 *
 * @author Admin
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.*")
//@EnableTransactionManagement
public class SpringMvcConfig implements WebMvcConfigurer{
    @Bean
    public DataSource getData(){
        DriverManagerDataSource dt = new DriverManagerDataSource();
        dt.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dt.setUrl("jdbc:sqlserver:54.151.193.161:1433;databaseName=g3shop");
        dt.setUsername("sa");
        dt.setPassword("admin@123");
        
        return dt;
    }

    @Bean
    public ViewResolver getView(){
        InternalResourceViewResolver reso = new InternalResourceViewResolver();
        reso.setPrefix("/WEB-INF/views/");
        reso.setSuffix(".jsp");
        
        return reso;
    }
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver com = new CommonsMultipartResolver();
        com.setMaxUploadSize(20848820);
        return com;
    }
//    @Bean
//    public MultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }
//    @Bean
//    public StandardServletMultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }
    
    @Bean
    public accountDAO getAccountDAO(){
        return new accountDAOImp(getData());
    }
    
    @Bean
    public cartDAO getCartDAO(){
        return new cartDAOImp(getData());
    }
    
    @Bean
    public billDAO getBillDAO(){
        return new billDAOImp(getData());
    }
    @Bean
    public commentDAO getCommentDAO(){
        return new commentDAOImp(getData());
    }
    @Bean
    public paymentDAO getPaymentDAO(){
        return new paymentDAOImp(getData());
    }
    @Bean
    public productDAO getProductDAO(){
        return new productDAOImp(getData());
    }
    @Bean
    public genreDAO getGenreDAO(){
        return new genreDAOImp(getData());
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get("./img");
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        
        registry.addResourceHandler("/img/**").addResourceLocations("file:/"+uploadPath+"/");
    }
    
}
