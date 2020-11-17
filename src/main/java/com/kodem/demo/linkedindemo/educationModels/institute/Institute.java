package com.kodem.demo.linkedindemo.educationModels.institute;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kodem.demo.linkedindemo.educationModels.education.Education;

@Entity
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically Generate a value
    private Integer id;
    @Column(unique = true) // Unique Key
    private String slug;
    private String name;
    private float rating;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL) // One to Many Relationship with Education
    private List<Education> education;

    // Constructors
    public Institute(String slug, String name, float rating) {
        this.slug = slug;
        this.name = name;
        this.rating = rating;
    }

    public Institute() {

    }

    public Institute(String slug) {
        this.slug = slug;
    }

    // Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Slug
    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    // Name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Rating
    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
