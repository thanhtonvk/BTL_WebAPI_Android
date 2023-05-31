package com.example.doantn.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.doantn.Models.Product;
import com.example.doantn.Utils.Common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();
    ProductService api = new Retrofit.Builder()
            .baseUrl(Common.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ProductService.class);

    @GET("product/getproductbyid")
    Call<Product> getProductById(@Query("id") int id);

    @GET("product/getproductmobile")
    Call<List<Product>> getProductList(@Query("keyword") String keyword, @Query("page") int page, @Query("size") int size);

    @GET("product/getproductflashsale")
    Call<List<Product>> getFlashSales(@Query("page") int page, @Query("size") int size);

    @GET("product/getproductbybrand")
    Call<List<Product>> getProductByBrand(@Query("idBrand") int idBrand, @Query("page") int page, @Query("size") int size);

    @GET("product/getproductbycategory")
    Call<List<Product>> getProductByCategory(@Query("idCategory") int idBrand, @Query("page") int page, @Query("size") int size);
}
