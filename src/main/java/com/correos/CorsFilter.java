package com.correos;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*") // Aplica a todas las rutas
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Permite el acceso desde cualquier origen
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        // Métodos permitidos
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE");
        // Encabezados permitidos
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Permitir el manejo de preflight requests
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
            return; // Si es un preflight request, terminamos aquí
        }

        chain.doFilter(request, response); // Continúa con la cadena de filtros
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización del filtro si es necesario
    }

    @Override
    public void destroy() {
        // Limpieza del filtro si es necesario
    }
}
