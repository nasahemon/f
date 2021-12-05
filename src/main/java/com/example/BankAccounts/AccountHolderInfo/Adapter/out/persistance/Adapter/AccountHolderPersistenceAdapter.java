package com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Adapter;

import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity.AccountHolderDbEntity;
import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Repository.AccountHolderRepository;
import com.example.BankAccounts.AccountHolderInfo.Application.port.out.AccountHolderDetailsPort;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import com.example.BankAccounts.MyUtil.MyException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AccountHolderPersistenceAdapter implements AccountHolderDetailsPort {


    private final AccountHolderRepository accountHolderRepository;

    public AccountHolderPersistenceAdapter(AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
    }
    ModelMapper mapper = new ModelMapper();


    @Override
    public AccountHolderEntity saveAccountHolderDetails(AccountHolderEntity accountHolderEntity) {
        AccountHolderDbEntity accountHolderDbEntity = mapper.map(accountHolderEntity,AccountHolderDbEntity.class);
        AccountHolderDbEntity savedAccountHolderDbEntity = accountHolderRepository.save(accountHolderDbEntity);
        AccountHolderEntity savedAccountHolderEntity = mapper.map(savedAccountHolderDbEntity,AccountHolderEntity.class);
        return savedAccountHolderEntity;
    }

    @Override
    public List<AccountHolderEntity> fetchAllAccountHolderDetails() throws MyException {
        List<AccountHolderEntity> ListOfAccountHolder;

        try {
            ListOfAccountHolder = accountHolderRepository.findAll().stream()
                    .map(t -> mapper.map(t, AccountHolderEntity.class)).collect(Collectors.toList());
        }
        catch (NullPointerException e){
            throw new MyException(HttpStatus.NO_CONTENT, "Adapter: Repository is empty.");
        }
        catch(Exception e){
            e.printStackTrace();
            throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR,"Adapter: Exception thrown by system");
        }
        return ListOfAccountHolder;
    }

    @Override
    public String deleteAccountHolderDetailsById(Long id) throws MyException {
        String message="ok";
        try{
            accountHolderRepository.deleteById(id);
            if(accountHolderRepository.existsById(id)) return message="Still_Exist";
        }catch(IllegalArgumentException e){
            throw new MyException(HttpStatus.NOT_FOUND,
                    "Adapter: Doesn't have anything with id : " + id);
        }
        return message;
    }
}
