package com.example.doantn.Fragment.MainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doantn.API.CartService;
import com.example.doantn.Activiy.PayActivity;
import com.example.doantn.Adapter.CartAdapter;
import com.example.doantn.Models.Cart;
import com.example.doantn.R;
import com.example.doantn.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    ListView lv_cart;
    TextView tv_summary;
    Button btn_buy;
    CartAdapter adapter;
    List<Cart> cartList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
        onClick();
    }

    private void initView(View view) {
        lv_cart = view.findViewById(R.id.lv_cart);
        btn_buy = view.findViewById(R.id.btn_buy_now);
        tv_summary = view.findViewById(R.id.tv_sum);
        adapter = new CartAdapter(getContext(), cartList);
        lv_cart.setAdapter(adapter);
        btn_buy= view.findViewById(R.id.btn_buy);
    }

    private void loadData() {
        CartService.api.getCartList(Common.username).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.body() != null) {
                    for (Cart cart : response.body()
                    ) {
                        cartList.add(cart);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                CartService.api.getSummary(Common.username).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() != null) {
                            tv_summary.setText(Common.formatMoney(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);

    }

    private void onClick() {
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartList.size()>0){
                    startActivity(new Intent(getContext(), PayActivity.class));
                }

            }
        });
    }

}