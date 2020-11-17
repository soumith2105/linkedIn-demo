package com.kodem.demo.linkedindemo.educationModels.education;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.educationModels.institute.Institute;
import com.kodem.demo.linkedindemo.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    // CREATE
    public void addEducation(Education education) {
        educationRepository.save(education);
    }

    // RETRIEVE
    public List<Education> getAllEducations(String username) {
        List<Education> educations = new ArrayList<>();
        educationRepository.findByUserUsername(username).forEach(educations::add);
        return educations;
    }

    public Optional<Education> getEducation(Integer id) {
        return educationRepository.findById(id);
    }

    public Optional<Education> getByCompanyAndUser(Institute institute, User user) {
        return educationRepository.findByInstituteAndUser(institute, user);
    }

    // UPDATE
    public void updateEducation(Education education) {
        educationRepository.save(education);
    }

    // DELETE
    public void deleteEducation(int id) {
        educationRepository.deleteById(id);
    }
}
