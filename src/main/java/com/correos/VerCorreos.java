package com.correos;

import com.correos.modelos.Correo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/correos/obtener")
public class VerCorreos extends HttpServlet {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            List<Correo> listaCorreos = usuarioDAO.verListaCorreos();
            JSONArray jsonArray = new JSONArray();

            for (Correo correo : listaCorreos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", correo.getId());
                jsonObject.put("remitente_id", correo.getRemitenteId());
                jsonObject.put("destinatario_id", correo.getDestinatarioId());
                jsonObject.put("asunto", correo.getAsunto());
                jsonObject.put("mensaje", correo.getMensaje());
                jsonObject.put("fecha_envio", correo.getFechaEnvio().toString());
                jsonObject.put("estado", correo.getEstado());
                jsonObject.put("leido", correo.isLeido());
                jsonArray.put(jsonObject);
            }

            try (PrintWriter out = response.getWriter()) {
                out.print(jsonArray.toString());
                out.flush();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al obtener la lista de correos\"}");
        }
    }
}
