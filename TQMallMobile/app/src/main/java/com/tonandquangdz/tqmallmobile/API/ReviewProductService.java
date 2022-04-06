package com.tonandquangdz.tqmallmobile.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tonandquangdz.tqmallmobile.Models.ReviewProduct;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReviewProductService {
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();
    ReviewProductService api = new Retrofit.Builder()
            .baseUrl(Common.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ReviewProductService.class);
    @POST("reviewproduct/addreivew")
    Call<Integer> addReview(@Body ReviewProduct reviewProduct);
    @GET("reviewproduct/getreview")
    Call<List<ReviewProduct>> getReviewProduct(@Query("IDProduct") int idProduct);
}
