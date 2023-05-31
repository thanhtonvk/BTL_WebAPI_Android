package com.example.doantn.Adapter;

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
import com.example.doantn.Models.Brand;
import com.example.doantn.R;

import java.util.List;

public class BrandGvAdapter extends ArrayAdapter<Brand> {
    List<Brand> brandList;
    Context context;

    public BrandGvAdapter(@NonNull Context context, List<Brand> brandList) {
        super(context, 0, brandList);
        this.context = context;
        this.brandList = brandList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_brand_category, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        Brand brand= brandList.get(position);
        Glide.with(context).load(brand.getImage()).into(imageView);
        tv_name.setText(brand.getName());
        return convertView;

    }

    @Override
    public int getCount() {
        return brandList.size();
    }

}
