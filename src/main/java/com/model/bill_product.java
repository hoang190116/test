/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class bill_product{
    private ArrayList<String> product_name;
    private ArrayList<Integer> quantity;
    private ArrayList<Integer> price;

    public ArrayList<String> getProduct_name() {
        return product_name;
    }

    public void setProduct_name(ArrayList<String> product_name) {
        this.product_name = product_name;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Integer> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<Integer> price) {
        this.price = price;
    }
    
    
}
