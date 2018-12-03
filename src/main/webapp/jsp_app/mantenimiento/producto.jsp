<%-- 
    Document   : categoria
    Created on : 19/11/2018, 09:45:41 PM
    Author     : JCode
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>My App</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="<%out.print(getServletContext().getContextPath());%>/resources_app/favicon.png">
        <link rel="stylesheet" href="<%out.print(getServletContext().getContextPath());%>/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%out.print(getServletContext().getContextPath());%>/assets/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%out.print(getServletContext().getContextPath());%>/assets/css/themify-icons.css">
        <link rel="stylesheet" href="<%out.print(getServletContext().getContextPath());%>/assets/css/metisMenu.css">
        <!-- others css -->
        <link rel="stylesheet" href="<%out.print(getServletContext().getContextPath());%>/assets/css/typography.css">
        <link rel="stylesheet" href="<%out.print(getServletContext().getContextPath());%>/assets/css/default-css.css">
        <link rel="stylesheet" href="<%out.print(getServletContext().getContextPath());%>/assets/css/styles.css">
        <link rel="stylesheet" href="<%out.print(getServletContext().getContextPath());%>/assets/css/responsive.css">
        <link rel="stylesheet" href="<%out.print(getServletContext().getContextPath());%>/css_app/view/sweetalert.css">
        <link rel="stylesheet" href="<%out.print(getServletContext().getContextPath());%>/css_app/view/estilos.css">

        <!-- modernizr css -->
        <script src="<%out.print(getServletContext().getContextPath());%>/assets/js/vendor/modernizr-2.8.3.min.js"></script>
    </head>

    <body>
        <!--[if lt IE 8]>
                <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
            <![endif]-->
        <!-- preloader area start -->
        <div id="preloader">
            <div class="loader"></div>
        </div>
        <!-- preloader area end -->
        <!-- page container area start -->
        <div class="page-container">
            <!-- sidebar menu area start -->
            <div class="sidebar-menu">
                <div class="sidebar-header">
                    <div class="logo">
                        <a href="index"><img src="assets/images/icon/logo.png" alt="logo"></a>
                    </div>
                    <br>
                    <h6 class="text-center" style="color: aliceblue"><i class="fa fa-user"></i> <strong><%out.print(((com.jcode.myapp.model.session.Usuario) request.getSession().getAttribute("usuario")).getName_user());%></strong></h6>
                </div>
                <jsp:include page="../../menu.jsp"/>
            </div>
            <!-- sidebar menu area end -->
            <!-- main content area start -->
            <div class="main-content">
                <!-- header area start -->
                <div class="header-area">
                    <div class="row align-items-center">
                        <!-- nav and search button -->
                        <div class="col-4 clearfix">
                            <div class="nav-btn pull-left" style="margin-top: 0px">
                                <span></span>
                                <span></span>
                                <span></span>
                            </div>
                        </div>
                        <!-- profile info & task notification -->
                        <div class="col-8 clearfix">
                            <ul class="notification-area pull-right">
                                <li>
                                    <a href="cerrarsession">
                                        Cerrar Sessión
                                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- header area end -->
                <div class="main-content-inner" style="padding-top: 30px">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">PRODUCTOS</h5>
                                    <input type="hidden" id="nameFormProducto" value="FrmProducto">
                                    <input type="hidden" id="actionProducto" name="action" value="paginarProducto">
                                    <input type="hidden" id="numberPageProducto" name="numberPageProducto" value="1">
                                    <form id="FrmProducto">
                                        <div class="row mt-3">
                                            <div class="form-group col-md-9 col-12">
                                                <input type="text" name="txtNombreProducto" id="txtNombreProducto" class="form-control form-control-sm" placeholder="NOMBRE">
                                            </div>
                                            <div class="form-group col-md-3 col-12">
                                                <button type="submit" id="btnBuscarProducto" class="btn btn-primary btn-xs mr-3" data-toggle="tooltip" title="Buscar Producto"><i class="fa fa-search" aria-hidden="true"></i> BUSCAR</button>
                                                <button type="button" id="btnAbrirNProducto" class="btn btn-primary btn-xs" data-toggle="tooltip" title="Agregar Producto"><i class="fa fa-plus-square" aria-hidden="true"></i></button>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="table-responsive">
                                                <table
                                                    class="table table-hover table-bordered ">
                                                    <thead class="bg-primary">
                                                        <tr class="text-white">
                                                            <th>NOMBRE</th>
                                                            <th>PRECIO</th>
                                                            <th>STOCK</th>
                                                            <th>STOCK MÍNIMO</th>
                                                            <th>STOCK MAXIMO</th>
                                                            <th style="width: 10%" colspan="2" class="text-medium-table">ACCIONES</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="tbodyProducto">
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="card"
                                                 style="height: 50px; margin-bottom: 0px">
                                                <div class="card-body" style="padding-top: 10px; padding-bottom: 10px; padding-left: 0px; padding-right: 0px">
                                                    <div class="row">
                                                        <div class="col-md-2 col-sm-3 col-4">
                                                            <select id="sizePageProducto" name="sizePageProducto" class="form-control form-control-sm combo-paginar" idBtnBuscar='btnBuscarProducto'>
                                                                <option value="10">10</option>
                                                                <option value="15">15</option>
                                                                <option value="20">20</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-10 col-sm-9 col-8">
                                                            <nav aria-label="Page navigation example">
                                                                <ul id="paginationProducto" class="pagination pagination-sm justify-content-end">

                                                                </ul>
                                                            </nav>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="ventanaModalManProducto" class="modal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form id="FrmProductoModal">
                            <div class="modal-header">
                                <h6 class="modal-title" id="tituloModalManProducto"></h6>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-12 form-group">
                                        <label for="txtNombreProductoER">NOMBRE</label>
                                        <input type="text" id="txtNombreProductoER" name="txtNombreProductoER" class="form-control form-control-sm" placeholder="NOMBRE">
                                        <div class="error-validation" id="validarNombreProductoER">Ingrese Nombre</div>
                                    </div>
                                    <div class="col-sm-6 col-12 form-group">
                                        <label for="txtPrecioProductoER">PRECIO</label>
                                        <input type="text" id="txtPrecioProductoER" name="txtPrecioProductoER" class="form-control form-control-sm" placeholder="PRECIO">
                                        <div class="error-validation" id="validarPrecioProductoER">Ingrese Precio</div>
                                    </div>
                                    <div class="col-sm-6 col-12 form-group">
                                        <label for="txtStockProductoER">STOCK</label>
                                        <input type="text" id="txtStockProductoER" name="txtStockProductoER" class="form-control form-control-sm" placeholder="STOCK">
                                        <div class="error-validation" id="validarStockProductoER">Ingrese Stock</div>
                                    </div>
                                    <div class="col-sm-6 col-12 form-group">
                                        <label for="txtStock_minProductoER">STOCK MÍNIMO</label>
                                        <input type="text" id="txtStock_minProductoER" name="txtStock_minProductoER" class="form-control form-control-sm" placeholder="STOCK MÍNIMO">
                                        <div class="error-validation" id="validarStock_minProductoER">Ingrese valor</div>
                                    </div>
                                    <div class="col-sm-6 col-12 form-group">
                                        <label for="txtStock_maxProductoER">STOCK MÁXIMO</label>
                                        <input type="text" id="txtStock_maxProductoER" name="txtStock_maxProductoER" class="form-control form-control-sm" placeholder="STOCK MÁXIMO">
                                        <div class="error-validation" id="validarStock_maxProductoER">Ingrese valor</div>
                                    </div>
                                    <div class="col-12 form-group">
                                        <label for="cboCategoriaProductoER">CATEGORIA</label>
                                        <select class="form-control form-control-sm" id="cboCategoriaProductoER" name="cboCategoriaProductoER">

                                        </select>
                                        <div class="error-validation" id="validarCategoriaProductoER">Seleccione Categoria</div>
                                    </div>
                                    <input type="hidden" id="txtIdProductoER" name="txtIdProductoER" value="">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary btn-xs" data-dismiss="modal">CERRAR</button>
                                <button type="submit" class="btn btn-primary btn-xs">GUARDAR</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal" id="modalCargandoProducto" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top: 18%; overflow-y: visible;">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div class="progress" style="margin-bottom: 0px;">
                                <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                                    Cargando...
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- main content area end -->
            <!-- footer area start-->
            <jsp:include page="../../footer.jsp"/>
            <!-- footer area end-->
        </div>
        <!-- page container area end -->
        <!-- jquery latest version -->
        <script src="<%out.print(getServletContext().getContextPath());%>/assets/js/vendor/jquery-2.2.4.min.js"></script>
        <!-- bootstrap 4 js -->
        <script src="<%out.print(getServletContext().getContextPath());%>/assets/js/popper.min.js"></script>
        <script src="<%out.print(getServletContext().getContextPath());%>/assets/js/bootstrap.min.js"></script>
        <script src="<%out.print(getServletContext().getContextPath());%>/assets/js/metisMenu.min.js"></script>
        <script src="<%out.print(getServletContext().getContextPath());%>/assets/js/jquery.slimscroll.min.js"></script>
        <script src="<%out.print(getServletContext().getContextPath());%>/assets/js/jquery.slicknav.min.js"></script>
        <script src="<%out.print(getServletContext().getContextPath());%>/assets/js/scripts.js"></script>

        <script src="<%out.print(getServletContext().getContextPath());%>/js_app/app/utilities/lib-utilities.js"></script>

        <script src="<%out.print(getServletContext().getContextPath());%>/js_app/view/jquery.Pagination.min.js"></script>
        <script src="<%out.print(getServletContext().getContextPath());%>/js_app/view/sweetalert.min.js"></script>
        <script src="<%out.print(getServletContext().getContextPath());%>/js_app/app/producto.js"></script>
    </body>
</html>
