/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.myapp.dao.impl.session;

import com.jcode.myapp.dao.session.UsuarioDAO;
import com.jcode.myapp.model.session.Usuario;
import com.jcode.myapp.utilities.BEAN_CRUD;
import com.jcode.myapp.utilities.BEAN_PAGINATION;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author JCode
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    private static final Logger LOG = Logger.getLogger(UsuarioDAOImpl.class.getName());
    private final DataSource pool;

    public UsuarioDAOImpl(DataSource pool) {
        this.pool = pool;
    }

    @Override
    public Usuario getUser(String user) throws SQLException {
        Usuario usuario = null;
        if (user.equals("admin")) {
            usuario = new Usuario();
            usuario.setUser(user);
            usuario.setPassword("admin");
            usuario.setName_user("JCode");
        }
        return usuario;
    }

    @Override
    public BEAN_PAGINATION getPagination(HashMap<String, Object> parameters, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BEAN_PAGINATION getPagination(HashMap<String, Object> parameters) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BEAN_CRUD add(Usuario obj, HashMap<String, Object> parameters) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BEAN_CRUD update(Usuario obj, HashMap<String, Object> parameters) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BEAN_CRUD delete(int id, HashMap<String, Object> parameters) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
