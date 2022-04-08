package com.tonandquangdz.tqmallmobile.Models;

public class ReviewProduct {
    private int ID;
    private String Username;
    private int IDProduct;
    private int Rate;
    private String Review;
    private String Image;

    private  Account Account;

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

    public int getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(int IDProduct) {
        this.IDProduct = IDProduct;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public com.tonandquangdz.tqmallmobile.Models.Account getAccount() {
        return Account;
    }

    public void setAccount(com.tonandquangdz.tqmallmobile.Models.Account account) {
        Account = account;
    }

    public ReviewProduct(int ID, String username, int IDProduct, int rate, String review, String image, com.tonandquangdz.tqmallmobile.Models.Account account) {
        this.ID = ID;
        Username = username;
        this.IDProduct = IDProduct;
        Rate = rate;
        Review = review;
        Image = image;
        Account = account;
    }
}
