package se.ecutbildning.cleanice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column
    private boolean done;

    public Cleaning(Date cleaningDate, String location, ECleaning cleaningType) {
        this.cleaningDate = cleaningDate;
        this.location = location;
        this.cleaningType = cleaningType;
    }
}
