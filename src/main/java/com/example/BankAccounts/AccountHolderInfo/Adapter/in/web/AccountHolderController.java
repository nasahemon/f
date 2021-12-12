package com.example.BankAccounts.AccountHolderInfo.Adapter.in.web;


import com.example.BankAccounts.AccountHolderInfo.Application.port.in.*;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import com.example.BankAccounts.MyUtil.CustomResponse;
import com.example.BankAccounts.MyUtil.MyException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="http://localhost:4200")
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

    ModelMapper mapper = new ModelMapper();


    // Store an Account Holder Details
    @PostMapping("/account")
    public ResponseEntity<?> storeATouristSpotByDTO(@Valid @RequestBody AccountHolderInfo givenAccountHolderDetails) {


        AccountHolderEntity  accountHolderDetails = convertToAccountHolderEntity(givenAccountHolderDetails);
        try{
            AccountHolderEntity returnedAccountHolderDetails = createAccountUseCases.saveAccountHolderDetails(accountHolderDetails);
            return new ResponseEntity<CustomResponse>(new CustomResponse(
                    "Account has been saved successfully!",HttpStatus.OK), HttpStatus.OK);
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
        List<AccountHolderInfo> fetchedListOfAccountHolder;

        try{
            List<AccountHolderEntity> listOfAccountHolder = fetchAccountDetailsUseCases.fetchAllAccountHolderDetails();
//            fetchedListOfAccountHolder = listOfAccountHolder.stream()
//                    .map(t -> mapper.map(t, AccountHolderInfo.class)).collect(Collectors.toList());
            fetchedListOfAccountHolder = convertListToAccountHolderInfo(listOfAccountHolder);
            return new ResponseEntity<List<AccountHolderInfo> >(fetchedListOfAccountHolder, HttpStatus.OK);
//            return new ResponseEntity<List<AccountHolderEntity> >(listOfAccountHolder, HttpStatus.OK);

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





    public AccountHolderEntity convertToAccountHolderEntity(AccountHolderInfo accountHolderInfo){
        AccountHolderEntity a = new AccountHolderEntity();
        a.setId(accountHolderInfo.getId());
        a.setName(accountHolderInfo.getName());
        a.setAge(accountHolderInfo.getAge());
        a.setAddress(accountHolderInfo.getPresentAddress());
        a.setContactNo(accountHolderInfo.getContactNo());
        a.setCreatedOn(accountHolderInfo.getAccountCreateDate());
        a.setDateOfBirth(accountHolderInfo.getDob());
        a.setGender(accountHolderInfo.getGender());
        a.setHobby(accountHolderInfo.getHobby());
        a.setAccountType(accountHolderInfo.getAccountType());
        return a;
    }


    public AccountHolderInfo convertToAccountHolderInfo(AccountHolderEntity accountHolderInfo){
        AccountHolderInfo a = new AccountHolderInfo();
        a.setId(accountHolderInfo.getId());
        a.setName(accountHolderInfo.getName());
        a.setAge(accountHolderInfo.getAge());
        a.setPresentAddress(accountHolderInfo.getAddress());
        a.setContactNo(accountHolderInfo.getContactNo());
        a.setAccountCreateDate(accountHolderInfo.getCreatedOn());
        a.setDob(accountHolderInfo.getDateOfBirth());
        a.setGender(accountHolderInfo.getGender());
        a.setHobby(accountHolderInfo.getHobby());
        a.setAccountType(accountHolderInfo.getAccountType());
        return a;
    }



    public List<AccountHolderInfo> convertListToAccountHolderInfo(List<AccountHolderEntity> listOfEntity){
        List<AccountHolderInfo> returnList = new ArrayList<>();

        for (AccountHolderEntity entity:listOfEntity) {
            returnList.add(convertToAccountHolderInfo(entity));
        }

        return returnList;
    }
}
