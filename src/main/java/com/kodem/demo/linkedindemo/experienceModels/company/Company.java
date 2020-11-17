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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String slug;
    private String name;
    private float rating;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    // CONSTRUCTORS
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

    // SLUG
    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    // RATING
    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
