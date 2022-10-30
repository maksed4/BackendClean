package se.ecutbildning.cleanice.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ecutbildning.cleanice.entities.CleanAssign;

import java.util.List;
import java.util.Optional;

@Repository
public interface CleanAssignRepository extends JpaRepository<CleanAssign, Long> {
    List<CleanAssign> findAll();
    Optional<CleanAssign> findById(long id);
}
