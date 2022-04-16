/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class account extends cart{
    private Integer id;
    private String fname;
    private String uname;
    private String pass;
    private String date_of_bird;
    private long phone;
    private String email;
    private String role;
    
    public account() {
    }
    
    public boolean login(String username, String pass){
        return username.equalsIgnoreCase("abc") && pass.equalsIgnoreCase("123");
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDate_of_bird() {
        return date_of_bird;
    }

    public void setDate_of_bird(String date_of_bird) {
        this.date_of_bird = date_of_bird;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
