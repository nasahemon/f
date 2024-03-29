package com.example.BankAccounts.AccountHolderInfo.Application.Service;

import com.example.BankAccounts.AccountHolderInfo.Application.port.in.CreateAccountUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.in.DeleteAccountUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.in.FetchAccountDetailsUseCases;
import com.example.BankAccounts.AccountHolderInfo.Application.port.in.UpdateAccountUseCases;
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
        UpdateAccountUseCases
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
        try {
            return accountHolderDetailsPort.fetchAllAccountHolderDetails();
        }
        catch(MyException e){
            if(e.code.is2xxSuccessful()) {
                throw new MyException(HttpStatus.NO_CONTENT, "Service: Adapter fail to provide any Spot");
            }else{
                throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR, "Service: mapping error");
            }
        }
    }

    @Override
    public String deleteAccountHolderDetailsById(Long id) throws MyException {
        try {
            return accountHolderDetailsPort.deleteAccountHolderDetailsById(id);
        }
        catch(MyException e){
            throw new MyException(HttpStatus.NOT_FOUND,
                    "Service: Adapter caused the  exception." + e.getMessage());
        }
    }

    @Override
    public AccountHolderEntity updateAccountHolderDetailsById(Long id, AccountHolderEntity accountHolderDetails) throws MyException {
        try {
            return accountHolderDetailsPort.updateAccountHolderDetailsById(id,accountHolderDetails);
        }
        catch(MyException e){
                throw new MyException(HttpStatus.BAD_REQUEST,
                        "Service: Adapter caused the  exception." + e.getMessage());
        }
    }
}
