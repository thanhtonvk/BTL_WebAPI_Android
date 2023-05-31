package com.example.doantn.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.doantn.Models.ProductDetail;
import com.example.doantn.Utils.Common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductDetailsService {
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();
    ProductDetailsService api = new Retrofit.Builder()
            .baseUrl(Common.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ProductDetailsService.class);

    @GET("productdetails/getproductdetailsbyidproduct")
    Call<List<ProductDetail>> getProductDetails(@Query("idProduct") int idProudct);

    @GET("productdetails/getproductdetailsbyid")
    Call<ProductDetail> getProductDetailsById(@Query("id") int id);
}
