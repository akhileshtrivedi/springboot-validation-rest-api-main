package com.springboot.at.service;


import com.springboot.at.dao.request.AccountRequest;
import com.springboot.at.entity.Account;
import java.util.List;

public interface AccountService {

  Account findById(Integer id);

  List<Account> fetchAll();

  void delete(Integer id);

  Account create(AccountRequest obj);

  Account update(Integer id, AccountRequest obj);
}
