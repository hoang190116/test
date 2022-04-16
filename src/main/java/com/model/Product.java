/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Product extends genre{
    private Integer product_id;
    private String name;
    private Integer price;
    private String releaseDate;
    private String detail;
    private Integer view;
    public ArrayList<String> photos;
    
//    public int totalPrice(){
//        int total = this.Quantity * this.price;
//        return total;
//    }
    
    
    
    public Product(){
    }
    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setView(int view) {
        this.view = view;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }
    
    
    
    public Integer getProduct_id() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDetail() {
        return detail;
    }

    public Integer getView() {
        return view;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }
    
    
    @Transient
    public String getLogoImgPath(){
        if(photos == null) return null;
        return "img/1/"+photos.get(0);
    }
}
