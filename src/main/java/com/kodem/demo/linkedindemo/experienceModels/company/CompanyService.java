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

    // CREATE
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    // RETRIEVE
    public Optional<Company> getCompany(Integer id) {
        return companyRepository.findById(id);
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().forEach(companies::add);
        return companies;
    }

    // UPDATE
    public void updateCompany(Company company) {
        companyRepository.save(company);
    }

    // DELETE
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}
