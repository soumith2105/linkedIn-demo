package com.kodem.demo.linkedindemo.educationModels.education;

import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.educationModels.institute.Institute;
import com.kodem.demo.linkedindemo.user.User;

import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<Education, Integer> {
    List<Education> findByUserUsername(String username);

    Optional<Education> findByInstituteAndUser(Institute institute, User user);
}
