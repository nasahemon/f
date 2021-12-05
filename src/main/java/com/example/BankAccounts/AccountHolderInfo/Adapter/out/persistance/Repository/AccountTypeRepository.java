package com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Repository;

import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity.AccountTypeDbEntity;
import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeDbEntity,Long> {
}
