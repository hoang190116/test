/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class payment {
    private Integer id;
    private Integer account_id;
    private String bank_name;
    private BigDecimal card_number;
    private String fname_card;
    private String fname;
    private String email;
    private String country;
    private String address;
    private Integer YY;
    private Integer MM;

    public String getFname_card() {
        return fname_card;
    }

    public void setFname_card(String fname_card) {
        this.fname_card = fname_card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getYY() {
        return YY;
    }

    public void setYY(Integer YY) {
        this.YY = YY;
    }

    public Integer getMM() {
        return MM;
    }

    public void setMM(Integer MM) {
        this.MM = MM;
    }
    
    public payment(){
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public BigDecimal getCard_number() {
        return card_number;
    }

    public void setCard_number(BigDecimal card_number) {
        this.card_number = card_number;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    
    
}
