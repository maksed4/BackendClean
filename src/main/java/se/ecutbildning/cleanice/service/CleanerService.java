package se.ecutbildning.cleanice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.ecutbildning.cleanice.entities.CleanAssign;
import se.ecutbildning.cleanice.entities.Cleaner;
import se.ecutbildning.cleanice.entities.Cleaning;
import se.ecutbildning.cleanice.entities.dto.CleanerDTO;
import se.ecutbildning.cleanice.entities.dto.CleaningCompletedDTO;
import se.ecutbildning.cleanice.payload.response.MessageResponse;
import se.ecutbildning.cleanice.repository.CleanAssignRepository;
import se.ecutbildning.cleanice.repository.CleanerRepository;
import se.ecutbildning.cleanice.repository.CleaningRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CleanerService {

    private final CleanerRepository cleanerRepository;
    private final CleaningRepository cleaningRepository;
    private final CleanAssignRepository cleanAssignRepository;

    public CleanerService(
            CleanerRepository cleanerRepository,
            CleaningRepository cleaningRepository,
            CleanAssignRepository cleanAssignRepository
    ) {
        this.cleanerRepository = cleanerRepository;
        this.cleaningRepository = cleaningRepository;
        this.cleanAssignRepository = cleanAssignRepository;
    }

    public List<Cleaning> getMyCleanings(long id) {
        List<CleanAssign> cleanAssignList = cleanAssignRepository.findAll()
                .stream()
                .filter(cleanAssign -> cleanAssign.getCleaner().getId() == id)
                .toList();

        List<Cleaning> cleanings = new ArrayList<>();

        for (Cleaning cleaning : cleaningRepository.findAll()) {
            for (CleanAssign cleanAssign : cleanAssignList) {
                if (cleaning.getId() == cleanAssign.getCleaning().getId()) {
                    cleanings.add(cleaning);
                }
            }
        }

        return cleanings;
    }

    public ResponseEntity<?> completedCleaning(CleaningCompletedDTO cleaningCompletedDTO) {
        Cleaning cleaning = cleaningRepository.findById(cleaningCompletedDTO.cleaningId()).orElseThrow();
        Cleaner cleaner = cleanAssignRepository.findById(cleaningCompletedDTO.cleaningId()).orElseThrow().getCleaner();

        /** CHECK IF CLEANERS ASSIGNMENT **/
        if (cleaner.getId() != cleaningCompletedDTO.cleanerId())
            return ResponseEntity.badRequest().body(new MessageResponse("Not your job!"));

        cleaning.setDone(cleaningCompletedDTO.isCompleted());
        cleaningRepository.save(cleaning);
        return ResponseEntity.ok(new MessageResponse("Cleaning is completed: " + cleaningCompletedDTO.isCompleted()));
    }

    public ResponseEntity<CleanerDTO> getCleanerInfo(long id) {
        CleanerDTO cleanerInfo = cleanerRepository.findById(id).orElseThrow().toResponseDTO();
        return ResponseEntity.ok(cleanerInfo);
    }
}
