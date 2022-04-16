/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class accountDAOImp implements accountDAO{
    private JdbcTemplate jt;

    public accountDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }
    
    public accountDAOImp(){
        
    }

    @Override
    public int save(account a) {
        String sql = "insert into account(fullname, username, password, date_of_bird, phone, email, role) values(?,?,HASHBYTES('SHA1','"+a.getPass()+"'),?,?,?,?)";
        return jt.update(sql, a.getFname(), a.getUname(), a.getDate_of_bird(), a.getPhone(), a.getEmail(), a.getRole());
    }
    @Override
    public int register(account a) {
        System.out.println("======================= JOIN REGIS SQL");
        System.out.println(a.getFname() +' '+a.getUname()+' '+a.getPass()+' '+a.getRole());
        String sql = "insert into account(fullname, username, password, role) values(?,?,HASHBYTES('SHA1','"+a.getPass()+"'),?)";
        return jt.update(sql, a.getFname(), a.getUname(), a.getRole());
    }
    @Override
    public boolean getName(String name) {
        String sql = "select username from account where username='"+name+"'";
        try{
            jt.queryForObject(sql, new Object[]{}, String.class);
            return true;
        }catch(Exception e){
            return false;
        }
    }
//    public String checkEmptyPass(String p, int id){
//        try{
//        String sql = "select password from account where password="+p+" && account_id="+id;
//        String temp = (String) jt.queryForObject(sql, new Object[] {},String.class);
//        return temp;
//        }catch(Exception e){
//            
//        }
//        return null;
//    }
    @Override
    public int update(account a, int id) {
        if(!a.getPass().isEmpty()){
            String sql="update account set fullname=?, username=?, password=HASHBYTES('SHA1','"+a.getPass()+"'), date_of_bird=?, phone=?, email=?, role=? where account_id=?";
            return jt.update(sql, a.getFname(), a.getUname(), a.getDate_of_bird(), a.getPhone(), a.getEmail(), a.getRole(), id);
        }
        else{
            String sql="update account set fullname=?, username=?, date_of_bird=?, phone=?, email=?, role=? where account_id=?";
            return jt.update(sql, a.getFname(), a.getUname(), a.getDate_of_bird(), a.getPhone(), a.getEmail(), a.getRole(), id);
        }
    }

    @Override
    public account get(int id) {
        String sql = "select * from account where account_id="+id;
        ResultSetExtractor<account> extrac = new ResultSetExtractor<account>(){
            public account extractData(ResultSet rs){
                try {
                    if(rs.next())
                    {
                        account a = new account();
                        a.setId(rs.getInt("account_id"));
                        a.setFname(rs.getString("fullname"));
                        a.setUname(rs.getString("username"));
                        a.setPass(rs.getString("password"));
                        a.setDate_of_bird(rs.getString("date_of_bird"));
                        a.setPhone(rs.getLong("phone"));
                        a.setEmail(rs.getString("email"));
                        a.setRole(rs.getString("role"));
                        return a;
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
        String sql = "delete from account where account_id="+id;
        return jt.update(sql);
    }

    @Override
    public List<account> list() {
        String sql = "select * from account";
        RowMapper<account> rowMap = new RowMapper<account>() {
            public account mapRow(ResultSet rs, int row) throws SQLException {
                account a = new account();
                a.setId(rs.getInt("account_id"));
                a.setFname(rs.getString("fullname"));
                a.setUname(rs.getString("username"));
                a.setPass(rs.getString("password"));
                a.setDate_of_bird(rs.getString("date_of_bird"));
                a.setPhone(rs.getLong("phone"));
                a.setEmail(rs.getString("email"));
                a.setRole(rs.getString("role"));
                return a;
            }
        };
        return jt.query(sql, rowMap);
    }

    @Override
    public List<account> search(String name) {
        String sql = "select * from account where fullname like '%"+name+"%'";
        RowMapper<account> rowMap = new RowMapper<account>() {
            public account mapRow(ResultSet rs, int row) throws SQLException {
                account a = new account();
                a.setId(rs.getInt("account_id"));
                a.setFname(rs.getString("fullname"));
                a.setUname(rs.getString("username"));
                a.setPass(rs.getString("password"));
                a.setDate_of_bird(rs.getString("date_of_bird"));
                a.setPhone(rs.getLong("phone"));
                a.setEmail(rs.getString("email"));
                a.setRole(rs.getString("role"));
                return a;
            }
        };
        return jt.query(sql,rowMap);
    }
    
    @Override
    public account login(account ac) {
        String sql = "select * from account where username='"+ac.getUname()+"' and password=HASHBYTES('SHA1','"+ac.getPass()+"')";
        ResultSetExtractor<account> extrac = new ResultSetExtractor<account>() {
            public account extractData(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    account a = new account();
                    a.setId(rs.getInt("account_id"));
                    a.setFname(rs.getString("fullname"));
                    a.setUname(rs.getString("username"));
                    a.setPass(rs.getString("password"));
                    a.setDate_of_bird(rs.getString("date_of_bird"));
                    a.setPhone(rs.getLong("phone"));
                    a.setEmail(rs.getString("email"));
                    a.setRole(rs.getString("role"));
                    return a;
                }
                return null;
            }
        };
        return jt.query(sql, extrac);
    }
    @Override
    public Integer getCountAccountPage() {
        String sql = "SELECT COUNT(account_id) from account";
        try {
            return jt.queryForObject(sql, new Object[]{}, int.class);
        } catch (Exception e) {
            return 1;
        }
    }
    @Override
    public List<account> getAccountByPage(int pageid, int total) {
        String sql = "SELECT * FROM account ORDER BY account_id DESC offset "+(pageid-1)+" rows fetch next "+total+" rows only";
        RowMapper<account> rowMap = new RowMapper<account>() {
            public account mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        account a = new account();
                        a.setId(rs.getInt("account_id"));
                        a.setFname(rs.getString("fullname"));
                        a.setUname(rs.getString("username"));
                        a.setPass(rs.getString("password"));
                        a.setDate_of_bird(rs.getString("date_of_bird"));
                        a.setPhone(rs.getLong("phone"));
                        a.setEmail(rs.getString("email"));
                        a.setRole(rs.getString("role"));
                        return a;
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
    public Integer getCountSearchAccountPage(String name) {
        String sql = "SELECT COUNT(account_id) from account where fullname like '%"+name+"%'";
        try {
            return jt.queryForObject(sql, new Object[]{}, int.class);
        } catch (Exception e) {
            return 1;
        }
    }
    @Override
    public List<account> searchAccount(String name, Integer pageid, int total) {
        String sql = "SELECT * FROM account where fullname like '%"+name+"%' ORDER BY account_id DESC offset "+(pageid-1)+" rows fetch next "+total+" rows only";
        RowMapper<account> rowMap = new RowMapper<account>() {
            public account mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        account a = new account();
                        a.setId(rs.getInt("account_id"));
                        a.setFname(rs.getString("fullname"));
                        a.setUname(rs.getString("username"));
                        a.setPass(rs.getString("password"));
                        a.setDate_of_bird(rs.getString("date_of_bird"));
                        a.setPhone(rs.getLong("phone"));
                        a.setEmail(rs.getString("email"));
                        a.setRole(rs.getString("role"));
                        return a;
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
