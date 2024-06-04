package com.codebykavindu.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

/**
 * @author Kavindu Perera
 */
public class RobotAuthenticatioFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Declare where we want to apply the filter?
        if (!Collections.list(request.getHeaderNames()).contains("x-robot-secret")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Check credentials and [authenticate | reject]
        if (!Objects.equals(request.getHeader("x-robot-secret"), "beep-boop")) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("You are not Ms. Robot");
            return;
        }
        var auth = new RobotAuthenticationToken();
        var newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(auth);
        SecurityContextHolder.setContext(newContext);


        // 3. Call next!
        filterChain.doFilter(request, response);

        // 4...
    }
}
