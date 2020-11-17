package com.kodem.demo.linkedindemo.educationModels.institute;

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
@RequestMapping("/institute") // Setting a prefix to all URI's below
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    // Create
    @PostMapping
    public void createInstitute(@RequestBody Institute institute) {
        instituteService.addInstitute(institute);
    }

    // Retrieve

    // Get institute object based on slug
    @GetMapping("/{slug}")
    public Optional<Institute> getInstitute(@PathVariable String slug) {
        return instituteService.getInstitute(slug);
    }

    // Get all institute objects
    @GetMapping
    public List<Institute> getInstitute() {
        return instituteService.getAllInstitutes();
    }

    // Update
    @PutMapping("/{slug}")
    public void updateInstitute(@RequestBody Institute institute) {
        instituteService.updateInstitute(institute);
    }

    // Delete
    @DeleteMapping("/{slug}")
    public void deleteInstitute(@PathVariable String slug) {
        instituteService.deleteInstitute(slug);
    }

}
