package com.example.doantn.Models;

public class Voucher {
    private String ID;
    private String Username;
    private double Sale;
    private int Quantity;
    private boolean Status;

    private Account Account;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public double getSale() {
        return Sale;
    }

    public void setSale(double sale) {
        Sale = sale;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public com.example.doantn.Models.Account getAccount() {
        return Account;
    }

    public void setAccount(com.example.doantn.Models.Account account) {
        Account = account;
    }

    public Voucher(String ID, String username, double sale, int quantity, boolean status, com.example.doantn.Models.Account account) {
        this.ID = ID;
        Username = username;
        Sale = sale;
        Quantity = quantity;
        Status = status;
        Account = account;
    }
}
