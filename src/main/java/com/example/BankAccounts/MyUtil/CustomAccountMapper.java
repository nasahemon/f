package com.example.BankAccounts.MyUtil;

import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity.AccountHolderDbEntity;
import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity.AccountTypeDbEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomAccountMapper implements RowMapper<AccountHolderDbEntity> {
    @Override
    public AccountHolderDbEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountHolderDbEntity user = new AccountHolderDbEntity();
        AccountTypeDbEntity accountTypeDb = new AccountTypeDbEntity();

        //a.account_id ,a.address ,a.age ,a.contact_no ,a.created_on ,a.date_of_birth ,a.gender ,a.hobby ,a."name" ,a.id ,t."type"
        user.setId(rs.getLong("account_id"));
        user.setName(rs.getString("name"));
        user.setAddress(rs.getString("address" ));

        user.setContactNo(rs.getString("contact_no"));
        user.setAge(rs.getInt("age"));
        user.setDateOfBirth(rs.getDate("date_of_birth"));
        user.setGender(rs.getString("gender"));
        user.setHobby(rs.getString("hobby"));
        user.setCreatedOn(rs.getDate("created_on"));
        accountTypeDb.setId(rs.getLong("id"));
        accountTypeDb.setType(rs.getString("type"));
        user.setAccountType(accountTypeDb);

        return user;
    }
}
