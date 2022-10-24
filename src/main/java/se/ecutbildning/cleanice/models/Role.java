package se.ecutbildning.cleanice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.ecutbildning.cleanice.models.Enums.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}
