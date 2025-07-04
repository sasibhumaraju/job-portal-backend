package com.sasibhumaraju.job_portal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDTODeep {
    private String id;
    private JobPostingDTO jobPostingDTO;
    private AppUserDTO appUserDTO;

    private Float expectedSalary;
    private String comment;
}
