$(document).ready(function () {

});

function viewAlert(type, message) {
    swal({
        title: "PEAM",
        text: message,
        /*7
         html : "<img src='/sisbu/img/logo_ceplomo.png' alt='Logo' style='width:150px;'><br><h5>"
         + message + "</h5>",*/
        type: type,
        showCancelButton: false,
        confirmButtonColor: '#3085d6',
        confirmButtonText: "Aceptar",
        confirmButtonClass: 'btn btn-info',
        buttonsStyling: false
    });
    $('.swal2-icon').css("margin-bottom", "20px");
}

function viewAlertWeb(type, message) {
    swal({
        title: "PEAM",
        text: message,
        /*7
         html : "<img src='/sisbu/img/logo_ceplomo.png' alt='Logo' style='width:150px;'><br><h5>"
         + message + "</h5>",*/
        type: type,
        showCancelButton: false,
        confirmButtonColor: '#3085d6',
        confirmButtonText: "Aceptar",
        confirmButtonClass: 'btn btn-success',
        buttonsStyling: false
    });
    $('.swal2-icon').css("margin-bottom", "20px");
}

function viewAlertDelete(entidad) {
    swal({
        title: 'PEAM',
        text: "¿Desea eliminar este registro?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, continuar!',
        cancelButtonText: 'No, cancelar!',
        confirmButtonClass: 'btn btn-info',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false
    }).then((result) => {
        if (result.value) {
            $('#action' + entidad).val("delete" + entidad);
            $("#nameForm").val("Frm" + entidad + "Modal");
            $('#modalCargando' + entidad).modal("show");
        } else {
            swal(
                    {
                        title: "PEAM!",
                        text: "Operación Cancelada",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: "Aceptar",
                        confirmButtonClass: 'btn btn-info',
                        buttonsStyling: false
                    }
            );
        }
    });
    $('.swal2-confirm').css("margin-right", "15px");
    $('.swal2-container').css('z-index', '2000');
}

function cargarFiltros(idimput_filter, classcheck, nameattrcheck) {
    var cadenaFiltros = "";
    $('.' + classcheck + ':checked').each(function (index, value) {
        if (index === $('.' + classcheck + ':checked').length - 1) {
            cadenaFiltros = cadenaFiltros + $(this).attr(nameattrcheck);
        } else {
            cadenaFiltros = cadenaFiltros + $(this).attr(nameattrcheck) + ",";
        }
    });
    $('#' + idimput_filter).val(cadenaFiltros);
}

function agregarValidacionesFiltrosCheck(idtodos, classchecks) {
    /* CHECKS */
    $("#" + idtodos).change(function () {
        $("." + classchecks).prop('checked', $(this).prop("checked"));
    });

    $('.' + classchecks).click(
            function () {
                if ($('.' + classchecks).length === $('.' + classchecks
                        + ':checked').length) {
                    $("#" + idtodos).prop('checked', true);
                } else {
                    $("#" + idtodos).prop('checked', false);
                }
            });
}

function getMessageServerTransaction(action, entidad, delimitador_sexo) {
    var messageOut = entidad;
    var operation = "";
    for (var i = 0; i < 3; i++) {
        operation += action.substring(i, i + 1);
    }
    switch (operation) {
        case "add":
            messageOut += " registrad" + delimitador_sexo + " exitosamente!";
            break;
        case "upd":
            messageOut += " modificad" + delimitador_sexo + " exitosamente!";
            break;
        case "del":
            messageOut += " eliminad" + delimitador_sexo + " exitosamente!";
            break;
        case "act":
            messageOut += " actualizad" + delimitador_sexo + " exitosamente!";
            break;
        case "fav":
            messageOut += " actualizad" + delimitador_sexo + " exitosamente!";
            break;
    }
    return messageOut;
}

function getContext() {
    return "/web";
}

function getDefaultOptionsPagination() {
    var defaultOpts = {
        totalPages: 10,
        visiblePages: 5,
        initiateStartPageClick: false,
        first: "<i class='fa fa-angle-double-left' aria-hidden='true'></i>",
        prev: "<i class='fa fa-angle-left' aria-hidden='true'></i>",
        next: "<i class='fa fa-angle-right' aria-hidden='true'></i>",
        last: "<i class='fa fa-angle-double-right' aria-hidden='true'></i>"
    };
    return defaultOpts;
}

