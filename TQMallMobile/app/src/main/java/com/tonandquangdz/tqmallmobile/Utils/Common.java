package com.tonandquangdz.tqmallmobile.Utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Common {
    public static String baseUrl = "http://192.168.1.107:2803/api/";
    public static String username;

    public static String formatMoney(int money) {
        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(money);
    }
}
