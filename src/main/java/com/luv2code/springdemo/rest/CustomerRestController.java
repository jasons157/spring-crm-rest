package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    //autowire CustomerService
    @Autowired
    CustomerService customerService;

    //add mapping for GET /customers
    @GetMapping("/customers")
    public List<Customer> getCustomers(){

        return customerService.getCustomers();
    }

    //add mapping for GET /customers/{customerId}
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null){
            throw new CustomerNotFoundException("Customer id not found: " + customerId);
        }

        return customer;
    }

    //add mapping for POST /customers - add new customer

    /**
     * @RequestBody accesses the request body as a POJO, pretty neat
     * @param customer
     * @return
     */
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){

        /**
         * Hibernate's saveOrUpdate() considers 0 the same as null.
         * This forces Hibernate to save the customer as a NEW
         * customer instead of accidentally updating one.
         */
        customer.setId(0);
        customerService.saveCustomer(customer);

        return customer;
    }

    /**
     * Update a customer.
     * REST client has to specify ID
     * @param customer
     * @return
     */
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){

        customerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) throw new CustomerNotFoundException("Customer not found: " + customerId);

        customerService.deleteCustomer(customerId);

        return "Deleted customer: " + customer;
    }
}
