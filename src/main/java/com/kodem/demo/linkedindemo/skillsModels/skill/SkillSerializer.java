package com.kodem.demo.linkedindemo.skillsModels.skill;

// This Class is just to refactor the request body
public class SkillSerializer {
    private String language;
    private int level;
    private String user;

    // Constructors
    public SkillSerializer(String language, int level, String user) {
        this.language = language;
        this.level = level;
        this.user = user;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
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
    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
