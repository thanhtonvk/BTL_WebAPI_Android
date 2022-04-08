package com.tonandquangdz.tqmallmobile.Models;

public class Order {
    private int ID ;
    private String Username ;
    private String Date ;
    private String PhoneNumber ;
    private String Address ;
    private Short Status ;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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

    public Order(int ID, String username, String date, String phoneNumber, String address, Short status) {
        this.ID = ID;
        Username = username;
        Date = date;
        PhoneNumber = phoneNumber;
        Address = address;
        Status = status;
    }
}
