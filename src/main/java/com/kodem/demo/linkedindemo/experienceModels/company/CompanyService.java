package com.kodem.demo.linkedindemo.experienceModels.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Create
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    // Retrieve

    // Get company object based on slug
    public Optional<Company> getCompany(Integer slug) {
        return companyRepository.findById(slug);
    }

    // Get all Company objects
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().forEach(companies::add);
        return companies;
    }

    // Update
    public void updateCompany(Company company) {
        companyRepository.save(company);
    }

    // Delete
    public void deleteCompany(Integer slug) {
        companyRepository.deleteById(slug);
    }
}
