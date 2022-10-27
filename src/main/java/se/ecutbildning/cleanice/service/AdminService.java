package se.ecutbildning.cleanice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.ecutbildning.cleanice.entities.AppUser;
import se.ecutbildning.cleanice.entities.Cleaner;
import se.ecutbildning.cleanice.payload.response.MessageResponse;
import se.ecutbildning.cleanice.repository.AppUserRepository;
import se.ecutbildning.cleanice.repository.CleanerRepository;

@Service
public class AdminService {

    private final AppUserRepository appUserRepository;
    private final CleanerRepository cleanerRepository;

    public AdminService(AppUserRepository appUserRepository, CleanerRepository cleanerRepository) {
        this.appUserRepository = appUserRepository;
        this.cleanerRepository = cleanerRepository;
    }

    public ResponseEntity<?> cleanerSignUp(long id, String firstname, String lastname, String city) {
        AppUser user = appUserRepository.findAppUserById(id).orElseThrow();

        Cleaner cleaner = new Cleaner(user, firstname, lastname, city);

        cleanerRepository.save(cleaner);
        return ResponseEntity.ok(new MessageResponse("Cleaner successfully registered"));
    }
}
