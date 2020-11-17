package com.kodem.demo.linkedindemo.educationModels.education;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.educationModels.institute.Institute;
import com.kodem.demo.linkedindemo.userModel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    // Create
    public void addEducation(Education education) {

        // Saving education object to DB
        educationRepository.save(education);
    }

    // Retrieve

    // Getting all Education objects based on username
    public List<Education> getAllEducations(String username) {
        List<Education> educations = new ArrayList<>();
        educationRepository.findByUserUsername(username).forEach(educations::add);
        return educations;
    }

    // Getting all Education objects based on id
    public Optional<Education> getEducation(Integer id) {
        return educationRepository.findById(id);
    }

    // Getting all Education objects based on Institute and User
    public Optional<Education> getByCompanyAndUser(Institute institute, User user) {
        return educationRepository.findByInstituteAndUser(institute, user);
    }

    // Update
    public void updateEducation(Education education) {
        educationRepository.save(education);
    }

    // Delete
    public void deleteEducation(int id) {
        educationRepository.deleteById(id);
    }
}
