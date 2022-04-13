package com.tonandquangdz.tqmallmobile.Adapter;

import android.content.Context;
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
import com.tonandquangdz.tqmallmobile.Models.Product;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

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
        tv_sale.setText("-" + product.getSale()*100 + "%");
        Glide.with(context).load(product.getImage()).into(img);
        return convertView;
    }
}
