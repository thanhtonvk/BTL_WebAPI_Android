package com.tonandquangdz.tqmallmobile.Models;

import com.tonandquangdz.tqmallmobile.API.ProductDetailsService;
import com.tonandquangdz.tqmallmobile.API.ProductService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart {
    private int ID;
    private String Username;
    private int IDProductDetails;
    private int Quantity;
    private ProductDetail productDetail;
    private Product product;

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

    public Cart(String username, int IDProductDetails, int quantity) {
        Username = username;
        this.IDProductDetails = IDProductDetails;
        Quantity = quantity;
    }

    public ProductDetail getProductDetail() {

        ProductDetailsService.api.getProductDetailsById(IDProductDetails).enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                if (response.body() != null) {
                    productDetail = response.body();
                }
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {

            }
        });
        return productDetail;
    }

    public Product getProduct() {
        ProductService.api.getProductById(getProductDetail().getIDProduct()).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.body() != null) {
                    product = response.body();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
        return product;
    }
}
