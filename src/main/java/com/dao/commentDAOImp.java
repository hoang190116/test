/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class commentDAOImp implements commentDAO{
    private JdbcTemplate jt;

    public commentDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }
    public commentDAOImp(){
        
    }

    @Override
    public int save(comment com, int a_id, int p_id) {
        String sql = "insert into comment values(?,?,?)";
        return jt.update(sql, p_id, a_id, com.getCommen());
    }

    @Override
    public comment get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int a_id, int p_id) {
        String sql = "delete from comment where account_id="+a_id+" && product_id="+p_id;
        return jt.update(sql);
    }
    private String getFullname(int a_id){
        String sql = "select fullname from account where account_id="+a_id;
        String temp = (String) jt.queryForObject(sql, new Object[] {},String.class);
        return temp;
    }
    @Override
    public List<comment> list(int p_id) {
        String sql = "select * from comment where product_id="+p_id;
        RowMapper<comment> rowMap = new RowMapper<comment>() {
            public comment mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs.next())
                    {
                        comment com = new comment();
                        com.setCommen(rs.getString("comment"));
                        com.setId(rs.getInt("account_id"));
                        com.setFname(getFullname(com.getId()));
                        return com;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(billDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return null;
            }
        };
        return jt.query(sql, rowMap);
    }
}
