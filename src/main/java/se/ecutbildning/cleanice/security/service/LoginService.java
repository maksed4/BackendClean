package se.ecutbildning.cleanice.security.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.ecutbildning.cleanice.entities.AppUser;
import se.ecutbildning.cleanice.entities.Enums.ERole;
import se.ecutbildning.cleanice.entities.dto.WhoAmIDTO;
import se.ecutbildning.cleanice.payload.response.MessageResponse;
import se.ecutbildning.cleanice.repository.AppUserRepository;
import se.ecutbildning.cleanice.security.jwt.JwtUtil;

import java.util.HashSet;
import java.util.Set;

@Service
public class LoginService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AppUserRepository appUserRepository;

    public LoginService(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AppUserRepository appUserRepository) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.appUserRepository = appUserRepository;
    }

    public ResponseEntity<String> login(String username, String password) {
        AppUser appUser = (AppUser) userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, appUser.getPassword())) {
            return ResponseEntity.ok(jwtUtil.generateToken(appUser));
        } else {
            return ResponseEntity.status(401).body("Username or password is incorrect");
        }
    }

    public WhoAmIDTO whoAmI(String token) {
        return jwtUtil.parseToken(token);
    }

    public ResponseEntity<?> signUp(String username, String password, String email, Set<ERole> roles) {
        if (appUserRepository.existsAppUserByUsername(username))
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));

        if (appUserRepository.existsAppUserByEmail(email))
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));

        AppUser user = new AppUser(
                username,
                email,
                passwordEncoder.encode(password)
        );

        Set<ERole> userRoles = new HashSet<>();

        if (roles == null)
            userRoles.add(ERole.USER);
        else {
            roles.forEach(role -> {
                switch (role) {
                    case ADMIN -> {
                        roles.add(ERole.ADMIN);
                    }
                    case CLEANER -> {
                        roles.add(ERole.CLEANER);
                    }
                    default -> {
                        roles.add(ERole.USER);
                    }
                }
            });
        }
        user.setRoles(roles);
        appUserRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User successfully registered"));
    }
}
