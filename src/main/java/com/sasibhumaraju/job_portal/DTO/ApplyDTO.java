package com.sasibhumaraju.job_portal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDTO {

    private String id;
    private String jobPostingID;
    private String appUserID;

    private Float expectedSalary;
    private String comment;
}
