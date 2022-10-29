package se.ecutbildning.cleanice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ecutbildning.cleanice.entities.dto.CleanerDTO;
import se.ecutbildning.cleanice.service.AdminService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {
        RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST, RequestMethod.OPTIONS
})
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/cleaner-signup")
    public ResponseEntity<?> registerCleaner(@Valid @RequestBody CleanerDTO cleanerDTO) {
        return adminService.cleanerSignUp(cleanerDTO);
    }

}
