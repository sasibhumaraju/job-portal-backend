package com.sasibhumaraju.job_portal.repository;

import com.sasibhumaraju.job_portal.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    AppUser findByEmail(String email);
}
