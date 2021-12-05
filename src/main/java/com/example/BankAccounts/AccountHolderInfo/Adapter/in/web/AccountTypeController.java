package com.example.BankAccounts.AccountHolderInfo.Adapter.in.web;


import com.example.BankAccounts.AccountHolderInfo.Application.port.in.AccountTypeUseCases;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import com.example.BankAccounts.MyUtil.CustomResponse;
import com.example.BankAccounts.MyUtil.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class AccountTypeController {


    private final AccountTypeUseCases accountTypeUseCases;

    public AccountTypeController(AccountTypeUseCases accountTypeUseCases){
        this.accountTypeUseCases = accountTypeUseCases;
    }


    // Store a Tourist spot
    @PostMapping("/account/type")
    public ResponseEntity<?> storeATouristSpotByDTO(@Valid @RequestBody AccountTypeEntity accountType) {

             AccountTypeEntity  returnedAccountType = null;
        try{
            returnedAccountType = accountTypeUseCases.saveAccountType(accountType);
            return new ResponseEntity<AccountTypeEntity>(returnedAccountType,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<CustomResponse>(new CustomResponse(
                    "Controller: sorry, Can't save now!"+e.getMessage(),HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
        }
    }

}
