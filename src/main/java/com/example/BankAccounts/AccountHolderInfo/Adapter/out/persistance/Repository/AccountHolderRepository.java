package com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Repository;

import com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity.AccountHolderDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolderDbEntity,Long> {
}
