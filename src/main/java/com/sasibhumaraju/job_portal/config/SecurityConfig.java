package com.sasibhumaraju.job_portal.config;

import com.sasibhumaraju.job_portal.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/v1/job-postings/**","/api/v1/app-users/**").permitAll()
                                .requestMatchers("/api/v1/app-users/search-by-email").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/job-postings/**").hasRole(Role.EMPLOYER.name())
                                .requestMatchers(HttpMethod.PUT, "/api/v1/job-postings/**").hasRole(Role.EMPLOYER.name())
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/job-postings/**").hasRole(Role.EMPLOYER.name())
                                .requestMatchers(HttpMethod.POST, "/api/v1/applies/**").hasRole(Role.JOB_SEEKER.name())
                                .requestMatchers(HttpMethod.PUT, "/api/v1/applies/**").hasRole(Role.JOB_SEEKER.name())
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/applies/**").hasRole(Role.JOB_SEEKER.name())
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .formLogin(FormLoginConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
