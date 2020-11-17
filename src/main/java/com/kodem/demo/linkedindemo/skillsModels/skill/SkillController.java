package com.kodem.demo.linkedindemo.skillsModels.skill;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.kodem.demo.linkedindemo.authentication.constants.JwtSecretKey;
import com.kodem.demo.linkedindemo.skillsModels.language.Language;
import com.kodem.demo.linkedindemo.skillsModels.language.LanguageRepository;
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
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LanguageRepository languageRepository;

    // SERIALIZER VERSION

    // CREATE
    @PostMapping
    public void createExp(@RequestBody SkillSerializer skillSerializer) {
        User user = userRepository.findByUsername(skillSerializer.getUser()).get();
        Language language = languageRepository.findBySlug(skillSerializer.getLanguage()).get();
        Skill skill = new Skill(language, skillSerializer.getLevel(), user);
        Optional<Skill> existingSkill = skillService.getByLanguageAndUser(language, user);
        if(existingSkill.isPresent()){
            skill.setId(existingSkill.get().getId());
        }
        skillService.addSkill(skill);
    }

    // RETRIEVE
    @GetMapping("/{id}")
    public Optional<Skill> getExp(@PathVariable int id) {
        return skillService.getSkill(id);
    }

    @GetMapping
    public List<Skill> getExp(@RequestParam("user") String user_id) {
        return skillService.getAllSkills(user_id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public void updateExp(@RequestBody SkillSerializer skillSerializer, @PathVariable Integer id,
            @RequestHeader("Authorization") String authHeader, HttpServletResponse response)
            throws NoSuchElementException {
        Skill skill = skillService.getSkill(id).orElseThrow();
        String token = authHeader.replace("Bearer ", "");
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();
        if (!skill.getUser().equals(userString)) {
            response.setStatus(403);
            return;
        }
        User user = userRepository.findByUsername(skillSerializer.getUser()).get();
        Language language = languageRepository.findBySlug(skillSerializer.getLanguage()).get();
        skill = new Skill(language, skillSerializer.getLevel(), user);
        skill.setId(id);
        skillService.updateSkill(skill);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteExp(@PathVariable int id, @RequestHeader("Authorization") String authHeader,
            HttpServletResponse response) throws NoSuchElementException {

        Skill skill = skillService.getSkill(id).orElseThrow();
        String token = authHeader.replace("Bearer ", "");
        String userString = Jwts.parserBuilder().setSigningKey(JwtSecretKey.getSecret()).build().parseClaimsJws(token)
                .getBody().getSubject();
        if (!skill.getUser().equals(userString)) {
            response.setStatus(403);
            return;
        }
        skillService.deleteSkill(id);
    }
}
