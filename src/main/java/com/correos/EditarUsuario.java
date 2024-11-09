package com.correos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/usuarios/editar")
public class EditarUsuario extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        try {
            JSONObject jsonRequest = new JSONObject(sb.toString());
            String email = jsonRequest.getString("email");
            String nuevoNombre = jsonRequest.getString("nuevoNombre");
            String nuevoEmail =jsonRequest.getString("nuevoEmail");

            //actualizar usuario
            usuarioDAO.editarUsuario(email,nuevoNombre,nuevoEmail);
            //respuesta
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");
            jsonResponse.put("message", "Datos actulizados correctamente");

            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponse.toString());
                out.flush();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message","No se fue posible actualizar los datos");

            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponse.toString());
                out.flush();
            }
        }
    }
}
