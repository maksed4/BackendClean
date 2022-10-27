package se.ecutbildning.cleanice.entities.dto;

import se.ecutbildning.cleanice.entities.Enums.ECustomer;

import java.util.Set;

public record CustomerResponseDTO(
        long id, String firstname, String lastname,
        String address, String zipcode, String city,
        Set<ECustomer> customerType
) {
}
