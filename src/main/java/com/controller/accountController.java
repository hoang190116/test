/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.regex.Pattern;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Admin
 */
@Controller
public class accountController {
    public static boolean isValid(String pass) {
        boolean flag = true;
        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        if (pass.length() < 8) {
            flag = false;
        }
        if (!specailCharPatten.matcher(pass).find()) {
            flag = false;
        }
        if (!UpperCasePatten.matcher(pass).find()) {
            flag = false;
        }
        if (!lowerCasePatten.matcher(pass).find()) {
            flag = false;
        }
        if (!digitCasePatten.matcher(pass).find()) {
            flag = false;
        }

        return flag;
    }
}
