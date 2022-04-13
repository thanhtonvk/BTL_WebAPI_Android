package com.tonandquangdz.tqmallmobile.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tonandquangdz.tqmallmobile.API.ProductService;
import com.tonandquangdz.tqmallmobile.Adapter.ExpandableHeightGridView;
import com.tonandquangdz.tqmallmobile.Adapter.ProductAdapter;
import com.tonandquangdz.tqmallmobile.Models.Product;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListByCategoryActivity extends AppCompatActivity {

    TextView tv_name;
    ExpandableHeightGridView gv_product;
    Button btn_view_more;
    ProductAdapter adapter;
    List<Product> productList;
    int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_by_category);
        initView();
        loadProduct();
        onClick();
    }
    private void initView(){
        tv_name = findViewById(R.id.tv_name);
        gv_product= findViewById(R.id.gv_product);
        btn_view_more = findViewById(R.id.btn_more);
        productList= new ArrayList<>();
        adapter = new ProductAdapter(getApplicationContext(),productList);
        gv_product.setAdapter(adapter);
        tv_name.setText("Sản phẩm cho danh mục: "+ Common.category.getName());
    }
    private void onClick(){
        btn_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProduct();
            }
        });
    }
    private void loadProduct() {
        page++;
        ProductService.api.getProductByCategory(Common.category.getID(), page, 10).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null) {
                    for (int i = ((page-1) * 10); i < response.body().size(); i++) {
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