function getOptionsPagination(count_filter, $sizePage, $numberPage, $action, valueAction, $form, valueForm, $modalLoanding) {
    var totalPages = getTotalPages(count_filter, parseInt($sizePage.val()));
    var options =
            {
                startPage: parseInt($numberPage.val()),
                totalPages: totalPages,
                visiblePages: 5,
                initiateStartPageClick: false,
                first: "<i class='fa fa-angle-double-left' aria-hidden='true'></i>",
                prev: "<i class='fa fa-angle-left' aria-hidden='true'></i>",
                next: "<i class='fa fa-angle-right' aria-hidden='true'></i>",
                last: "<i class='fa fa-angle-double-right' aria-hidden='true'></i>",
                onPageClick: function (evt, page) {
                    $action.val(valueAction);
                    $numberPage.val(page);
                    $form.val(valueForm);
                    $modalLoanding.modal("show");
                    console.log("paso por el click del paginar :(");
                }
            };
    return options;
}

function getTotalPages(count_filter, size_page) {
    var count_pages = count_filter / size_page;
    if (count_filter % size_page > 0) {
        count_pages += 1;
    }
    return count_pages;
}

function addEventoCombosPaginar() {
    $('.combo-paginar').on('change', function () {
        $("#" + $(this).attr('idBtnBuscar')).trigger("click");
    });
}

function addEventoCombosPaginarFormulario() {
    $('.combo-paginar-formulario').on('change', function () {
        $("#" + $(this).attr('idinput')).val($(this).val());
        $("#" + $(this).attr('idformulario')).submit();
    });
}

function getFullDateJS(string_full_date) {
    var valoresGenerales = string_full_date.split(" ");
    //DATE DD/MM/YYYY
    var valoresDate = valoresGenerales[0].split("/");
    //HOUR hh:mm:ss
    var valoresHora = valoresGenerales[1].split(":");
    return new Date(parseInt(valoresDate[2]), (parseInt(valoresDate[1]) - 1), parseInt(valoresDate[0]), parseInt(valoresHora[0]), parseInt(valoresHora[1]), parseInt(valoresHora[2]));
}

function getDateJS(string_date) {
    var valoresDate = string_date.split("/");
    return new Date(parseInt(valoresDate[2]), (parseInt(valoresDate[1]) - 1), parseInt(valoresDate[0]), 0, 0, 0);
}

function cargarAniosCombo($combo, anioInicio, valueInicial, texto_Inicial) {
    var date = new Date();
    var opcion;
    $combo.append("<option value='-1'>" + texto_Inicial + "</option>");
    for (var i = date.getFullYear(); i >= anioInicio; i--) {
        opcion = "<option value='" + i + "'>" + i + "</option>";
        $combo.append(opcion);
    }
    $combo.val(valueInicial);
}

function replaceAll(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
}

function getTituloWeb(titulo, tamFila) {
    var cadenaTituloFormateado = "";
    var palabras = titulo.split(' ');
    var filas = 1;
    for (var i = 0; i < palabras.length; i++) {
        if (cadenaTituloFormateado.length > (tamFila * filas)) {
            cadenaTituloFormateado += "</br>" + palabras[i] + " ";
            filas++;
        } else {
            cadenaTituloFormateado += palabras[i] + " ";
        }
    }
    return cadenaTituloFormateado;
}

function getResumenContenidoWeb(contenido, tam) {
    var cadenaResumen = "";
    var palabras = contenido.split(' ');
    for (var i = 0; i < palabras.length; i++) {
        if (cadenaResumen.length + palabras[i].length > tam) {
            break;
        } else {
            cadenaResumen += palabras[i] + " ";
        }
    }
    return cadenaResumen;
}

function removeTagHTML(cadena) {
    cadena = cadena.replace(/<\/?[^>]+(>|$)/g, "");
    return cadena;
    /*
     if ((str===null) || (str===''))
     return false;
     else
     str = str.toString();
     return str.replace(/<[^>]*>/g, ''); 
     */
}

function getStringDateFormat(cadenaFecha) {
    //cadenaFecha -> dd/MM/yyyy
    var valores = cadenaFecha.split("/");
    var fecha = new Date(parseInt(valores[2]), parseInt(valores[1]), parseInt(valores[0]), 0, 0, 0);
    var options = {weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'};
    return fecha.toLocaleDateString("es-ES", options);
}