package se.ecutbildning.cleanice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

@Getter @Setter @ToString
public class User {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;

}
