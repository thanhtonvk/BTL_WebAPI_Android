package com.tonandquangdz.tqmallmobile.Models;

public class Voucher {
    public String ID;
    public String Username;
    public double Sale;
    public int Quantity;
    public boolean Status;

    public Account Account;

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

    public com.tonandquangdz.tqmallmobile.Models.Account getAccount() {
        return Account;
    }

    public void setAccount(com.tonandquangdz.tqmallmobile.Models.Account account) {
        Account = account;
    }

    public Voucher(String ID, String username, double sale, int quantity, boolean status, com.tonandquangdz.tqmallmobile.Models.Account account) {
        this.ID = ID;
        Username = username;
        Sale = sale;
        Quantity = quantity;
        Status = status;
        Account = account;
    }
}
