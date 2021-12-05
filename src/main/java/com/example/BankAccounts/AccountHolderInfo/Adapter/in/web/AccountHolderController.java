package com.example.BankAccounts.AccountHolderInfo.Adapter.in.web;


import com.example.BankAccounts.AccountHolderInfo.Application.port.in.*;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import com.example.BankAccounts.MyUtil.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class AccountHolderController {

    private final CreateAccountUseCases createAccountUseCases;
    private final DeleteAccountUseCases deleteAccountUseCases;
    private final FetchAccountDetailsUseCases fetchAccountDetailsUseCases;
    private final UpdateAccountUsecases updateAccountUsecases;



    public AccountHolderController(CreateAccountUseCases createAccountUseCases,
                                   DeleteAccountUseCases deleteAccountUseCases,
                                   FetchAccountDetailsUseCases fetchAccountDetailsUseCases,
                                   UpdateAccountUsecases updateAccountUsecases)
    {
        this.createAccountUseCases = createAccountUseCases;
        this.deleteAccountUseCases = deleteAccountUseCases;
        this.fetchAccountDetailsUseCases = fetchAccountDetailsUseCases;
        this.updateAccountUsecases = updateAccountUsecases;
    }


    // Store a Tourist spot
    @PostMapping("/account")
    public ResponseEntity<?> storeATouristSpotByDTO(@Valid @RequestBody AccountHolderEntity accountHolderDetails) {

        AccountHolderEntity  returnedAccountHolderDetails = null;
        try{
            returnedAccountHolderDetails = createAccountUseCases.saveAccountHolderDetails(accountHolderDetails);
            return new ResponseEntity<AccountHolderEntity>(returnedAccountHolderDetails, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<CustomResponse>(new CustomResponse(
                    "Controller: sorry, Can't save now!"+e.getMessage(),HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
        }
    }
}
