package com.sasibhumaraju.job_portal.DTO;

import com.sasibhumaraju.job_portal.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Role role;

}
