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
public class AccountModel {
    public boolean login(String username, String password) {
		return username.equalsIgnoreCase("abc") && password.equalsIgnoreCase("123");
	}
}
