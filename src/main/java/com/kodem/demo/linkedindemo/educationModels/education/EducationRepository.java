package com.kodem.demo.linkedindemo.educationModels.education;

import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.educationModels.institute.Institute;
import com.kodem.demo.linkedindemo.userModel.User;

import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<Education, Integer> {

    // Find by User's Username
    List<Education> findByUserUsername(String username);

    // Find by Institute and User
    Optional<Education> findByInstituteAndUser(Institute institute, User user);
}
