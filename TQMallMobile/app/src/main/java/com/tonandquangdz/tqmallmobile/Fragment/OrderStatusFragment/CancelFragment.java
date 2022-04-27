package com.tonandquangdz.tqmallmobile.Fragment.OrderStatusFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tonandquangdz.tqmallmobile.API.OrderService;
import com.tonandquangdz.tqmallmobile.Adapter.StatusOrderAdapter;
import com.tonandquangdz.tqmallmobile.Models.Order;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CancelFragment extends Fragment {

    ListView lv_product;
    StatusOrderAdapter orderAdapter;
    List<Order> orderList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
    }


    private void initView(View view) {
        lv_product = view.findViewById(R.id.lv_product);
        orderAdapter = new StatusOrderAdapter(getContext(), orderList);
        lv_product.setAdapter(orderAdapter);
    }

    private void loadData() {
        OrderService.api.getOrderList(Common.username).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.body() != null) {
                    orderList.clear();
                    for (Order order : response.body()) {
                        if (order.getStatus() == -1) {
                            orderList.add(order);
                            orderAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cancel, container, false);
    }
}