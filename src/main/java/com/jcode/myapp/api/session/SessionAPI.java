/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.myapp.api.session;

import com.google.gson.Gson;
import com.jcode.myapp.dao.impl.session.UsuarioDAOImpl;
import com.jcode.myapp.dao.session.UsuarioDAO;
import com.jcode.myapp.model.session.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author JCode
 */
@WebServlet(name = "SessionAPI", urlPatterns = {"/session"})
public class SessionAPI extends HttpServlet {

    @Resource(name = "jdbc/dbmyapp")
    private DataSource pool;
    private Gson json;
    private String jsonResponse;
    private HashMap<String, Object> JSONROOT;
    private HttpSession session;

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.JSONROOT = new HashMap<>();
        this.json = new Gson();

        this.usuarioDAO = new UsuarioDAOImpl(this.pool);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.session = request.getSession();
        if (this.session.getAttribute("usuario") == null) {
            try {
                //NO HAY SESSION CREAMOS UNA
                String user = request.getParameter("txtUsuario");
                Usuario usuario = this.usuarioDAO.getUser(user);
                if (usuario != null) {
                    String pass = request.getParameter("txtPass");
                    if (pass.equals(usuario.getPassword())) {
                        //ASIGNAMOS EL USUARIO A SESSION
                        this.session.setAttribute("usuario", usuario);
                        this.JSONROOT.put("RESPUESTA_SERVER", "ok");
                    } else {
                        this.JSONROOT.put("RESPUESTA_SERVER", "Contraseña Incorrecta");
                    }
                } else {
                    this.JSONROOT.put("RESPUESTA_SERVER", "El Usuario Ingresado no existe");
                }
                this.jsonResponse = this.json.toJson(this.JSONROOT);
                response.setContentType("application/json");
                response.getWriter().write(this.jsonResponse);
            } catch (SQLException ex) {
                Logger.getLogger(SessionAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("index");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
