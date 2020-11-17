package com.kodem.demo.linkedindemo.skillsModels.skill;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.kodem.demo.linkedindemo.skillsModels.language.Language;
import com.kodem.demo.linkedindemo.user.User;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically Generate a value
    private int id;

    @ManyToOne // Many to One Relationship with Company
    private Language language;
    private int level;

    @ManyToOne // Many to One Relationship with User
    private User user;

    // Constructors
    public Skill(Language language, int level, User user) {
        this.language = language;
        this.level = level;
        this.user = user;
    }

    public Skill() {
    }

    // Id
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Language
    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    // Level
    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // User
    public void setUser(User user) {
        this.user = user;
    }

    public String getUser() {
        return this.user.getUsername();
    }

}
