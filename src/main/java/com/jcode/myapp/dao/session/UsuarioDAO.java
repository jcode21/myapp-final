/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.myapp.dao.session;

import com.jcode.myapp.dao.CRUD;
import com.jcode.myapp.model.session.Usuario;
import java.sql.SQLException;

/**
 *
 * @author JCode
 */
public interface UsuarioDAO extends CRUD<Usuario>{
    
    Usuario getUser(String user) throws SQLException;
}
