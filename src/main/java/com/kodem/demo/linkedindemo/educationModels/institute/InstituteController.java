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
@RequestMapping("/institute")
public class InstituteController {
    
    @Autowired
    private InstituteService instituteService;


    // CREATE
    @PostMapping
    public void createInstitute(@RequestBody Institute institute) {
        instituteService.addInstitute(institute);
    }

    // RETRIEVE
    @GetMapping("/{slug}")
    public Optional<Institute> getInstitute(@PathVariable String slug) {
        return instituteService.getInstitute(slug);
    }

    @GetMapping
    public List<Institute> getInstitute() {
        return instituteService.getAllInstitutes();
    }

    // UPDATE
    @PutMapping("/{slug}")
    public void updateInstitute(@RequestBody Institute institute) {
        instituteService.updateInstitute(institute);
    }

    // DELETE
    @DeleteMapping("/{slug}")
    public void deleteInstitute(@PathVariable String slug) {
        instituteService.deleteInstitute(slug);
    }

}
