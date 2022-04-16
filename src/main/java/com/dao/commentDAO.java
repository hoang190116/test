/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.comment;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface commentDAO{
    public int save(String cmt, int a_id, int p_id, Date date);
    public comment get(int id);
    public int delete(int a_id, int p_id);
    public List<comment> list(int p_id);
}
