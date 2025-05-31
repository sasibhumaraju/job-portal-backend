package com.sasibhumaraju.job_portal.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingDTO {

    private String id;
    private String appUserID;
    private String appUserEmail;

    private String companyName;
    private String designation;
    @Column(columnDefinition = "TEXT")
    private String comment;
    private String jobLink;
}
