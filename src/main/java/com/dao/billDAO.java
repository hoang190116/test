/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Product;
import com.model.account;
import com.model.bill;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface billDAO {
    public int save(bill b, account a);
    public int update(bill b);
    public int delete(int id);
    public List<bill> list();
    public List<bill> search(int id);
}
