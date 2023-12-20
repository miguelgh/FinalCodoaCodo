<%@page import="modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Actualizar cliente</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!--Css-->
        <link rel="stylesheet" href="../css/style.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg bg-dark p-0 pt-3" data-bs-theme="dark">
                <div class="container-lg align-items-end">
                  <a href="#"><img class="navbar-brand" src="../img/logo.svg" alt="Atrezzo ideas gráficas" /></a>
                </div>
            </nav>
        </header>
        <div class="separador"></div>
        <div class="container mt-5">
            <h2>Actualizar cliente</h2>
            <form action="GestionClienteServlet" method="post">
                <input type="hidden" name="accion" value="confirmarActualizacion">
                <input type="hidden" name="id" value="${cliente.idCliente}">

                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente.nombre}" required>
                </div>

                <div class="form-group">
                    <label for="apellido">Apellido:</label>
                    <input type="text" class="form-control" id="apellido" name="apellido" value="${cliente.apellido}" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" class="form-control" id="email" name="email" value="${cliente.email}" required>
                </div>

                <div class="form-group">
                    <label for="fechaAlta">Fecha Alta:</label>
                    <input type="date" class="form-control" id="fechaAlta" name="fechaAlta" value="${cliente.fechaAlta}" required>
                </div>

                <button type="submit" class="btn btn-primary">Actualizar</button>
                <a href="gestionClientes.jsp" class="btn btn-success">Volver</a>

            </form>
        </div>
    </body>
</html>