package com.kodem.demo.linkedindemo.authentication.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kodem.demo.linkedindemo.authentication.constants.JwtSecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get the request header from the request object
        String authHeader = request.getHeader("Authorization");

        // Check whether the JWT is present or not
        if (authHeader == null || authHeader.trim().isEmpty() || !authHeader.startsWith("Bearer ")) {

            // If no, then user needs to login again. So request should be terminated
            filterChain.doFilter(request, response);

            // Don't pass request and response to next filters as request is not authorized
            return;
        }

        try {

            // Get the JWT out of Authorization header
            String token = authHeader.replace("Bearer ", "");

            // Parse the JWT and get the body out of JWT
            Claims body = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                    .getBody();

            // Extract roles from JWT
            @SuppressWarnings("unchecked")
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());

            // Authenticating the user
            Authentication authentication = new UsernamePasswordAuthenticationToken(body.getSubject(), null,
                    simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {

            // Any error in JWT parsing results in non trusted token
            throw new IllegalStateException("Token is not trusted");
        }

        // Pass the request and response to next filters for Authorization
        filterChain.doFilter(request, response);
    }

}
