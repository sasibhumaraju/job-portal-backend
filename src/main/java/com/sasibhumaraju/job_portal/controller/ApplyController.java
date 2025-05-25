package com.sasibhumaraju.job_portal.controller;

import com.sasibhumaraju.job_portal.DTO.AppUserDTO;
import com.sasibhumaraju.job_portal.DTO.ApplyDTO;
import com.sasibhumaraju.job_portal.DTO.ApplyDTODeep;
import com.sasibhumaraju.job_portal.model.Apply;
import com.sasibhumaraju.job_portal.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/applies")
@CrossOrigin(origins = {"https://job-portal-360.web.app","http://localhost:5173"})
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @GetMapping("{id}")
    public ApplyDTO getApply(String id) {
        return applyService.getApply(id);
    }

    @GetMapping("job-postings/{jobPostingID}/app-users/{appUserID}")
    public ApplyDTO getApplyByJobPostingAndByAppUser(@PathVariable  String jobPostingID, @PathVariable String appUserID) {
        return applyService.getApplyByJobPostingAndByAppUser(jobPostingID,appUserID);
    }

    @GetMapping
    public List<Apply> getAllApplies() {
        return applyService.getAllApplies();
    }

    @GetMapping("app-users/{appUserID}")
    public List<ApplyDTODeep> getAllAppliesByAppUser(@PathVariable String appUserID) {
        return applyService.getAllAppliesByAppUser(appUserID);
    }

    @GetMapping("job-postings/{jobPostingID}")
    public List<ApplyDTODeep> getAllAppliesByJobPosting(@PathVariable String jobPostingID) {
        return applyService.getAllAppliesByJobPosting(jobPostingID);
    }

    @PostMapping
    public ApplyDTO addApply(@RequestBody ApplyDTO applyDTO) {
        return applyService.addApply(applyDTO);
    }

    @PutMapping("{id}")
    public Apply updateApply(@PathVariable String id, @RequestBody Apply apply) {
        return applyService.updateApply(id,apply);
    }

    @DeleteMapping("{id}")
    public void deleteApply(@PathVariable String id) {
        applyService.deleteApply(id);
    }
}
