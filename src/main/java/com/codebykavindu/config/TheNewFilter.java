package com.codebykavindu.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Kavindu Perera
 */
class TheNewFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(TheNewFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        log.info("Doing filter things");
        if (Objects.equals(request.getHeader("x-forbidden"), "yes")) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("It is FORBIDDEN");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
