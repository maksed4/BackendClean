package se.ecutbildning.cleanice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Cleaner {

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Id
    private Long id;

    public Cleaner(AppUser user) {
        this.id = user.getId();
        this.user = user;
    }
}
