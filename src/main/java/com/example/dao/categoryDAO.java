/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import java.util.List;
import com.example.model.category;

/**
 *
 * @author Admin
 */
public interface categoryDAO {
    public int save(category cate);
    public int update(category cate);
    public category get(int id);
    public int delete(int id);
    public List<category> list();
    public List<category> search(String name);
}
