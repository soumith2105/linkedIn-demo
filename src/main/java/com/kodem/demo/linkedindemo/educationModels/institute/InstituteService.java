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

    // CREATE
    public void addInstitute(Institute institute) {
        instituteRepository.save(institute);
    }

    // RETRIEVE
    public Optional<Institute> getInstitute(String id) {
        return instituteRepository.findById(id);
    }

    public List<Institute> getAllInstitutes() {
        List<Institute> institutes = new ArrayList<>();
        instituteRepository.findAll().forEach(institutes::add);
        return institutes;
    }

    // UPDATE
    public void updateInstitute(Institute institute) {
        instituteRepository.save(institute);
    }

    // DELETE
    public void deleteInstitute(String id) {
        instituteRepository.deleteById(id);
    }
}
