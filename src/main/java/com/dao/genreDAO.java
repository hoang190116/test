/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.genre;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface genreDAO {
    public int save(String name);
    public List<genre> list();
}
