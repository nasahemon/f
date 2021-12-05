package com.example.BankAccounts.AccountHolderInfo.Application.Service;

import com.example.BankAccounts.AccountHolderInfo.Application.port.in.CreateAccountUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.in.DeleteAccountUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.in.FetchAccountDetailsUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.in.UpdateAccountUsecases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.out.AccountHolderDetailsPort;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderInfoService
        implements CreateAccountUseCases,
        DeleteAccountUseCases,
        FetchAccountDetailsUseCases,
        UpdateAccountUsecases
{
    private final AccountHolderDetailsPort accountHolderDetailsPort;

    public AccountHolderInfoService(AccountHolderDetailsPort accountHolderDetailsPort) {
        this.accountHolderDetailsPort = accountHolderDetailsPort;
    }


    @Override
    public AccountHolderEntity saveAccountHolderDetails(AccountHolderEntity accountHolderEntity) {
        return accountHolderDetailsPort.saveAccountHolderDetails(accountHolderEntity);
    }
}
