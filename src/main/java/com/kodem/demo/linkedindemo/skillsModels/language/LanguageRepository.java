package com.kodem.demo.linkedindemo.skillsModels.language;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<Language, String> {

    // Find by Slug
    Optional<Language> findBySlug(String slug);
}
