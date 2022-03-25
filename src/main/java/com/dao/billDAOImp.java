/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.account;
import com.model.bill;
import com.model.bill_product;
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
public class billDAOImp implements billDAO{
    private JdbcTemplate jt;

    public billDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }
    public billDAOImp(){
        
    }
    //from save method
    public int selectBillID(int a_id, String date){
        String sql = "select bill_id from bill where account_id="+a_id+" && date="+date;
        int temp = (int) jt.queryForObject(sql, new Object[] {},int.class);
        return temp;
    }
    //from save method
    public int selectCartID(int a_id){
        String sql = "select cart_id from cart_account where account_id="+a_id;
        int temp = (int) jt.queryForObject(sql, new Object[] {},int.class);
        return temp;
    }
    //from save method
    public void selectproduct(final cart c, final int id){
        String sql = "select name, price from product where product_id="+c.getProduct_id();
        ResultSetExtractor<cart> extrac = new ResultSetExtractor<cart>(){
            public cart extractData(ResultSet rs){
                try {
                    if(rs.next())
                    {
                        insertBillProduct(rs.getInt("price"), rs.getString("name"), id, c.getQuantity());
                        return null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(accountDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        jt.query(sql, extrac);
    }
    //from selectproduct method
    public int insertBillProduct(int price, String name, int id, int quantity) {
            String sql = "insert into bill_product(bill_id, product_name, quantity, price) values(?,?,?,?)";
            jt.update(sql, id, name, quantity, price);
        return 1;
    }
    public int deleteCart(int id) {
        String sql = "delete from cart_detail where cart_id="+id;
        jt.update(sql);
        String sql2 = "delete from cart_account where cart_id="+id;
        return jt.update(sql2);
    }
    @Override
    public int save(final bill b, final account a) {
        String sql = "insert into bill(account_id, total_to_paid, pay_type, pay_status, status, date) values(?,?,?,?,?,?)";
        jt.update(sql, a.getId(), b.getTotal(), b.getPay_type(), b.getPay_satus(), b.getStatus(), b.getDate());
        
        String sql2 = "select * from cart_detail where cart_id="+selectCartID(a.getId());
        ResultSetExtractor<cart> extrac = new ResultSetExtractor<cart>(){
            public cart extractData(ResultSet rs){
                try {
                    cart c = new cart();
                    if(rs.next())
                    {
                        c.setProduct_id(rs.getInt("product_id"));
                        c.setQuantity(rs.getInt("quantity"));
                        selectproduct(c, selectBillID(a.getId(), b.getDate()));
                        return null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(accountDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        jt.query(sql2, extrac);
        
        deleteCart(selectCartID(a.getId()));
        
        return 0;
    }

    @Override
    public int update(bill b) {
        String sql="update bill set pay_status=?, status=? where bill_id=?";
        return jt.update(sql, b.getPay_satus(), b.getStatus(), b.getBill_id());
    }
    //from list/search method, this for select information for bill_product table
    private bill listForBillProduct(final bill b){
        String sql = "select * from bill_product where bill_id="+b.getBill_id();
        ResultSetExtractor<bill> extrac = new ResultSetExtractor<bill>(){
            public bill extractData(ResultSet rs){
                try {
                    ArrayList<String> name = new ArrayList<String>();
                    ArrayList<Integer> quantity = new ArrayList<Integer>();
                    ArrayList<Integer> price = new ArrayList<Integer>();
                    if(rs.next())
                    {
                        name.add(rs.getString("product_name"));
                        quantity.add(rs.getInt("quantity"));
                        price.add(rs.getInt("price"));
                    }
                    b.setProduct_name(name);
                    b.setPrice(price);
                    b.setQuantity(quantity);
                    return b;
                } catch (SQLException ex) {
                    Logger.getLogger(billDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return b;
            }
        };
        return jt.query(sql, extrac);
    }
//    @Override
//    public bill get(int id) {
//        String sql = "select * from bill where account_id="+id;
//        ResultSetExtractor<bill> extrac = new ResultSetExtractor<bill>(){
//            public bill extractData(ResultSet rs){
//                try {
//                    if(rs.next())
//                    {
//                        bill b = new bill();
//                        b.set
//                        b = listForBillProduct(b);
//                        return b;
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(billDAOImp.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                return null;
//            }
//        };
//        return jt.query(sql, extrac);
//    }

    @Override
    public List<bill> list() {
        String sql = "select * from bill";
        RowMapper<bill> rowMap = new RowMapper<bill>() {
            public bill mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs.next())
                    {
                        bill b = new bill();
                        b.setBill_id(rs.getInt("bill_id"));
                        b.setDate(rs.getString("date"));
                        b.setPay_satus(rs.getString("pay_status"));
                        b.setPay_type(rs.getString("pay_type"));
                        b.setStatus(rs.getString("status"));
                        b.setTotal(rs.getInt("total"));
                        b = listForBillProduct(b);
                        return b;
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
    public int delete(int id) {
        String sql = "delete from bill_product where bill_id="+id;
        jt.update(sql);
        String sql2 = "delete from bill where bill_id="+id;
        return jt.update(sql2);
    }
    
    @Override
    public List<bill> search(int id) {
        String sql = "select * from bill where bill_id="+id;
        RowMapper<bill> rowMap = new RowMapper<bill>() {
            public bill mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs.next())
                    {
                        bill b = new bill();
                        b.setBill_id(rs.getInt("bill_id"));
                        b.setDate(rs.getString("date"));
                        b.setPay_satus(rs.getString("pay_status"));
                        b.setPay_type(rs.getString("pay_type"));
                        b.setStatus(rs.getString("status"));
                        b.setTotal(rs.getInt("total"));
                        if(b.getBill_id()==null){
                            return null;
                        }
                        else{
                            b = listForBillProduct(b);
                        }
                        return b;
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
