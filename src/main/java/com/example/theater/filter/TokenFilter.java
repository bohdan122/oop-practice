package com.example.theater.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import com.example.theater.controller.AuthController;

import java.io.IOException;
import java.util.Enumeration;

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

        // Log request details
        logRequestDetails(httpRequest);

        // Token validation logic
        String token = httpRequest.getHeader("Authorization");
        if (token == null || !authController.isTokenValid(token)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("401 Unauthorized: Invalid or expired token");
            return;
        }

        chain.doFilter(request, response);
    }

    private void logRequestDetails(HttpServletRequest request) {
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Query String: " + request.getQueryString());

        System.out.println("Headers:");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }

        System.out.println("Parameters:");
        request.getParameterMap().forEach((key, value) -> {
            System.out.println(key + ": " + String.join(", ", value));
        });
    }
}
