package com.example.BankAccounts.AccountHolderInfo.Adapter.out.persistance.Entity;

import com.example.BankAccounts.AccountHolderInfo.Domain.AccountTypeEntity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="account_holder_info")
public class AccountHolderDbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;

    @Column(name="contact_no")
    private String contactNo;
    @Column(name="age")
    private int age;
    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name="gender")
    private String gender;
    @Column(name = "hobby")
    private String hobby;
    @Column(name="created_on")
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "id")
    private AccountTypeDbEntity accountType;

    public AccountHolderDbEntity() {
    }

    public AccountHolderDbEntity(Long id, String name, String address, String contactNo, int age,
                               Date dateOfBirth, String gender, String hobby,
                               Date createdOn, AccountTypeDbEntity accountType) {
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

    public AccountTypeDbEntity getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeDbEntity accountType) {
        this.accountType = accountType;
    }

}
