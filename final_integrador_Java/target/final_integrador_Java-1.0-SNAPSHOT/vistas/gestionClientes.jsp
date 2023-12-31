<%@page import="dao.ClientesDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gestión de clientes</title>
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
            <h2>Gestión de clientes</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Email</th>
                        <th>Fecha Alta</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ClientesDAO clientesDAO = new ClientesDAO();
                        List<Cliente> clientes = clientesDAO.obtenerTodos();

                        if (clientes != null && !clientes.isEmpty()) {
                            for (Cliente cliente : clientes) {
                    %>
                    <tr>
                        <td><%= cliente.getIdCliente()%></td>
                        <td><%= cliente.getNombre()%></td>
                        <td><%= cliente.getApellido()%></td>
                        <td><%= cliente.getEmail()%></td>
                        <td><%= cliente.getFechaAlta()%></td>
                        <td>
                            <div class="d-flex">
                                <!-- Formulario para actualizar -->
                                <form action="GestionClienteServlet" method="post" class="mr-2">
                                    <input type="hidden" name="accion" value="actualizar">
                                    <input type="hidden" name="id" value="<%= cliente.getIdCliente()%>">
                                    <button type="submit" class="btn btn-warning btn-block">Actualizar</button>
                                </form>

                                <!-- Formulario para eliminar -->
                                <form action="GestionClienteServlet" method="post">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="id" value="<%= cliente.getIdCliente()%>">
                                    <button type="submit" class="btn btn-danger btn-block">Eliminar</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6">No hay oradores registrados.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <a href="../" class="btn btn-primary">Volver</a>   
        </div>
    </body>
</html>