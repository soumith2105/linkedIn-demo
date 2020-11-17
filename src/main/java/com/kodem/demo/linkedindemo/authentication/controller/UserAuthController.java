package com.kodem.demo.linkedindemo.authentication.controller;

import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.kodem.demo.linkedindemo.authentication.constants.JwtSecretKey;
import com.kodem.demo.linkedindemo.authentication.payload.UserAuthResponse;
import com.kodem.demo.linkedindemo.user.User;
import com.kodem.demo.linkedindemo.user.UserSerializer;
import com.kodem.demo.linkedindemo.user.UserService;

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
        User user = new User(userData.getUsername(), encoder.encode(userData.getPassword()), userData.getUsername(),
                userData.getRoles(), userData.isActive(), userData.getEmail(), userData.getDescription(),
                userData.getAddress());
        userService.addUser(user);
        Authentication authResult = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        String token = Jwts.builder().setSubject(authResult.getName()).claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(1)))
                .signWith(JwtSecretKey.getSecret()).compact();
        return new UserAuthResponse("Bearer " + token, "token");
    }

    @PutMapping("/update")
    public UserAuthResponse editUserInfo(@RequestBody UserSerializer userData,
            @RequestHeader("Authorization") String authHeader, HttpServletResponse response) {
        String token = authHeader.replace("Bearer ", "");
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();
        if (!userData.getUsername().equals(userString)) {
            response.setStatus(403);
            return new UserAuthResponse("Access Denied", "error");
        }
        User user = userService.getUser(userString).get();
        User changedUser = new User(userData.getUsername(), encoder.encode(userData.getPassword()),
                userData.getUsername(), userData.getRoles(), userData.isActive(), userData.getEmail(),
                userData.getDescription(), userData.getAddress());
        changedUser.setId(user.getId());
        userService.updateUser(changedUser);
        Authentication authResult = new UsernamePasswordAuthenticationToken(changedUser.getUsername(),
                changedUser.getPassword());
        String newToken = Jwts.builder().setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities()).setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(1))).signWith(JwtSecretKey.getSecret())
                .compact();
        return new UserAuthResponse("Bearer " + newToken, "token");
    }
}