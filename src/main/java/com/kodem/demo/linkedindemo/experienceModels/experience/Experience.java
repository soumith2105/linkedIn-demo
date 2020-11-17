package com.kodem.demo.linkedindemo.experienceModels.experience;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.kodem.demo.linkedindemo.experienceModels.company.Company;
import com.kodem.demo.linkedindemo.userModel.User;

@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically Generate a value
    private Integer id;
    private String role;

    @ManyToOne // Many to One Relationship with Company
    private Company company;

    private String duration;
    private String startMonth;
    private String endMonth;
    private String description;
    private String location;

    @ManyToOne // Many to One Relationship with User
    private User user;

    // Constructors
    public Experience(String role, Company company, String duration, String startMonth, String endMonth,
            String description, String location, User user) {
        this.role = role;
        this.company = company;
        this.duration = duration;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.description = description;
        this.location = location;
        this.user = user;
    }

    public Experience() {
    }

    // User
    public void setUser(User user) {
        this.user = user;
    }

    public String getUser() {
        return this.user.getUsername();
    }

    // Id
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Role
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Company
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // Duration
    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // StartMonth
    public String getStartMonth() {
        return this.startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    // EndMonth
    public String getEndMonth() {
        return this.endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    // Description
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Location
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
