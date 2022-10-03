package com.clientbank.clientbank.dao;

import com.clientbank.clientbank.entities.Account;
import com.clientbank.clientbank.entities.Customer;
import com.clientbank.clientbank.utils.Currency;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao implements DAO<Customer> {

  private final List<Account> accounts = new ArrayList<>() {{
    add(new Account("265432", Currency.EUR));
    add(new Account("234664", Currency.USD));
  }};
  private final List<Customer> customers = new ArrayList<>() {{
    add(new Customer(1L, "Maksim", "frte@mail", 20, accounts));
    add(new Customer(2L, "Oleg", "1232@mail", 25, accounts));
  }};

  @Override
  public Customer save(Customer customer) {
    customers.add(customer);
    return customer;
  }

  @Override
  public boolean delete(Customer customer) {
    return customers.remove(customer);
  }

  @Override
  public void deleteAll(List<Customer> listOfCustomers) {
    customers.removeAll(listOfCustomers);
  }

  @Override
  public void saveAll(List<Customer> listOfCustomers) {
    customers.addAll(listOfCustomers);
  }

  @Override
  public List<Customer> findAll() {
    return customers;
  }

  @Override
  public boolean deleteById(Long id) {
    return customers.removeIf(customer -> id.equals(customer.getId()));
  }

  @Override
  public Customer getOne(Long id) {
    Customer customer = null;
    for (Customer cust : customers) {
      if (cust.getId().equals(id)) {
        customer = cust;
      }
    }
    return customer;
  }
}
