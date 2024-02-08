package com.springboot.at.service.impl;


import com.springboot.at.dao.request.AccountRequest;
import com.springboot.at.entity.Account;
import com.springboot.at.exception.DuplicateRecordException;
import com.springboot.at.repository.AccountRepository;
import com.springboot.at.service.AccountService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  public Account update(Integer accountId, AccountRequest request) {
    accountRepository.findByName(request.getName()).ifPresent(account -> {
          if (!account.getId().equals(
              accountId)) {
            throw new DuplicateRecordException("Account name already exists.");
          }
        }
    );

    Account dbAccount = findById(accountId);
    if (dbAccount != null) {
      Account accountToSave = getAccountToSave(request, dbAccount);
      return accountRepository.save(accountToSave);
    }

    return null;
  }

  private Account getAccountToSave(AccountRequest request, Account dbAccount) {
    if (Objects.nonNull(request.getName())) {
      dbAccount.setName(request.getName());
    }
    if (Objects.nonNull(request.getWebsite())) {
      dbAccount.setWebsite(request.getWebsite());
    }
    if (Objects.nonNull(request.getProductUrl())) {
      dbAccount.setProductUrl(request.getProductUrl());
    }
    if (Objects.nonNull(request.getLogoUrl())) {
      dbAccount.setLogoUrl(request.getLogoUrl());
    }
    if (Objects.nonNull(request.getSize())) {
      dbAccount.setSize(request.getSize());
    }
    if (Objects.nonNull(request.getAnnualRevenue())) {
      dbAccount.setAnnualRevenue(request.getAnnualRevenue());
    }
    if (Objects.nonNull(request.getAnnualAdSpend())) {
      dbAccount.setAnnualAdSpend(request.getAnnualAdSpend());
    }

    return dbAccount;
  }

  @Override
  public Account findById(Integer id) {
    return accountRepository.findById(id).orElse(null);
  }

  @Override
  public List<Account> fetchAll() {
    return accountRepository.findAll();
  }

  @Override
  public void delete(Integer id) {
    accountRepository.deleteById(id);
  }

  @Override
  public Account create(AccountRequest obj) {
    return null;
  }
}
