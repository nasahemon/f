package com.example.BankAccounts.AccountHolderInfo.Application.port.in;

import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import com.example.BankAccounts.MyUtil.MyException;

import java.util.List;

public interface AccountTypeUseCases {
    public AccountTypeEntity saveAccountType(AccountTypeEntity accountType);
    List<AccountTypeEntity> fetchAllAccountType();
    String deleteAccountTypeById(Long id) throws MyException;
}
