package com.example.doantn.Models;

public class Product {
    private int ID;
    private String Name;
    private String Username;
    private int IDCategory;
    private int IDBrand;
    private int Cost;
    private double Sale;
    private double FlashSaleFrom;
    private double FlashSaleTo;
    private String Image;
    private String Description;
    private String Details;
    private int Quantity;
    private boolean Status;
    private  Account Account;
    private  Brand Brand;
    private  Category Category;

    public Product(int ID, String name, String username, int IDCategory, int IDBrand, int cost, double sale, double flashSaleFrom, double flashSaleTo, String image, String description, String details, int quantity, boolean status, Account account, Brand brand, Category category) {
        this.ID = ID;
        Name = name;
        Username = username;
        this.IDCategory = IDCategory;
        this.IDBrand = IDBrand;
        Cost = cost;
        Sale = sale;
        FlashSaleFrom = flashSaleFrom;
        FlashSaleTo = flashSaleTo;
        Image = image;
        Description = description;
        Details = details;
        Quantity = quantity;
        Status = status;
        Account = account;
        Brand = brand;
        Category = category;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getIDCategory() {
        return IDCategory;
    }

    public void setIDCategory(int IDCategory) {
        this.IDCategory = IDCategory;
    }

    public int getIDBrand() {
        return IDBrand;
    }

    public void setIDBrand(int IDBrand) {
        this.IDBrand = IDBrand;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public double getSale() {
        return Sale;
    }

    public void setSale(double sale) {
        Sale = sale;
    }

    public double getFlashSaleFrom() {
        return FlashSaleFrom;
    }

    public void setFlashSaleFrom(double flashSaleFrom) {
        FlashSaleFrom = flashSaleFrom;
    }

    public double getFlashSaleTo() {
        return FlashSaleTo;
    }

    public void setFlashSaleTo(double flashSaleTo) {
        FlashSaleTo = flashSaleTo;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
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

    public com.example.doantn.Models.Brand getBrand() {
        return Brand;
    }

    public void setBrand(com.example.doantn.Models.Brand brand) {
        Brand = brand;
    }

    public com.example.doantn.Models.Category getCategory() {
        return Category;
    }

    public void setCategory(com.example.doantn.Models.Category category) {
        Category = category;
    }
}
