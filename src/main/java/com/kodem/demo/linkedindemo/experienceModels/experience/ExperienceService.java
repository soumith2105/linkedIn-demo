package com.kodem.demo.linkedindemo.experienceModels.experience;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kodem.demo.linkedindemo.experienceModels.company.Company;
import com.kodem.demo.linkedindemo.userModel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    // Create
    public void addExperience(Experience experience) {

        // Saving experience object to DB
        experienceRepository.save(experience);
    }

    // Retrieve

    // Getting all Experience objects based on username
    public List<Experience> getAllExperiences(String username) {
        List<Experience> experiences = new ArrayList<>();
        experienceRepository.findByUserUsername(username).forEach(experiences::add);
        return experiences;
    }

    // Getting all Experience objects based on id
    public Optional<Experience> getExperience(Integer id) {
        return experienceRepository.findById(id);
    }

    // Getting all Experience objects based on Institute and User
    public Optional<Experience> getByCompanyAndUser(Company company, User user) {
        return experienceRepository.findByCompanyAndUser(company, user);
    }

    // Update
    public void updateExperience(Experience experience) {
        experienceRepository.save(experience);
    }

    // Delete
    public void deleteExperience(int id) {
        experienceRepository.deleteById(id);
    }
}