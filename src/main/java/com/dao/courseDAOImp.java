/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.course;
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
public class courseDAOImp implements courseDAO{
    private JdbcTemplate jt;
    
    public courseDAOImp(DataSource ds){
        this.jt = new JdbcTemplate(ds);
    }
    
    public courseDAOImp(){
    
    }
    
    @Override
    public int save(course cou) {
        String sql = "insert into course(c_name, c_des, category_id) values(?,?,?)";
        return jt.update(sql, cou.getC_name(), cou.getC_des(), cou.getCategory_id());
    }

    @Override
    public int update(course cou) {
        String sql="update course set c_name=?, c_des=?, category_id=? where c_id=?";
        return jt.update(sql, cou.getC_name(), cou.getC_des(), cou.getCategory_id(), cou.getC_id());
    }

    @Override
    public int delete(int id) {
        String sql = "delete from course where c_id="+id;
        return jt.update(sql);
    }

    @Override
    public course get(final int id) {
        String sql = "select * from course where c_id=" + id;
        ResultSetExtractor<course> extrac = new ResultSetExtractor<course>() {
            public course extractData(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    String name = rs.getString("c_name");
                    String des = rs.getString("c_des");
                    Integer id2 = rs.getInt("category_id");

                    return new course(id, name, des, id2);
                }
                return null;
            }
        };
        return jt.query(sql, extrac);
    }

    @Override
    public List<course> list() {
        String sql = "select * from course inner join category on course.category_id = category.category_id";
        RowMapper<course> rowMap = new RowMapper<course>() {
            public course mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt("category_id");
                String name = rs.getString("c_name");
                String des = rs.getString("c_des");
                int c_id = rs.getInt("c_id");
                String nameCate = rs.getString("category_name");
                return new course(c_id, name, des, id, nameCate);
            }
        };
        return jt.query(sql, rowMap);
    }
    @Override
    public List<course> search(String name) {
        String sql = "select * from course inner join category on course.category_id = category.category_id where c_name like '%"+name+"%'";
        RowMapper<course> rowMap = new RowMapper<course>() {
            public course mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt("category_id");
                String name = rs.getString("c_name");
                String des = rs.getString("c_des");
                int c_id = rs.getInt("c_id");
                String nameCate = rs.getString("category_name");
                return new course(c_id, name, des, id, nameCate);
            }
        };
        return jt.query(sql, rowMap);
    }
    
    @Override
    public List<course> list4Student(int id) {
        String sql = "select * from user_course inner join course on course.c_id = user_course.c_id where u_id ="+id;
        RowMapper<course> rowMap = new RowMapper<course>() {
            public course mapRow(ResultSet rs, int row) throws SQLException {
                Integer id = rs.getInt("category_id");
                String name = rs.getString("c_name");
                String des = rs.getString("c_des");
                Integer c_id = rs.getInt("c_id");
                return new course(c_id, name, des, id);
            }
        };
        return jt.query(sql, rowMap);
    }
    
    @Override
    public List<course> searchById(String name, int id) {
        String sql = "select * from user_course inner join course on course.c_id = user_course.c_id where u_id = "+id+" and c_name like '%"+name+"%'";
        RowMapper<course> rowMap = new RowMapper<course>() {
            public course mapRow(ResultSet rs, int row) throws SQLException {
                Integer id = rs.getInt("category_id");
                String name = rs.getString("c_name");
                String des = rs.getString("c_des");
                Integer c_id = rs.getInt("c_id");
                return new course(c_id, name, des, id);
            }
        };
        return jt.query(sql, rowMap);
    }
}
