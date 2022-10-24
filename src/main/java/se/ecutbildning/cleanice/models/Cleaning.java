package se.ecutbildning.cleanice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.ecutbildning.cleanice.models.Enums.ECleaning;

import javax.persistence.*;
import java.util.Date;

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

    @Column(nullable = false)
    private ECleaning cleaningType;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Cleaner cleaner;

    @Column
    private boolean done;

    public Cleaning(Customer customer, Date cleaningDate, ECleaning cleaningType) {
        this.customer = customer;
        this.cleaningDate = cleaningDate;
        this.location = customer.getLocation();
        this.cleaningType = cleaningType;
    }
}
