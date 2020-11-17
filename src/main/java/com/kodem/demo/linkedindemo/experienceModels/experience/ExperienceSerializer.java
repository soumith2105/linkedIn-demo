package com.kodem.demo.linkedindemo.experienceModels.experience;

// This Class is just to refactor the request body
public class ExperienceSerializer {
    private String role;
    private String company;
    private String duration;
    private String startMonth;
    private String endMonth;
    private String description;
    private String location;
    private String user;

    // Constructors
    public ExperienceSerializer(String role, String company, String duration, String startMonth,
            String endMonth, String description, String location, String user) {
        this.role = role;
        this.company = company;
        this.duration = duration;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.description = description;
        this.location = location;
        this.user = user;
    }

    public ExperienceSerializer() {
    }

    // Role
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Company
    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
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

    // User
    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
