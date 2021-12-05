package com.example.BankAccounts.AccountHolderInfo.Application.port.out;

import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;

public interface AccountTypePort {
    AccountTypeEntity saveAccountType(AccountTypeEntity accountType);
}

