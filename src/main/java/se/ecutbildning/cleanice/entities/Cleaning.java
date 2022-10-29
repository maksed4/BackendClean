package se.ecutbildning.cleanice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.ecutbildning.cleanice.entities.Enums.ECleaning;
import se.ecutbildning.cleanice.entities.dto.CleaningDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Cleaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date cleaningDate;

    @Column
    private int cleaningLength;

    @Column(nullable = false)
    private String location;

    @ElementCollection(targetClass = ECleaning.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column
    private Set<ECleaning> cleaningType;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Cleaner cleaner;

    @Column
    private boolean done;

    public CleaningDTO toResponseDTO() { return new CleaningDTO(
            id, cleaningDate, cleaningLength, location, cleaningType, customer.getId(), cleaner, done
    ); }

    public Cleaning(Customer customer, Date cleaningDate) {
        this.customer = customer;
        this.cleaningDate = cleaningDate;
        this.location = customer.getAddress();
    }
}
