package com.model;

import java.sql.Date;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class user extends role{
    private Integer u_id;
    private String u_name;
    private String u_pass;
    private String u_fullname;
    private int u_phone = 0;
    private Date u_date_of_bird;
    private String u_address;
    private String u_detail;

    public user() {
    }

    public user(Integer u_id, String u_name, String u_pass, String u_fullname, int u_phone, 
            Date u_date_of_bird, String u_address, String u_detail, String role_name) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_pass = u_pass;
        this.u_fullname = u_fullname;
        this.u_phone = u_phone;
        this.u_date_of_bird = u_date_of_bird;
        this.u_address = u_address;
        this.u_detail = u_detail;
        setRole_name(role_name);
    }
    
    public user(String u_name, String u_pass, String u_fullname, int u_phone, Date u_date_of_bird, 
            String u_address, String u_detail, String role) {
        this.u_name = u_name;
        this.u_pass = u_pass;
        this.u_fullname = u_fullname;
        this.u_phone = u_phone;
        this.u_date_of_bird = u_date_of_bird;
        this.u_address = u_address;
        this.u_detail = u_detail;
        setRole_name(role);
    }
    
    public user(String u_fullname, int u_phone, Date u_date_of_bird, String u_address, 
            String u_detail, String role) {
        this.u_fullname = u_fullname;
        this.u_phone = u_phone;
        this.u_date_of_bird = u_date_of_bird;
        this.u_address = u_address;
        this.u_detail = u_detail;
        setRole_name(role);
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_pass() {
        return u_pass;
    }

    public void setU_pass(String u_pass) {
        this.u_pass = u_pass;
    }

    public String getU_fullname() {
        return u_fullname;
    }

    public void setU_fullname(String u_fullname) {
        this.u_fullname = u_fullname;
    }

    public int getU_phone() {
        return u_phone;
    }

    public void setU_phone(int u_phone) {
        this.u_phone = u_phone;
    }

    public Date getU_date_of_bird() {
        return u_date_of_bird;
    }

    public void setU_date_of_bird(Date u_date_of_bird) {
        this.u_date_of_bird = u_date_of_bird;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getU_detail() {
        return u_detail;
    }

    public void setU_detail(String u_detail) {
        this.u_detail = u_detail;
    }


}
