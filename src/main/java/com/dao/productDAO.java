/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Product;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface productDAO {
    public int save(Product p);
    public int update(Product p);
    public int addImg(Product p);
    public Product get(int id);
    public int delete(int id);
    public int deleteImg(String name);
    public List<Product> list();
    public List<Product> search(String name);
}
