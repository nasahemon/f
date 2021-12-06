package com.example.BankAccounts.AccountHolderInfo.Application.port.in;

import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import com.example.BankAccounts.MyUtil.MyException;

public interface UpdateAccountUseCases {
    AccountHolderEntity updateAccountHolderDetailsById(Long id, AccountHolderEntity accountHolderDetails) throws MyException;
}
