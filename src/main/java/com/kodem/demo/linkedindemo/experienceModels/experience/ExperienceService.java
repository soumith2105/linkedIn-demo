package com.kodem.demo.linkedindemo.experienceModels.experience;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.experienceModels.company.Company;
import com.kodem.demo.linkedindemo.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    // CREATE
    public void addExperience(Experience experience) {
        experienceRepository.save(experience);
    }

    // RETRIEVE
    public List<Experience> getAllExperiences(String username) {
        List<Experience> experiences = new ArrayList<>();
        experienceRepository.findByUserUsername(username).forEach(experiences::add);
        return experiences;
    }

    public Optional<Experience> getExperience(Integer id) {
        return experienceRepository.findById(id);
    }

    public Optional<Experience> getByCompanyAndUser(Company company, User user) {
        return experienceRepository.findByCompanyAndUser(company, user);
    }

    // UPDATE
    public void updateExperience(Experience experience) {
        experienceRepository.save(experience);
    }

    // DELETE
    public void deleteExperience(int id) {
        experienceRepository.deleteById(id);
    }
}