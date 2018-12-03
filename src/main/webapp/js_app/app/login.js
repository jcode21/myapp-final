$(document).ready(function () {

    $("#FrmLogin").submit(function () {
        if (validarFormularioLogin()) {
            $('#modalCargandoLogin').modal("show");
        }
        return false;
    });
    
    $("#modalCargandoLogin").on('shown.bs.modal', function (e) {
        processAjaxLogin();
    });
    
    addValidacionesFormularioLogin();

});

function processAjaxLogin() {
    var datosSerializadosCompletos = $('#FrmLogin').serialize();
    $.ajax({
        url: 'session',
        type: 'POST',
        data: datosSerializadosCompletos,
        dataType: 'json',
        success: function (jsonResponse) {
            console.log(jsonResponse);
            $('#modalCargandoLogin').modal("hide");
            if (jsonResponse.RESPUESTA_SERVER.toLowerCase() === "ok") {
                $(location).attr('href', 'index');
            } else {
                viewAlert('warning', jsonResponse.RESPUESTA_SERVER);
            }
        },
        error: function () {
            $('#modalCargandoLogin').modal("hide");
            viewAlert('error', 'Erroe interno en el Servidor');
        }
    });
    return false;
}

function addValidacionesFormularioLogin() {
    $('#txtUsuario').on('change', function () {
        (this.value === "") ? $("#validarUsuario").fadeIn("slow") : $("#validarUsuario").fadeOut();
    });
    $('#txtPass').on('change', function () {
        (this.value === "") ? $("#validarPass").fadeIn("slow") : $("#validarPass").fadeOut();
    });
}

function validarFormularioLogin() {
    if ($('#txtUsuario').val() === "") {
        $("#validarUsuario").fadeIn("slow");
        $('#txtUsuario').focus();
        return false;
    } else {
        $("#validarUsuario").fadeOut();
    }
    if ($('#txtPass').val() === "") {
        $('#txtPass').focus();
        $("#validarPass").fadeIn("slow");
        return false;
    } else {
        $("#validarPass").fadeOut();
    }
    return true;
}
