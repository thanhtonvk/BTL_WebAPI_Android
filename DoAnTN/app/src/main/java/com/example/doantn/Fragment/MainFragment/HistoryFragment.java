package com.example.doantn.Fragment.MainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doantn.API.ProductService;
import com.example.doantn.Activiy.ProductActivity;
import com.example.doantn.Adapter.ProductAdapter;
import com.example.doantn.Models.DataUser;
import com.example.doantn.Models.Product;
import com.example.doantn.R;
import com.example.doantn.Utils.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    GridView gridView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        loadData();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.product = productList.get(i);
                startActivity(new Intent(getContext(), ProductActivity.class));
            }
        });
    }

    private void initView(View view) {
        gridView = view.findViewById(R.id.lv_product);
    }

    List<DataUser> dataUsers;
    List<Product> productList = new ArrayList<>();

    private void loadData() {
        ProductAdapter productAdapter = new ProductAdapter(getContext(), productList);
        gridView.setAdapter(productAdapter);
        if (Common.account.getDataUser() != null) {
            dataUsers = Common.toListObject(Common.account.getDataUser());
            Collections.reverse(dataUsers);
            Log.e("TAG",dataUsers.size()+"");
            for (DataUser dataUser : dataUsers
            ) {
                if (dataUser.getIdProduct() > 0) {
                    ProductService.api.getProductById(dataUser.getIdProduct()).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            if (response.body() != null) {
                                Log.e("TAG",response.body().getName());
                                productList.add(response.body());
                                productAdapter.notifyDataSetChanged();
                            }

                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {

                        }
                    });
                }

            }
        }

    }
}