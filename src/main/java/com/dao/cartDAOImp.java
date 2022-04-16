/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.cart;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
        try{
            String sql = "select cart_id from cart_account where account_id="+a_id;
            Integer temp = (int) jt.queryForObject(sql, new Object[] {},int.class);
            return temp;
        }catch(Exception e){
            
        }
        return null;
    }
    public Integer checkCart_detail(int c_id, int p_id){
        try{
            String sql = "select product_id from cart_detail where cart_id="+c_id+" and product_id="+p_id;
            Integer temp = (int) jt.queryForObject(sql, new Object[] {},int.class);
            return temp;
        }catch(Exception e){
            
        }
        return null;
    }
    @Override
    public int save(cart c, int a_id) {
        Integer c_id = selectCartID(a_id);
        if(c_id == null){
            String sql = "insert into cart_account(account_id) values(?)";
            jt.update(sql, a_id);
            c_id = selectCartID(a_id);
        }else if(checkCart_detail(selectCartID(a_id), c.getProduct_id())!=null){
            c.setId(c_id);
            return update(c);
        }
        String sql = "insert into cart_detail(cart_id, product_id, quantity) values(?,?,?)";
        return jt.update(sql, c_id, c.getProduct_id(), 1);
    }

    @Override
    public int update(cart c) {
        String sql = "update cart_detail set quantity+=1 where cart_id="+c.getId()+" and product_id="+c.getProduct_id();
        return jt.update(sql);
    }
    @Override
    public int updateMinus(cart c) {
        String sql = "update cart_detail set quantity-=1 where cart_id="+c.getId()+" and product_id="+c.getProduct_id();
        return jt.update(sql);
    }

    @Override
    public cart get(int id) {
        String sql = "select * from product where product_id="+id;
        ResultSetExtractor<cart> extrac = new ResultSetExtractor<cart>() {
            public cart extractData(ResultSet rs) {
                try {
                    if (rs.next()) {
                        cart c = new cart();
                        c.setName(rs.getString("name"));
                        c.setQuantity(1);
                        c.setPrice(rs.getInt("price"));
                        return c;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(accountDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        return jt.query(sql, extrac);
    }

    @Override
    public int delete(cart c) {
        String sql = "delete from cart_detail where product_id="+c.getProduct_id()+" and cart_id="+c.getId();
        return jt.update(sql);
    }
    private cart getImg(cart p) {
        String sql = "select top 1 * from product_img where product_id=" + p.getProduct_id();
        ArrayList<String> photo = new ArrayList<>();
        RowMapper<List<String>> rowMap = new RowMapper<List<String>>() {
            public List<String> mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        photo.add(rs.getString("img"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(billDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        jt.query(sql, rowMap);
        if (photo.isEmpty()) {
            photo.add("G3default.png");
        }
        p.setPhotos(photo);
        return p;
    }
    @Override
    public List<cart> list(int a_id) {
        String sql = "select * from cart_detail inner join cart_account on cart_account.cart_id = cart_detail.cart_id inner join product on product.product_id=cart_detail.product_id where account_id = "+a_id;
        RowMapper<cart> rowMap = new RowMapper<cart>() {
            public cart mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs != null)
                    {
                        cart c = new cart();
                        c.setId(rs.getInt("cart_id"));
                        c.setProduct_id(rs.getInt("product_id"));
                        c.setQuantity(rs.getInt("quantity"));
                        c.setName(rs.getString("name"));
                        c.setPrice(rs.getInt("price"));
                        c.setGenre(rs.getString("genre"));
                        c = getImg(c);
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

    @Override
    public Integer getCountCart(int id) {
        String sql = "select SUM(quantity) as number from cart_detail inner join cart_account on cart_detail.cart_id = cart_account.cart_id where account_id = "+id;
        Integer temp = 0;
        try{
            temp = (int) jt.queryForObject(sql, new Object[] {},int.class);
        }catch(Exception e){
            
        }
        return temp;
    }
}
