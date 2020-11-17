package com.kodem.demo.linkedindemo.educationModels.education;

// This Class is just to refactor the request body
public class EducationSerializer {
    private String institute;
    private String duration;
    private String startMonth;
    private String endMonth;
    private String description;
    private String location;
    private String course;
    private String user;

    // Constructors
    public EducationSerializer(String institute, String duration, String startMonth, String endMonth,
            String description, String location, String course, String user) {
        this.institute = institute;
        this.duration = duration;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.description = description;
        this.location = location;
        this.course = course;
        this.user = user;
    }

    public EducationSerializer() {
    }

    // Institute
    public String getInstitute() {
        return this.institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
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

    // Course
    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // User
    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
