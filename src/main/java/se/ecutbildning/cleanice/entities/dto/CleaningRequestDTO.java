package se.ecutbildning.cleanice.entities.dto;

import se.ecutbildning.cleanice.entities.Cleaner;
import se.ecutbildning.cleanice.entities.Customer;
import se.ecutbildning.cleanice.entities.Enums.ECleaning;

import java.util.Date;
import java.util.Set;

public record CleaningRequestDTO(
        long id, Date cleaningDate,
        String location, Set<ECleaning> cleaningType,
        long customerId
        ) {
}
