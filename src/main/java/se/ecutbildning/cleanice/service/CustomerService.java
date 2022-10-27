package se.ecutbildning.cleanice.service;

import org.springframework.stereotype.Service;
import se.ecutbildning.cleanice.entities.Customer;
import se.ecutbildning.cleanice.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
