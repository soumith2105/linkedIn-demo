package com.kodem.demo.linkedindemo.experienceModels.company;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    // Find by Slug
    Optional<Company> findBySlug(String slug);
}
