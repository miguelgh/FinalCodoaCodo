package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import util.ConexionDB;

//ClientesDao = OradoresDAO
public class ClientesDAO {
    
    //agrega un cliente nuevo
    public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, apellido, email, fecha_alta) VALUES (?, ?, ?, ?)";
        //bloque try-with-resources
        //asegura que los recursos abiertos en su declaración (dentro de los paréntesis) se cierren automáticamente al final del bloque try 
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setDate(4, cliente.getFechaAlta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //obtiene un cliente por ID
    public Cliente obtenerPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFechaAlta(rs.getDate("fecha_alta"));
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //Obtener todos los clientes
    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFechaAlta(rs.getDate("fecha_alta"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
    
    //modifica datos de un cliente
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, email = ?, fecha_alta = ? WHERE id_cliente = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setDate(4, cliente.getFechaAlta());
            pstmt.setInt(5, cliente.getIdCliente());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Elimina un cliente
     public void eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
