package com.clientbank.clientbank.controllers;

import com.clientbank.clientbank.entities.Account;
import com.clientbank.clientbank.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/accounts")
public class AccountController {
  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping
  public List<Account> findAll() {
    return accountService.findAll();
  }

  @PutMapping("/replenish")
  public boolean replenishAccount(@RequestParam(value = "number") String number,
                                  @RequestParam(value = "amount") Double amount
  ) {
    return accountService.replenishAccount(number, amount);
  }

  @PutMapping("/withdraw")
  public boolean withdrawAmount(@RequestParam(value = "number") String number,
                                @RequestParam(value = "amount") Double amount
  ) {
    return accountService.withdrawAmountFromAccount(number, amount);
  }

  @PutMapping("/transfer")
  public boolean transferAmount(@RequestParam(value = "number1") String number1,
                                @RequestParam(value = "number2") String number2,
                                @RequestParam(value = "amount") Double amount
  ) {
    return accountService.transferAmount(number1, number2, amount);
  }
}
