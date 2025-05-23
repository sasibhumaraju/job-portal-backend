package com.sasibhumaraju.job_portal.mapper;

import com.sasibhumaraju.job_portal.DTO.JobPostingDTO;
import com.sasibhumaraju.job_portal.model.AppUser;
import com.sasibhumaraju.job_portal.model.JobPosting;

public class JobPostingMapper {

    public static JobPostingDTO toDTO(JobPosting jobPosting) {
        return new JobPostingDTO(jobPosting.getId(), jobPosting.getEmployer().getId(), jobPosting.getEmployer().getEmail(), jobPosting.getCompanyName(), jobPosting.getDesignation(), jobPosting.getComment());
    }

    public static JobPosting toModel(JobPostingDTO jobPostingDTO, AppUser employer) {
        JobPosting jobPosting = new JobPosting();
        jobPosting.setId(jobPosting.getId());
        jobPosting.setEmployer(employer);
        jobPosting.setCompanyName(jobPostingDTO.getCompanyName());
        jobPosting.setDesignation(jobPostingDTO.getDesignation());
        jobPosting.setComment(jobPostingDTO.getComment());
        return jobPosting;
    }
}
