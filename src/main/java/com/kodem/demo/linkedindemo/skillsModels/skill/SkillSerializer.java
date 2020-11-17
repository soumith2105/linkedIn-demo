package com.kodem.demo.linkedindemo.skillsModels.skill;

public class SkillSerializer {
    private String language;
    private int level;
    private String user;

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

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
