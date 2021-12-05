package com.example.BankAccounts.AccountHolderInfo.Application.Service;

import com.example.BankAccounts.AccountHolderInfo.Application.port.in.AccountTypeUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.out.AccountTypePort;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import com.example.BankAccounts.MyUtil.MyException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountTypeService implements AccountTypeUseCases {

    private final AccountTypePort accountTypePort;

    public AccountTypeService(AccountTypePort accountTypePort) {
        this.accountTypePort=accountTypePort;
    }

    public AccountTypeEntity saveAccountType(AccountTypeEntity accountType) {
        return accountTypePort.saveAccountType(accountType);
    }

    @Override
    public List<AccountTypeEntity> fetchAllAccountType() {
        return accountTypePort.fetchAllAccountType();
    }

    @Override
    public String deleteAccountTypeById(Long id) throws MyException {
        return accountTypePort.deleteAccountTypeById(id);
    }


}
