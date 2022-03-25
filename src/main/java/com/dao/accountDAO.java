/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.account;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface accountDAO {
    public int save(account a);
    public int update(account a);
    public account get(int id);
    public int delete(int id);
    public List<account> list();
    public List<account> search(String name);
    public account login(account ac);
}
