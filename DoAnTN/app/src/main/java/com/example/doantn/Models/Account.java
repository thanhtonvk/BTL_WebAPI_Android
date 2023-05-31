package com.example.doantn.Models;

public class Account {
    private String Username;
    private String Password;
    private String FullName;
    private String Avatar;
    private String DateOfBirth;
    private String PhoneNumber;
    private String Address;
    private Short Status;
    private String DataUser;

    public Account(String username, String password, String fullName, String avatar, String dateOfBirth, String phoneNumber, String address, Short status, String dataUser) {
        Username = username;
        Password = password;
        FullName = fullName;
        Avatar = avatar;
        DateOfBirth = dateOfBirth;
        PhoneNumber = phoneNumber;
        Address = address;
        Status = status;
        DataUser = dataUser;
    }

    public Account() {

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Short getStatus() {
        return Status;
    }

    public void setStatus(Short status) {
        Status = status;
    }

    public String getDataUser() {
        return DataUser;
    }

    public void setDataUser(String dataUser) {
        DataUser = dataUser;
    }
}
