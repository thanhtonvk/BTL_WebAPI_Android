package com.example.doantn.Models;

public class DataUser {
    private int idProduct;
    private int idCategory;
    private int idBrand;
    private String search;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public DataUser(int idProduct, int idCategory, int idBrand, String search) {
        this.idProduct = idProduct;
        this.idCategory = idCategory;
        this.idBrand = idBrand;
        this.search = search;
    }
}

