package com.kodem.demo.linkedindemo.skillsModels.language;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {
    
    @Autowired
    private LanguageRepository languageRepository;

    // Create
    public void addLanguage(Language language) {
        languageRepository.save(language);
    }

    // Retrieve

    // Get Language object based on slug
    public Optional<Language> getLanguage(String slug) {
        return languageRepository.findById(slug);
    }

    // Get all Language objects
    public List<Language> getAllLanguages() {
        List<Language> languages = new ArrayList<>();
        languageRepository.findAll().forEach(languages::add);
        return languages;
    }

    // Update
    public void updateLanguage(Language language) {
        languageRepository.save(language);
    }

    // Delete
    public void deleteLanguage(String slug) {
        languageRepository.deleteById(slug);
    }
}
