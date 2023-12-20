
package controlador;

import dao.ClientesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import modelo.Cliente;
import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registroCliente")
public class RegistroCliente extends HttpServlet {
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
         
        // Obtener datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");

        // Crear un objeto Orador con los datos
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);

        // Obtener la fecha actual
        java.util.Date fechaActual = new java.util.Date(); //es una forma de utilizar la clase sin necesitar una declaracion 'import'
        cliente.setFechaAlta(new Date(fechaActual.getTime()));

        // Agregar el orador a la base de datos
        ClientesDAO clientesDAO = new ClientesDAO();
        clientesDAO.agregarCliente(cliente);

        // Redireccionar a la página de visualización de oradores
        response.sendRedirect(request.getContextPath() + "/vistas/verClientes.jsp");
    }
}
