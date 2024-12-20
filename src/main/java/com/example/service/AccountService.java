package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.entity.Account;

@Service
public class AccountService {
    
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account checkForUsername(String userName){
        return accountRepository.findByusername(userName);
    }

    public Account checkForId(int accountid){
        return accountRepository.findByaccountId(accountid);
    }

    public Account registerAccount (String userName, String passWord){
        if(userName.isBlank() || passWord.length() < 4) return null;
        else{
            Account newAccount = new Account();
            newAccount.setUsername(userName);
            newAccount.setPassword(passWord);
            return accountRepository.save(newAccount);
        }
    }

    public Account loginAccount(String userName, String passWord){
        Account newLogin = accountRepository.findByusername(userName);
        if(newLogin == null) return null;
        else if (newLogin.getPassword().equals(passWord)) return newLogin;
        else return null;
    }
}
