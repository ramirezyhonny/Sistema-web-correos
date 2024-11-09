package com.correos;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/usuarios/registro")
public class Registro extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding(("UTF-8"));

        StringBuilder sb = new StringBuilder();
        String line;
        try(BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        }
        try {
            JSONObject jsonRequest = new JSONObject(sb.toString());
            String nombre = jsonRequest.getString("nombre");
            String email = jsonRequest.getString("email");
            String dni = jsonRequest.getString("dni");
            boolean isAdmin = jsonRequest.getBoolean("isAdmin");
            //Guardar en la base de datos
            usuarioDAO.registroUsuario(nombre, email, dni, isAdmin);

            //Respuesta exitosa
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");
            jsonResponse.put("message", "Usuario registrado correctamente");

            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponse.toString());
                out.flush();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // imprimir error en consola, por si acaso
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Error al registrar al usuario: " + e.getMessage());
            try (PrintWriter out = response.getWriter()) {
                out.print(jsonResponse.toString());
                out.flush();
            }
        }
    }
}
