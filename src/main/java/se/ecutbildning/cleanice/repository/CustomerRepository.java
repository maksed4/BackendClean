package se.ecutbildning.cleanice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ecutbildning.cleanice.entities.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByFirstnameAndLastname(String firstname, String lastname);
    Optional<Customer> findCustomerById(long id);
    List<Customer> findAll();
}
