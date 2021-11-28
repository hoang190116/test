/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.topic;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface topicDAO {
    public int save(topic to);
    public int update(topic to);
    public topic get(int id);
    public int delete(int id);
    public List<topic> list();
    public List<topic> listTopic2(int id);
    public List<topic> list4Teacher(int id);
    public List<topic> list4Course(int id, int cate_id);
    public List<topic> list4Topic(int id, int c_id);
    public List<topic> search(String name);
    public List<topic> searchById(String name, int id);
}
