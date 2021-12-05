package com.example.BankAccounts.AccountHolderInfo.Adapter.in.web;


import com.example.BankAccounts.AccountHolderInfo.Application.port.in.AccountTypeUseCases;
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
public class AccountTypeController {


    private final AccountTypeUseCases accountTypeUseCases;

    public AccountTypeController(AccountTypeUseCases accountTypeUseCases){
        this.accountTypeUseCases = accountTypeUseCases;
    }


    // Store Account type
    @PostMapping("/account/type")
    public ResponseEntity<?> storeAAccountType(@Valid @RequestBody AccountTypeEntity accountType) {

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




    //Find all available Account type
    @GetMapping("/account/type")
    public ResponseEntity<?> getAllAccountType() {

        //Required variables
        List<AccountTypeEntity> listOfAccountType;

        try{
            listOfAccountType = accountTypeUseCases.fetchAllAccountType();
            return new ResponseEntity<List<AccountTypeEntity> >(listOfAccountType, HttpStatus.OK);

        }catch(Exception e){

                return new ResponseEntity<CustomResponse>(new CustomResponse(
                        "Controller: sorry, we messed up something! " + e.getMessage() ,
                        HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }




    //Delete an Account type by id
    @DeleteMapping("/account/type/{id}")
    @ResponseBody
    public ResponseEntity<CustomResponse> deleteTouristSpotById(@PathVariable Long id){

        try{
            String message = accountTypeUseCases.deleteAccountTypeById(id);
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




}
