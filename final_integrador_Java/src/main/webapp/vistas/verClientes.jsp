<%@page import="dao.ClientesDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Cliente"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Oradores</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Lista de Oradores</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Email</th>
                        <th>Fecha Alta</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Crear una instancia de ClientesDAO para acceder a la base de datos
                        ClientesDAO clientesDAO = new ClientesDAO();

                        // Obtener la lista de clienes desde la base de datos
                        List<Cliente> clientes = clientesDAO.obtenerTodos();

                        if (clientes != null && !clientes.isEmpty()) {
                            // Iterar sobre la lista de clientes y mostrar sus datos en la tabla
                            for (Cliente cliente : clientes) {
                    %>
                    <tr>
                        <td><%= cliente.getIdCliente()%></td>
                        <td><%= cliente.getNombre()%></td>
                        <td><%= cliente.getApellido()%></td>
                        <td><%= cliente.getEmail()%></td>
                        <td><%= cliente.getFechaAlta()%></td>
                    </tr>
                    <%
                            }
                    } else {
                    %>
                    <tr>
                        <td colspan="5">No hay clienes registrados.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <!-- Botón para volver al índice -->
            <a href="../" class="btn btn-success">Volver</a>   
        </div>
    </body>
</html>
