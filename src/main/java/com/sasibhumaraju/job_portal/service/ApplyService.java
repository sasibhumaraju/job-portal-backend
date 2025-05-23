package com.sasibhumaraju.job_portal.service;

import com.sasibhumaraju.job_portal.DTO.ApplyDTO;
import com.sasibhumaraju.job_portal.DTO.ApplyDTODeep;
import com.sasibhumaraju.job_portal.mapper.ApplyMapper;
import com.sasibhumaraju.job_portal.model.AppUser;
import com.sasibhumaraju.job_portal.model.Apply;
import com.sasibhumaraju.job_portal.model.JobPosting;
import com.sasibhumaraju.job_portal.repository.AppUserRepository;
import com.sasibhumaraju.job_portal.repository.ApplyRepository;
import com.sasibhumaraju.job_portal.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ApplyService {

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public ApplyDTO getApply(String id) {
        Apply a = applyRepository.findById(id).orElse(null);
        return ApplyMapper.toDTO(Objects.requireNonNull(a));
    }

    public List<Apply> getAllApplies() {
        return applyRepository.findAll();
    }

    public ApplyDTO addApply(ApplyDTO applyDTO) {
        JobPosting jP = jobPostingRepository.findById(applyDTO.getJobPostingID()).orElse(null);
        AppUser aU = appUserRepository.findById(applyDTO.getAppUserID()).orElse(null);
        Apply a = applyRepository.save(ApplyMapper.toModel(applyDTO, Objects.requireNonNull(jP),Objects.requireNonNull(aU)));
        return ApplyMapper.toDTO(a);
    }

    public Apply updateApply(String id, Apply apply) {
        Apply apply1 = applyRepository.findById(id).orElse(null);
        if(apply1 == null) return null;
        return applyRepository.save(apply);
    }

    public void deleteApply(String id) {
        Apply apply1 = applyRepository.findById(id).orElse(null);
        if(apply1 == null) return;
        applyRepository.delete(apply1);
    }

    public ApplyDTO getApplyByJobPostingAndByAppUser(String jobPostingID, String appUserID) {
        Apply a = applyRepository.findByJobPosting_IdAndJobSeeker_Id(jobPostingID,appUserID);
        if(a==null) return null;
        return ApplyMapper.toDTO(a);
    }


    public List<ApplyDTODeep> getAllAppliesByAppUser(String appUserID) {
        List<Apply> applies = applyRepository.findAllByJobSeeker_Id(appUserID);
        return applies.stream()
                .map(ApplyMapper::toDTODeep)
                .toList();
    }

    public List<ApplyDTODeep> getAllAppliesByJobPosting(String jobPostingID) {
        return applyRepository.findAllByJobPosting_Id(jobPostingID)
                .stream()
                .map(ApplyMapper::toDTODeep)
                .toList();
    }
}
