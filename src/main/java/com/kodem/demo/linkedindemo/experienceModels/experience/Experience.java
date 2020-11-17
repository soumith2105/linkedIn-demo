package com.kodem.demo.linkedindemo.experienceModels.experience;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.kodem.demo.linkedindemo.experienceModels.company.Company;
import com.kodem.demo.linkedindemo.user.User;

@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;

    @ManyToOne
    private Company company;

    private String duration;
    private String startMonth;
    private String endMonth;
    private String description;
    private String location;

    @ManyToOne
    private User user;

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

    // USER
    public void setUser(User user) {
        this.user = user;
    }

    public String getUser() {
        return this.user.getUsername();
    }

    // ID
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // ROLE
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // COMPANY
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // DURATION
    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // START MONTH
    public String getStartMonth() {
        return this.startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    // END MONTH
    public String getEndMonth() {
        return this.endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    // DESCRIPTION
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // LOCATION
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
