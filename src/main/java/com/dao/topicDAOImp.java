/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.topic;
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
public class topicDAOImp implements topicDAO{
    private JdbcTemplate jt;
    
    public topicDAOImp(DataSource ds){
        this.jt = new JdbcTemplate(ds);
    }
    
    public topicDAOImp(){
        
    }
    @Override
    public int save(topic to) {
            String sql = "insert into topic(topic_name, topic_des, c_id, u_id) values(?,?,?,?)";
            jt.update(sql, to.getTopic_name(), to.getTopic_des(), to.getC_id(), to.getU_id());
        return 1;
    }

    @Override
    public int update(topic to) {
            String sql = "update topic set topic_name=?, topic_des=?, c_id=?, u_id=? where topic_id=?";
            jt.update(sql, to.getTopic_name(), to.getTopic_des(), to.getC_id(), to.getU_id(), to.getTopic_id());
        return 1;
    }

    @Override
    public topic get(final int id) {
        String sql = "select * from topic where topic_id=" + id;
        ResultSetExtractor<topic> extrac = new ResultSetExtractor<topic>() {
            public topic extractData(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    String name = rs.getString("topic_name");
                    String des = rs.getString("topic_des");
                    Integer c_id = rs.getInt("c_id");
                    String c_name="";
                    if(c_id != null && c_id != 0){
                        c_name = selectCourse(c_id);
                    }
                    String u_name="";
                    Integer u_id = rs.getInt("u_id");
                    if(u_id != null && u_id != 0){
                        u_name = selectUser(u_id);
                    }
                        return new topic(id, name, des, c_id, c_name, u_id, u_name);
                }
                return null;
            }
        };
        return jt.query(sql, extrac);
    }

    @Override
    public int delete(int id) {
        String sql = "delete from topic where topic_id="+id;
        return jt.update(sql);
    }
    public String selectCourse(int id){
        String sql2 = "select c_name from course where c_id="+id;
        String temp = (String) jt.queryForObject(sql2, new Object[] {},String.class);
        return temp;
    }
    public String selectUser(int id){
        String sql3 = "select u_fullname from userDetail where u_id="+id;
        String temp = (String) jt.queryForObject(sql3, new Object[] {},String.class);
        return temp;
    }
    @Override
    public List<topic> list() {
        String sql = "select * from topic";
        RowMapper<topic> rowMap = new RowMapper<topic>() {
            public topic mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt("topic_id");
                String name = rs.getString("topic_name");
                    String des = rs.getString("topic_des");
                    Integer c_id = rs.getInt("c_id");
                    String c_name="";
                    if(c_id != null && c_id != 0){
                        c_name = selectCourse(c_id);
                    }
                    String u_name="";
                    Integer u_id = rs.getInt("u_id");
                    if(u_id != null && u_id != 0){
                        u_name = selectUser(u_id);
                    }
                        return new topic(id, name, des, c_id, c_name, u_id, u_name);
            }
        };
        return jt.query(sql, rowMap);
    }
    @Override
    public List<topic> search(String name) {
        String sql = "select * from topic where topic_name like '%"+name+"%'";
        RowMapper<topic> rowMap = new RowMapper<topic>() {
            public topic mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt("topic_id");
                String name = rs.getString("topic_name");
                    String des = rs.getString("topic_des");
                    Integer c_id = rs.getInt("c_id");
                    String c_name="";
                    if(c_id != null && c_id != 0){
                        c_name = selectCourse(c_id);
                    }
                    String u_name="";
                    Integer u_id = rs.getInt("u_id");
                    if(u_id != null && u_id != 0){
                        u_name = selectUser(u_id);
                    }
                        return new topic(id, name, des, c_id, c_name, u_id, u_name);
            }
        };
        return jt.query(sql,rowMap);
    }
    
    @Override
    public List<topic> listTopic2(final int id2){
        String sql = "select * from topic where c_id="+id2;
        RowMapper<topic> rowMap = new RowMapper<topic>() {
            public topic mapRow(ResultSet rs, int row) throws SQLException {
                int id = rs.getInt("topic_id");
                String name = rs.getString("topic_name");
                String des = rs.getString("topic_des");
                String c_name = "";
                c_name = selectCourse(id2);
                String u_name = "";
                Integer u_id = rs.getInt("u_id");
                if (u_id != null && u_id != 0) {
                    u_name = selectUser(u_id);
                }
                return new topic(id, name, des, id2, c_name, u_id, u_name);
            }
        };
        return jt.query(sql, rowMap);
    }
    
    @Override
    public List<topic> list4Teacher(int id) {
        String sql = "select distinct category.category_name, category.category_id, category.category_des from "
                + "topic inner join course on course.c_id = topic.c_id inner join category on "
                + "course.category_id = category.category_id where u_id = "+id;
        RowMapper<topic> rowMap = new RowMapper<topic>() {
            public topic mapRow(ResultSet rs, int row) throws SQLException {
                topic to = new topic();
                to.setCategory_id(rs.getInt("category_id"));
                to.setCategory_name(rs.getString("category_name"));
                to.setCategory_des(rs.getString("category_des"));
                return to;
            }
        };
        return jt.query(sql, rowMap);
    }
    
    @Override
    public List<topic> list4Course(int id, int cate_id) {
        String sql = "select distinct course.c_name, course.c_id, course.c_des from topic inner join "
                + "course on course.c_id = topic.c_id where u_id = "+id+" and category_id = "+cate_id;
        RowMapper<topic> rowMap = new RowMapper<topic>() {
            public topic mapRow(ResultSet rs, int row) throws SQLException {
                topic to = new topic();
                to.setC_id(rs.getInt("c_id"));
                to.setC_name(rs.getString("c_name"));
                to.setC_des(rs.getString("c_des"));
                return to;
            }
        };
        return jt.query(sql, rowMap);
    }
    
    @Override
    public List<topic> list4Topic(int id, int c_id) {
        String sql = "select * from topic where u_id = "+id+" and c_id = "+c_id;
        RowMapper<topic> rowMap = new RowMapper<topic>() {
            public topic mapRow(ResultSet rs, int row) throws SQLException {
                topic to = new topic();
                to.setTopic_id(rs.getInt("topic_id"));
                to.setTopic_name(rs.getString("topic_name"));
                to.setTopic_des(rs.getString("topic_des"));
                return to;
            }
        };
        return jt.query(sql, rowMap);
    }
    
    @Override
    public List<topic> searchById(String name, int id){
        String sql = "select distinct category.category_name, category.category_id, category.category_des from "
                + "topic inner join course on course.c_id = topic.c_id inner join category on "
                + "course.category_id = category.category_id where u_id = "+id+" and category_name like '%"+name+"%'";
        RowMapper<topic> rowMap = new RowMapper<topic>() {
            public topic mapRow(ResultSet rs, int row) throws SQLException {
                topic to = new topic();
                to.setCategory_id(rs.getInt("category_id"));
                to.setCategory_name(rs.getString("category_name"));
                to.setCategory_des(rs.getString("category_des"));
                return to;
            }
        };
        return jt.query(sql, rowMap);
    }
}
