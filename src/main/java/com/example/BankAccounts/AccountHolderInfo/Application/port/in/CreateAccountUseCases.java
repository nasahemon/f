package com.example.BankAccounts.AccountHolderInfo.Application.port.in;

import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;

public interface CreateAccountUseCases {
    public AccountHolderEntity saveAccountHolderDetails(AccountHolderEntity accountHolderEntity);
}
