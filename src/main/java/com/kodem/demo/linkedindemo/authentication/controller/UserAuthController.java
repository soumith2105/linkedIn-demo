package com.kodem.demo.linkedindemo.authentication.controller;

import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.kodem.demo.linkedindemo.authentication.constants.JwtSecretKey;
import com.kodem.demo.linkedindemo.authentication.payload.UserAuthResponse;
import com.kodem.demo.linkedindemo.userModel.User;
import com.kodem.demo.linkedindemo.userModel.UserSerializer;
import com.kodem.demo.linkedindemo.userModel.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("")
public class UserAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    // CREATE
    @PostMapping("/signup")
    public UserAuthResponse createUser(@RequestBody UserSerializer userData) {

        // Get the user data from the request body
        User user = new User(userData.getUsername(), encoder.encode(userData.getPassword()), userData.getUsername(),
                userData.getRoles(), userData.isActive(), userData.getEmail(), userData.getDescription(),
                userData.getAddress());

        // Add the user to DB
        userService.addUser(user);

        // Create authentication variable so that we could build the token
        Authentication authResult = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        // Generating the token by the user details
        String token = Jwts.builder().setSubject(authResult.getName()).claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(1)))
                .signWith(JwtSecretKey.getSecret()).compact();

        // Return the Token as a Response body
        return new UserAuthResponse("Bearer " + token, "token");
    }

    @PutMapping("/update")
    public UserAuthResponse editUserInfo(@RequestBody UserSerializer userData,
            @RequestHeader("Authorization") String authHeader, HttpServletResponse response) {

        // Get the JWT from the request header
        String token = authHeader.replace("Bearer ", "");

        // Extracting the user information from JWT
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();

        // Check weather the user is trying to change their account or not
        if (!userData.getUsername().equals(userString)) {
            // If yes then reject the request and send forbidden response status
            response.setStatus(403);
            return new UserAuthResponse("Access Denied", "error");
        }

        // Get user information from the DB
        User user = userService.getUser(userString).get();

        // Get the information from the request body
        User changedUser = new User(userData.getUsername(), encoder.encode(userData.getPassword()),
                userData.getUsername(), userData.getRoles(), userData.isActive(), userData.getEmail(),
                userData.getDescription(), userData.getAddress());

        // Setting the id so that the data in DB can be overridden
        changedUser.setId(user.getId());

        // Sve the updated info to DB
        userService.updateUser(changedUser);

        // If the user changes their role or username, then jwt wont have that latest
        // information which result in rejection of token.
        // So we need to generate new token

        // Generate the Authentication variable to build the token
        Authentication authResult = new UsernamePasswordAuthenticationToken(changedUser.getUsername(),
                changedUser.getPassword());

        // Building the token
        String newToken = Jwts.builder().setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities()).setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(1))).signWith(JwtSecretKey.getSecret())
                .compact();

        // Return the Token as a Response body
        return new UserAuthResponse("Bearer " + newToken, "token");
    }
}