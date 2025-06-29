package com.sasibhumaraju.job_portal.mapper;

import com.sasibhumaraju.job_portal.DTO.WorkExperienceDTO;
import com.sasibhumaraju.job_portal.user.AppUser;
import com.sasibhumaraju.job_portal.model.WorkExperience;

public class WorkExperienceMapper {

    public static WorkExperienceDTO toDTO(WorkExperience workExperience) {
        return new WorkExperienceDTO(workExperience.getId(), workExperience.getAppUser().getId(), workExperience.getCompanyName(), workExperience.getYearsWorked(), workExperience.getDesignation(), workExperience.getComment());
    }

    public static WorkExperience toModel(WorkExperienceDTO workExperienceDTO, AppUser appUser) {
        WorkExperience workExperience = new WorkExperience();

        workExperience.setId(workExperienceDTO.getId());
        workExperience.setAppUser(appUser);
        workExperience.setCompanyName(workExperienceDTO.getCompanyName());
        workExperience.setYearsWorked(workExperienceDTO.getYearsWorked());
        workExperience.setDesignation(workExperienceDTO.getDesignation());
        workExperience.setComment(workExperienceDTO.getComment());

        return workExperience;
    }
}
