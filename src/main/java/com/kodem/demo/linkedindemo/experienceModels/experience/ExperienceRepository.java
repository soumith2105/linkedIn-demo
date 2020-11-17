package com.kodem.demo.linkedindemo.experienceModels.experience;

import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.experienceModels.company.Company;
import com.kodem.demo.linkedindemo.user.User;

import org.springframework.data.repository.CrudRepository;

public interface ExperienceRepository extends CrudRepository<Experience, Integer> {

    // Find by User's Username
    List<Experience> findByUserUsername(String username);

    // Find by Company and User
    Optional<Experience> findByCompanyAndUser(Company company, User user);
}
