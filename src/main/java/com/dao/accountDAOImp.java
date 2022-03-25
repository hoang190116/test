/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.account;
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
public class accountDAOImp implements accountDAO{
    private JdbcTemplate jt;

    public accountDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }
    
    public accountDAOImp(){
        
    }

    @Override
    public int save(account a) {
        String sql = "insert into account(fullname, username, password=HASHBYTES(SHA1,?), date_of_bird, phone, email, role) values(?,?,?,?,?,?,?)";
        return jt.update(sql, a.getFname(), a.getUname(), a.getPass(), a.getDate_of_bird(), a.getPhone(), a.getEmail(), a.getRole());
    }
    public String checkEmptyPass(String p, int id){
        String sql = "select password from account where password="+p+" && account_id="+id;
        String temp = (String) jt.queryForObject(sql, new Object[] {},String.class);
        return temp;
    }
    @Override
    public int update(account a) {
        if(checkEmptyPass(a.getPass(), a.getId()).isEmpty()){
            String sql="update account set fullname=?, username=?, password=HASHBYTES(SHA1,?), date_of_bird=?, phone=?, email=?, role=? where account_id=?";
            return jt.update(sql, a.getFname(), a.getUname(), a.getPass(), a.getDate_of_bird(), a.getPhone(), a.getEmail(), a.getRole());
        }
        else{
            String sql="update account set fullname=?, username=?, date_of_bird=?, phone=?, email=?, role=? where account_id=?";
            return jt.update(sql, a.getFname(), a.getUname(), a.getDate_of_bird(), a.getPhone(), a.getEmail(), a.getRole());
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
                        a.setFname(rs.getString("fullname"));
                        a.setUname(rs.getString("username"));
                        a.setPass(rs.getString("password"));
                        a.setDate_of_bird(rs.getDate("date_of_bird"));
                        a.setPhone(rs.getInt("phone"));
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
                a.setDate_of_bird(rs.getDate("date_of_bird"));
                a.setPhone(rs.getInt("phone"));
                a.setEmail(rs.getString("email"));
                a.setRole(rs.getString("role"));
                return a;
            }
        };
        return jt.query(sql, rowMap);
    }

    @Override
    public List<account> search(String name) {
        String sql = "select * from account where account_name like '%"+name+"%'";
        RowMapper<account> rowMap = new RowMapper<account>() {
            public account mapRow(ResultSet rs, int row) throws SQLException {
                account a = new account();
                a.setId(rs.getInt("account_id"));
                a.setFname(rs.getString("fullname"));
                a.setUname(rs.getString("username"));
                a.setPass(rs.getString("password"));
                a.setDate_of_bird(rs.getDate("date_of_bird"));
                a.setPhone(rs.getInt("phone"));
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
                    a.setDate_of_bird(rs.getDate("date_of_bird"));
                    a.setPhone(rs.getInt("phone"));
                    a.setEmail(rs.getString("email"));
                    a.setRole(rs.getString("role"));
                    System.out.println(a.getId());
                    return a;
                }
                return null;
            }
        };
        return jt.query(sql, extrac);
    }
}
