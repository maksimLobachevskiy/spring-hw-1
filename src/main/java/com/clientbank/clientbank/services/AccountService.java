package com.clientbank.clientbank.services;

import com.clientbank.clientbank.dao.AccountDao;
import com.clientbank.clientbank.entities.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
  private final AccountDao accountDao;

  public AccountService(AccountDao accountDao) {
    this.accountDao = accountDao;
  }

  public List<Account> findAll() {
    return accountDao.findAll();
  }

  public boolean replenishAccount(String number, Double amount) {
    Account account = accountDao.findAccountByNumber(number);
    List<Account> accounts = this.findAll();
    if (account != null) {
      int index = accounts.indexOf(account);
      account.setBalance(amount + account.getBalance());
      accounts.add(index, account);
      return true;
    }

    return false;
  }

  public boolean withdrawAmountFromAccount(String number, Double amount) {
    Account account = accountDao.findAccountByNumber(number);
    List<Account> accounts = this.findAll();
    if (account != null && account.getBalance() >= amount) {
      int index = accounts.indexOf(account);
      account.setBalance(account.getBalance() - amount);
      accounts.add(index, account);
      return true;
    }

    return false;
  }

  public boolean updateAccount(Account account) {
    Account acc = accountDao.findAll().stream().filter(a -> account.getId().equals(a.getId())).findFirst().orElse(null);
    if (acc != null) {
      int index = accountDao.findAll().indexOf(acc);
      accountDao.findAll().set(index, account);
      return true;
    }
    return false;
  }

  public boolean transferAmount(String number1, String number2, Double amount) {
    Account account1 = accountDao.findAccountByNumber(number1);
    Account account2 = accountDao.findAccountByNumber(number2);
    if (account1.getBalance() > amount) {
      account1.setBalance(account1.getBalance() - amount);
      account2.setBalance(account2.getBalance() + amount);
      this.updateAccount(account2);
      return this.updateAccount(account1);
    }
    return false;
  }
}
