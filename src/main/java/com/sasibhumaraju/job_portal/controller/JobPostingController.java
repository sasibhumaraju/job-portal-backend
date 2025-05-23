package com.sasibhumaraju.job_portal.controller;

import com.sasibhumaraju.job_portal.DTO.JobPostingDTO;
import com.sasibhumaraju.job_portal.model.JobPosting;
import com.sasibhumaraju.job_portal.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/job-postings")
@CrossOrigin("http://localhost:5173")
public class JobPostingController {

    @Autowired
    private JobPostingService jobPostingService;

    @GetMapping("{id}")
    public JobPostingDTO getJobPosting(@PathVariable String id) {
        return jobPostingService.getJobPosting(id);
    }

    @GetMapping
    public List<JobPostingDTO> getAllJobPostings() {
        return jobPostingService.getAllJobPostings();
    }

    @GetMapping("app-users/{appUserID}")
    public List<JobPostingDTO> getAllJobPostingsByAppUser(@PathVariable String appUserID) {
        return jobPostingService.getAllJobPostingsByAppUser(appUserID);
    }

    @GetMapping("search")
    public List<JobPostingDTO> getAllJobPostingsByKeyword(@RequestParam String keyword) {
        System.out.println("search method called -> keyword is "  + keyword+ "<-");
        return jobPostingService.getAllJobPostingsByKeyword(keyword);
    }

    @PostMapping
    public JobPostingDTO addJobPosting(@RequestBody JobPostingDTO jobPostingDTO) {
        return jobPostingService.addJobPosting(jobPostingDTO);
    }

    @PutMapping("{id}")
    public JobPostingDTO updateJobPosting(@PathVariable String id, @RequestBody JobPostingDTO jobPostingDTO) {
        return jobPostingService.updateJobPosting(id,jobPostingDTO);
    }

    @DeleteMapping("{id}")
    public void deleteJobPosting(@PathVariable String id) {
        jobPostingService.deleteJobPOsting(id);
    }



}
