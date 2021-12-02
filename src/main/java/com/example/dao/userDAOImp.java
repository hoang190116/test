/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.model.user;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class userDAOImp implements userDAO{
    
    private JdbcTemplate jt;
    
    public userDAOImp(DataSource ds){
        this.jt = new JdbcTemplate(ds);
    }
    
    public userDAOImp(){
        
    }
    
    @Override
    public int save(user u) {
        String sql = "insert into userDetail(u_username, u_pass, u_fullname, u_phone, u_date_of_bird, u_address, u_detail, role_name) values(?,?,?,?,?,?,?,?)";
        return jt.update(sql, u.getU_name(), u.getU_pass(), u.getU_fullname(), u.getU_phone(), u.getU_date_of_bird(),
        u.getU_address(), u.getU_detail(), u.getRole_name());
    }
    
    @Override
    public int save2(int id, int c_id) {
        String sql = "insert into user_course(u_id, c_id) values(?,?)";
        return jt.update(sql, id, c_id);
    }
    
    @Override
    public int update(user u) {
        String sql = "update userDetail set u_username=?, u_pass=?, u_fullname=?, u_phone=?, u_date_of_bird=?, u_address=?,"
                + " u_detail=?, role_name=? where u_id=?";
        return jt.update(sql, u.getU_name(), u.getU_pass(), u.getU_fullname(), u.getU_phone(), u.getU_date_of_bird(),
        u.getU_address(), u.getU_detail(), u.getRole_name(), u.getU_id());
    }
    
    @Override
    public int update2(user u) {
        String sql = "update userDetail set u_fullname=?, u_phone=?, u_date_of_bird=?, u_address=?"
                + " where u_id=?";
        return jt.update(sql, u.getU_fullname(), u.getU_phone(), u.getU_date_of_bird(),
        u.getU_address(), u.getU_id());
    }

    @Override
    public user get(final int id) {
        String sql = "select * from userDetail where u_id=" + id;
        ResultSetExtractor<user> extrac = new ResultSetExtractor<user>() {
            public user extractData(ResultSet rs) throws SQLException {
                if (rs.next()) {
                String user = rs.getString(2);
                String pass = rs.getString(3);
                String name = rs.getString(4);
                int phone = rs.getInt(5);
                Date date = rs.getDate(6);
                String addr = rs.getString(7);
                String detail = rs.getString(8);
                String role = rs.getString(9);
                return new user(id,user,pass,name,phone,date,addr,detail,role);
                }
                return null;
            }
        };
        return jt.query(sql, extrac);
    }
    
    @Override
    public user login(user u) {
        String sql = "select * from userDetail where u_username=? and u_pass=?";
        ResultSetExtractor<user> extrac = new ResultSetExtractor<user>() {
            public user extractData(ResultSet rs) throws SQLException {
                if (rs.next()) {
                int id = rs.getInt("u_id");
                String user = rs.getString("u_username");
                String pass = rs.getString("u_pass");
                String name = rs.getString("u_fullname");
                int phone = rs.getInt("u_phone");
                Date date = rs.getDate("u_date_of_bird");
                String addr = rs.getString("u_address");
                String detail = rs.getString("u_detail");
                String role = rs.getString("role_name");
                return new user(id,user,pass,name,phone,date,addr,detail,role);
                }
                return null;
            }
        };
        return jt.query(sql, extrac, u.getU_name(), u.getU_pass());
    }

    @Override
    public int delete(int id) {
        String sql = "delete from userDetail where u_id="+id;
        return jt.update(sql);
    }
    
    @Override
    public int delete2(int id, int c_id) {
        String sql = "delete from user_course where u_id="+id+" and c_id="+c_id;
        return jt.update(sql);
    }
    
    @Override
    public List<user> list() {
        String sql = "select * from userDetail where role_name like 'Admin' or role_name like 'Staff' or role_name like 'Teacher'";
        RowMapper<user> rowMap = new RowMapper<user>() {
            public user mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt(1);
                String user = rs.getString(2);
                String pass = rs.getString(3);
                String name = rs.getString(4);
                int phone = rs.getInt(5);
                Date date = rs.getDate(6);
                String addr = rs.getString(7);
                String detail = rs.getString(8);
                String role = rs.getString(9);
                return new user(id,user,pass,name,phone,date,addr,detail,role);
            }
        };
        return jt.query(sql, rowMap);
    }

    @Override
    public List<user> search(String name) {
        String sql = "select * from userDetail where u_fullname like '%"+name+"%' AND role_name like 'Admin' or role_name like 'Staff' or role_name like 'Teacher'";
        RowMapper<user> rowMap = new RowMapper<user>() {
            public user mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt(1);
                String user = rs.getString(2);
                String pass = rs.getString(3);
                String name = rs.getString(4);
                int phone = rs.getInt(5);
                Date date = rs.getDate(6);
                String addr = rs.getString(7);
                String detail = rs.getString(8);
                String role = rs.getString(9);
                return new user(id,user,pass,name,phone,date,addr,detail,role);
            }
        };
        return jt.query(sql,rowMap);
    }
    
    
    @Override
    public List<user> list2() {
        String sql = "select * from userDetail where role_name like 'Student'";
        RowMapper<user> rowMap = new RowMapper<user>() {
            public user mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt(1);
                String user = rs.getString(2);
                String pass = rs.getString(3);
                String name = rs.getString(4);
                int phone = rs.getInt(5);
                Date date = rs.getDate(6);
                String addr = rs.getString(7);
                String detail = rs.getString(8);
                String role = rs.getString(9);
                return new user(id,user,pass,name,phone,date,addr,detail,role);
            }
        };
        return jt.query(sql, rowMap);
    }
    
    @Override
    public List<user> search2(String name) {
        String sql = "select * from userDetail where u_fullname like '%"+name+"%' AND role_name like 'Student'";
        RowMapper<user> rowMap = new RowMapper<user>() {
            public user mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt(1);
                String user = rs.getString(2);
                String pass = rs.getString(3);
                String name = rs.getString(4);
                int phone = rs.getInt(5);
                Date date = rs.getDate(6);
                String addr = rs.getString(7);
                String detail = rs.getString(8);
                String role = rs.getString(9);
                return new user(id,user,pass,name,phone,date,addr,detail,role);
            }
        };
        return jt.query(sql,rowMap);
    }
    public int check(int id, int c_id){
        String sql = "select u_id from user_course where u_id="+id+" and c_id="+c_id;
        int a = 0;
        try{
            a = jt.queryForObject(sql, int.class);
            return a;
        }catch(Exception e){
            
        }
        return a;
    }
    
    @Override
    public List<user> list3(final int c_id) {
        String sql = "select * from userDetail where role_name like 'Student'";
        RowMapper<user> rowMap = new RowMapper<user>() {
            public user mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt(1);
                if(check(id, c_id) == 0){
                    String user = "";
                    String pass = "";
                    String name = rs.getString(4);
                    int phone = 0;
                    Date date = rs.getDate(6);
                    String addr = "";
                    String detail = rs.getString(8);
                    String role = "";
                    return new user(id,user,pass,name,phone,date,addr,detail,role);
                }
                return null;
            }
        };
        return jt.query(sql, rowMap);
    }
    
    @Override
    public List<user> list4(int id) {
        String sql = "select * from user_course inner join userDetail on user_course.u_id = userDetail.u_id where c_id="+id;
        RowMapper<user> rowMap = new RowMapper<user>() {
            public user mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt("u_id");
                String user = "";
                String pass = "";
                String name = rs.getString("u_fullname");
                int phone = 0;
                Date date = rs.getDate("u_date_of_bird");
                String addr = "";
                String detail = rs.getString("u_detail");
                String role = "";
                return new user(id, user, pass, name, phone, date, addr, detail, role);
            }
        };
        return jt.query(sql, rowMap);
    }
    
    @Override
    public List<user> search3(String name, final int c_id) {
        String sql = "select * from userDetail where u_fullname like '%"+name+"%' AND role_name like 'Student'";
        RowMapper<user> rowMap = new RowMapper<user>() {
            public user mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt(1);
                if(check(id, c_id) == 0){
                    String user = "";
                    String pass = "";
                    String name = rs.getString(4);
                    int phone = 0;
                    Date date = rs.getDate(6);
                    String addr = "";
                    String detail = rs.getString(8);
                    String role = "";
                    return new user(id,user,pass,name,phone,date,addr,detail,role);
                }
                return null;
            }
        };
        return jt.query(sql,rowMap);
    }
    
    @Override
    public List<user> search4(String name, int c_id) {
        String sql = "select * from user_course inner join userDetail on user_course.u_id = userDetail.u_id where c_id = "+c_id+" and u_fullname like '%"+name+"%'";
        RowMapper<user> rowMap = new RowMapper<user>() {
            public user mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt("u_id");
                String user = "";
                String pass = "";
                String name = rs.getString("u_fullname");
                int phone = 0;
                Date date = rs.getDate("u_date_of_bird");
                String addr = "";
                String detail = rs.getString("u_detail");
                String role = "";
                return new user(id,user,pass,name,phone,date,addr,detail,role);
            }
        };
        return jt.query(sql,rowMap);
    }
    
    @Override
    public List<user> listTeacher() {
        String sql = "select * from userDetail where role_name like 'Teacher'";
        RowMapper<user> rowMap = new RowMapper<user>() {
            public user mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt(1);
                String user = rs.getString(2);
                String pass = rs.getString(3);
                String name = rs.getString(4);
                int phone = rs.getInt(5);
                Date date = rs.getDate(6);
                String addr = rs.getString(7);
                String detail = rs.getString(8);
                String role = rs.getString(9);
                return new user(id,user,pass,name,phone,date,addr,detail,role);
            }
        };
        return jt.query(sql, rowMap);
    }
}
