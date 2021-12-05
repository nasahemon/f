package com.example.BankAccounts.AccountHolderInfo.Application.Service;

import com.example.BankAccounts.AccountHolderInfo.Application.port.in.AccountTypeUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.out.AccountTypePort;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import org.springframework.stereotype.Service;


@Service
public class AccountTypeService implements AccountTypeUseCases {

    private final AccountTypePort accountTypePort;

    public AccountTypeService(AccountTypePort accountTypePort) {
        this.accountTypePort=accountTypePort;
    }

    public AccountTypeEntity saveAccountType(AccountTypeEntity accountType) {
        return accountTypePort.saveAccountType(accountType);
    }
}
