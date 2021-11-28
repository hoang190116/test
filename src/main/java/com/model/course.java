/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author Admin
 */
public class course extends category{
    private Integer c_id;
    private String c_name;
    private String c_des;

    public course() {
    }

    public course(String c_name, String c_des, Integer category_id) {
        this.c_name = c_name;
        this.c_des = c_des;
        setCategory_id(category_id);
    }
    
    public course(Integer id,String c_name, String c_des, Integer category_id, String nameCate) {
        this.c_id = id;
        this.c_name = c_name;
        this.c_des = c_des;
        setCategory_id(category_id);
        setCategory_name(nameCate);
    }

    public course(Integer id,String c_name, String c_des, Integer category_id) {
        this.c_id = id;
        this.c_name = c_name;
        this.c_des = c_des;
        setCategory_id(category_id);
    }
    
    public course(Integer id,String c_name, String c_des) {
        this.c_id = id;
        this.c_name = c_name;
        this.c_des = c_des;
    }
    
    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_des() {
        return c_des;
    }

    public void setC_des(String c_des) {
        this.c_des = c_des;
    }
    
}
