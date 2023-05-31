package com.example.doantn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.doantn.Activiy.ProductActivity;
import com.example.doantn.Models.DataUser;
import com.example.doantn.Models.Product;
import com.example.doantn.R;
import com.example.doantn.Utils.Common;

import java.util.List;

public class ProductFlashSaleAdapter extends ArrayAdapter<Product> {
    Context context;
    List<Product> productList;

    public ProductFlashSaleAdapter(@NonNull Context context, List<Product> productList) {
        super(context, 0, productList);
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_flash_sale, parent, false);
        }
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_cost_real = convertView.findViewById(R.id.tv_cost_real);
        TextView tv_cost_sale = convertView.findViewById(R.id.tv_cost_sale);
        ImageView img = convertView.findViewById(R.id.image_product);
        TextView tv_sale = convertView.findViewById(R.id.tv_sale);
        Product product = productList.get(position);
        tv_name.setText(product.getName());
        tv_cost_real.setText(Common.formatMoney(product.getCost()));
        tv_cost_real.setPaintFlags(tv_cost_real.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tv_cost_sale.setText(Common.formatMoney((int) (product.getCost() * (1 - product.getSale()))));
        tv_sale.setText("-" + product.getSale() * 100 + "%");
        Glide.with(context).load(product.getImage()).into(img);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.product = product;
                Common.writeUser(new DataUser(Common.product.getID(),0,0,"null"));
                Intent intent=new Intent(getContext(), ProductActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
