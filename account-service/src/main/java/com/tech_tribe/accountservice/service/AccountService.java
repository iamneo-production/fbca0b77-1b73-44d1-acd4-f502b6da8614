package com.tech_tribe.accountservice.service;

import com.tech_tribe.accountservice.dto.AccountDTO;
import com.tech_tribe.accountservice.entity.Account;
import com.tech_tribe.accountservice.exception.AccountNotFoundException;
import com.tech_tribe.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setCustomerId(accountDTO.getCustomerId());
        account.setName(accountDTO.getName());
        account.setAccountBalance(accountDTO.getAccountBalance());

        accountRepository.save(account);
        return account.buildDTO();
    }

    public AccountDTO getAccountDetailsByCustomerId(long customerId) {

        Optional<Account> account = accountRepository.findByCustomerId(customerId);
        if (account.isEmpty()) {
            throw new AccountNotFoundException(customerId);
        }
        return account.get().buildDTO();
    }

    public AccountDTO updateAccountBalance(long customerId, long billAmount) {

        Optional<Account> existingAccount = accountRepository.findByCustomerId(customerId);
        if (existingAccount.isEmpty()) {
            throw new AccountNotFoundException(customerId);
        }

        Account account = existingAccount.get();
        account.setAccountBalance(account.getAccountBalance() - billAmount);
        accountRepository.save(account);
        return account.buildDTO();
    }

}
