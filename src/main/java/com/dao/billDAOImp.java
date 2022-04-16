/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.bill;
import com.model.bill_product;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public int selectBillID(int a_id, Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(date);
        String sql = "select bill_id from bill where account_id="+a_id+" and date='"+format+"'";
        try{
            int temp = (int) jt.queryForObject(sql, new Object[] {},int.class);
            return temp;
        }catch(Exception e){
            
        }
        return 0;
    }
    //from save method
    public int selectCartID(int a_id){
        String sql = "select cart_id from cart_account where account_id="+a_id;
        try{
            int temp = (int) jt.queryForObject(sql, new Object[] {},int.class);
            return temp;
        }catch(Exception e){
            
        }
        return 0;
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
    public int save(final bill b, int a_id) {
        String sql = "insert into bill(account_id, total_to_paid, pay_type, pay_status, status, date,"
                + "fullname, email, country, b_address, card_number, name_on_card) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        jt.update(sql, a_id, b.getTotal(), b.getPay_type(), b.getPay_status(), b.getStatus(), b.getDate(), 
                b.getFname(), b.getEmail(), b.getCountry(), b.getAddress(), b.getCard_number(), b.getFname_card());
        
        String sql2 = "select * from cart_detail inner join product on cart_detail.product_id = product.product_id where cart_id = "+selectCartID(a_id);
        RowMapper<bill> rowMap = new RowMapper<bill>(){
            public bill mapRow(ResultSet rs, int row) throws SQLException{
                try {
                    if(rs != null)
                    {
                        insertBillProduct(rs.getInt("price"), rs.getString("name"), selectBillID(a_id, b.getDate()), rs.getInt("quantity"));
                        return null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(accountDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        jt.query(sql2, rowMap);
        
        deleteCart(selectCartID(a_id));
        
        return 0;
    }
    @Override
    public int save_cash(final bill b, int a_id) {
        String sql = "insert into bill(account_id, total_to_paid, pay_type, pay_status, status, date,"
                + "fullname, email, country, b_address) values(?,?,?,?,?,?,?,?,?,?)";
        jt.update(sql, a_id, b.getTotal(), b.getPay_type(), b.getPay_status(), b.getStatus(), b.getDate(), 
                b.getFname(), b.getEmail(), b.getCountry(), b.getAddress());
        
        String sql2 = "select * from cart_detail inner join product on cart_detail.product_id = product.product_id where cart_id = "+selectCartID(a_id);
        RowMapper<bill> rowMap = new RowMapper<bill>(){
            public bill mapRow(ResultSet rs, int row) throws SQLException{
                try {
                    if(rs != null)
                    {
                        insertBillProduct(rs.getInt("price"), rs.getString("name"), selectBillID(a_id, b.getDate()), rs.getInt("quantity"));
                        return null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(accountDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        jt.query(sql2, rowMap);
        
        deleteCart(selectCartID(a_id));
        
        return 0;
    }
    @Override
    public int update(bill b) {
        if(b.getStatus().equals("Finish")){
            b.setPay_status("Paid");
        }
        if(b.getPay_status() != null){
            String sql="update bill set pay_status=?, status=? where bill_id=?";
            return jt.update(sql, b.getPay_status(), b.getStatus(), b.getBill_id());
        }else{
            String sql="update bill set status=? where bill_id=?";
            return jt.update(sql, b.getStatus(), b.getBill_id());
        }
    }
    //from list/search method, this for select information for bill_product table
    private bill listForBillProduct(final bill b){
        String sql = "select * from bill_product where bill_id="+b.getBill_id();
        ArrayList<bill_product> list = new ArrayList<>();
        RowMapper<List<bill_product>> rowMap = new RowMapper<List<bill_product>>(){
            public List<bill_product> mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs != null)
                    {
                        bill_product b = new bill_product();
                        b.setProduct_name(rs.getString("product_name"));
                        b.setQuantity(rs.getInt("quantity"));
                        b.setPrice(rs.getInt("price"));
                        list.add(b);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(billDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return null;
            }
        };
        jt.query(sql, rowMap);
        b.setBillProduct(list);
        return b;
    }
    @Override
    public bill get(int id) {
        String sql = "select * from bill where bill_id="+id;
        ResultSetExtractor<bill> extrac = new ResultSetExtractor<bill>() {
            public bill extractData(ResultSet rs) {
                try {
                    if (rs.next()) {
                        bill b = new bill();
                        b.setBill_id(rs.getInt("bill_id"));
                        b.setDate(rs.getDate("date"));
                        b.setPay_status(rs.getString("pay_status"));
                        b.setPay_type(rs.getString("pay_type"));
                        b.setStatus(rs.getString("status"));
                        b.setTotal(rs.getInt("total_to_paid"));
                        b.setFname(rs.getString("fullname"));
                        b.setEmail(rs.getString("email"));
                        b.setCountry(rs.getString("country"));
                        b.setAddress(rs.getString("b_address"));
                        if(rs.getBigDecimal("card_number")!=null){
                            long divi = rs.getBigDecimal("card_number").longValue();
                            divi = divi % 10000;
                            b.setCard_number(new BigDecimal(divi));
                        }
                        
                        b = listForBillProduct(b);
                        return b;
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
    public List<bill> listBill(int a_id) {
        String sql = "select * from bill where account_id="+a_id;
        RowMapper<bill> rowMap = new RowMapper<bill>() {
            public bill mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs != null)
                    {
                        bill b = new bill();
                        b.setBill_id(rs.getInt("bill_id"));
                        b.setDate(rs.getDate("date"));
                        b.setPay_status(rs.getString("pay_status"));
                        b.setPay_type(rs.getString("pay_type"));
                        b.setStatus(rs.getString("status"));
                        b.setTotal(rs.getInt("total_to_paid"));
                        b.setFname(rs.getString("fullname"));
                        b.setEmail(rs.getString("email"));
                        b.setCountry(rs.getString("country"));
                        b.setAddress(rs.getString("b_address"));
//                        b = listForBillProduct(b);
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
                    if(rs != null)
                    {
                        bill b = new bill();
                        b.setBill_id(rs.getInt("bill_id"));
                        b.setDate(rs.getDate("date"));
                        b.setPay_status(rs.getString("pay_status"));
                        b.setPay_type(rs.getString("pay_type"));
                        b.setStatus(rs.getString("status"));
                        b.setTotal(rs.getInt("total_to_paid"));
                        b.setFname(rs.getString("fullname"));
                        b.setEmail(rs.getString("email"));
                        b.setCountry(rs.getString("country"));
                        b.setAddress(rs.getString("b_address"));
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
    public Integer getCountBillPage() {
        String sql = "SELECT COUNT(bill_id) from bill";
        try {
            return jt.queryForObject(sql, new Object[]{}, int.class);
        } catch (Exception e) {
            return 1;
        }
    }
    @Override
    public List<bill> getBillByPage(int pageid, int total) {
        String sql = "SELECT * FROM bill ORDER BY bill_id DESC offset "+(pageid-1)+" rows fetch next "+total+" rows only";
        RowMapper<bill> rowMap = new RowMapper<bill>() {
            public bill mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        bill b = new bill();
                        b.setBill_id(rs.getInt("bill_id"));
                        b.setDate(rs.getDate("date"));
                        b.setPay_status(rs.getString("pay_status"));
                        b.setPay_type(rs.getString("pay_type"));
                        b.setStatus(rs.getString("status"));
                        b.setTotal(rs.getInt("total_to_paid"));
                        b.setFname(rs.getString("fullname"));
                        b.setEmail(rs.getString("email"));
                        b.setCountry(rs.getString("country"));
                        b.setAddress(rs.getString("b_address"));
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
    public List<bill> listWaiting() {
        String sql = "select * from bill where status='Waiting'";
        RowMapper<bill> rowMap = new RowMapper<bill>() {
            public bill mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs != null)
                    {
                        bill b = new bill();
                        b.setBill_id(rs.getInt("bill_id"));
                        b.setDate(rs.getDate("date"));
                        
                        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
                        String date = dateFormat.format(b.getDate());
                        b.setDateString(date);
                        
                        b.setPay_status(rs.getString("pay_status"));
                        b.setPay_type(rs.getString("pay_type"));
                        b.setStatus(rs.getString("status"));
                        b.setTotal(rs.getInt("total_to_paid"));
                        b.setFname(rs.getString("fullname"));
                        b.setEmail(rs.getString("email"));
                        b.setCountry(rs.getString("country"));
                        b.setAddress(rs.getString("b_address"));
//                        b = listForBillProduct(b);
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
    public List<bill> listProcess() {
        String sql = "select * from bill where status='In processing'";
        RowMapper<bill> rowMap = new RowMapper<bill>() {
            public bill mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs != null)
                    {
                        bill b = new bill();
                        b.setBill_id(rs.getInt("bill_id"));
                        b.setDate(rs.getDate("date"));
                        
                        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
                        String date = dateFormat.format(b.getDate());
                        b.setDateString(date);
                        
                        b.setPay_status(rs.getString("pay_status"));
                        b.setPay_type(rs.getString("pay_type"));
                        b.setStatus(rs.getString("status"));
                        b.setTotal(rs.getInt("total_to_paid"));
                        b.setFname(rs.getString("fullname"));
                        b.setEmail(rs.getString("email"));
                        b.setCountry(rs.getString("country"));
                        b.setAddress(rs.getString("b_address"));
//                        b = listForBillProduct(b);
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
    public List<bill> listDeliver() {
        String sql = "select * from bill where status='Delivering'";
        RowMapper<bill> rowMap = new RowMapper<bill>() {
            public bill mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs != null)
                    {
                        bill b = new bill();
                        b.setBill_id(rs.getInt("bill_id"));
                        b.setDate(rs.getDate("date"));
                        
                        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
                        String date = dateFormat.format(b.getDate());
                        b.setDateString(date);
                        
                        b.setPay_status(rs.getString("pay_status"));
                        b.setPay_type(rs.getString("pay_type"));
                        b.setStatus(rs.getString("status"));
                        b.setTotal(rs.getInt("total_to_paid"));
                        b.setFname(rs.getString("fullname"));
                        b.setEmail(rs.getString("email"));
                        b.setCountry(rs.getString("country"));
                        b.setAddress(rs.getString("b_address"));
//                        b = listForBillProduct(b);
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
    public List<bill> listFinish() {
        String sql = "select * from bill where status='Finish'";
        RowMapper<bill> rowMap = new RowMapper<bill>() {
            public bill mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs != null)
                    {
                        bill b = new bill();
                        b.setBill_id(rs.getInt("bill_id"));
                        b.setDate(rs.getDate("date"));
                        
                        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
                        String date = dateFormat.format(b.getDate());
                        b.setDateString(date);
                        
                        b.setPay_status(rs.getString("pay_status"));
                        b.setPay_type(rs.getString("pay_type"));
                        b.setStatus(rs.getString("status"));
                        b.setTotal(rs.getInt("total_to_paid"));
                        b.setFname(rs.getString("fullname"));
                        b.setEmail(rs.getString("email"));
                        b.setCountry(rs.getString("country"));
                        b.setAddress(rs.getString("b_address"));
//                        b = listForBillProduct(b);
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
