package com.clientbank.clientbank.services;

import com.clientbank.clientbank.dao.AccountDao;
import com.clientbank.clientbank.entities.Account;

import java.util.List;

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
}
