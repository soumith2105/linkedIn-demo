package com.kodem.demo.linkedindemo.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kodem.demo.linkedindemo.educationModels.education.Education;
import com.kodem.demo.linkedindemo.experienceModels.experience.Experience;
import com.kodem.demo.linkedindemo.skillsModels.skill.Skill;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    private Integer roles;
    private boolean active;
    private String email;
    private String description;
    private String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Education> educations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Skill> skills;

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

    // ID
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // NAME
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // PASSWORD

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // USERNAME
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // ROLES
    public Integer getRoles() {
        return this.roles;
    }

    public void setRoles(Integer roles) {
        this.roles = roles;
    }

    // ACTIVE
    public boolean getActive() {
        return this.active;
    }

    // EMAIL
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // DESCRIPTION
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // ADDRESS
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
