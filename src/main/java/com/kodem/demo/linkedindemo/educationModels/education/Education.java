package com.kodem.demo.linkedindemo.educationModels.education;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.kodem.demo.linkedindemo.educationModels.institute.Institute;
import com.kodem.demo.linkedindemo.user.User;

@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically Generate a value
    private int id;

    @ManyToOne // Many to One Relationship with Institute
    private Institute institute;

    private String duration;
    private String startMonth;
    private String endMonth;
    private String description;
    private String location;
    private String course;

    @ManyToOne // Many to One Relationship with User
    private User user;

    // Constructors
    public Education(Institute institute, String duration, String startMonth, String endMonth, String description,
            String location, String course, User user) {
        this.institute = institute;
        this.duration = duration;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.description = description;
        this.location = location;
        this.course = course;
        this.user = user;
    }

    public Education() {
    }

    // Id
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Institute
    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
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
    public void setUser(User user) {
        this.user = user;
    }

    public String getUser() {
        return this.user.getUsername();
    }

}
