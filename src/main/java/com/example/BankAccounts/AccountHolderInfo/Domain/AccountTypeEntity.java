package com.example.BankAccounts.AccountHolderInfo.Domain;

public class AccountTypeEntity {
    private Long id;
    private String type;

    public AccountTypeEntity() {
    }

    public AccountTypeEntity(Long id, String type) {
        this.id = id;
        this.type = type;
    }
    public AccountTypeEntity( String type) {
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
