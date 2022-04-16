/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.payment;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class paymentDAOImp implements paymentDAO{
    private JdbcTemplate jt;

    public paymentDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }
    public paymentDAOImp(){
        
    }

    @Override
    public int save(payment p, int a_id) {
        String sql = "insert into payment(account_id, fullname, bank, card_number, email, country, pay_address, expYY, expMM) values(?,?,?,?,?,?,?,?,?)";
        return jt.update(sql, a_id, p.getFname_card(), p.getBank_name(), p.getCard_number(), p.getEmail(), p.getCountry(), p.getAddress(), p.getYY(), p.getMM());
    }

    @Override
    public int update(payment p, int id) {
        String sql = "update payment set bank_name=?, car_number=?, fullname=?";
        return jt.update(sql, p.getBank_name(), p.getCard_number(), p.getFname());
    }

    @Override
    public payment get(int id) {
        String sql = "select * from payment where payment_id="+id;
        ResultSetExtractor<payment> extrac = new ResultSetExtractor<payment>(){
            public payment extractData(ResultSet rs){
                try {
                    if(rs.next())
                    {
                        payment p = new payment();
                        p.setId(rs.getInt("payment_id"));
                        p.setBank_name(rs.getString("bank_name"));
                        p.setCard_number(rs.getBigDecimal("card_number"));
                        p.setFname(rs.getString("fullname"));
                        return p;
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
    public int delete(int id) {
        String sql = "delete from payment where payment_id="+id;
        return jt.update(sql);
    }

    @Override
    public List<payment> list(int a_id) {
        String sql = "select * from payment where account_id="+a_id;
        RowMapper<payment> rowMap = new RowMapper<payment>() {
            public payment mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs != null)
                    {
                        payment p = new payment();
                        p.setId(rs.getInt("payment_id"));
                        p.setBank_name(rs.getString("bank"));
                        p.setCard_number(rs.getBigDecimal("card_number"));
                        p.setFname(rs.getString("fullname"));
                        p.setFname_card(rs.getString("fullname"));
                        p.setAccount_id(a_id);
                        p.setEmail(rs.getString("email"));
                        p.setCountry(rs.getString("country"));
                        p.setAddress(rs.getString("pay_address"));
                        p.setMM(rs.getInt("expMM"));
                        p.setYY(rs.getInt("expYY"));
                        return p;
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
