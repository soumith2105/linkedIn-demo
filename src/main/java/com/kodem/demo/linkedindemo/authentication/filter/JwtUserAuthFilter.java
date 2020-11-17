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

    // Authentication Manager is needed to authenticate the user
    private final AuthenticationManager authenticationManager;

    // Constructors
    public JwtUserAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {

            // Take the Request body and stream into UserAuthRequest object
            UserAuthRequest userAuthRequest = new ObjectMapper().readValue(request.getInputStream(),
                    UserAuthRequest.class);

            // Authentication the user based on user information
            Authentication authentication = new UsernamePasswordAuthenticationToken(userAuthRequest.getUsername(),
                    userAuthRequest.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            // If any error in JWT, throw the error
            throw new RuntimeException(e);
        }
    }

    // When the user is authenticated in the attemptAuthentication method, this
    // method will be executed
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        // After user is authenticated, create a JWT to the user
        String token = Jwts.builder().setSubject(authResult.getName()).claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(1)))
                .signWith(JwtSecretKey.getSecret()).compact();

        // Creating the response body to sent the token to user
        UserAuthResponse userAuthResponse = new UserAuthResponse("Bearer " + token, "token");

        // Setting type of data being sent as response
        response.setContentType("application/json");

        // Sending the data as a response body
        response.getWriter().append(userAuthResponse.toString());
    }

    // When the user is not authenticated in the attemptAuthentication method,
    // this method will be executed
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

        // Creating the response body to sent the token to user (Incorrect Credentials)
        UserAuthResponse userAuthResponse = new UserAuthResponse("Incorrect Credentials", "error");

        // Setting type of data being sent as response
        response.setContentType("application/json");

        // Sending the data as a response body
        response.getWriter().append(userAuthResponse.toString());

        // Setting status of response to 401 (Unauthorized request)
        response.setStatus(401);
    }

}
