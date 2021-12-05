package com.example.BankAccounts.AccountHolderInfo.Application.port.in;

import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;

public interface AccountTypeUseCases {
    public AccountTypeEntity saveAccountType(AccountTypeEntity accountType);
}
