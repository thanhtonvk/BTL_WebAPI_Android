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
import com.example.doantn.Models.Category;
import com.example.doantn.R;

import java.util.List;

public class CategoryGvAdapter extends ArrayAdapter<Category> {
    List<Category> categoryList;
    Context context;

    public CategoryGvAdapter(@NonNull Context context, List<Category> categoryList) {
        super(context, 0, categoryList);
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_brand_category, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        Category category = categoryList.get(position);
        Glide.with(context).load(category.getImage()).into(imageView);
        tv_name.setText(category.getName());
        return convertView;

    }

    @Override
    public int getCount() {
        return categoryList.size();
    }
}