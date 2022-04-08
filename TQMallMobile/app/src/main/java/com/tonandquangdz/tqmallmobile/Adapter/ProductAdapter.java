package com.tonandquangdz.tqmallmobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.tonandquangdz.tqmallmobile.Models.Product;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    Context context;
    List<Product> productList;

    public ProductAdapter(@NonNull Context context, List<Product> productList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.image_product);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_cost = convertView.findViewById(R.id.tv_cost);
        Product product = productList.get(position);
        if (!product.getImage().equals("")) {
            Glide.with(context).load(product.getImage()).into(imageView);
        }
        if (product.getName().length() > 30) {
            tv_name.setText(product.getName().substring(0,30));
        } else {
            tv_name.setText(product.getName());
        }
        tv_cost.setText(Common.formatMoney(product.getCost()));

        return convertView;
    }
}
