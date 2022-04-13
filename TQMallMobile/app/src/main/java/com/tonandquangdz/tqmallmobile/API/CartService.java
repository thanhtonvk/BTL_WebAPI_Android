package com.tonandquangdz.tqmallmobile.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tonandquangdz.tqmallmobile.Models.Cart;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface CartService {
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();
    CartService api = new Retrofit.Builder()
            .baseUrl(Common.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(CartService.class);

    @GET("cart/getcarbyuser")
    Call<List<Cart>> getCartList(@Query("username") String username);

    @POST("cart/addcart")
    Call<Integer> addCart(@Body Cart cart);

    @PUT("cart/updatecart")
    Call<Integer> updateCart(@Query("id") int id, @Query("quantity") int quantity);

    @DELETE("cart/deletecart")
    Call<Integer> deleteCart(@Query("id") int id);

    @DELETE("cart/deleteallcart")
    Call<Integer> deleteAllCart(@Query("username") String username);

    @GET("cart/summary")
    Call<Integer> getSummary(@Query("username") String username);
}
