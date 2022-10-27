package se.ecutbildning.cleanice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ecutbildning.cleanice.entities.Cleaner;

import java.util.List;

@Repository
public interface CleanerRepository extends JpaRepository<Cleaner, Long> {
    List<Cleaner> findAll();
}
