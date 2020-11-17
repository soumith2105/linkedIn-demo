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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Language language;
    private int level;

    @ManyToOne
    private User user;

    public Skill(Language language, int level, User user) {
        this.language = language;
        this.level = level;
        this.user = user;
    }

    public Skill() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUser() {
        return this.user.getUsername();
    }

}
