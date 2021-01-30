package com.kodem.demo.linkedindemo.experienceModels.experience;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.kodem.demo.linkedindemo.authentication.constants.JwtSecretKey;
import com.kodem.demo.linkedindemo.experienceModels.company.Company;
import com.kodem.demo.linkedindemo.experienceModels.company.CompanyRepository;
import com.kodem.demo.linkedindemo.userModel.User;
import com.kodem.demo.linkedindemo.userModel.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/experience") // Setting a prefix to all URI's below
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // Create (POST Request)
    @PostMapping
    public void createExp(@RequestBody ExperienceSerializer experienceSerializer) {

        // Get the user from Request body
        User user = userRepository.findByUsername(experienceSerializer.getUser()).get();

        // Get the Company slug from Request body and fetch Company from DB
        Company company = companyRepository.findBySlug(experienceSerializer.getCompany()).get();

        // Create an Experience object
        Experience experience = new Experience(experienceSerializer.getRole(), company,
                experienceSerializer.getDuration(), experienceSerializer.getStartMonth(),
                experienceSerializer.getEndMonth(), experienceSerializer.getDescription(),
                experienceSerializer.getLocation(), user);

        // Get if any experience object is present with same user and with same company
        Optional<Experience> existingExperience = experienceService.getByCompanyAndUser(company, user);
        if (existingExperience.isPresent()) {

            // If Yes then assign the id to the created experience object to override the
            // existing data
            experience.setId(existingExperience.get().getId());
        }

        // Save the experience object in DB
        experienceService.addExperience(experience);
    }

    // Retrieve (GET Request)

    // Get experience object based on id
    @GetMapping("/{id}")
    public Optional<Experience> getExp(@PathVariable int id) {
        return experienceService.getExperience(id);
    }

    // Get all experience objects based on username
    @GetMapping
    public List<Experience> getExp(@RequestParam("user") String username) {
        return experienceService.getAllExperiences(username);
    }

    // Update (PUT Request)
    @PutMapping("/{id}")
    public void updateExp(@RequestBody ExperienceSerializer experienceSerializer, @PathVariable Integer id,
            @RequestHeader("Authorization") String authHeader, HttpServletResponse response)
            throws NoSuchElementException {

        // Get experience object based on id
        Experience experience = experienceService.getExperience(id).orElseThrow();

        // Get the token of the user
        String token = authHeader.replace("Bearer ", "");

        // Get user out of JWT
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();

        // Check whether user is access his own object or not
        if (!experience.getUser().equals(userString)) {

            // If no then return Forbidden status code
            response.setStatus(403);
            return;
        }

        // Get the user object from DB
        User user = userRepository.findByUsername(experienceSerializer.getUser()).get();

        // Get the Company object from DB
        Company company = companyRepository.findBySlug(experienceSerializer.getCompany()).get();

        // Create Experience object
        experience = new Experience(experienceSerializer.getRole(), company, experienceSerializer.getDuration(),
                experienceSerializer.getStartMonth(), experienceSerializer.getEndMonth(),
                experienceSerializer.getDescription(), experienceSerializer.getLocation(), user);

        // Assign id of existing object
        experience.setId(id);

        // Save the updated experience object into DB
        experienceService.updateExperience(experience);
    }

    // Delete (DELETE Request)
    @DeleteMapping("/{id}")
    public void deleteExp(@PathVariable int id, @RequestHeader("Authorization") String authHeader,
            HttpServletResponse response) throws NoSuchElementException {

        // Get experience object based on id
        Experience experience = experienceService.getExperience(id).orElseThrow();

        // Get the token of the user
        String token = authHeader.replace("Bearer ", "");

        // Get user out of JWT
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();

        // Check whether user is access his own object or not
        if (!experience.getUser().equals(userString)) {

            // If no then return Forbidden status code
            response.setStatus(403);
            return;
        }

        // Delete the experience object from DB
        experienceService.deleteExperience(id);
    }
}
