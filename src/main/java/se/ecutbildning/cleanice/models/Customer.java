package se.ecutbildning.cleanice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.ecutbildning.cleanice.models.Enums.ECustomer;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Customer {

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Id
    private Long id;

    @Column(nullable = false)
    private String location;

    @Enumerated
    @Column(nullable = false)
    private ECustomer customerType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cleaning> cleanings;

    public Customer(AppUser user, String location, ECustomer customerType) {
        this.id = user.getId();
        this.user = user;
        this.location = location;
        this.customerType = customerType;
    }
}
