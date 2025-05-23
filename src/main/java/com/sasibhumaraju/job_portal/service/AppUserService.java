package com.sasibhumaraju.job_portal.service;

import com.sasibhumaraju.job_portal.DTO.AppUserDTO;
import com.sasibhumaraju.job_portal.DTO.ApplyDTO;
import com.sasibhumaraju.job_portal.mapper.AppUserMapper;
import com.sasibhumaraju.job_portal.model.AppUser;
import com.sasibhumaraju.job_portal.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public List<AppUserDTO> getAllAppUsers() {
        return appUserRepository.findAll()
                .stream()
                .map(AppUserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AppUserDTO getAppUser(String id) {
        AppUser appUser = appUserRepository.findById(id).orElse(null);
        if(appUser == null) return null;
        return AppUserMapper.toDTO(appUser);
    }

    public AppUserDTO addAppUser(AppUserDTO appUserDTO) {
        appUserRepository.save(AppUserMapper.toModel(appUserDTO));
        AppUser appUser = appUserRepository.findByEmail(appUserDTO.getEmail());
        if(appUser == null) return null;
        return AppUserMapper.toDTO(appUser);
    }

    public AppUserDTO updateAppUser(String id, AppUserDTO appUserDTO) {
        AppUser a2 = AppUserMapper.toModel(appUserDTO);
        appUserRepository.save(a2);
        AppUser a = appUserRepository.findByEmail(appUserDTO.getEmail());
        return AppUserMapper.toDTO(a);
    }

    public void deleteAppUser(String id) {
        AppUser a = appUserRepository.findById(id).orElse(null);
        if( a  == null ) return;
        appUserRepository.delete(a);
    }

    public AppUserDTO getAppUserByEmail(String email) {
        AppUser appUser = appUserRepository.findByEmail(email);
        if(appUser == null) return null;
        return AppUserMapper.toDTO(appUser);
    }
}
