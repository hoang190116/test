/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.model.course;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface courseDAO {
    public int save(course cou);
    public int update(course cou);
    public int delete(int id);
    public course get(int id);
    public List<course> list();
    public List<course> list4Student(int id);
    public List<course> searchById(String name, int id);
    public List<course> search(String name);
}
