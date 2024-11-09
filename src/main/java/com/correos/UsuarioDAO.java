package com.correos;
import com.correos.modelos.Correo;
import com.correos.modelos.Usuario;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class UsuarioDAO {
    private Connection getConnection() throws SQLException{
        Dotenv dotenv = Dotenv.configure()
                .directory("/home/yhonny/IdeaProjects/WebCorreos") // Ruta donde se encuentra el archivo .env
                .load();
        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");
        return DriverManager.getConnection(url,user,password);
    }

    //Consultas a la base de datos
    public void registroUsuario(String nombre, String email, String dni, boolean isAdmin) throws SQLException {
        String query = "INSERT INTO usuarios (nombre,email, dni, isAdmin) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, dni);
            ps.setBoolean(4,isAdmin);
            ps.executeUpdate();
        }
    }

    public List<Usuario> obtenerUsuarios() throws SQLException{
        List<Usuario> listaUsuarios = new ArrayList<>();
        String query = "SELECT id, nombre, email, dni, isAdmin FROM usuarios";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String dni = rs.getString("dni");
                boolean isAdmin = rs.getBoolean("isAdmin");

                Usuario usuario = new Usuario(id, nombre, email, dni, isAdmin);
                listaUsuarios.add(usuario);
            }
        }
        return listaUsuarios;
    }

    public void eliminarUsuario(String email) throws SQLException {
        String query = "DELETE FROM usuarios WHERE email = ?";
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ps.executeUpdate();
        }
    }
    public void editarUsuario(String email, String nuevoNombre, String nuevoEmail) throws SQLException {
        String query = "UPDATE usuarios SET nombre = ?, email = ? WHERE email = ?";
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevoEmail);
            ps.setString(3, email);
            ps.executeUpdate();
        }
    }
    //Seccion de correos y sus relaciones con los usuarios
    //obtener id de usuario mediante email
    public int obtenerIdPorEmail(String email) throws SQLException {
        String query = "SELECT id FROM usuarios WHERE email = ?";
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Usuario no encontrado");
                }
            }
        }
    }
    //registrar correo
    public void registrarCorreo(int remitenteId, int destinatarioId, String asunto, String mensaje) throws SQLException {
        String query = "INSERT INTO correos (remitente_id, destinatario_id, asunto, mensaje) VALUES (?,?,?,?)";
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1,remitenteId);
            ps.setInt(2, destinatarioId);
            ps.setString(3, asunto);
            ps.setString(4, mensaje);
            ps.executeUpdate();
        }
    }
    //eliminar correo
    public void eliminarCorreo(int id) throws SQLException {
        String query = "DELETE FROM correos WHERE id = ?";
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public  List<Correo> verListaCorreos() throws SQLException {
        List<Correo> listaCorreos = new ArrayList<>();
        String query = "SELECT id, remitente_id, destinatario_id, asunto, mensaje, fecha_envio, estado, leido FROM correos";
        try (Connection connection =getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int remitenteId = resultSet.getInt("remitente_id");
                int destinatarioId = resultSet.getInt("destinatario_id");
                String asunto = resultSet.getString("asunto");
                String mensaje = resultSet.getString("mensaje");
                Date fechaEnvio = resultSet.getDate("fecha_envio"); // Usa java.sql.Date si es compatible
                String estado = resultSet.getString("estado");
                boolean leido = resultSet.getBoolean("leido");

                // Crea el objeto `Correo` con los datos correctos en el orden adecuado
                Correo correo = new Correo(id, remitenteId, destinatarioId, asunto, mensaje, fechaEnvio, estado, leido);
                listaCorreos.add(correo);
            }
        }
        return listaCorreos;
    }
}
