package com.example.BankAccounts.AccountHolderInfo.Domain;

import java.util.Date;

public class AccountHolderEntity {
    private Long id;
    private String name;
    private String address;
    private String contactNo;
    private int age;
    private Date dateOfBirth;
    private String gender;
    private String hobby;
    private Date createdOn;
    private AccountTypeEntity accountType;

    public AccountHolderEntity() {
    }

    public AccountHolderEntity(Long id, String name, String address, String contactNo, int age,
                               Date dateOfBirth, String gender, String hobby,
                               Date createdOn, AccountTypeEntity accountType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.hobby = hobby;
        this.createdOn = createdOn;
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public AccountTypeEntity getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEntity accountType) {
        this.accountType = accountType;
    }
}
