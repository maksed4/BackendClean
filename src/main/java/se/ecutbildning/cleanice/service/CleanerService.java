package se.ecutbildning.cleanice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.ecutbildning.cleanice.entities.Cleaning;
import se.ecutbildning.cleanice.entities.dto.AssignCleanerDTO;
import se.ecutbildning.cleanice.entities.dto.CleaningCompletedDTO;
import se.ecutbildning.cleanice.entities.dto.CleaningRequestDTO;
import se.ecutbildning.cleanice.payload.response.MessageResponse;
import se.ecutbildning.cleanice.repository.CleanerRepository;
import se.ecutbildning.cleanice.repository.CleaningRepository;

@Service
public class CleanerService {

    private final CleanerRepository cleanerRepository;
    private final CleaningRepository cleaningRepository;

    public CleanerService(CleanerRepository cleanerRepository, CleaningRepository cleaningRepository) {
        this.cleanerRepository = cleanerRepository;
        this.cleaningRepository = cleaningRepository;
    }

    public ResponseEntity<?> completedCleaning(CleaningCompletedDTO cleaningCompletedDTO) {
        Cleaning cleaning = cleaningRepository.findById(cleaningCompletedDTO.cleaningId()).orElseThrow();

        /** CHECK IF CLEANERS ASSIGNMENT **/
        if (cleaning.getCleaner().getId() != cleaningCompletedDTO.cleanerId())
            return ResponseEntity.badRequest().body(new MessageResponse("Not your job!"));

        cleaning.setDone(cleaningCompletedDTO.isCompleted());
        cleaningRepository.save(cleaning);
        return ResponseEntity.ok(new MessageResponse("Cleaning is completed: " + cleaningCompletedDTO.isCompleted()));
    }
}
