package com.sasibhumaraju.job_portal.service;

import com.sasibhumaraju.job_portal.DTO.WorkExperienceDTO;
import com.sasibhumaraju.job_portal.mapper.WorkExperienceMapper;
import com.sasibhumaraju.job_portal.model.AppUser;
import com.sasibhumaraju.job_portal.model.WorkExperience;
import com.sasibhumaraju.job_portal.repository.AppUserRepository;
import com.sasibhumaraju.job_portal.repository.WorkExperienceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkExperienceService {

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Autowired
    private AppUserRepository appUserRepository;


    public WorkExperienceDTO getWorkExperience(String id) {
       WorkExperience w = workExperienceRepository.findById(id).orElse(null);
       if(w == null) return null;
       return WorkExperienceMapper.toDTO(w);
    }

    public List<WorkExperienceDTO> getAllWorkExperience() {
        return workExperienceRepository.findAll().stream()
                .map(WorkExperienceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public WorkExperienceDTO addWorkExperience(WorkExperienceDTO workExperienceDTO) {
        AppUser a = appUserRepository.findById(workExperienceDTO.getAppUserID()).orElse(null);
        if(a == null) return null;
        WorkExperience w = workExperienceRepository.save(WorkExperienceMapper.toModel(workExperienceDTO,a));
        return WorkExperienceMapper.toDTO(w);
    }

    public WorkExperienceDTO updateWorkExperience(String id, WorkExperienceDTO workExperienceDTO) {
        AppUser a = appUserRepository.findById(workExperienceDTO.getAppUserID()).orElse(null);
        if(a == null) return null;
        WorkExperience w = workExperienceRepository.save(WorkExperienceMapper.toModel(workExperienceDTO,a));
        return WorkExperienceMapper.toDTO(w);
    }

    public void deleteWorkExperience(String id) {
        WorkExperience workExperience = workExperienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkExperience not found"));

        // Break the bidirectional relationship
        AppUser user = workExperience.getAppUser();
        if (user != null) {
            user.getWorkExperience().removeIf(w -> w.getId().equals(id));
            workExperience.setAppUser(null); // Break back-reference
        }

        workExperienceRepository.delete(workExperience);
    }

    public List<WorkExperienceDTO> getAllWorkExperiencesByAppUser(String appUserID) {
        return workExperienceRepository.findAllByAppUser_Id(appUserID).stream()
                .map(WorkExperienceMapper::toDTO)
                .collect(Collectors.toList());
    }
}
