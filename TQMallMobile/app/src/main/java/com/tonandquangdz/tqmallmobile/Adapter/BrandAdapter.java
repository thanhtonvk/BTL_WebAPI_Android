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
import com.tonandquangdz.tqmallmobile.Models.Brand;
import com.tonandquangdz.tqmallmobile.R;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
    Context context;
    List<Brand> brandList;

    public BrandAdapter(Context context, List<Brand> brandList) {
        this.context = context;
        this.brandList = brandList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brand_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Brand brand = brandList.get(position);
        holder.tv_name.setText(brand.getName());
        if (!brand.getImage().equals("")) {
            if (context != null) {
                Glide.with(context).load(brand.getImage()).into(holder.imageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
