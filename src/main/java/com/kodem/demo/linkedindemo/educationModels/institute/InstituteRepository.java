package com.kodem.demo.linkedindemo.educationModels.institute;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface InstituteRepository extends CrudRepository<Institute, String> {

    // Find by Slug
    Optional<Institute> findBySlug(String slug);
}
