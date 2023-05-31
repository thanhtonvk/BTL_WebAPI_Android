package com.example.doantn.Activiy;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doantn.API.ProductService;
import com.example.doantn.Adapter.ExpandableHeightGridView;
import com.example.doantn.Adapter.ProductFlashSaleAdapter;
import com.example.doantn.Models.Product;
import com.example.doantn.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlashSaleListActivity extends AppCompatActivity {

    ExpandableHeightGridView gridView;
    ProductFlashSaleAdapter adapter;
    List<Product> productList = new ArrayList<>();
    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_sale_list);
        initView();
        loadData();
    }

    private void initView() {
        gridView = findViewById(R.id.gv_product);
        adapter = new ProductFlashSaleAdapter(getApplicationContext(), productList);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);
        findViewById(R.id.btn_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
    }

    private void loadData() {
        page++;
        ProductService.api.getFlashSales(page, 10).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null) {
                    for (int i = ((page - 1) * 10); i < response.body().size(); i++) {
                        productList.add(response.body().get(i));
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

}