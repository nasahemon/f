package com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Adapter;

import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity.AccountTypeDbEntity;
import com.example.BankAccounts.AccountHolderInfo.Application.port.out.AccountTypePort;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import com.example.BankAccounts.MyUtil.MyException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AccountTypePersistenceAdapter implements AccountTypePort {

    private final JdbcTemplate jdbcTemplate;

    public AccountTypePersistenceAdapter( JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<AccountTypeEntity> fetchAllAccountType()  {

        List<AccountTypeDbEntity> accountTypeDbEntity = jdbcTemplate.query(
                "SELECT * FROM public.account_types",
                BeanPropertyRowMapper.newInstance(AccountTypeDbEntity.class));

        return accountTypeDbEntity.stream()
                .map(t -> mapper.map(t, AccountTypeEntity.class)).collect(Collectors.toList());
    }


    @Override
    public AccountTypeEntity saveAccountType(AccountTypeEntity accountType) {
        AccountTypeDbEntity accountTypeDbEntity = mapper.map(accountType,AccountTypeDbEntity.class);

        jdbcTemplate.update("INSERT INTO public.account_types (type) VALUES(?)",
                accountTypeDbEntity.getType());

        AccountTypeDbEntity savedAccountTypeDbEntity = jdbcTemplate.queryForObject("SELECT * FROM public.account_types WHERE type=?",
                BeanPropertyRowMapper.newInstance(AccountTypeDbEntity.class), accountTypeDbEntity.getType());

        return mapper.map(savedAccountTypeDbEntity,AccountTypeEntity.class);
    }


    @Override
    public AccountTypeEntity updateAccountType(Long id, AccountTypeEntity accountType) {
        AccountTypeDbEntity accountTypeDbEntity = mapper.map(accountType,AccountTypeDbEntity.class);

         jdbcTemplate.update("UPDATE public.account_types SET type=? WHERE id=?",
                 accountTypeDbEntity.getType(),id);

        AccountTypeDbEntity updatedAccountTypeDbEntity = jdbcTemplate.queryForObject("SELECT * FROM public.account_types WHERE id=?",
                BeanPropertyRowMapper.newInstance(AccountTypeDbEntity.class), id);

        return mapper.map(updatedAccountTypeDbEntity,AccountTypeEntity.class);
    }


    @Override
    public String deleteAccountTypeById(Long id) throws MyException {
        String message="ok";
        try{
            jdbcTemplate.update("DELETE FROM public.account_types WHERE id=?", id);
        }catch(IllegalArgumentException e){
            throw new MyException(HttpStatus.NOT_FOUND,
                    "Adapter: Doesn't have anything with id : " + id);
        }
        return message;
    }


}

//    @Override
//    public AccountTypeEntity saveAccountType(AccountTypeEntity accountType) {
//        AccountTypeDbEntity accountTypeDbEntity = mapper.map(accountType,AccountTypeDbEntity.class);
//        AccountTypeDbEntity savedAccountTypeDbEntity = accountTypeRepository.save(accountTypeDbEntity);
//        AccountTypeEntity accountTypeEntity = mapper.map(savedAccountTypeDbEntity,AccountTypeEntity.class);
//        return accountTypeEntity;
//    }
//    get  all types
//    @Override
//    public List<AccountTypeEntity> fetchAllAccountType() {
//        List<AccountTypeEntity> listOfAccountTypes = accountTypeRepository.findAll().stream()
//                .map(t -> mapper.map(t, AccountTypeEntity.class)).collect(Collectors.toList());
//        return listOfAccountTypes;
//    }
