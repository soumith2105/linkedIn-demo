package com.kodem.demo.linkedindemo.experienceModels.company;

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
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    // CREATE
    @PostMapping
    public void createCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    // RETRIEVE
    @GetMapping("/{id}")
    public Optional<Company> getCompany(@PathVariable Integer id) {
        return companyService.getCompany(id);
    }

    @GetMapping
    public List<Company> getCompany() {
        return companyService.getAllCompanies();
    }

    // UPDATE
    @PutMapping("/{id}")
    public void updateCompany(@RequestBody Company company) {
        companyService.updateCompany(company);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
    }

}
