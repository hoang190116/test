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
public class topic extends user{
    private Integer topic_id;
    private String topic_des;
    private String topic_name;

    public topic() {
    }

    public topic(int id, String name, String des, Integer c_id, Integer u_id){
        topic_id = id;
        topic_name = name;
        topic_des = des;
        setC_id(c_id);
        setU_id(u_id);
    }
    
    public topic(int id, String name, String des, Integer c_id, String c_name, Integer u_id, String u_name){
        topic_id = id;
        topic_name = name;
        topic_des = des;
        setC_id(c_id);
        setC_name(c_name);
        setU_id(u_id);
        setU_fullname(u_name);
    }

    public Integer getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(Integer topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_des() {
        return topic_des;
    }

    public void setTopic_des(String topic_des) {
        this.topic_des = topic_des;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    
}
