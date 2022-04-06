package com.tonandquangdz.tqmallmobile.Models;

public class Product {
    public int ID;
    public String Name;
    public String Username;
    public int IDCategory;
    public int IDBrand;
    public int Cost;
    public double Sale;
    public double FlashSaleFrom;
    public double FlashSaleTo;
    public String Image;
    public String Description;
    public String Details;
    public int Quantity;
    public boolean Status;
    public  Account Account;
    public  Brand Brand;
    public  Category Category;

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
}
