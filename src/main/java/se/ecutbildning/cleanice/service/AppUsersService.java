package se.ecutbildning.cleanice.service;

import org.springframework.stereotype.Service;
import se.ecutbildning.cleanice.entities.AppUser;
import se.ecutbildning.cleanice.repository.AppUserRepository;

import java.util.List;

@Service
public class AppUsersService {

    private final AppUserRepository appUserRepository;

    public AppUsersService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser findUserById(long userId) {
        return appUserRepository.findById(userId).orElseThrow();
    }

    public void deleteUserById(long id) {
        appUserRepository.deleteById(id);
    }
}
