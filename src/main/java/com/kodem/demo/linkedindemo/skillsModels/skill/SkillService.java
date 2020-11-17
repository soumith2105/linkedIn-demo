package com.kodem.demo.linkedindemo.skillsModels.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.skillsModels.language.Language;
import com.kodem.demo.linkedindemo.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    // CREATE
    public void addSkill(Skill skill) {
        skillRepository.save(skill);
    }

    // RETRIEVE
    public List<Skill> getAllSkills(String username) {
        List<Skill> skills = new ArrayList<>();
        skillRepository.findByUserUsername(username).forEach(skills::add);
        return skills;
    }

    public Optional<Skill> getSkill(Integer id) {
        return skillRepository.findById(id);
    }

    public Optional<Skill> getByLanguageAndUser(Language language, User user) {
        return skillRepository.findByLanguageAndUser(language, user);
    }

    // UPDATE
    public void updateSkill(Skill skill) {
        skillRepository.save(skill);
    }

    // DELETE
    public void deleteSkill(int id) {
        skillRepository.deleteById(id);
    }
}
