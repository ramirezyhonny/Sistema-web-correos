package com.correos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/usuarios/eliminar")
public class EliminarUsuario extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");

        if (email == null || email.isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message","El email es requerido");

            try (PrintWriter out = response.getWriter()) {
                out.print(jsonResponse.toString());
                out.flush();
            }
            return;
        }
        try {
            usuarioDAO.eliminarUsuario(email);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");
            jsonResponse.put("message", "Usuario eliminado correctamente");

            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponse.toString());
                out.flush();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject jsonResponce = new JSONObject();
            jsonResponce.put("status", "error");
            jsonResponce.put("message", "Error al eliminar al usuario");

            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponce.toString());
                out.flush();
            }
        }
    }
}
