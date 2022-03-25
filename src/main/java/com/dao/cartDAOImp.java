/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.cart;
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
public class cartDAOImp implements cartDAO{
    private JdbcTemplate jt;

    public cartDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }
    public cartDAOImp(){
        
    }
    public Integer selectCartID(int a_id){
        String sql = "select cart_id from cart_account where account_id="+a_id;
        int temp = (int) jt.queryForObject(sql, new Object[] {},int.class);
        return temp;
    }
    public Integer checkCart_detail(int c_id, int p_id){
        String sql = "select product_id from cart_detail where cart_id="+c_id+" && product_id="+p_id;
        int temp = (int) jt.queryForObject(sql, new Object[] {},int.class);
        return temp;
    }
    @Override
    public int save(cart c, int a_id) {
        if(selectCartID(a_id)==null){
            String sql = "insert into cart_account(account_id) values(?)";
            jt.update(sql, a_id);
        }else if(checkCart_detail(selectCartID(a_id), c.getProduct_id())!=null){
            c.setId(selectCartID(a_id));
            return update(c);
        }
        Integer c_id = selectCartID(a_id);
        String sql = "insert into cart_detail(cart_id, product_id, quantity) values(?,?,?)";
        return jt.update(sql, c_id, c.getProduct_id(), c.getQuantity());
    }

    @Override
    public int update(cart c) {
        String sql = "update cart_detail set quantity=? where cart_id="+c.getId()+" && product_id="+c.getProduct_id();
        return jt.update(sql, c.getQuantity());
    }

    @Override
    public cart get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(cart c) {
        String sql = "delete from cart_detail where product_id="+c.getProduct_id()+" && cart_id="+c.getId();
        return jt.update(sql);
    }

    @Override
    public List<cart> list(int a_id) {
        Integer c_id = selectCartID(a_id);
        if(c_id == null){
            return null;
        }
        String sql = "select * from cart_detail where cart_id="+c_id;
        RowMapper<cart> rowMap = new RowMapper<cart>() {
            public cart mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs.next())
                    {
                        cart c = new cart();
                        c.setId(rs.getInt("cart_id"));
                        c.setProduct_id(rs.getInt("product_id"));
                        c.setQuantity(rs.getInt("quantity"));
                        return c;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(billDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return null;
            }
        };
        return jt.query(sql, rowMap);
    }

    @Override
    public List<cart> search(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
