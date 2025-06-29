package com.sasibhumaraju.job_portal.user;

import com.sasibhumaraju.job_portal.DTO.AppUserDTO;
import com.sasibhumaraju.job_portal.mapper.AppUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(username).orElseThrow( () -> new UsernameNotFoundException(username + " Not Found.."));
        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }

    public List<AppUserDTO> getAllAppUsers() {
        return appUserRepository.findAll()
                .stream()
                .map(AppUserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AppUserDTO> getAppUser(String id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        return appUser.map(AppUserMapper::toDTO);
    }

    public AppUserDTO addAppUser(AppUserDTO appUserDTO) throws Exception {
        appUserRepository.save(AppUserMapper.toModel(appUserDTO));
        AppUser appUser = appUserRepository.findByEmail(appUserDTO.getEmail()).orElseThrow( () -> new Exception("User Creation Failed.."));
        return AppUserMapper.toDTO(appUser);
    }

    public AppUserDTO updateAppUser(String id, AppUserDTO appUserDTO) throws Exception {
        AppUser a2 = AppUserMapper.toModel(appUserDTO);
        appUserRepository.save(a2);
        AppUser a = appUserRepository.findByEmail(appUserDTO.getEmail()).orElseThrow( () -> new Exception("User Update Failed.."));;
        return AppUserMapper.toDTO(a);
    }

    public void deleteAppUser(String id) {
        AppUser a = appUserRepository.findById(id).orElse(null);
        if( a  == null ) return;
        appUserRepository.delete(a);
    }

    public Optional<AppUserDTO>  getAppUserByEmail(String email) {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        return appUser.map(AppUserMapper::toDTO);
    }

}
