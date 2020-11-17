package com.kodem.demo.linkedindemo.user;

public class UserSerializer {
    private String username;
    private String name;
    private String password;
    private Integer roles;
    private boolean active;
    private String email;
    private String description;
    private String address;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoles() {
        return roles;
    }

    public void setRoles(Integer roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
