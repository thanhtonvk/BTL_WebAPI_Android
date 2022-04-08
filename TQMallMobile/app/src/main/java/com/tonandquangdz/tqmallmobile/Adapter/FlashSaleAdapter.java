package com.tonandquangdz.tqmallmobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tonandquangdz.tqmallmobile.Models.Product;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.List;

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.ViewHolder> {
    Context context;
    List<Product> productList;

    public FlashSaleAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }


    @NonNull
    @Override
    public FlashSaleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_sale, parent, false);
        return new FlashSaleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        if (!product.getName().equals("")) {
            Glide.with(context).load(product.getImage()).into(holder.img_product);
        }
        holder.tv_sale.setText((product.getSale() * 100) + "%");
        String money = Common.formatMoney((int) (product.getCost() * (1 - product.getSale())));
        holder.tv_cost.setText(money);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_product;
        TextView tv_cost, tv_sale;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product = itemView.findViewById(R.id.image_product);
            tv_cost = itemView.findViewById(R.id.tv_cost);
            tv_sale = itemView.findViewById(R.id.tv_sale);
        }
    }
}
