package com.sasibhumaraju.job_portal.user;

import com.sasibhumaraju.job_portal.DTO.AppUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/app-users")
@CrossOrigin(origins = {"https://jobspoortal.web.app","http://localhost:5173"})
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public List<AppUserDTO> getAppUsers() {
        return appUserService.getAllAppUsers();
    }

    @GetMapping("{id}")
    public Optional<AppUserDTO> getAppUser(@PathVariable String id) {
        return appUserService.getAppUser(id);
    }

    @GetMapping("search-by-email")
    public Optional<AppUserDTO> getUserByEmail(@RequestParam String email) {
        return appUserService.getAppUserByEmail(email);
    }

    @PostMapping
    public AppUserDTO addAppUser(@RequestBody AppUserDTO appUserDTO) throws Exception {
        return appUserService.addAppUser(appUserDTO);
    }

    @PutMapping("{id}")
    public AppUserDTO updateAppUser(@PathVariable String id, @Validated @RequestBody AppUserDTO appUserDTO) throws Exception {
        return appUserService.updateAppUser(id, appUserDTO);
    }

    @DeleteMapping("{id}")
    public void deleteAppUser(@PathVariable String id) {
        appUserService.deleteAppUser(id);
    }

}
