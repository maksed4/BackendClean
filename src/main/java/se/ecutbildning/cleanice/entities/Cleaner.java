package se.ecutbildning.cleanice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Cleaner {

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Id
    private Long id;

    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "cleaner", cascade = CascadeType.ALL)
    private List<Cleaning> cleanings;

    public Cleaner(AppUser user, String firstname, String lastname, String city) {
        this.id = user.getId();
        this.user = user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
    }
}
