package se.ecutbildning.cleanice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ecutbildning.cleanice.entities.Cleaning;

import java.util.List;
import java.util.Optional;

@Repository
public interface CleaningRepository extends JpaRepository<Cleaning, Long> {
    List<Cleaning> findAll();
    Optional<Cleaning> findById(Long id);
}
