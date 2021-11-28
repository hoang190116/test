/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.user;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface userDAO {
    public int save(user u);
    public int save2(int id, int c_id);
    public int update(user u);
    public int update2(user u);
    public user get(int id);
    public int delete(int id);
    public List<user> list();
    public List<user> list2();
    public List<user> list3(int c_id);
    public List<user> list4(int id);
    public List<user> listTeacher();
    public user login(user u);
    public List<user> search(String name);
    public List<user> search2(String name);
    public List<user> search3(String name, int c_id);
    public List<user> search4(String name, int c_id);
    public int delete2(int id, int c_id);
}
