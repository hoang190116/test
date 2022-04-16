/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/**
 *
 * @author Admin
 */
public class WebInitializer implements WebApplicationInitializer{  
    @Override
    public void onStartup(ServletContext slt) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(SpringMvcConfig.class);
        
        ServletRegistration.Dynamic dispatcher 
                = slt.addServlet("Springdispatcher", new DispatcherServlet(appContext));
        
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
    }
}
