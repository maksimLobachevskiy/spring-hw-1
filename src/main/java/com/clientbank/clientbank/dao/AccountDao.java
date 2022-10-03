package com.clientbank.clientbank.dao;

import com.clientbank.clientbank.entities.Account;
import com.clientbank.clientbank.entities.Customer;
import com.clientbank.clientbank.utils.Currency;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDao implements DAO<Account> {

  private final List<Account> accounts = new ArrayList<>() {{
    add(new Account(Currency.EUR, new Customer("Maks", "dwfre@vsfv", 24)));
  }};

  @Override
  public Account save(Account account) {
    accounts.add(account);
    return account;
  }

  @Override
  public boolean delete(Account account) {
    return accounts.remove(account);
  }

  @Override
  public void deleteAll(List<Account> listOfAccounts) {
    accounts.removeAll(listOfAccounts);
  }

  @Override
  public void saveAll(List<Account> listOfAccounts) {
    accounts.addAll(listOfAccounts);
  }

  @Override
  public List<Account> findAll() {
    return accounts;
  }

  @Override
  public boolean deleteById(Long id) {
    return accounts.removeIf(account -> id.equals(account.getId()));
  }

  @Override
  public Account getOne(Long id) {
    Account account = null;
    for (Account acc : accounts) {
      if (acc.getId().equals(id)) {
        account = acc;
      }
    }
    return account;
  }

  public Account findAccountByNumber(String number) {
    return accounts.stream().filter(account -> account.getNumber().equals(number)).findFirst().orElse(null);
  }

}
