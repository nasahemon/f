package com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Adapter;

import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity.AccountHolderDbEntity;
import com.example.BankAccounts.AccountHolderInfo.Application.port.out.AccountHolderDetailsPort;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountHolderEntity;
import com.example.BankAccounts.MyUtil.CustomAccountMapper;
import com.example.BankAccounts.MyUtil.MyException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AccountHolderPersistenceAdapter implements AccountHolderDetailsPort {




    private final JdbcTemplate jdbcTemplate;

    public AccountHolderPersistenceAdapter( JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }
    ModelMapper mapper = new ModelMapper();


    @Override
    public AccountHolderEntity saveAccountHolderDetails(AccountHolderEntity accountHolderEntity) {
        AccountHolderDbEntity accountHolderDbEntity = mapper.map(accountHolderEntity,AccountHolderDbEntity.class);
        String sql = "INSERT INTO public.account_holder_info\n" +
                "(address, age, contact_no, created_on, date_of_birth, gender, hobby, \"name\", id)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(sql,accountHolderDbEntity.getAddress(),
                accountHolderDbEntity.getAge(),
                accountHolderDbEntity.getContactNo(),
                accountHolderDbEntity.getCreatedOn(),
                accountHolderDbEntity.getDateOfBirth(),
                accountHolderDbEntity.getGender(),
                accountHolderDbEntity.getHobby(),
                accountHolderDbEntity.getName(),
                accountHolderDbEntity.getAccountType().getId());
        AccountHolderEntity savedAccountHolderEntity = mapper.map(accountHolderDbEntity,AccountHolderEntity.class);
        return savedAccountHolderEntity;
    }

    @Override
    public List<AccountHolderEntity> fetchAllAccountHolderDetails() throws MyException {
        List<AccountHolderEntity> ListOfAccountHolder;
        try {
            String sql = "SELECT a.account_id ,a.address ,a.age ,a.contact_no ,a.created_on ,a.date_of_birth ,a.gender ,a.hobby ,a.\"name\" ,a.id ,t.\"type\" \n" +
                    "FROM public.account_holder_info a\n" +
                    "INNER join public.account_types t \n" +
                    "on a.id = t.id ;";
            List<AccountHolderDbEntity> accountHolders = jdbcTemplate.query(sql, new CustomAccountMapper());

            ListOfAccountHolder = accountHolders.stream()
                    .map(t -> mapper.map(t, AccountHolderEntity.class)).collect(Collectors.toList());
        }catch(Exception e){
            e.printStackTrace();
            throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR,"Adapter: Exception thrown by system");
        }
        return ListOfAccountHolder;
    }



    @Override
    public String deleteAccountHolderDetailsById(Long id) throws MyException {
        String message="ok";
        try{
            jdbcTemplate.update("DELETE FROM public.account_holder_info WHERE account_id=?", id);
        }catch(Exception e){
            e.printStackTrace();
            throw new MyException(HttpStatus.NOT_FOUND,
                    "Adapter: Doesn't have anything with id : " + id);
        }
        return message;
    }

    @Override
    public AccountHolderEntity updateAccountHolderDetailsById(Long id, AccountHolderEntity accountHolderEntity) throws MyException {
        try{
            AccountHolderDbEntity updatedAccountHolderDetails = mapper.map(accountHolderEntity,AccountHolderDbEntity.class);
            jdbcTemplate.update("UPDATE public.account_holder_info SET address=?,age=?,contact_no=?,created_on=?,"+
                            "date_of_birth=?,gender=?,hobby=?,\"name\"=?,id=? WHERE account_id=?",
                    updatedAccountHolderDetails.getAddress(),
                    updatedAccountHolderDetails.getAge(),
                    updatedAccountHolderDetails.getContactNo(),
                    updatedAccountHolderDetails.getCreatedOn(),
                    updatedAccountHolderDetails.getDateOfBirth(),
                    updatedAccountHolderDetails.getGender(),
                    updatedAccountHolderDetails.getHobby(),
                    updatedAccountHolderDetails.getName(),
                    updatedAccountHolderDetails.getAccountType().getId(),
                    id);
            String sql = "SELECT a.account_id ,a.address ,a.age ,a.contact_no ,a.created_on ,a.date_of_birth ,a.gender ,a.hobby ,a.\"name\" ,a.id ,t.\"type\" \n" +
                    "FROM public.account_holder_info a\n" +
                    "INNER join public.account_types t \n" +
                    "on a.id = t.id WHERE account_id=? ;";
            AccountHolderDbEntity savedAccountHolderEntity= jdbcTemplate.queryForObject(sql,
                    new CustomAccountMapper(),id);
            return mapper.map(savedAccountHolderEntity,AccountHolderEntity.class);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new MyException(HttpStatus.NOT_FOUND,
                    "Adapter: repository doesn't have anything with id : " + id);
        }
    }


}
//    @Override
//    public List<AccountHolderEntity> fetchAllAccountHolderDetails() throws MyException {
//        List<AccountHolderEntity> ListOfAccountHolder;
//
//        try {
//            ListOfAccountHolder = accountHolderRepository.findAll().stream()
//                    .map(t -> mapper.map(t, AccountHolderEntity.class)).collect(Collectors.toList());
//        }
//        catch (NullPointerException e){
//            throw new MyException(HttpStatus.NO_CONTENT, "Adapter: Repository is empty.");
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR,"Adapter: Exception thrown by system");
//        }
//        return ListOfAccountHolder;
//    }

//    @Override
//    public AccountHolderEntity updateAccountHolderDetailsById(Long id, AccountHolderEntity accountHolderEntity) throws MyException {
//        try{
//            AccountHolderDbEntity found = accountHolderRepository.findById(id).get();
//            AccountHolderDbEntity updatedAccountHolderDetails = mapper.map(accountHolderEntity,AccountHolderDbEntity.class);
//            updatedAccountHolderDetails.setId(id);
//            AccountHolderDbEntity savedAccountHolderEntity= accountHolderRepository.save(updatedAccountHolderDetails);
//            return mapper.map(savedAccountHolderEntity,AccountHolderEntity.class);
//        }
//        catch(Exception e){
//            throw new MyException(HttpStatus.NOT_FOUND,
//                    "Adapter: repository doesn't have anything with id : " + id);
//        }
//    }
