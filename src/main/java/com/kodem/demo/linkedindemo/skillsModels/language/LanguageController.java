package com.kodem.demo.linkedindemo.skillsModels.language;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    // CREATE
    @PostMapping
    public void createLanguage(@RequestBody Language language) {
        languageService.addLanguage(language);
    }

    // RETRIEVE
    @GetMapping("/{slug}")
    public Optional<Language> getLanguage(@PathVariable String slug) {
        return languageService.getLanguage(slug);
    }

    @GetMapping
    public List<Language> getLanguage() {
        return languageService.getAllLanguages();
    }

    // UPDATE
    @PutMapping("/{slug}")
    public void updateLanguage(@RequestBody Language language) {
        languageService.updateLanguage(language);
    }

    // DELETE
    @DeleteMapping("/{slug}")
    public void deleteLanguage(@PathVariable String slug) {
        languageService.deleteLanguage(slug);
    }

}
