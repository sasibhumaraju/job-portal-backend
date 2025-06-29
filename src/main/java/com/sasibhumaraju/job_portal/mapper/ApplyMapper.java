package com.sasibhumaraju.job_portal.mapper;

import com.sasibhumaraju.job_portal.DTO.ApplyDTO;
import com.sasibhumaraju.job_portal.DTO.ApplyDTODeep;
import com.sasibhumaraju.job_portal.user.AppUser;
import com.sasibhumaraju.job_portal.model.Apply;
import com.sasibhumaraju.job_portal.model.JobPosting;

public class ApplyMapper {

    public static ApplyDTO toDTO(Apply apply) {
        return new ApplyDTO(apply.getId(), apply.getJobPosting().getId(), apply.getJobSeeker().getId(), apply.getExpectedSalary(), apply.getComment());
    }

    public static Apply toModel(ApplyDTO applyDTO, JobPosting jobPosting, AppUser jobSeeker) {
        Apply apply = new Apply();
        apply.setId(applyDTO.getId());
        apply.setComment(applyDTO.getComment());
        apply.setExpectedSalary(applyDTO.getExpectedSalary());
        apply.setJobSeeker(jobSeeker);
        apply.setJobPosting(jobPosting);
        return apply;
    }


    public static ApplyDTODeep toDTODeep(Apply apply) {
        return new ApplyDTODeep(apply.getId(), JobPostingMapper.toDTO(apply.getJobPosting()), AppUserMapper.toDTO(apply.getJobSeeker()), apply.getExpectedSalary(), apply.getComment());
    }

    public static Apply toModelDeep(ApplyDTO applyDTO, JobPosting jobPosting, AppUser jobSeeker) {
        Apply apply = new Apply();
        apply.setId(applyDTO.getId());
        apply.setComment(applyDTO.getComment());
        apply.setExpectedSalary(applyDTO.getExpectedSalary());
        apply.setJobSeeker(jobSeeker);
        apply.setJobPosting(jobPosting);
        return apply;
    }
}
