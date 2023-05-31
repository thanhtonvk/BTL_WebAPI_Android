package com.example.doantn.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doantn.API.OrderDetailsService;
import com.example.doantn.Models.Cart;
import com.example.doantn.Models.Order;
import com.example.doantn.Models.OrderDetail;
import com.example.doantn.R;
import com.example.doantn.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusOrderAdapter extends ArrayAdapter<Order> {
    List<Order> orderList;
    Context context;

    public StatusOrderAdapter(@NonNull Context context, List<Order> orderList) {
        super(context, 0, orderList);
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_status_order, parent, false);
        }
        TextView tv_status = convertView.findViewById(R.id.tv_status);
        TextView tv_date = convertView.findViewById(R.id.tv_date);
        ExpandableHeightGridView lv_product = convertView.findViewById(R.id.lv_product);
        TextView tv_sum = convertView.findViewById(R.id.tv_sum);
        Order order = orderList.get(position);
        tv_date.setText(order.getDate());
        switch (order.getStatus()) {
            case 0:
                tv_status.setTextColor(R.color.prepare);
                tv_status.setText("Đang chuẩn bị");
                break;
            case 1:
                tv_status.setTextColor(Color.YELLOW);
                tv_status.setText("Đang vận chuyển");
                break;
            case 2:
                tv_status.setTextColor(Color.GREEN);
                tv_status.setText("Đã giao thành công");
                break;
            case -1:
                tv_status.setTextColor(Color.RED);
                tv_status.setText("Đã hủy");
                break;
        }
        List<Cart> cartList = new ArrayList<>();
        CartOrderAdapter adapter = new CartOrderAdapter(context, cartList);
        lv_product.setAdapter(adapter);
        lv_product.setExpanded(true);
        OrderDetailsService.api.getOrderDetailsList(order.getID()).enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                if (response.body() != null) {
                    for (OrderDetail orderDetail : response.body()
                    ) {
                        Cart cart= new Cart();
                        cart.setIDProductDetails(orderDetail.getIDProductDetails());
                        cart.setQuantity(orderDetail.getQuantity());
                        cartList.add(cart);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {

            }
        });
       OrderDetailsService.api.summary(order.getID()).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() != null) {
                    tv_sum.setText(Common.formatMoney(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });


        return convertView;
    }
}
