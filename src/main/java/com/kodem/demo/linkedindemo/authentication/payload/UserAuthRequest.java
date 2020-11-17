package com.kodem.demo.linkedindemo.authentication.payload;

// This Class is just to refactor the request body
public class UserAuthRequest {
    private String username;
    private String password;

    // Constructors
    public UserAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAuthRequest() {
    }

    // Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
