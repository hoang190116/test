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
    public int register(account a);
    public boolean getName(String name);
    public int update(account a, int id);
    public account get(int id);
    public int delete(int id);
    public List<account> list();
    public List<account> search(String name);
    public account login(account ac);
    public Integer getCountAccountPage();
    public List<account> getAccountByPage(int pageid, int total);
    public Integer getCountSearchAccountPage(String name);
    public List<account> searchAccount(String name, Integer pageid, int total);
}
