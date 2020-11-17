package com.kodem.demo.linkedindemo.authentication.filter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodem.demo.linkedindemo.authentication.constants.JwtSecretKey;
import com.kodem.demo.linkedindemo.authentication.payload.UserAuthRequest;
import com.kodem.demo.linkedindemo.authentication.payload.UserAuthResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JwtUserAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUserAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            UserAuthRequest userAuthRequest = new ObjectMapper().readValue(request.getInputStream(),
                    UserAuthRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userAuthRequest.getUsername(),
                    userAuthRequest.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder().setSubject(authResult.getName()).claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(1)))
                .signWith(JwtSecretKey.getSecret()).compact();

        UserAuthResponse userAuthResponse = new UserAuthResponse("Bearer " + token, "token");
        response.setContentType("application/json");
        response.getWriter().append(userAuthResponse.toString());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        UserAuthResponse userAuthResponse = new UserAuthResponse("Incorrect Credentials", "error");
        response.setContentType("application/json");
        response.getWriter().append(userAuthResponse.toString());
        response.setStatus(401);
    }

}
