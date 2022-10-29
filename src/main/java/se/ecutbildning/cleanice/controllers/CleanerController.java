package se.ecutbildning.cleanice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.ecutbildning.cleanice.entities.dto.AssignCleanerDTO;
import se.ecutbildning.cleanice.entities.dto.CleaningCompletedDTO;
import se.ecutbildning.cleanice.entities.dto.CleaningRequestDTO;
import se.ecutbildning.cleanice.service.CleanerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cleaner")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {
        RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST, RequestMethod.OPTIONS
})
public class CleanerController {

    private final CleanerService cleanerService;

    public CleanerController(CleanerService cleanerService) {
        this.cleanerService = cleanerService;
    }

    @PutMapping("/completed")
    @PreAuthorize("hasRole('CLEANER')")
    public ResponseEntity<?> cleaningCompleted(@Valid @RequestBody CleaningCompletedDTO cleaningCompletedDTO) {
        return cleanerService.completedCleaning(cleaningCompletedDTO);
    }

}
