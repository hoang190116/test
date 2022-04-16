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
    public int updateView(int id);
    public int addImg(Product p);
    public Product get(int id);
    public int delete(int id);
    public int deleteImg(String name);
    public List<Product> list();
    public List<Product> hotList();
    public List<Product> slideBar();
    public List<Product> randomList(String genre);
    public List<Product> search(String name, Integer pageid, int total);
    public boolean selectProductName(String name);
    public List<Product> getProductByPage(int pageid, int total);
    public Integer getCountProductPage();
    public List<Product> getHotProductByPage(int pageid, int total);
    public Integer getCountSearchPage(String name);
}
