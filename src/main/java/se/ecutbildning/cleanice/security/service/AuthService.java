package se.ecutbildning.cleanice.security.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.ecutbildning.cleanice.entities.AppUser;

import java.util.Set;

@Service
public class AuthService {

    public static boolean isAdmin() {
        AppUser appUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return appUser.getAuthorities().contains(Set.of("ADMIN"));
    }


}
