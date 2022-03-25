/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class genreDAOImp implements genreDAO{
    private JdbcTemplate jt;

    public genreDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }
    public genreDAOImp(){
        
    }

    @Override
    public int save(String name) {
        try{
            String sql = "insert into genre values(?)";
            jt.update(sql, name);
            return 1;
        }catch(Exception e){
            System.out.println("Error duplicate");
        }
        return 0;
    }

    @Override
    public List<genre> list() {
        String sql = "select * from genre";
        RowMapper<genre> rowMap = new RowMapper<genre>() {
            public genre mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs!= null)
                    {
                        genre g = new genre();
                        g.setGenre(rs.getString("genre_name"));
                        return g;
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
