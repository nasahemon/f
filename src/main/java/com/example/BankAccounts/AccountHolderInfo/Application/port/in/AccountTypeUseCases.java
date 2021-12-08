package com.example.BankAccounts.AccountHolderInfo.Application.port.in;

import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import com.example.BankAccounts.MyUtil.MyException;

import java.util.List;

public interface AccountTypeUseCases {
    AccountTypeEntity saveAccountType(AccountTypeEntity accountType);
    List<AccountTypeEntity> fetchAllAccountType();
    AccountTypeEntity updateAccountType(Long id,AccountTypeEntity accountType);
    String deleteAccountTypeById(Long id) throws MyException;
}
