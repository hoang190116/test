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
public class comment extends account{
    private String commen;
    private Date date;
    private String timeAgo;

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
            
    public String getCommen() {
        return commen;
    }

    public void setCommen(String commen) {
        this.commen = commen;
    }
    
}
