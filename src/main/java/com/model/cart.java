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
public class cart extends Product{
    private Integer id;
    
    private Integer quantity;
    
    private Integer countCart;

    public Integer getCountCart() {
        return countCart;
    }

    public void setCountCart(Integer countCart) {
        this.countCart = countCart;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
