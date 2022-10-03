package com.clientbank.clientbank.controllers;

import com.clientbank.clientbank.entities.Account;
import com.clientbank.clientbank.entities.Customer;
import com.clientbank.clientbank.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public List<Customer> findAll() {
    return customerService.getAll();
  }

  @GetMapping("/{id}")
  public Customer getOne(@PathVariable(name = "id") Long id) {
    return customerService.getOne(id);
  }

  @PostMapping
  public Customer save(@RequestBody Customer customer) {
    return customerService.saveCustomer(customer);
  }

  @PostMapping("/{id}/account")
  public Account createAccount(@PathVariable(name = "id") Long id, @RequestBody Account account) {
    return customerService.createAccount(account, id);
  }

  @PutMapping
  public boolean update(@RequestBody Customer customer, Long id) {
    return customerService.updateCustomer(customer, id);
  }

  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable(name = "id") Long id) {
    return customerService.deleteCustomerById(id);
  }

  @PutMapping("/account")
  public Customer delete(
          @RequestBody String accNumber, Long id) {
    return customerService.deleteAccount(accNumber, id);
  }


}
