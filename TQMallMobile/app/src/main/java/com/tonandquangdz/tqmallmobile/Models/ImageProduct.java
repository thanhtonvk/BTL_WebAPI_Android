package com.tonandquangdz.tqmallmobile.Models;

public class ImageProduct {
    public int ID;
    public int IDProduct;
    public String Image;
    public boolean Status;

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

    public ImageProduct(int ID, int IDProduct, String image, boolean status) {
        this.ID = ID;
        this.IDProduct = IDProduct;
        Image = image;
        Status = status;
    }
}
