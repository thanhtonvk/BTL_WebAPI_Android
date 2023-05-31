package com.example.doantn.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.doantn.API.AccountService;
import com.example.doantn.Models.Account;
import com.example.doantn.Models.Brand;
import com.example.doantn.Models.Category;
import com.example.doantn.Models.DataUser;
import com.example.doantn.Models.Product;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Common {
    public static String baseUrl = "http://192.168.1.107:2803/api/";
    public static String username;
    public static Brand brand;
    public static Category category;
    public static Product product;
    public static int sum = 0;
    public static int status;
    public static Account account;
    public static Gson gson = new Gson();
    public static String searchString;
    public static String toJsonString(List<DataUser> dataUsers) {
        return gson.toJson(dataUsers);
    }

    public static List<DataUser> toListObject(String json) {
        Type userDataUsersListType = new TypeToken<List<DataUser>>() {
        }.getType();
        return gson.fromJson(json, userDataUsersListType);
    }
    public static void writeUser(DataUser dataUser){
        List<DataUser>dataUsers = toListObject(account.getDataUser());
        if(dataUsers==null){
            dataUsers = new ArrayList<>();
        }
        dataUsers.add(dataUser);
        account.setDataUser(toJsonString(dataUsers));
        AccountService.api.update(account).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.body()!=null){
                    Log.e("TAG",response.body()+"");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
    public static String formatMoney(int money) {
        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(money);
    }
}
