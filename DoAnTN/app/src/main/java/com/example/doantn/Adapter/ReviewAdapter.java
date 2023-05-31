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

import com.example.doantn.Models.ReviewProduct;
import com.example.doantn.R;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<ReviewProduct> {
    List<ReviewProduct> reviewProductList;
    Context context;

    public ReviewAdapter(@NonNull Context context, List<ReviewProduct> reviewProductList) {
        super(context, 0, reviewProductList);
        this.reviewProductList = reviewProductList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return reviewProductList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.image_avatar);
        ImageView star1 = convertView.findViewById(R.id.img_star_1);
        ImageView star2 = convertView.findViewById(R.id.img_star_2);
        ImageView star3 = convertView.findViewById(R.id.img_star_3);
        ImageView star4 = convertView.findViewById(R.id.img_star_4);
        ImageView star5 = convertView.findViewById(R.id.img_star_5);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_review = convertView.findViewById(R.id.tv_comment);
        ReviewProduct reviewProduct = reviewProductList.get(position);
        tv_name.setText(reviewProduct.getUsername());
        tv_review.setText(reviewProduct.getReview());
        switch (reviewProduct.getRate()) {
            case 1:
                star1.setVisibility(View.VISIBLE);
                break;
            case 2:
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                break;
            case 3:
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                break;
            case 4:
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                break;
            case 5:
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star5.setVisibility(View.VISIBLE);
                break;
        }
        return convertView;

    }
}
