/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

/**
 *
 * @author Admin
 */
public class category{
    private Integer category_id;
    private String category_name;
    private String category_des;

    public category() {
    }

    public category(String category_name, String category_des, Integer category2_id) {
        this.category_id = category2_id;
        this.category_name = category_name;
        this.category_des = category_des;
    }
    public category(String category_name, String category_des) {
        this.category_name = category_name;
        this.category_des = category_des;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_des() {
        return category_des;
    }

    public void setCategory_des(String category_des) {
        this.category_des = category_des;
    }

    @Override
    public String toString() {
        return "category{" + "category_id=" + category_id + ", category_name=" + category_name + ", category_des=" + category_des + '}';
    }
    
    
}
