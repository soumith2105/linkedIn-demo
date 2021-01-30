package com.kodem.demo.linkedindemo.educationModels.education;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.kodem.demo.linkedindemo.authentication.constants.JwtSecretKey;
import com.kodem.demo.linkedindemo.educationModels.institute.Institute;
import com.kodem.demo.linkedindemo.educationModels.institute.InstituteRepository;
import com.kodem.demo.linkedindemo.userModel.User;
import com.kodem.demo.linkedindemo.userModel.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/education") // Setting a prefix to all URI's below
public class EducationController {

    @Autowired
    private EducationService educationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    // Create (POST Request)
    @PostMapping
    public void createExp(@RequestBody EducationSerializer educationSerializer) {

        // Get the user from Request body
        User user = userRepository.findByUsername(educationSerializer.getUser()).get();

        // Get the Institute slug from Request body and fetch Institute from DB
        Institute institute = instituteRepository.findBySlug(educationSerializer.getInstitute()).get();

        // Create an Education object
        Education education = new Education(institute, educationSerializer.getDuration(),
                educationSerializer.getStartMonth(), educationSerializer.getEndMonth(),
                educationSerializer.getDescription(), educationSerializer.getLocation(),
                educationSerializer.getCourse(), user);

        // Get if any education object is present with same user and with same institute
        Optional<Education> existingEducation = educationService.getByCompanyAndUser(institute, user);
        if (existingEducation.isPresent()) {

            // If Yes then assign the id to the created education object to override the
            // existing data
            education.setId(existingEducation.get().getId());
        }

        // Save the education object in DB
        educationService.addEducation(education);
    }

    // Retrieve (GET Request)

    // Get education object based on id
    @GetMapping("/{id}")
    public Optional<Education> getExp(@PathVariable int id) {
        return educationService.getEducation(id);
    }

    // Get all education objects based on username
    @GetMapping
    public List<Education> getExp(@RequestParam("user") String username) {
        return educationService.getAllEducations(username);
    }

    // Update (PUT Request)
    @PutMapping("/{id}")
    public void updateExp(@RequestBody EducationSerializer educationSerializer, @PathVariable Integer id,
            @RequestHeader("Authorization") String authHeader, HttpServletResponse response)
            throws NoSuchElementException {

        // Get education object based on id
        Education education = educationService.getEducation(id).orElseThrow();

        // Get the token of the user
        String token = authHeader.replace("Bearer ", "");

        // Get user out of JWT
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();

        // Check whether user is access his own object or not
        if (!education.getUser().equals(userString)) {

            // If no then return Forbidden status code
            response.setStatus(403);
            return;
        }

        // Get the user object from DB
        User user = userRepository.findByUsername(educationSerializer.getUser()).get();

        // Get the Institute object from DB
        Institute institute = instituteRepository.findBySlug(educationSerializer.getInstitute()).get();

        // Create Education object
        education = new Education(institute, educationSerializer.getDuration(), educationSerializer.getStartMonth(),
                educationSerializer.getEndMonth(), educationSerializer.getDescription(),
                educationSerializer.getLocation(), educationSerializer.getCourse(), user);

        // Assign id of existing object
        education.setId(id);

        // Save the updated education object into DB
        educationService.updateEducation(education);
    }

    // Delete (DELETE Request)
    @DeleteMapping("/{id}")
    public void deleteExp(@PathVariable int id, @RequestHeader("Authorization") String authHeader,
            HttpServletResponse response) throws NoSuchElementException {

        // Get education object based on id
        Education education = educationService.getEducation(id).orElseThrow();

        // Get the token of the user
        String token = authHeader.replace("Bearer ", "");

        // Get user out of JWT
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();

        // Check whether user is access his own object or not
        if (!education.getUser().equals(userString)) {

            // If no then return Forbidden status code
            response.setStatus(403);
            return;
        }

        // Delete the education object from DB
        educationService.deleteEducation(id);
    }
}
