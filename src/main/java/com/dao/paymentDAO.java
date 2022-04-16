/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.payment;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface paymentDAO {
    public int save(payment p, int a_id);
    public int update(payment p, int id);
    public payment get(int id);
    public int delete(int id);
    public List<payment> list(int id);
}
