package com.tonandquangdz.tqmallmobile.Models;

public class ProductDetail {
    public int ID;
    public int IDProduct;
    public String Name;
    public int Cost;
    public String Image;
    public boolean Status;
    public  Product Product;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(int IDProduct) {
        this.IDProduct = IDProduct;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public com.tonandquangdz.tqmallmobile.Models.Product getProduct() {
        return Product;
    }

    public void setProduct(com.tonandquangdz.tqmallmobile.Models.Product product) {
        Product = product;
    }

    public ProductDetail(int ID, int IDProduct, String name, int cost, String image, boolean status, com.tonandquangdz.tqmallmobile.Models.Product product) {
        this.ID = ID;
        this.IDProduct = IDProduct;
        Name = name;
        Cost = cost;
        Image = image;
        Status = status;
        Product = product;
    }
}
