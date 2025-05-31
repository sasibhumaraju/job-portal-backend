package com.sasibhumaraju.job_portal.controller;

import com.sasibhumaraju.job_portal.DTO.WorkExperienceDTO;
import com.sasibhumaraju.job_portal.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/work-experiences")
@CrossOrigin(origins = {"https://jobspoortal.web.app","https://job-portal-360.web.app","http://localhost:5173"})
public class WorkExperienceController {

    @Autowired
    private WorkExperienceService workExperienceService;

    @GetMapping("{id}")
    public WorkExperienceDTO getWorkExperience(@PathVariable String id) {
        return workExperienceService.getWorkExperience(id);
    }

    @GetMapping
    public List<WorkExperienceDTO> getAllWorkExperiences() {
        return workExperienceService.getAllWorkExperience();
    }

    @GetMapping("app-users/{appUserID}")
    public List<WorkExperienceDTO> getAllWorkExperiencesByAppUser(@PathVariable String appUserID) {
        return workExperienceService.getAllWorkExperiencesByAppUser(appUserID);
    }

    @PostMapping
    public WorkExperienceDTO addWorkExperience(@RequestBody WorkExperienceDTO workExperienceDTO) {
        return workExperienceService.addWorkExperience(workExperienceDTO);
    }

    @PutMapping("{id}")
    public WorkExperienceDTO updateWorkExperience(@PathVariable String id, @RequestBody WorkExperienceDTO workExperience) {
        return workExperienceService.updateWorkExperience(id,workExperience);
    }

    @DeleteMapping("{id}")
    public void deleteWorkExperience(@PathVariable String id) {
        System.out.println("delete exp called.. ");
        workExperienceService.deleteWorkExperience(id);
    }
}
