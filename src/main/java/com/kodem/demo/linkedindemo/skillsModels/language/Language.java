package com.kodem.demo.linkedindemo.skillsModels.language;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kodem.demo.linkedindemo.skillsModels.skill.Skill;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically Generate a value
    private Integer id;
    @Column(unique = true, length = 128) // Unique Key
    private String slug;
    private String name;
    private String type;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL) // One to Many Relationship with Skill
    private List<Skill> skills;

    // Constructors
    public Language() {

    }

    public Language(String slug) {
        this.slug = slug;
    }

    public Language(String slug, String name, String type) {
        this.slug = slug;
        this.name = name;
        this.type = type;
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

    // Type
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        if (type == "") {
            this.type = "Core";
        } else {

            this.type = type;
        }
    }
}
