package se.ecutbildning.cleanice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.ecutbildning.cleanice.entities.CleanAssign;
import se.ecutbildning.cleanice.entities.Cleaner;
import se.ecutbildning.cleanice.entities.Cleaning;
import se.ecutbildning.cleanice.entities.Customer;
import se.ecutbildning.cleanice.entities.Enums.ECleaning;
import se.ecutbildning.cleanice.entities.dto.AssignCleanerDTO;
import se.ecutbildning.cleanice.entities.dto.CleaningDTO;
import se.ecutbildning.cleanice.payload.response.MessageResponse;
import se.ecutbildning.cleanice.repository.CleanAssignRepository;
import se.ecutbildning.cleanice.repository.CleanerRepository;
import se.ecutbildning.cleanice.repository.CleaningRepository;
import se.ecutbildning.cleanice.repository.CustomerRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static se.ecutbildning.cleanice.entities.Enums.ECleaning.*;

@Service
public class CleaningService {

    private final CleaningRepository cleaningRepository;
    private final CustomerRepository customerRepository;
    private final CleanerRepository cleanerRepository;
    private final CleanAssignRepository cleanAssignRepository;

    public CleaningService(CleaningRepository cleaningRepository, CustomerRepository customerRepository, CleanerRepository cleanerRepository, CleanAssignRepository cleanAssignRepository) {
        this.cleaningRepository = cleaningRepository;
        this.customerRepository = customerRepository;
        this.cleanerRepository = cleanerRepository;
        this.cleanAssignRepository = cleanAssignRepository;
    }

    public List<Cleaning> getAllCleanings() {
        return cleaningRepository.findAll();
    }

    public ResponseEntity<?> bookACleaning(CleaningDTO cleaningRequestDTO) {
        Customer customer = customerRepository
                .findCustomerById(cleaningRequestDTO.customerId())
                .orElseThrow();

        Cleaning cleaning = new Cleaning(customer, cleaningRequestDTO.cleaningDate());

        Set<ECleaning> cleaningTypes = new HashSet<>();

        if (cleaningRequestDTO.cleaningType() == null) cleaningTypes.add(BASIC_CLEANING);
        else {
            cleaningRequestDTO.cleaningType().forEach(type -> {
                switch (type) {
                    case TOP_CLEANING -> {
                        cleaningTypes.add(TOP_CLEANING);
                    }
                    case DIAMOND_CLEANING -> {
                        cleaningTypes.add(DIAMOND_CLEANING);
                    }
                    case WINDOW_CLEANING -> {
                        cleaningTypes.add(WINDOW_CLEANING);
                    }
                    default -> {
                        cleaningTypes.add(BASIC_CLEANING);
                    }
                }
            });
        }

        cleaning.setCleaningType(cleaningTypes);
        cleaningRepository.save(cleaning);
        return ResponseEntity.ok(new MessageResponse("Cleaning booked"));
    }

    public ResponseEntity<?> assign(AssignCleanerDTO assignCleanerDTO) {
        Cleaning cleaning = cleaningRepository.findById(assignCleanerDTO.cleaningId()).orElseThrow();

        /** CHECK IF CLEANING DATE EQUALS OTHER CLEANING DATE **/
        for (CleanAssign cleanAssign : cleanAssignRepository
                .findAll()
                .stream()
                .filter(cleanAssign -> cleanAssign.getCleaner().getId() == assignCleanerDTO.cleanerId())
                .toList()
        ) {
            for (Cleaning clean : cleaningRepository
                    .findAll()
                    .stream()
                    .filter(cleaning1 -> cleaning1.getId() != cleaning.getId())
                    .toList()
            ) {
                if (
                        cleanAssign.getCleaner().getId() == assignCleanerDTO.cleanerId() &&
                        cleanAssign.getCleaning().getId() != cleaning.getId()
                ) {
                    if (
                            clean.getCleaningDate().equals(cleaning.getCleaningDate()) &&
                            clean.getCleaningDate().getTime() == cleaning.getCleaningDate().getTime()
                    ) {
                        return ResponseEntity.badRequest().body(new MessageResponse("Cleaner already assigned"));
                    }
                }
            }
        }
        Cleaner cleaner = cleanerRepository.findById(assignCleanerDTO.cleanerId()).orElseThrow();
        CleanAssign cleanAssign = new CleanAssign(cleaning, cleaner);
        cleanAssignRepository.save(cleanAssign);
        return ResponseEntity.ok(new MessageResponse("Cleaner assigned"));
    }
}
