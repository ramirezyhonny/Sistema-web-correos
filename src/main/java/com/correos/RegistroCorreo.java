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

@WebServlet("/correos/registro")
public class RegistroCorreo extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()){
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        try {
            JSONObject jsonRequest = new JSONObject(sb.toString());
            String remitenteEmail = jsonRequest.getString("remitente_email");
            String destinatarioEmail = jsonRequest.getString("destinatario_email");
            String asunto = jsonRequest.getString("asunto");
            String mensaje = jsonRequest.getString("mensaje");

            //obtener los id
            int remitenteId = usuarioDAO.obtenerIdPorEmail(remitenteEmail);
            int destinatarioId = usuarioDAO.obtenerIdPorEmail(destinatarioEmail);

            //guardar en la db
            usuarioDAO.registrarCorreo(remitenteId, destinatarioId, asunto, mensaje);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");
            jsonResponse.put("message", "Correo registrado");

            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponse.toString());
                out.flush();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Error en el registro/envio del correo");
            try (PrintWriter out = response.getWriter()){
                out.print(jsonResponse.toString());
                out.flush();
            }
        }
    }
}
