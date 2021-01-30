package com.kodem.demo.linkedindemo.userModel;

import java.util.List;

import javax.persistence.*;

import com.kodem.demo.linkedindemo.educationModels.education.Education;
import com.kodem.demo.linkedindemo.experienceModels.experience.Experience;
import com.kodem.demo.linkedindemo.skillsModels.skill.Skill;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically Generate a value
    private Integer id;
    @Column(unique = true, length = 128) // Unique Key
    private String username;
    private String name;
    private String password;
    private Integer roles;
    private boolean active;
    @Column(unique = true, length = 128) // Unique Key
    private String email;
    private String description;
    private String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // One to Many Relationship with Experience
    private List<Experience> experiences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // One to Many Relationship with Education
    private List<Education> educations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // One to Many Relationship with Skill
    private List<Skill> skills;

    // Constructor
    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String name, String password, String username, Integer roles, boolean active, String email,
            String description, String address) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.roles = roles;
        this.active = active;
        this.email = email;
        this.description = description;
        this.address = address;
    }

    // Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Password
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Username
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Roles
    public Integer getRoles() {
        return this.roles;
    }

    public void setRoles(Integer roles) {
        this.roles = roles;
    }

    // Active
    public boolean getActive() {
        return this.active;
    }

    // Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Description
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Address
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
