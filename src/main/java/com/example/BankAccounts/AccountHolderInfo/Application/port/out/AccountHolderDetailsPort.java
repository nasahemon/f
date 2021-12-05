package com.example.BankAccounts.AccountHolderInfo.Application.port.out;

import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;

public interface AccountHolderDetailsPort {
    public AccountHolderEntity saveAccountHolderDetails(AccountHolderEntity accountHolderEntity);
}
