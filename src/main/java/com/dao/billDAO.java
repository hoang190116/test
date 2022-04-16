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
    public int save(bill b, int a_id);
    public int save_cash(bill b, int a_id);
    public int update(bill b);
    public int delete(int id);
    public List<bill> listBill(int a_id);
    public List<bill> search(int id);
    public bill get(int id);
    public Integer getCountBillPage();
    public List<bill> getBillByPage(int pageid, int total);
    public List<bill> listWaiting();
    public List<bill> listProcess();
    public List<bill> listDeliver();
    public List<bill> listFinish();
}
