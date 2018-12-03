/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.myapp.dao.impl;

import com.jcode.myapp.dao.ProductoDAO;
import com.jcode.myapp.dao.SQLCloseable;
import com.jcode.myapp.model.Categoria;
import com.jcode.myapp.model.Producto;
import com.jcode.myapp.utilities.BEAN_CRUD;
import com.jcode.myapp.utilities.BEAN_PAGINATION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author JCode
 */
public class ProductoDAOImpl implements ProductoDAO {

    private static final Logger LOG = Logger.getLogger(ProductoDAOImpl.class.getName());
    private final DataSource pool;

    public ProductoDAOImpl(DataSource pool) {
        this.pool = pool;
    }

    @Override
    public BEAN_PAGINATION getPagination(HashMap<String, Object> parameters, Connection conn) throws SQLException {
        BEAN_PAGINATION beanPagination = new BEAN_PAGINATION();
        List<Producto> list = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = conn.prepareStatement("SELECT COUNT(IDPRODUCTO) AS COUNT FROM PRODUCTO WHERE "
                    + "LOWER(NOMBRE) LIKE CONCAT('%',?,'%')");
            pst.setString(1, String.valueOf(parameters.get("FILTER")));
            LOG.info(pst.toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                beanPagination.setCOUNT_FILTER(rs.getInt("COUNT"));
                if (rs.getInt("COUNT") > 0) {
                    pst = conn.prepareStatement("SELECT * FROM PRODUCTO WHERE "
                            + "LOWER(NOMBRE) LIKE CONCAT('%',?,'%') "
                            + "ORDER BY " + String.valueOf(parameters.get("SQL_ORDERS")) + " " + parameters.get("SQL_LIMIT"));
                    pst.setString(1, String.valueOf(parameters.get("FILTER")));
                    LOG.info(pst.toString());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Producto producto = new Producto();
                        producto.setIdproducto(rs.getInt("IDPRODUCTO"));
                        producto.setNombre(rs.getString("NOMBRE"));
                        producto.setPrecio(rs.getDouble("PRECIO"));
                        producto.setStock(rs.getInt("STOCK"));
                        producto.setStock_min(rs.getInt("STOCK_MIN"));
                        producto.setStock_max(rs.getInt("STOCK_MAX"));
                        producto.setCategoria(new Categoria(rs.getInt("IDCATEGORIA")));
                        list.add(producto);
                    }
                }
            }
            beanPagination.setLIST(list);
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return beanPagination;
    }

    @Override
    public BEAN_PAGINATION getPagination(HashMap<String, Object> parameters) throws SQLException {
        BEAN_PAGINATION beanPagination = null;
        try (Connection conn = pool.getConnection()) {
            beanPagination = getPagination(parameters, conn);
        } catch (SQLException e) {
            throw e;
        }
        return beanPagination;
    }

    @Override
    public BEAN_CRUD add(Producto obj, HashMap<String, Object> parameters) throws SQLException {
        BEAN_CRUD beanCrud = new BEAN_CRUD();
        PreparedStatement pst;
        ResultSet rs;
        try (Connection conn = this.pool.getConnection();
                SQLCloseable finish = conn::rollback;) {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("SELECT COUNT(IDPRODUCTO) AS COUNT FROM PRODUCTO WHERE NOMBRE = ?");
            pst.setString(1, obj.getNombre());
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("COUNT") == 0) {
                    pst = conn.prepareStatement("INSERT INTO PRODUCTO(NOMBRE,PRECIO,STOCK,STOCK_MIN,STOCK_MAX,IDCATEGORIA) VALUES(?,?,?,?,?,?)");
                    pst.setString(1, obj.getNombre());
                    pst.setDouble(2, obj.getPrecio());
                    pst.setInt(3, obj.getStock());
                    pst.setInt(4, obj.getStock_min());
                    pst.setInt(5, obj.getStock_max());
                    pst.setInt(6, obj.getCategoria().getIdcategoria());
                    LOG.info(pst.toString());
                    pst.executeUpdate();
                    conn.commit();
                    beanCrud.setMESSAGE_SERVER("ok");
                    beanCrud.setBEAN_PAGINATION(getPagination(parameters, conn));
                } else {
                    beanCrud.setMESSAGE_SERVER("No se registró, ya existe un Producto con el nombre ingresado");
                }
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return beanCrud;
    }

    @Override
    public BEAN_CRUD update(Producto obj, HashMap<String, Object> parameters) throws SQLException {
        BEAN_CRUD beanCrud = new BEAN_CRUD();
        PreparedStatement pst;
        ResultSet rs;
        try (Connection conn = this.pool.getConnection();
                SQLCloseable finish = conn::rollback;) {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("SELECT COUNT(IDPRODUCTO) AS COUNT FROM PRODUCTO WHERE NOMBRE = ? AND IDPRODUCTO != ?");
            pst.setString(1, obj.getNombre());
            pst.setInt(2, obj.getIdproducto());
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("COUNT") == 0) {
                    pst = conn.prepareStatement("UPDATE PRODUCTO SET NOMBRE = ?, PRECIO = ?, STOCK = ?, STOCK_MIN = ?, "
                            + "STOCK_MAX = ?, IDCATEGORIA = ? WHERE IDPRODUCTO = ?");
                    pst.setString(1, obj.getNombre());
                    pst.setDouble(2, obj.getPrecio());
                    pst.setInt(3, obj.getStock());
                    pst.setInt(4, obj.getStock_min());
                    pst.setInt(5, obj.getStock_max());
                    pst.setInt(6, obj.getCategoria().getIdcategoria());
                    pst.setInt(7, obj.getIdproducto());
                    LOG.info(pst.toString());
                    pst.executeUpdate();
                    conn.commit();
                    beanCrud.setMESSAGE_SERVER("ok");
                    beanCrud.setBEAN_PAGINATION(getPagination(parameters, conn));
                } else {
                    beanCrud.setMESSAGE_SERVER("No se modificó, ya existe un Producto con el nombre ingresado");
                }
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return beanCrud;
    }

    @Override
    public BEAN_CRUD delete(int id, HashMap<String, Object> parameters) throws SQLException {
        BEAN_CRUD beanCrud = new BEAN_CRUD();
        PreparedStatement pst;
        try (Connection conn = this.pool.getConnection();
                SQLCloseable finish = conn::rollback;) {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("DELETE FROM PRODUCTO WHERE IDPRODUCTO = ?");
            pst.setInt(1, id);
            LOG.info(pst.toString());
            pst.executeUpdate();
            conn.commit();
            beanCrud.setMESSAGE_SERVER("ok");
            beanCrud.setBEAN_PAGINATION(getPagination(parameters, conn));
            pst.close();
        } catch (SQLException e) {
            throw e;
        }
        return beanCrud;
    }

}
