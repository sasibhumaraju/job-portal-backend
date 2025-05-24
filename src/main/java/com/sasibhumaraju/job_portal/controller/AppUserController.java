package com.sasibhumaraju.job_portal.controller;

import com.sasibhumaraju.job_portal.DTO.AppUserDTO;
import com.sasibhumaraju.job_portal.DTO.ApplyDTO;
import com.sasibhumaraju.job_portal.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/app-users")
@CrossOrigin("https://job-portal-360.web.app")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public List<AppUserDTO> getAppUsers() {
        return appUserService.getAllAppUsers();
    }

    @GetMapping("{id}")
    public AppUserDTO getAppUser(@PathVariable String id) {
        return appUserService.getAppUser(id);
    }

    @GetMapping("search-by-email")
    public AppUserDTO getUserByEmail(@RequestParam String email) {
        return appUserService.getAppUserByEmail(email);
    }

    @PostMapping
    public AppUserDTO addAppUser(@RequestBody AppUserDTO appUserDTO) {
        return appUserService.addAppUser(appUserDTO);
    }

    @PutMapping("{id}")
    public AppUserDTO updateAppUser(@PathVariable String id, @Validated @RequestBody AppUserDTO appUserDTO) {
        return appUserService.updateAppUser(id, appUserDTO);
    }

    @DeleteMapping("{id}")
    public void deleteAppUser(@PathVariable String id) {
        appUserService.deleteAppUser(id);
    }

}
