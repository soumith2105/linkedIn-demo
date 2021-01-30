package com.kodem.demo.linkedindemo.experienceModels.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company") // Setting a prefix to all URI's below
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Create (POST Request)
    @PostMapping
    public void createCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    // Retrieve (GET Request)

    // Get company object based on slug
    @GetMapping("/{slug}")
    public Optional<Company> getCompany(@PathVariable Integer slug) {
        return companyService.getCompany(slug);
    }

    // Get all company objects
    @GetMapping
    public List<Company> getCompany() {
        return companyService.getAllCompanies();
    }

    // Update (PUT Request)
    @PutMapping("/{slug}")
    public void updateCompany(@RequestBody Company company) {
        companyService.updateCompany(company);
    }

    // Delete (DELETE Request)
    @DeleteMapping("/{slug}")
    public void deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
    }

}
