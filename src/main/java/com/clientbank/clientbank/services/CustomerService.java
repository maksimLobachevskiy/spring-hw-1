package com.clientbank.clientbank.services;

import com.clientbank.clientbank.dao.AccountDao;
import com.clientbank.clientbank.dao.CustomerDao;
import com.clientbank.clientbank.entities.Account;
import com.clientbank.clientbank.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
  private final CustomerDao customerDao;
  private final AccountDao accountDao;

  public CustomerService(CustomerDao customerDao, AccountDao accountDao) {
    this.customerDao = customerDao;
    this.accountDao = accountDao;
  }

  public List<Customer> getAll() {
    return customerDao.findAll();
  }

  public Customer getOne(Long id) {
    return customerDao.getOne(id);
  }

  public Customer saveCustomer(Customer customer) {
    return customerDao.save(customer);
  }

  public boolean deleteCustomer(Customer customer) {
    accountDao.deleteAll(customer.getAccounts());
    return customerDao.delete(customer);
  }

  public boolean deleteCustomerById(Long id) {
    accountDao.deleteAll(getOne(id).getAccounts());
    return customerDao.deleteById(id);
  }

  public Account createAccount(Account account, Long id) {
    account.setNumber(UUID.randomUUID().toString());
    customerDao.getOne(id).getAccounts().add(account);
    return accountDao.save(account);
  }

  public Customer deleteAccount(String number, Long id) {
    Customer customer = getAll().stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    if (customer != null) {
      int index = getAll().indexOf(customer);
      Account acc = customer.getAccounts().stream().filter(a -> a.getNumber().equals(number)).findFirst().orElse(null);
      customer.getAccounts().remove(acc);
      getAll().add(index, customer);
      return customer;
    }
    return null;
  }

  public boolean updateCustomer(Customer customer, Long id) {
    Customer cust = customerDao.getOne(id);
    if (cust != null) {
      customerDao.save(cust);
      return true;
    }
    return false;
  }
}
