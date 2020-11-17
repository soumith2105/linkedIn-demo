package com.kodem.demo.linkedindemo.experienceModels.experience;

import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.experienceModels.company.Company;
import com.kodem.demo.linkedindemo.user.User;

import org.springframework.data.repository.CrudRepository;

public interface ExperienceRepository extends CrudRepository<Experience, Integer> {
    List<Experience> findByUserUsername(String username);

    Optional<Experience> findByCompanyAndUser(Company company, User user);
}
