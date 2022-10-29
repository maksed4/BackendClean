package se.ecutbildning.cleanice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.ecutbildning.cleanice.entities.Cleaning;
import se.ecutbildning.cleanice.entities.dto.CleaningDTO;
import se.ecutbildning.cleanice.service.CleaningService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cleaning")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {
        RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST, RequestMethod.OPTIONS
})
public class CleaningController {

    private final CleaningService cleaningService;


    public CleaningController(CleaningService cleaningService) {
        this.cleaningService = cleaningService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CleaningDTO> getAllCleanings() {
        return cleaningService.getAllCleanings()
                .stream()
                .map(Cleaning::toResponseDTO)
                .toList();
    }

    @PostMapping("/book")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> bookCleaning(@Valid @RequestBody CleaningDTO cleaningRequestDTO) {
        return cleaningService.bookACleaning(cleaningRequestDTO);
    }


}
