package com.kodem.demo.linkedindemo.educationModels.education;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.kodem.demo.linkedindemo.authentication.constants.JwtSecretKey;
import com.kodem.demo.linkedindemo.educationModels.institute.Institute;
import com.kodem.demo.linkedindemo.educationModels.institute.InstituteRepository;
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
@RequestMapping("/education")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    // SERIALIZER VERSION

    // CREATE
    @PostMapping
    public void createExp(@RequestBody EducationSerializer educationSerializer) {
        User user = userRepository.findByUsername(educationSerializer.getUser()).get();
        Institute institute = instituteRepository.findBySlug(educationSerializer.getInstitute()).get();
        Education education = new Education(institute, educationSerializer.getDuration(),
                educationSerializer.getStartMonth(), educationSerializer.getEndMonth(),
                educationSerializer.getDescription(), educationSerializer.getLocation(),
                educationSerializer.getCourse(), user);
        Optional<Education> existingEducation = educationService.getByCompanyAndUser(institute, user);
        if (existingEducation.isPresent()) {
            education.setId(existingEducation.get().getId());
        }
        educationService.addEducation(education);
    }

    // RETRIEVE
    @GetMapping("/{id}")
    public Optional<Education> getExp(@PathVariable int id) {
        return educationService.getEducation(id);
    }

    @GetMapping
    public List<Education> getExp(@RequestParam("user") String user_id) {
        return educationService.getAllEducations(user_id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public void updateExp(@RequestBody EducationSerializer educationSerializer, @PathVariable Integer id,
            @RequestHeader("Authorization") String authHeader, HttpServletResponse response)
            throws NoSuchElementException {

        Education education = educationService.getEducation(id).orElseThrow();
        String token = authHeader.replace("Bearer ", "");
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();
        if (!education.getUser().equals(userString)) {
            response.setStatus(403);
            return;
        }
        User user = userRepository.findByUsername(educationSerializer.getUser()).get();
        Institute institute = instituteRepository.findBySlug(educationSerializer.getInstitute()).get();
        education = new Education(institute, educationSerializer.getDuration(), educationSerializer.getStartMonth(),
                educationSerializer.getEndMonth(), educationSerializer.getDescription(),
                educationSerializer.getLocation(), educationSerializer.getCourse(), user);
        education.setId(id);
        educationService.updateEducation(education);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteExp(@PathVariable int id, @RequestHeader("Authorization") String authHeader,
            HttpServletResponse response) throws NoSuchElementException {
        Education education = educationService.getEducation(id).orElseThrow();
        String token = authHeader.replace("Bearer ", "");
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();
        if (!education.getUser().equals(userString)) {
            response.setStatus(403);
            return;
        }
        educationService.deleteEducation(id);
    }
}
