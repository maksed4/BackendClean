package se.ecutbildning.cleanice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import se.ecutbildning.cleanice.entities.dto.AssignCleanerDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
public class CleanAssign {

    @Id
    private Long id;

    @OneToOne
    private Cleaning cleaning;

    @ManyToOne
    private Cleaner cleaner;

    public AssignCleanerDTO toResponseDTO() { return new AssignCleanerDTO(id, cleaner.getId()); }

    public CleanAssign(Cleaning cleaning, Cleaner cleaner) {
        this.id = cleaning.getId();
        this.cleaning = cleaning;
        this.cleaner = cleaner;
    }
}
