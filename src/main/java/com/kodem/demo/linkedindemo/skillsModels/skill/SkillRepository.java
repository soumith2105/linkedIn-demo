package com.kodem.demo.linkedindemo.skillsModels.skill;

import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.skillsModels.language.Language;
import com.kodem.demo.linkedindemo.user.User;

import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Integer> {

    // Find by User's Username
    List<Skill> findByUserUsername(String username);

    // Find by Language and User
    Optional<Skill> findByLanguageAndUser(Language language, User user);
}
