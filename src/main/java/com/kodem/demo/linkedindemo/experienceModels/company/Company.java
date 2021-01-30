package com.kodem.demo.linkedindemo.experienceModels.company;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kodem.demo.linkedindemo.experienceModels.experience.Experience;


@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically Generate a value
    private Integer id;
    @Column(unique = true, length = 128) // Unique Key
    private String slug;
    private String name;
    private float rating;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL) // One to Many Relationship with Experience
    private List<Experience> experiences;

    // Constructors
    public Company(String name, String slug, float rating) {
        this.name = name;
        this.slug = slug;
        this.rating = rating;
    }

    public Company() {
        
    }

    public Company(String slug) {
        this.slug = slug;
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

    // Slug
    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    // Rating
    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
