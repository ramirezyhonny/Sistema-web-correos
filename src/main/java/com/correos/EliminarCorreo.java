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

@WebServlet("/correos/eliminar")
public class EliminarCorreo  extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParametro = request.getParameter("id");

        if (idParametro == null || idParametro.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "se solicita id de correo");

            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponse.toString());
                out.flush();
            }
            return;
        }
        try {
            int idCorreo = Integer.parseInt(idParametro);
            usuarioDAO.eliminarCorreo(idCorreo);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");
            jsonResponse.put("message", "Correo eliminado");

            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponse.toString());
                out.flush();
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "ID de correo inválido");

            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponse.toString());
                out.flush();
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Error al eliminar el correo");

            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponse.toString());
                out.flush();
            }
        }

    }
}
