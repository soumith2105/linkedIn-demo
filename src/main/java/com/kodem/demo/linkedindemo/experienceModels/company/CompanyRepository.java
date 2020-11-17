package com.kodem.demo.linkedindemo.experienceModels.company;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Optional<Company> findBySlug(String slug);
}
