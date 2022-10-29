package se.ecutbildning.cleanice.entities.dto;

import se.ecutbildning.cleanice.entities.Cleaner;
import se.ecutbildning.cleanice.entities.Customer;
import se.ecutbildning.cleanice.entities.Enums.ECleaning;

import java.util.Date;
import java.util.Set;

public record CleaningDTO(
        Long id, Date cleaningDate, int cleaningLength,
        String location, Set<ECleaning> cleaningType,
        long customerId, Cleaner cleaner, boolean done
        ) {
}
