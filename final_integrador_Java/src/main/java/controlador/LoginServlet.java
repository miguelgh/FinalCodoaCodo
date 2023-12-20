
package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConexionDB;


//La URL en la anotación debe reflejar cómo quieres acceder al servlet desde el navegador,
//no su ubicación en la estructura de carpetas del proyecto
@WebServlet("/vistas/LoginServlet")
public class LoginServlet extends HttpServlet {
    //carga el usuario y el password
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (autenticarUsuario(username, password)) {
            // Usuario autenticado con éxito
            HttpSession session = request.getSession();
            session.setAttribute("usuario", username);
            response.sendRedirect("gestionClientes.jsp"); // Redirigir a la página de gestion
        } else {
            // Autenticación fallida
            request.setAttribute("error", "Nombre de usuario o contraseña inválidos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    //autenticar usuario
     private boolean autenticarUsuario(String username, String password) {
        // Aquí va la lógica para conectarse a la base de datos y verificar las credenciales
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        
        // cerrando los recursos manualmente
        try {
            conn = ConexionDB.conectar(); // Asume que tienes un método estático getConnection en tu clase Conexion
            String sql = "SELECT * FROM login WHERE usuario = ? AND contraseña = ?"; // Asegúrate de que esta consulta coincida con tu esquema de base de datos
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            return rs.next(); // Si hay un resultado, las credenciales son correctas
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     }
    
}
