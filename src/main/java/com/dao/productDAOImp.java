/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Product;
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
public class productDAOImp implements productDAO{
    private JdbcTemplate jt;

    public productDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }
    public productDAOImp(){
        
    }

    @Override
    public int save(Product p) {
        String sql = "insert into product(name, price, release_date, detail, view, genre) values(?,?,?,?,?,?)";
        jt.update(sql, p.getName(), p.getPrice(), p.getReleaseDate(), p.getDetail(), p.getView(), p.getGenre());
        if(p.getPhotos() != null){
            for (String p2 : p.getPhotos()) {
                System.out.println(p2);
            }
        }
        return 1;
    }

    @Override
    public int update(Product p) {
        String sql = "update product set name=?, price=?, release_date=?, detail=?, view=?, genre=?";
        return jt.update(sql, p.getName(), p.getPrice(), p.getReleaseDate(), p.getDetail(), p.getView(), p.getGenre());
    }
    @Override
    public int addImg(Product p) {
        for (String photo : p.getPhotos()) {
//            String sql = "insert into product_img(product_id, img) values(?,?)";
//            jt.update(sql, p.getProduct_id(), photo);
            System.out.println(photo);
        }
        return 1;
    }
    @Override
    public Product get(final int id) {
        String sql = "select * from product where payment_id="+id;
        ResultSetExtractor<Product> extrac = new ResultSetExtractor<Product>(){
            public Product extractData(ResultSet rs){
                try {
                    if(rs.next())
                    {
                        Product p = new Product();
                        p.setProduct_id(id);
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
//                        p.setReleaseDate(rs.getDate("release_date"));
                        p.setView(rs.getInt("view"));
                        p = getImg(p);
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
    //use for method get/list
    private Product getImg(final Product p) {
        String sql = "select * from product_img where product_id="+p.getProduct_id();
        ResultSetExtractor<Product> extrac = new ResultSetExtractor<Product>() {
            public Product extractData(ResultSet rs) throws SQLException {
                try {
                    ArrayList<String> photos = new ArrayList<String>();
                    if(rs.next())
                    {
                        photos.add(rs.getString("img"));
                    }
                    p.setPhotos(photos);
                    return p;
                } catch (SQLException ex) {
                    Logger.getLogger(billDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return null;
            }
        };
        return jt.query(sql, extrac);
    }
    @Override
    public int delete(int id) {
        String sql = "delete from product_img where product_id="+id;
        jt.update(sql);
        String sql2 = "delete from produc where product_id="+id;
        return jt.update(sql2);
    }
    @Override
    public int deleteImg(String name) {
        String sql = "delete from product_img where img="+name;
        return jt.update(sql);
    }
    @Override
    public List<Product> list() {
        String sql = "select * from product";
        RowMapper<Product> rowMap = new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs.next())
                    {
                        Product p = new Product();
                        p.setProduct_id(rs.getInt("product_id"));
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
//                        p.setReleaseDate(rs.getDate("release_date"));
                        p.setView(rs.getInt("view"));
                        p = getImg(p);
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

    @Override
    public List<Product> search(String name) {
        String sql = "select * from product where name like '%"+name+"%'";
        RowMapper<Product> rowMap = new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if(rs != null)
                    {
                        Product p = new Product();
                        p.setProduct_id(rs.getInt("product_id"));
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
                        p.setReleaseDate(rs.getString("release_date"));
                        p.setView(rs.getInt("view"));
                        p = getImg(p);
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
