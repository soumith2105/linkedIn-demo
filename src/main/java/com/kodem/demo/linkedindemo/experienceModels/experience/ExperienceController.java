package com.kodem.demo.linkedindemo.experienceModels.experience;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.kodem.demo.linkedindemo.authentication.constants.JwtSecretKey;
import com.kodem.demo.linkedindemo.experienceModels.company.Company;
import com.kodem.demo.linkedindemo.experienceModels.company.CompanyRepository;
import com.kodem.demo.linkedindemo.user.User;
import com.kodem.demo.linkedindemo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // CREATE
    @PostMapping
    public void createExp(@RequestBody ExperienceSerializer experienceSerializer) {
        User user = userRepository.findByUsername(experienceSerializer.getUser()).get();
        Company company = companyRepository.findBySlug(experienceSerializer.getCompany()).get();
        Experience experience = new Experience(experienceSerializer.getRole(), company,
                experienceSerializer.getDuration(), experienceSerializer.getStartMonth(),
                experienceSerializer.getEndMonth(), experienceSerializer.getDescription(),
                experienceSerializer.getLocation(), user);
        Optional<Experience> existingExperience = experienceService.getByCompanyAndUser(company, user);
        if (existingExperience.isPresent()) {
            experience.setId(existingExperience.get().getId());
        }
        experienceService.addExperience(experience);
    }

    // RETRIEVE
    @GetMapping("/{id}")
    public Optional<Experience> getExp(@PathVariable int id) {
        return experienceService.getExperience(id);
    }

    @GetMapping
    public List<Experience> getExp(@RequestParam("user") String user_id) {
        return experienceService.getAllExperiences(user_id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public void updateExp(@RequestBody ExperienceSerializer experienceSerializer, @PathVariable Integer id,
            @RequestHeader("Authorization") String authHeader, HttpServletResponse response)
            throws NoSuchElementException {
        Experience experience = experienceService.getExperience(id).orElseThrow();
        String token = authHeader.replace("Bearer ", "");
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();
        if (!experience.getUser().equals(userString)) {
            response.setStatus(403);
            return;
        }
        User user = userRepository.findByUsername(experienceSerializer.getUser()).get();
        Company company = companyRepository.findBySlug(experienceSerializer.getCompany()).get();
        experience = new Experience(experienceSerializer.getRole(), company, experienceSerializer.getDuration(),
                experienceSerializer.getStartMonth(), experienceSerializer.getEndMonth(),
                experienceSerializer.getDescription(), experienceSerializer.getLocation(), user);
        experience.setId(id);
        experienceService.updateExperience(experience);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteExp(@PathVariable int id, @RequestHeader("Authorization") String authHeader,
            HttpServletResponse response) throws NoSuchElementException {
        Experience experience = experienceService.getExperience(id).orElseThrow();
        String token = authHeader.replace("Bearer ", "");
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();
        if (!experience.getUser().equals(userString)) {
            response.setStatus(403);
            return;
        }
        experienceService.deleteExperience(id);
    }
}
