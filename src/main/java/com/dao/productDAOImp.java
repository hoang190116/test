/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class productDAOImp implements productDAO {

    private JdbcTemplate jt;

    public productDAOImp(DataSource datasource) {
        this.jt = new JdbcTemplate(datasource);
    }

    public productDAOImp() {

    }

    //FOR productController - GET UNIQUE NAME
    @Override
    public boolean selectProductName(String name) {
        String sql = "select name from product where name='" + name + "'";
        try {
            jt.queryForObject(sql, new Object[]{}, String.class);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    //FOR SAVE METHOD - GET ID FROM NAME
    private Integer selectProductID(String name) {
        String sql = "select product_id from product where name='" + name + "'";
        Integer temp = null;
        try {
            return temp = jt.queryForObject(sql, new Object[]{}, int.class);
        } catch (Exception e) {
            return temp;
        }
    }

    public Product productBuilder(Product p) {
        if (p.getDetail() == null) {
            p.setDetail("Null");
        }
        if (p.getPrice() == null) {
            p.setPrice(0);
        }
        if (p.getReleaseDate() == null) {
            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            p.setReleaseDate(date);
        }
        if (p.getDetail() == null) {
            p.setDetail("Null");
        }
        if (p.getView() == null) {
            p.setView(0);
        }
        if (p.getGenre() == null) {
            p.setGenre("Laptop");
        }
        return p;
    }

    @Override
    public int save(Product p) {
        if (p.getName() != null) {
            p = productBuilder(p);
            String sql = "insert into product(name, price, release_date, detail, p_view, genre) values(?,?,?,?,?,?)";
            jt.update(sql, p.getName(), p.getPrice(), p.getReleaseDate(), p.getDetail(), p.getView(), p.getGenre());
            if (p.getPhotos() != null) {
                Integer a = selectProductID(p.getName());
                if (a != null) {
                    for (String p2 : p.getPhotos()) {
                        sql = "insert into product_img(product_id, img) values(?,?)";
                        jt.update(sql, a, p2);
                    }
                }
                return 1;
            }
        };
        return 0;
    }

    @Override
    public int update(Product p) {
        System.out.println("Join update");
        String sql = "update product set name=?, price=?, release_date=?, detail=?, genre=? where product_id=?";
        return jt.update(sql, p.getName(), p.getPrice(), p.getReleaseDate(), p.getDetail(), p.getGenre(), p.getProduct_id());
    }
    
    @Override
    public int updateView(int id) {
        String sql = "update product set p_view+= 1 where product_id="+id;
        return jt.update(sql);
    }

    @Override
    public int addImg(Product p) {
        System.out.println("join add img"+ p.getPhotos());
        if (p.getPhotos() != null) {
            for (String photo : p.getPhotos()) {
                String sql = "insert into product_img(product_id, img) values(?,?)";
                jt.update(sql, p.getProduct_id(), photo);
            }
        }else{
            System.out.println("IMG NULL");
        }
        return 1;
    }

    @Override
    public Product get(int id) {
        String sql = "select * from product where product_id="+id;
        ResultSetExtractor<Product> extrac = new ResultSetExtractor<Product>() {
            public Product extractData(ResultSet rs) {
                Product p = new Product();
                try {
                    if (rs.next()) {
                        p.setProduct_id(id);
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
                        p.setReleaseDate(rs.getString("release_date"));
                        p.setView(rs.getInt("p_view"));
                        p = getImg(p);
                        return p;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(accountDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                return p;
            }
        };
        return jt.query(sql, extrac);
    }

    //use for method get/list
    private Product getImg(Product p) {
        String sql = "select * from product_img where product_id=" + p.getProduct_id();
        ArrayList<String> photo = new ArrayList<>();
        RowMapper<List<String>> rowMap = new RowMapper<List<String>>() {
            public List<String> mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        photo.add(rs.getString("img"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(billDAOImp.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        jt.query(sql, rowMap);
        if (photo.isEmpty()) {
            photo.add("G3default.png");
        }
        p.setPhotos(photo);
        return p;
    }

    @Override
    public int delete(int id) {
        String sql2 = "delete from product where product_id=" + id;
        return jt.update(sql2);
    }

    @Override
    public int deleteImg(String name) {
        String sql = "delete from product_img where img='"+name+"'";
        return jt.update(sql);
    }

    @Override
    public List<Product> list() {
        String sql = "select top 12 * from product order by product_id desc";
        RowMapper<Product> rowMap = new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        Product p = new Product();
                        p.setProduct_id(rs.getInt("product_id"));
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
                        p.setReleaseDate(rs.getString("release_date"));
                        p.setView(rs.getInt("p_view"));
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
    public List<Product> hotList(){
        String sql = "select top 12 * from product order by p_view desc";
        RowMapper<Product> rowMap = new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        Product p = new Product();
                        p.setProduct_id(rs.getInt("product_id"));
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
                        p.setReleaseDate(rs.getString("release_date"));
                        p.setView(rs.getInt("p_view"));
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
    public List<Product> slideBar(){
        String sql = "select top 3 * from product order by p_view desc";
        RowMapper<Product> rowMap = new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        Product p = new Product();
                        p.setProduct_id(rs.getInt("product_id"));
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
                        p.setReleaseDate(rs.getString("release_date"));
                        p.setView(rs.getInt("p_view"));
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
    public List<Product> randomList(String genre) {
        String sql = "SELECT TOP 3 * FROM product where genre = '"+genre+"' ORDER BY NEWID()";
        RowMapper<Product> rowMap = new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        Product p = new Product();
                        p.setProduct_id(rs.getInt("product_id"));
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
                        
                        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
                        String date = dateFormat.format(rs.getDate("release_date"));
                        
                        p.setReleaseDate(date);
                        p.setView(rs.getInt("p_view"));
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
    public List<Product> search(String name, Integer pageid, int total) {
        String sql = "SELECT * FROM product where name like '%"+name+"%' ORDER BY product_id DESC offset "+(pageid-1)+" rows fetch next "+total+" rows only";
        RowMapper<Product> rowMap = new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        Product p = new Product();
                        p.setProduct_id(rs.getInt("product_id"));
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
                        p.setReleaseDate(rs.getString("release_date"));
                        p.setView(rs.getInt("p_view"));
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
    public Integer getCountSearchPage(String name) {
        String sql = "SELECT COUNT(product_id) from product where name like '%"+name+"%'";
        try {
            return jt.queryForObject(sql, new Object[]{}, int.class);
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public List<Product> getProductByPage(int pageid, int total) {
        String sql = "SELECT * FROM product ORDER BY product_id DESC offset "+(pageid-1)+" rows fetch next "+total+" rows only";
        RowMapper<Product> rowMap = new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        Product p = new Product();
                        p.setProduct_id(rs.getInt("product_id"));
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
                        p.setReleaseDate(rs.getString("release_date"));
                        p.setView(rs.getInt("p_view"));
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
    public List<Product> getHotProductByPage(int pageid, int total) {
        String sql = "SELECT * FROM product ORDER BY p_view DESC offset "+(pageid-1)+" rows fetch next "+total+" rows only";
        RowMapper<Product> rowMap = new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int row) throws SQLException {
                try {
                    if (rs != null) {
                        Product p = new Product();
                        p.setProduct_id(rs.getInt("product_id"));
                        p.setDetail(rs.getString("detail"));
                        p.setGenre(rs.getString("genre"));
                        p.setName(rs.getString("name"));
                        p.setPrice(rs.getInt("price"));
                        p.setReleaseDate(rs.getString("release_date"));
                        p.setView(rs.getInt("p_view"));
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
    public Integer getCountProductPage() {
        String sql = "SELECT COUNT(product_id) from product";
        try {
            return jt.queryForObject(sql, new Object[]{}, int.class);
        } catch (Exception e) {
            return 1;
        }
    }
}
