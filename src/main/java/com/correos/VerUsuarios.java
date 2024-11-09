package com.correos;

import com.correos.modelos.Usuario;
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


@WebServlet("/usuarios/obtener")
public class VerUsuarios extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            List<Usuario> listaUsuarios = usuarioDAO.obtenerUsuarios();
            JSONArray jsonArray = new JSONArray();
            for (Usuario usuario:listaUsuarios) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", usuario.getId());
                jsonObject.put("nombre", usuario.getNombre());
                jsonObject.put("email", usuario.getEmail());
                jsonObject.put("dni", usuario.getDni());
                jsonObject.put("isAdmin", usuario.isAdmin());
                jsonArray.put(jsonObject);
            }


            try (PrintWriter out = response.getWriter()){
                out.print(jsonArray.toString());
                out.flush();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Error al obtener la lista de usuarios");

            try (PrintWriter out = response.getWriter()) {
                out.print(jsonResponse.toString());
                out.flush();
            }
        }
    }
}
