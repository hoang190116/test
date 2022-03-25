/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.cart;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface cartDAO {
    public int save(cart c, int a_id);
    public int update(cart c);
    public cart get(int id);
    public int delete(cart c);
    public List<cart> list(int id);
    public List<cart> search(String name);
}
