package se.ecutbildning.cleanice.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ecutbildning.cleanice.entities.CleanAssign;

import java.util.List;

@Repository
public interface CleanAssignRepository extends JpaRepository<CleanAssign, Long> {
    List<CleanAssign> findAll();
}
