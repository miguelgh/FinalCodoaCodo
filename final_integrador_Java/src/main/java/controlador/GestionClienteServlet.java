
package controlador;

import dao.ClientesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Cliente;
import java.io.IOException;


@WebServlet("/vistas/GestionClienteServlet")
public class GestionClienteServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        ClientesDAO clientesDAO = new ClientesDAO();

        // Inicializar idOrador antes del switch para que esté disponible en todos los casos
        int idCliente = Integer.parseInt(request.getParameter("id"));

        switch (accion) {
            case "actualizar":
                Cliente cliente = clientesDAO.obtenerPorId(idCliente);
                request.setAttribute("cliente", cliente); //Esto permite pasar datos del servlet a una vista (como un archivo JSP) o a otro servlet al que se redirige o se reenvía la solicitud
                request.getRequestDispatcher("actualizar.jsp").forward(request, response);
                break;
            case "confirmarActualizacion":
                Cliente clienteActualizado = new Cliente();
                clienteActualizado.setIdCliente(idCliente);
                clienteActualizado.setNombre(request.getParameter("nombre"));
                clienteActualizado.setApellido(request.getParameter("apellido"));
                clienteActualizado.setEmail(request.getParameter("email"));
                // Asume que el método setFechaAlta acepta un java.sql.Date
                clienteActualizado.setFechaAlta(java.sql.Date.valueOf(request.getParameter("fechaAlta")));

                clientesDAO.actualizarCliente(clienteActualizado);
                response.sendRedirect("gestionClientes.jsp");
                break;
            case "eliminar":
                clientesDAO.eliminarCliente(idCliente);
                response.sendRedirect("gestionClientes.jsp");
                break;
            default:
                response.sendRedirect("gestionClientes.jsp");
                break;
        }
    }
}
