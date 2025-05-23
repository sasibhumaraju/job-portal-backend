package com.sasibhumaraju.job_portal.service;

import com.sasibhumaraju.job_portal.DTO.JobPostingDTO;
import com.sasibhumaraju.job_portal.mapper.JobPostingMapper;
import com.sasibhumaraju.job_portal.model.AppUser;
import com.sasibhumaraju.job_portal.model.JobPosting;
import com.sasibhumaraju.job_portal.repository.AppUserRepository;
import com.sasibhumaraju.job_portal.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public JobPostingDTO getJobPosting(String id) {
        JobPosting jP = jobPostingRepository.findById(id).orElse(null);
        return JobPostingMapper.toDTO(Objects.requireNonNull(jP));
    }

    public List<JobPostingDTO> getAllJobPostings() {
        return jobPostingRepository.findAll()
                .stream()
                .map(JobPostingMapper::toDTO)
                .collect(Collectors.toList());
    }

    public JobPostingDTO addJobPosting(JobPostingDTO jobPostingDTO) {
        AppUser a = appUserRepository.findById(jobPostingDTO.getAppUserID()).orElse(null);
        JobPosting j = jobPostingRepository.save(JobPostingMapper.toModel(jobPostingDTO,Objects.requireNonNull(a)));
        return JobPostingMapper.toDTO(j);
    }

    public JobPostingDTO updateJobPosting(String id, JobPostingDTO jobPostingDTO) {
        JobPosting jP = jobPostingRepository.findById(id).orElse(null);
        if(jP == null) return null;
        AppUser a = appUserRepository.findById(jobPostingDTO.getAppUserID()).orElse(null);
        return JobPostingMapper.toDTO(jobPostingRepository.save(JobPostingMapper.toModel(jobPostingDTO,Objects.requireNonNull(a))));
    }

    public void deleteJobPOsting(String id) {
        JobPosting jP = jobPostingRepository.findById(id).orElse(null);
        if(jP == null) return;
        jobPostingRepository.delete(jP);
    }

    public List<JobPostingDTO> getAllJobPostingsByKeyword(String keyword) {
        return jobPostingRepository.findByCommentContainingIgnoreCaseOrDesignationContainingIgnoreCase(keyword,keyword)
                .stream()
                .map(JobPostingMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<JobPostingDTO> getAllJobPostingsByAppUser(String appUserID) {
        return jobPostingRepository.findAllByEmployer_Id(appUserID)
                .stream()
                .map(JobPostingMapper::toDTO)
                .collect(Collectors.toList());
    }
}
