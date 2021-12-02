/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import com.example.model.category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class categoryDAOImp implements categoryDAO{
    private JdbcTemplate jt;

    public categoryDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }

    public categoryDAOImp() {
    }
    
    
    
    @Override
    public int save(category cate) {
        String sql = "insert into category(category_name, category_des) values(?,?)";
        return jt.update(sql, cate.getCategory_name(), cate.getCategory_des());
    }

    @Override
    public int update(category c) {
        String sql="update category set category_name=?, category_des=? where category_id=?";
        return jt.update(sql, c.getCategory_name(), c.getCategory_des(), c.getCategory_id());
    }

    @Override
    public category get(final int id) {
        String sql = "select * from category where category_id="+id;
        ResultSetExtractor<category> extrac = new ResultSetExtractor<category>(){
            public category extractData(ResultSet rs){
                try {
                    if(rs.next())
                    {
                        String name = rs.getString("category_name");
                        String des = rs.getString("category_des");
                        
                        return new category(name, des, id);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(categoryDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return null;
            }
        };
        return jt.query(sql, extrac);
    }

    @Override
    public int delete(int id) {
        String sql = "delete from category where category_id="+id;
        return jt.update(sql);
    }


    @Override
    public List<category> list() {

        String sql = "select * from category";
        RowMapper<category> rowMap = new RowMapper<category>() {
            public category mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");
                String des = rs.getString("category_des");
                return new category(name, des, id);
            }
        };
        return jt.query(sql, rowMap);
    }
    
    @Override
    public List<category> search(String name) {
        String sql = "select * from category where category_name like '%"+name+"%'";
        RowMapper<category> rowMap = new RowMapper<category>() {
            public category mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");
                String des = rs.getString("category_des");
                return new category(name, des, id);
            }
        };
        return jt.query(sql,rowMap);
    }
    
}
