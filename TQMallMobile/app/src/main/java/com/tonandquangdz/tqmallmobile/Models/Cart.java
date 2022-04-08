package com.tonandquangdz.tqmallmobile.Models;

public class Cart {
    private int ID;
    private String Username;
    private int IDProductDetails;
    private int Quantity;

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

    public int getIDProductDetails() {
        return IDProductDetails;
    }

    public void setIDProductDetails(int IDProductDetails) {
        this.IDProductDetails = IDProductDetails;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Cart(int ID, String username, int IDProductDetails, int quantity) {
        this.ID = ID;
        Username = username;
        this.IDProductDetails = IDProductDetails;
        Quantity = quantity;
    }
}
