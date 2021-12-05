package com.example.BankAccounts.AccountHolderInfo.Application.port.in;

import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import com.example.BankAccounts.MyUtil.MyException;

import java.util.List;

public interface FetchAccountDetailsUseCases {
    List<AccountHolderEntity> fetchAllAccountHolderDetails()throws MyException;
}
