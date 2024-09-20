package com.chbb.theaketing.core.config.security;

import java.io.IOException;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class EntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {

        String url = request.getRequestURI().toString();
        response.sendError(401, "로그인이 필요합니다");
        // if (url.contains("/api/")) {
        // } else {
        // response.sendRedirect("/login");
        // }

    }

}
