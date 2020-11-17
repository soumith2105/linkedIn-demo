# **Kodem Demo Project - LinkedIn Profile Page**

Implementation of the project with following features:
1. Users can register
2. Users can login and logout
3. Users should be edit and save their profile(Replicate Linkedin profile page)
   - Basic Profile
   - Experience
   - Education
   - Skills

Technologies used for the Backend: Spring Boot, Mysql

<br>
---

# Directory Structure

*Runnable main application path : `src > main > java > com.kodem.demo.linkedindemo`*

 `MainApplication.java`

<br>
---

# User Functionality
### **User Model**
Folder Location : `com.kodem.demo.linkedindemo.user`
```
User.java
UserController.java
UserService.java
UserRepository.java(Interface)
```
<br>
---

# Experience Functionality
### **Company Model**
Folder Location : `com.kodem.demo.linkedindemo.experienceModels.company`
```
Company.java
CompanyController.java
CompanyService.java
CompanyRepository.java (Interface)
```
<br>

### **Experience Model**
Folder Location : `com.kodem.demo.linkedindemo.experienceModels.company`
```
Experience.java
ExperienceSeriializer.java
ExperienceController.java
ExperienceService.java
ExperienceRepository.java (Interface)
```
<br>
---

# Education Functionality
### **Institution Model**
Folder Location : `com.kodem.demo.linkedindemo.educationModels.institution`
```
Institution.java
InstitutionController.java
InstitutionService.java
InstitutionRepository.java (Interface)
```

<br>

### **Experience Model**
Folder Location : `com.kodem.demo.linkedindemo.experienceModels.company`
```
Education.java
EducationSeriializer.java
EducationController.java
EducationService.java
EducationRepository.java (Interface)
```
<br>
---


# Skills Functionality
### **Language Model**
Folder Location : `com.kodem.demo.linkedindemo.skillsModels.language`
```
Language.java
LanguageController.java
LanguageService.java
LanguageRepository.java (Interface)
```

<br>

### **Skill Model**
Folder Location : `com.kodem.demo.linkedindemo.skillsModels.skill`
```
Skill.java
SkillSeriializer.java
SkillController.java
SkillService.java
SkillRepository.java (Interface)
```
