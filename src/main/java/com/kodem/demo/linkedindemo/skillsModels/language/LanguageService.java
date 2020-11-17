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

    // CREATE
    public void addLanguage(Language language) {
        languageRepository.save(language);
    }

    // RETRIEVE
    public Optional<Language> getLanguage(String id) {
        return languageRepository.findById(id);
    }

    public List<Language> getAllLanguages() {
        List<Language> languages = new ArrayList<>();
        languageRepository.findAll().forEach(languages::add);
        return languages;
    }

    // UPDATE
    public void updateLanguage(Language language) {
        languageRepository.save(language);
    }

    // DELETE
    public void deleteLanguage(String id) {
        languageRepository.deleteById(id);
    }
}
