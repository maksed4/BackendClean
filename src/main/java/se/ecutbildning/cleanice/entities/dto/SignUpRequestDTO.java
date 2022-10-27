package se.ecutbildning.cleanice.entities.dto;

import se.ecutbildning.cleanice.entities.Enums.ERole;

import java.util.Set;

public record SignUpRequestDTO(String username, String password, String email, Set<ERole> roles) {
}
