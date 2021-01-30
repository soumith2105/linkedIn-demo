package com.kodem.demo.linkedindemo.skillsModels.skill;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.kodem.demo.linkedindemo.authentication.constants.JwtSecretKey;
import com.kodem.demo.linkedindemo.skillsModels.language.Language;
import com.kodem.demo.linkedindemo.skillsModels.language.LanguageRepository;
import com.kodem.demo.linkedindemo.userModel.User;
import com.kodem.demo.linkedindemo.userModel.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/skill") // Setting a prefix to all URI's below
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LanguageRepository languageRepository;

    // Create (POST Request)
    @PostMapping
    public void createExp(@RequestBody SkillSerializer skillSerializer) {

        // Get the user from Request body
        User user = userRepository.findByUsername(skillSerializer.getUser()).get();

        // Get the Language slug from Request body and fetch Language from DB
        Language language = languageRepository.findBySlug(skillSerializer.getLanguage()).get();

        // Create an Skill object
        Skill skill = new Skill(language, skillSerializer.getLevel(), user);

        // Get if any skill object is present with same user and with same language
        Optional<Skill> existingSkill = skillService.getByLanguageAndUser(language, user);
        if (existingSkill.isPresent()) {

            // If Yes then assign the id to the created skill object to override the
            // existing data
            skill.setId(existingSkill.get().getId());
        }

        // Save the skill object in DB
        skillService.addSkill(skill);
    }

    // Retrieve (GET Request)

    // Get skill object based on id
    @GetMapping("/{id}")
    public Optional<Skill> getExp(@PathVariable int id) {
        return skillService.getSkill(id);
    }

    // Get all skill objects based on username
    @GetMapping
    public List<Skill> getExp(@RequestParam("user") String username) {
        return skillService.getAllSkills(username);
    }

    // Update (PUT Request)
    @PutMapping("/{id}")
    public void updateExp(@RequestBody SkillSerializer skillSerializer, @PathVariable Integer id,
            @RequestHeader("Authorization") String authHeader, HttpServletResponse response)
            throws NoSuchElementException {

        // Get skill object based on id
        Skill skill = skillService.getSkill(id).orElseThrow();

        // Get the token of the user
        String token = authHeader.replace("Bearer ", "");

        // Get user out of JWT
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();

        // Check whether user is access his own object or not
        if (!skill.getUser().equals(userString)) {

            // If no then return Forbidden status code
            response.setStatus(403);
            return;
        }

        // Get the user object from DB
        User user = userRepository.findByUsername(skillSerializer.getUser()).get();

        // Get the Language object from DB
        Language language = languageRepository.findBySlug(skillSerializer.getLanguage()).get();

        // Create Skill object
        skill = new Skill(language, skillSerializer.getLevel(), user);

        // Assign id of existing object
        skill.setId(id);

        // Save the updated skill object into DB
        skillService.updateSkill(skill);
    }

    // Delete (DELETE Request)
    @DeleteMapping("/{id}")
    public void deleteExp(@PathVariable int id, @RequestHeader("Authorization") String authHeader,
            HttpServletResponse response) throws NoSuchElementException {

        // Get skill object based on id
        Skill skill = skillService.getSkill(id).orElseThrow();

        // Get the token of the user
        String token = authHeader.replace("Bearer ", "");

        // Get user out of JWT
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();

        // Check whether user is access his own object or not
        if (!skill.getUser().equals(userString)) {

            // If no then return Forbidden status code
            response.setStatus(403);
            return;
        }

        // Delete the skill object from DB
        skillService.deleteSkill(id);
    }
}
