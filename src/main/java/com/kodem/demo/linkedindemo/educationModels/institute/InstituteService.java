package com.kodem.demo.linkedindemo.educationModels.institute;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstituteService {

    @Autowired
    private InstituteRepository instituteRepository;

    // Create
    public void addInstitute(Institute institute) {
        instituteRepository.save(institute);
    }

    // Retrieve

    // Get institute object based on slug
    public Optional<Institute> getInstitute(String slug) {
        return instituteRepository.findById(slug);
    }

    // Get all institute objects
    public List<Institute> getAllInstitutes() {
        List<Institute> institutes = new ArrayList<>();
        instituteRepository.findAll().forEach(institutes::add);
        return institutes;
    }

    // Update
    public void updateInstitute(Institute institute) {
        instituteRepository.save(institute);
    }

    // Delete
    public void deleteInstitute(String slug) {
        instituteRepository.deleteById(slug);
    }
}
