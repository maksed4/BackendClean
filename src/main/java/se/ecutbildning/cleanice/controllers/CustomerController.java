package se.ecutbildning.cleanice.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.ecutbildning.cleanice.entities.Customer;
import se.ecutbildning.cleanice.entities.dto.CustomerDTO;
import se.ecutbildning.cleanice.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {
        RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST, RequestMethod.OPTIONS
})
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers()
                .stream()
                .map(Customer::toCustomerResponseDTO)
                .toList();
    }
}
