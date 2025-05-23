package com.sasibhumaraju.job_portal.repository;

import com.sasibhumaraju.job_portal.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostingRepository extends JpaRepository<JobPosting, String> {

    List<JobPosting> findByCommentContainingIgnoreCaseOrDesignationContainingIgnoreCase(String companyKeyword, String designationKeyword);

    List<JobPosting> findAllByEmployer_Id(String appUserID);

}
