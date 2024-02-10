package com.springboot.at.service;


import com.springboot.at.payload.AccountDto;
import com.springboot.at.entity.Account;
import java.util.List;

public interface AccountService {

  Account findById(Integer id);

  List<Account> fetchAll();

  void delete(Integer id);

  Account create(AccountDto obj);

  Account update(Integer id, AccountDto obj);
}
