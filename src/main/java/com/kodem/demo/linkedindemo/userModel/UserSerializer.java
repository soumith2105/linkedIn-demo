package com.kodem.demo.linkedindemo.userModel;

public class UserSerializer {
    private String username;
    private String name;
    private String password;
    private Integer roles;
    private boolean active;
    private String email;
    private String description;
    private String address;

    // Constructors
    public UserSerializer(String username, String name, String password, Integer roles, boolean active, String email,
            String description, String address) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.active = active;
        this.email = email;
        this.description = description;
        this.address = address;
    }

    // Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Roles
    public Integer getRoles() {
        return roles;
    }

    public void setRoles(Integer roles) {
        this.roles = roles;
    }

    // Active
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
