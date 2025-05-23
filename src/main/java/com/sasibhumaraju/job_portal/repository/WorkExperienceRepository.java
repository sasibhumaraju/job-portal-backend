package com.sasibhumaraju.job_portal.repository;

import com.sasibhumaraju.job_portal.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, String> {
    List<WorkExperience> findAllByAppUser_Id(String id);
}
