package com.example.BankAccounts.AccountHolderInfo.Application.port.out;

import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import com.example.BankAccounts.MyUtil.MyException;

import java.util.List;

public interface AccountHolderDetailsPort {
    public AccountHolderEntity saveAccountHolderDetails(AccountHolderEntity accountHolderEntity);
    List<AccountHolderEntity> fetchAllAccountHolderDetails()throws MyException;
    String deleteAccountHolderDetailsById(Long id) throws MyException;
}
