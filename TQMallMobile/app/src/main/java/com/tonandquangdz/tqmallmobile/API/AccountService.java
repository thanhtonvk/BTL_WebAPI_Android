package com.tonandquangdz.tqmallmobile.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tonandquangdz.tqmallmobile.Models.Account;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AccountService {
    Gson gson  =new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();
    AccountService api = new Retrofit.Builder()
            .baseUrl(Common.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(AccountService.class);
    @GET("accounts/login")
    Call<Integer> login(@Query("username") String username, @Query("password") String password);
    @GET("accounts/getaccountbyusername")
    Call<Account> getAccount(@Query("username") String username);
    @POST("accounts/register")
    Call<Integer> register(@Body Account account);
    @PUT("accounts/update")
    Call<Integer> update(@Body Account account);
}
