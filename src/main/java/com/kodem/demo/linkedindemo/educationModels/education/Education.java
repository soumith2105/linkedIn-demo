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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Institute institute;

    private String duration;
    private String startMonth;
    private String endMonth;
    private String description;
    private String location;
    private String course;

    @ManyToOne
    private User user;

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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartMonth() {
        return this.startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return this.endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUser() {
        return this.user.getUsername();
    }

}
