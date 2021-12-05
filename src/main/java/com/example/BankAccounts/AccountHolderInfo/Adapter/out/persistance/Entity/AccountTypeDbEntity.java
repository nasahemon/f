package com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity;


import javax.persistence.*;

@Entity
@Table(name = "account_types")
public class AccountTypeDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "account_type", unique = true)
    private String type;

    public AccountTypeDbEntity() {
    }

    public AccountTypeDbEntity(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
