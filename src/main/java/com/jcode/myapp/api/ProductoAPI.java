/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.myapp.api;

import com.google.gson.Gson;
import com.jcode.myapp.dao.ProductoDAO;
import com.jcode.myapp.dao.impl.ProductoDAOImpl;
import com.jcode.myapp.model.Categoria;
import com.jcode.myapp.model.Producto;
import com.jcode.myapp.utilities.BEAN_CRUD;
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
@WebServlet(name = "ProductoAPI", urlPatterns = {"/productos"})
public class ProductoAPI extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ProductoAPI.class.getName());
    @Resource(name = "jdbc/dbmyapp")
    private DataSource pool;
    private Gson json;
    private String jsonResponse;
    private HashMap<String, Object> parameters;
    private String action;
    private HttpSession session;

    private ProductoDAO productoDAO;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.parameters = new HashMap<>();
        this.json = new Gson();

        this.productoDAO = new ProductoDAOImpl(this.pool);
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
        try {
            this.action = request.getParameter("action") == null ? "" : request.getParameter("action");
            switch (this.action) {
                case "paginarProducto":
                    processProducto(new BEAN_CRUD(this.productoDAO.getPagination(getParameters(request))), response);
                    break;
                case "addProducto":
                    processProducto(this.productoDAO.add(getProducto(request), getParameters(request)), response);
                    break;
                case "updateProducto":
                    processProducto(this.productoDAO.update(getProducto(request), getParameters(request)), response);
                    break;
                case "deleteProducto":
                    processProducto(this.productoDAO.delete(Integer.parseInt(request.getParameter("txtIdProductoER")), getParameters(request)), response);
                    break;
                default:
                    request.getRequestDispatcher("/jsp_app/producto.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoAPI.class.getName()).log(Level.SEVERE, null, ex);
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
        this.session = request.getSession();
        if (this.session.getAttribute("usuario") == null) {
            response.sendRedirect("login");
        } else {
            processRequest(request, response);
        }
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
        this.session = request.getSession();
        if (this.session.getAttribute("usuario") == null) {
            response.sendRedirect("login");
        } else {
            processRequest(request, response);
        }
    }

    private Producto getProducto(HttpServletRequest request) {
        Producto producto = new Producto();
        if (request.getParameter("action").equals("updateProducto")) {
            producto.setIdproducto(Integer.parseInt(request.getParameter("txtIdProductoER")));
        }
        producto.setNombre(request.getParameter("txtNombreProductoER"));
        producto.setPrecio(Double.parseDouble(request.getParameter("txtPrecioProductoER")));
        producto.setStock(Integer.parseInt(request.getParameter("txtStockProductoER")));
        producto.setStock_min(Integer.parseInt(request.getParameter("txtStock_minProductoER")));
        producto.setStock_max(Integer.parseInt(request.getParameter("txtStock_maxProductoER")));
        producto.setCategoria(new Categoria(Integer.parseInt(request.getParameter("cboCategoriaProductoER"))));
        return producto;
    }

    private HashMap<String, Object> getParameters(HttpServletRequest request) {
        this.parameters.clear();
        this.parameters.put("FILTER", request.getParameter("txtNombreProducto"));
        this.parameters.put("SQL_ORDERS", "NOMBRE ASC");
        this.parameters.put("SQL_LIMIT", " LIMIT " + request.getParameter("sizePageProducto") + " OFFSET "
                + (Integer.parseInt(request.getParameter("numberPageProducto")) - 1) * Integer.parseInt(request.getParameter("sizePageProducto")));
        return this.parameters;
    }

    private void processProducto(BEAN_CRUD beanCrud, HttpServletResponse response) {
        try {
            this.jsonResponse = this.json.toJson(beanCrud);
            LOG.info(this.jsonResponse);
            response.setContentType("application/json");
            response.getWriter().write(this.jsonResponse);
        } catch (IOException ex) {
            Logger.getLogger(ProductoAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
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
