package com.sasibhumaraju.job_portal.mapper;

import com.sasibhumaraju.job_portal.DTO.AppUserDTO;
import com.sasibhumaraju.job_portal.model.AppUser;

public class AppUserMapper {
    public static AppUserDTO toDTO(AppUser appUser) {
        return new AppUserDTO(appUser.getId(), appUser.getName(), appUser.getEmail(), appUser.getPassword(), appUser.getPhone(), appUser.getRole());
    }

    public static AppUser toModel(AppUserDTO appUserDTO) {
        AppUser appUser = new AppUser();
        appUser.setId(appUserDTO.getId());
        appUser.setName(appUserDTO.getName());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPassword(appUserDTO.getPassword());
        appUser.setPhone(appUserDTO.getPhone());
        appUser.setRole(appUserDTO.getRole());
        return appUser;
    }
}
