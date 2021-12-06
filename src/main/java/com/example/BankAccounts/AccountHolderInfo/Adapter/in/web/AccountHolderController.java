package com.example.BankAccounts.AccountHolderInfo.Adapter.in.web;


import com.example.BankAccounts.AccountHolderInfo.Application.port.in.*;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import com.example.BankAccounts.MyUtil.CustomResponse;
import com.example.BankAccounts.MyUtil.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class AccountHolderController {

    private final CreateAccountUseCases createAccountUseCases;
    private final DeleteAccountUseCases deleteAccountUseCases;
    private final FetchAccountDetailsUseCases fetchAccountDetailsUseCases;
    private final UpdateAccountUseCases updateAccountUseCases;



    public AccountHolderController(CreateAccountUseCases createAccountUseCases,
                                   DeleteAccountUseCases deleteAccountUseCases,
                                   FetchAccountDetailsUseCases fetchAccountDetailsUseCases,
                                   UpdateAccountUseCases updateAccountUseCases)
    {
        this.createAccountUseCases = createAccountUseCases;
        this.deleteAccountUseCases = deleteAccountUseCases;
        this.fetchAccountDetailsUseCases = fetchAccountDetailsUseCases;
        this.updateAccountUseCases = updateAccountUseCases;
    }


    // Store an Account Holder Details
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




    //Find all available Account Holder details
    @GetMapping("/account")
    public ResponseEntity<?> getAllAccountHolderDetails() {

        //Required variables
        List<AccountHolderEntity> listOfAccountHolder;

        try{
            listOfAccountHolder = fetchAccountDetailsUseCases.fetchAllAccountHolderDetails();
            return new ResponseEntity<List<AccountHolderEntity> >(listOfAccountHolder, HttpStatus.OK);

        }catch(MyException e){
            if(e.code.is2xxSuccessful()) {
                return new ResponseEntity<CustomResponse>(new CustomResponse(
                        "Controller: sorry, we don't have anything to show! " + e.getMessage() ,
                        HttpStatus.NO_CONTENT),HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<CustomResponse>(new CustomResponse(
                        "Controller: sorry, we messed up something! " + e.getMessage() ,
                        HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);

            }


        }

    }



    //Delete an Account holder details by id
    @DeleteMapping("/account/{id}")
    @ResponseBody
    public ResponseEntity<CustomResponse> deleteAccountHolderDetailsById(@PathVariable Long id){

        try{
            String message = deleteAccountUseCases.deleteAccountHolderDetailsById(id);
            if(message.equals("ok")) {
                return new ResponseEntity<CustomResponse>(new CustomResponse(
                        "The spot is Deleted successfully", HttpStatus.OK), HttpStatus.OK);
            }else {
                return new ResponseEntity<CustomResponse>(new CustomResponse(
                        "The spot Still exist", HttpStatus.OK), HttpStatus.OK);
            }
        }
        catch(MyException e){
            return new ResponseEntity<CustomResponse>(new CustomResponse(
                    "Controller: sorry, Can't Delete!"+e.getMessage(),e.getCode()),HttpStatus.BAD_REQUEST);
        }
    }



    //Update an account holder details by id
    @PutMapping("/account/{id}")
    @ResponseBody
    public ResponseEntity<?> updateAccountHolderDetailsById(@PathVariable Long id,
                                                                @Valid @RequestBody AccountHolderEntity accountHolderEntity){
        try {
            AccountHolderEntity updatedAccountHolderDetails = updateAccountUseCases.updateAccountHolderDetailsById(id, accountHolderEntity);
            return new ResponseEntity<AccountHolderEntity>(updatedAccountHolderDetails,HttpStatus.OK);
        }
        catch(MyException e){

                return new ResponseEntity<CustomResponse>(new CustomResponse(
                        "Controller: sorry, Can't Update! " + e.getMessage() ,
                        e.getCode()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
