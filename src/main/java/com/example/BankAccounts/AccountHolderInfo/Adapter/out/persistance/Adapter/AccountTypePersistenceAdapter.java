package com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Adapter;

import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity.AccountTypeDbEntity;
import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Repository.AccountTypeRepository;
import com.example.BankAccounts.AccountHolderInfo.Application.port.out.AccountTypePort;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import com.example.BankAccounts.MyUtil.MyException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AccountTypePersistenceAdapter implements AccountTypePort {
    public AccountTypeRepository accountTypeRepository;

    public AccountTypePersistenceAdapter(AccountTypeRepository accountTypeRepository){
        this.accountTypeRepository = accountTypeRepository;
    }

    ModelMapper mapper = new ModelMapper();

    @Override
    public AccountTypeEntity saveAccountType(AccountTypeEntity accountType) {
        AccountTypeDbEntity accountTypeDbEntity = mapper.map(accountType,AccountTypeDbEntity.class);
        AccountTypeDbEntity savedAccountTypeDbEntity = accountTypeRepository.save(accountTypeDbEntity);
        AccountTypeEntity accountTypeEntity = mapper.map(savedAccountTypeDbEntity,AccountTypeEntity.class);
        return accountTypeEntity;
    }

    @Override
    public List<AccountTypeEntity> fetchAllAccountType() {
        List<AccountTypeEntity> listOfAccountTypes = accountTypeRepository.findAll().stream()
                .map(t -> mapper.map(t, AccountTypeEntity.class)).collect(Collectors.toList());
        return listOfAccountTypes;
    }

    @Override
    public String deleteAccountTypeById(Long id) throws MyException {
        String message="ok";
        try{
            accountTypeRepository.deleteById(id);
            if(accountTypeRepository.existsById(id)) return message="Still_Exist";
        }catch(IllegalArgumentException e){
            throw new MyException(HttpStatus.NOT_FOUND,
                    "Adapter: Doesn't have anything with id : " + id);
        }
        return message;
    }


}
