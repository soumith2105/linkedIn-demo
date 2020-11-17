package com.kodem.demo.linkedindemo.skillsModels.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.skillsModels.language.Language;
import com.kodem.demo.linkedindemo.userModel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    // Create
    public void addSkill(Skill skill) {

        // Saving skill object to DB
        skillRepository.save(skill);
    }

    // Retrieve

    // Getting all Skill objects based on username
    public List<Skill> getAllSkills(String username) {
        List<Skill> skills = new ArrayList<>();
        skillRepository.findByUserUsername(username).forEach(skills::add);
        return skills;
    }

    // Getting all Skill objects based on id
    public Optional<Skill> getSkill(Integer id) {
        return skillRepository.findById(id);
    }

    // Getting all Skill objects based on Language and User
    public Optional<Skill> getByLanguageAndUser(Language language, User user) {
        return skillRepository.findByLanguageAndUser(language, user);
    }

    // Update
    public void updateSkill(Skill skill) {
        skillRepository.save(skill);
    }

    // Delete
    public void deleteSkill(int id) {
        skillRepository.deleteById(id);
    }
}
