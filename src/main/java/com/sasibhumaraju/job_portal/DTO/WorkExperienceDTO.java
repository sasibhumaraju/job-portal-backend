package com.sasibhumaraju.job_portal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceDTO {

    private String id;
    private String appUserID;

    private String companyName;
    private String yearsWorked;
    private String designation;
    private String comment;

}
