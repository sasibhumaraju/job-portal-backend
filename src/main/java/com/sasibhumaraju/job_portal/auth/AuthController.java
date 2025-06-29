package com.sasibhumaraju.job_portal.auth;

import com.sasibhumaraju.job_portal.config.JwtService;
import com.sasibhumaraju.job_portal.user.AppUser;
import com.sasibhumaraju.job_portal.user.AppUserRepository;
import com.sasibhumaraju.job_portal.user.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = {"https://jobspoortal.web.app","http://localhost:5173"})
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest regRequest) {
        AppUser appUser = AppUser.builder()
                .email(regRequest.getEmail())
                .phone(regRequest.getPhone())
                .role(regRequest.getRole())
                .name(regRequest.getName())
                .password(passwordEncoder.encode(regRequest.getPassword()))
                .build();
        AppUser a = appUserRepository.save(appUser);
        String jwtToken = jwtService.buildToken(appUser);

        return AuthResponse.builder()
                .jwtToken(jwtToken)
                .id(a.getId())
                .role(appUser.getRole())
                .phone(appUser.getPhone())
                .email(appUser.getEmail())
                .build();
    }

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            authRequest.getEmail(), authRequest.getPassword()
        ));

        AppUser appUser = appUserRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        String jwtToken = jwtService.buildToken(appUser);
        return AuthResponse.builder()
                .jwtToken(jwtToken)
                .id(appUser.getId())
                .role(appUser.getRole())
                .phone(appUser.getPhone())
                .email(appUser.getEmail())
                .build();
    }
}
