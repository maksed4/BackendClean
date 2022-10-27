package se.ecutbildning.cleanice.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.ecutbildning.cleanice.entities.AppUser;
import se.ecutbildning.cleanice.entities.dto.AppUserResponseDTO;
import se.ecutbildning.cleanice.security.service.AuthService;
import se.ecutbildning.cleanice.service.AppUsersService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {
        RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST, RequestMethod.OPTIONS
})
public class AppUsersController {

    private final AppUsersService appUsersService;
    private final AuthService authService;

    public AppUsersController(AppUsersService appUsersService, AuthService authService) {
        this.appUsersService = appUsersService;
        this.authService = authService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AppUserResponseDTO> getAllUsers() {
        return appUsersService.getAllUsers()
                .stream()
                .map(AppUser::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public AppUserResponseDTO getUser(@PathVariable("id") long userId) {
        return appUsersService.findUserById(userId).toResponseDTO();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(@PathVariable long id){
        appUsersService.deleteUserById(id);
    }
}
