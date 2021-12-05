package com.example.BankAccounts.AccountHolderInfo.Application.port.in;

import com.example.BankAccounts.MyUtil.MyException;

public interface DeleteAccountUseCases {

    String deleteAccountHolderDetailsById(Long id) throws MyException;
}
