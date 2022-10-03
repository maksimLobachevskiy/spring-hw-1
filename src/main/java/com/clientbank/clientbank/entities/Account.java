package com.clientbank.clientbank.entities;

import com.clientbank.clientbank.utils.Currency;

import java.util.Objects;

public class Account {
  private Long id;
  private String number;
  private Currency currency;
  private Double balance;
  private Customer customer;

  public Account(Currency currency, Customer customer) {
    this.currency = currency;
    this.customer = customer;
  }

  public Account(String number, Currency currency) {
    this.number = number;
    this.currency = currency;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Account account)) return false;
    return getId().equals(account.getId()) && Objects.equals(getNumber(), account.getNumber()) && getCurrency() == account.getCurrency() && Objects.equals(getBalance(), account.getBalance()) && Objects.equals(getCustomer(), account.getCustomer());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getNumber(), getCurrency(), getBalance(), getCustomer());
  }

  @Override
  public String toString() {
    return "Account{" +
            "id=" + id +
            ", number='" + number + '\'' +
            ", currency=" + currency +
            ", balance=" + balance +
            ", customer=" + customer +
            '}';
  }
}
