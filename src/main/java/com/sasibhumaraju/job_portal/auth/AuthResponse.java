package com.sasibhumaraju.job_portal.auth;

import com.sasibhumaraju.job_portal.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String id;
    private String jwtToken;
    private String email;
    private String phone;
    private Role role;

}
