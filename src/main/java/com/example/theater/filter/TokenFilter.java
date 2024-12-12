package com.example.theater.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import com.example.theater.controller.AuthController;

import java.io.IOException;

@Component
public class TokenFilter implements Filter {

    private final AuthController authController;

    public TokenFilter(AuthController authController) {
        this.authController = authController;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("Authorization");
        if (token == null || !authController.isTokenValid(token)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("401 Unauthorized: Invalid or expired token");
            return;
        }
        chain.doFilter(request, response);
    }
}
