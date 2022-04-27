package com.tonandquangdz.tqmallmobile.Activiy;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tonandquangdz.tqmallmobile.API.ProductService;
import com.tonandquangdz.tqmallmobile.Adapter.ProductAdapter;
import com.tonandquangdz.tqmallmobile.Adapter.SearchAdapter;
import com.tonandquangdz.tqmallmobile.Models.DataUser;
import com.tonandquangdz.tqmallmobile.Models.Product;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductActivity extends AppCompatActivity {

    AutoCompleteTextView edt_seach;
    GridView lv_search;
    SearchAdapter autoSearchAdapter;
    List<String> autoSearchList = new ArrayList<>();
    TextView tv_searchString;
    ProductAdapter adapter;
    List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        initView();
        loadData();
        edt_seach.clearFocus();
        edt_seach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    Common.searchString = edt_seach.getText().toString();
                    Common.writeUser(new DataUser(0, 0, 0, edt_seach.getText().toString()));
                    startActivity(new Intent(getApplicationContext(), SearchProductActivity.class));
                    finish();
                    return true;
                }
                return false;
            }
        });

    }

    private void initView() {
        edt_seach = findViewById(R.id.tv_search);
        lv_search = findViewById(R.id.lv_search);
        tv_searchString = findViewById(R.id.tv_search_string);
        adapter = new ProductAdapter(SearchProductActivity.this, productList);
        lv_search.setAdapter(adapter);
    }

    private void loadData() {
        ProductService.api.getProductList("", 1, 100).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null) {
                    for (Product product : response.body()
                    ) {
                        autoSearchList.add(product.getName());
                    }
                    autoSearchAdapter = new SearchAdapter(SearchProductActivity.this, autoSearchList);
                    edt_seach.setAdapter(autoSearchAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        tv_searchString.setText("Kết quả tìm kiếm cho: " + Common.searchString);
        ProductService.api.getProductList(Common.searchString, 1, 100).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null) {
                    for (Product product : response.body()
                    ) {
                        productList.add(product);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.product = productList.get(i);
                startActivity(new Intent(getApplicationContext(), ProductActivity.class));
            }
        });

    }
}