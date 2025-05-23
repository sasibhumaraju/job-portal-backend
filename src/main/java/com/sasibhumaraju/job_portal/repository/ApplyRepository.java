package com.sasibhumaraju.job_portal.repository;

import com.sasibhumaraju.job_portal.model.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ApplyRepository extends JpaRepository<Apply, String> {
    Apply findByJobPosting_IdAndJobSeeker_Id(String jobPostingID,String appUserID);
    List<Apply> findAllByJobSeeker_Id(String appUserID);
    List<Apply> findAllByJobPosting_Id(String jobPostingID);
}
