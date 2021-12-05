package com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Adapter;

import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity.AccountHolderDbEntity;
import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Repository.AccountHolderRepository;
import com.example.BankAccounts.AccountHolderInfo.Application.port.out.AccountHolderDetailsPort;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


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
}
