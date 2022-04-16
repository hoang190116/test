/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class commentDAOImp implements commentDAO{
    private JdbcTemplate jt;

    public commentDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }
    public commentDAOImp(){
        
    }

    @Override
    public int save(String cmt, int a_id, int p_id, Date date) {
        String sql = "insert into comment values(?,?,?,?)";
        return jt.update(sql, p_id, a_id, cmt, date);
    }

    @Override
    public comment get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int a_id, int p_id) {
        String sql = "delete from comment where account_id="+a_id+" && product_id="+p_id;
        return jt.update(sql);
    }
    private String getFullname(int a_id){
        String sql = "select fullname from account where account_id="+a_id;
        String temp = (String) jt.queryForObject(sql, new Object[] {},String.class);
        return temp;
    }
    @Override
    public List<comment> list(int p_id) {
        String sql = "select product_id, account.account_id, cmt, cmtDate, fullname from comment inner join account on account.account_id=comment.account_id where product_id= "+p_id+" order by cmtDate desc";
        RowMapper<comment> rowMap = new RowMapper<comment>() {
            public comment mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs != null)
                    {
                        comment com = new comment();
                        com.setCommen(rs.getString("cmt"));
                        com.setId(rs.getInt("account_id"));
                        com.setFname(rs.getString("fullname"));
                        com.setDate(rs.getTimestamp("cmtDate"));
                        com.setTimeAgo(relativeDate(com.getDate()));
                        return com;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(billDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return null;
            }
        };
        return jt.query(sql, rowMap);
    }
    public static String relativeDate(Date date){
        try {
            Integer year = Integer.valueOf(new SimpleDateFormat("yyyy").format(date));
            Integer month = Integer.valueOf(new SimpleDateFormat("MM").format(date));
            Integer day = Integer.valueOf(new SimpleDateFormat("dd").format(date));
            Integer hour = Integer.valueOf(new SimpleDateFormat("HH").format(date));
            Integer minute = Integer.valueOf(new SimpleDateFormat("mm").format(date));
            Integer second = Integer.valueOf(new SimpleDateFormat("ss").format(date));
            
            String date2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            Date now = new SimpleDateFormat("yyyyMMddHHmmss").parse(date2);
                        
            Integer yearNow = Integer.valueOf(new SimpleDateFormat("yyyy").format(now));
            Integer monthNow = Integer.valueOf(new SimpleDateFormat("MM").format(now));
            Integer dayNow = Integer.valueOf(new SimpleDateFormat("dd").format(now));
            Integer hourNow = Integer.valueOf(new SimpleDateFormat("HH").format(now));
            Integer minuteNow = Integer.valueOf(new SimpleDateFormat("mm").format(now));
            Integer secondNow = Integer.valueOf(new SimpleDateFormat("ss").format(now));
            
            if(date.before(now)){
                if(yearNow > year){
                    return (yearNow - year) + " years ago";
                }else if(monthNow > month){
                    return (monthNow - month) + " moths ago";
                }else if(dayNow > day){
                    return (dayNow - day) + " days ago";
                }else if(hourNow > hour){
                    return (hourNow - hour) + " hours ago";
                }else if(minuteNow > minute){
                    return (minuteNow - minute) + " minutes ago";
                }else if(secondNow > second){
                    return (secondNow - second) + " seconds ago";
                }
            }else{
                return "0 second ago";
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(commentDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
