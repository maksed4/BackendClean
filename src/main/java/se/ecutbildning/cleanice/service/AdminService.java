package se.ecutbildning.cleanice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.ecutbildning.cleanice.entities.AppUser;
import se.ecutbildning.cleanice.entities.Cleaner;
import se.ecutbildning.cleanice.entities.dto.CleanerDTO;
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

    public ResponseEntity<?> cleanerSignUp(CleanerDTO cleanerDTO) {
        AppUser user = appUserRepository.findAppUserById(cleanerDTO.id()).orElseThrow();

        Cleaner cleaner = new Cleaner(user, cleanerDTO.firstname(), cleanerDTO.lastname(), cleanerDTO.city());

        cleanerRepository.save(cleaner);
        return ResponseEntity.ok(new MessageResponse("Cleaner successfully registered"));
    }
}
