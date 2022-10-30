package se.ecutbildning.cleanice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.ecutbildning.cleanice.entities.Cleaning;
import se.ecutbildning.cleanice.entities.dto.AssignCleanerDTO;
import se.ecutbildning.cleanice.entities.dto.CleaningCompletedDTO;
import se.ecutbildning.cleanice.entities.dto.CleaningDTO;
import se.ecutbildning.cleanice.entities.dto.CleaningRequestDTO;
import se.ecutbildning.cleanice.service.CleanerService;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/{id}/my-cleanings")
    @PreAuthorize("@authService.idMatchesUser(#id)")
    public List<CleaningDTO> myCleanings(@PathVariable("id") long id) {
        return cleanerService.getMyCleanings(id)
                .stream()
                .map(Cleaning::toResponseDTO)
                .toList();
    }

    @PutMapping("/completed")
    @PreAuthorize("hasRole('CLEANER')")
    public ResponseEntity<?> cleaningCompleted(@Valid @RequestBody CleaningCompletedDTO cleaningCompletedDTO) {
        return cleanerService.completedCleaning(cleaningCompletedDTO);
    }

}
