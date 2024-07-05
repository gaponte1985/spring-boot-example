package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping()
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String lastName,
            String email,
            Integer age) {

    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setLastName(request.lastName);
        customer.setEmail(request.email);
        customer.getAge(request.age);
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Optional<Customer>> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody Customer request ) {
        Optional<Customer> updatedCustomer =  customerRepository.findById(id);
        updatedCustomer.get().setName(request.getName());
        updatedCustomer.get().setLastName(request.getLastName());
        updatedCustomer.get().setEmail(request.getEmail());
        customerRepository.save(request);
        return ResponseEntity.ok(updatedCustomer);
    }
    }
