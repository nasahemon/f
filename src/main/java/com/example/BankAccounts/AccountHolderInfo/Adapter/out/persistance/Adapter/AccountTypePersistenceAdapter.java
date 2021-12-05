package com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Adapter;

import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity.AccountTypeDbEntity;
import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Repository.AccountTypeRepository;
import com.example.BankAccounts.AccountHolderInfo.Application.port.out.AccountTypePort;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


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
}
