package com.tonandquangdz.tqmallmobile.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tonandquangdz.tqmallmobile.API.BrandService;
import com.tonandquangdz.tqmallmobile.Adapter.BrandGvAdapter;
import com.tonandquangdz.tqmallmobile.Models.Brand;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandListActivity extends AppCompatActivity {

    GridView gridView;
    List<Brand> brandList;
    BrandGvAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list);
        initView();
        loadData();
        onClick();
    }
    private void onClick(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.brand = brandList.get(i);
                startActivity(new Intent(getApplicationContext(),ProductListByBrandActivity.class));
            }
        });
    }
    private void initView(){
        gridView = findViewById(R.id.gv_brand);
        brandList = new ArrayList<>();
        adapter = new BrandGvAdapter(getApplicationContext(),brandList);
        gridView.setAdapter(adapter);
    }
    private void loadData(){
        BrandService.api.getBrandList().enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                if(response.body()!=null){
                    for(Brand brand: response.body()){
                        brandList.add(brand);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {

            }
        });
    }

}