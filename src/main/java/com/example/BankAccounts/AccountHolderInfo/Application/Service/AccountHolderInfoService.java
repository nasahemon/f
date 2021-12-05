package com.example.BankAccounts.AccountHolderInfo.Application.Service;

import com.example.BankAccounts.AccountHolderInfo.Application.port.in.CreateAccountUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.in.DeleteAccountUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.in.FetchAccountDetailsUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.in.UpdateAccountUsecases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.out.AccountHolderDetailsPort;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import com.example.BankAccounts.MyUtil.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<AccountHolderEntity> fetchAllAccountHolderDetails() throws MyException{
        return accountHolderDetailsPort.fetchAllAccountHolderDetails();
    }

    @Override
    public String deleteAccountHolderDetailsById(Long id) throws MyException {
        return accountHolderDetailsPort.deleteAccountHolderDetailsById(id);
    }
}
