package com.example.doantn.Activiy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doantn.API.CategoryService;
import com.example.doantn.Adapter.CategoryGvAdapter;
import com.example.doantn.Models.Category;
import com.example.doantn.R;
import com.example.doantn.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListActivity extends AppCompatActivity {

    GridView gridView;
    List<Category> categoryList;
    CategoryGvAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        initView();
        loadData();
        onClick();
    }
    private void onClick(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.category = categoryList.get(i);
                startActivity(new Intent(getApplicationContext(),ProductListByCategoryActivity.class));
            }
        });
    }
    private void initView(){
        gridView = findViewById(R.id.gv_category);
        categoryList = new ArrayList<>();
        adapter = new CategoryGvAdapter(getApplicationContext(),categoryList);
        gridView.setAdapter(adapter);
    }
    private void loadData(){
        CategoryService.api.getCategoryList().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.body()!=null){
                    for(Category category: response.body()){
                        categoryList.add(category);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }
}