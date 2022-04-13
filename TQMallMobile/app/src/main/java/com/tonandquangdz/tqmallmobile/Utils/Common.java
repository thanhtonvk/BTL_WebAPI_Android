package com.tonandquangdz.tqmallmobile.Utils;

import com.tonandquangdz.tqmallmobile.Models.Brand;
import com.tonandquangdz.tqmallmobile.Models.Category;
import com.tonandquangdz.tqmallmobile.Models.Product;

import java.text.NumberFormat;
import java.util.Locale;

public class Common {
    public static String baseUrl = "http://192.168.1.107:2803/api/";
    public static String username;
    public static Brand brand;
    public static Category category;
    public static Product product;
    public static int sum = 0;

    public static String formatMoney(int money) {
        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(money);
    }
}
