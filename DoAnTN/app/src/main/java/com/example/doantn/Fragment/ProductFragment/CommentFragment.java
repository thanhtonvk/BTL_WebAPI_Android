package com.example.doantn.Fragment.ProductFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doantn.API.ReviewProductService;
import com.example.doantn.Activiy.ReviewActivity;
import com.example.doantn.Adapter.ReviewAdapter;
import com.example.doantn.Models.ReviewProduct;
import com.example.doantn.R;
import com.example.doantn.Utils.Common;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentFragment extends Fragment {


    TwoWayView lv_comment;
    ReviewAdapter reviewAdapter;
    List<ReviewProduct> reviewProductList = new ArrayList<>();
    Button btn_rate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
        onClick();
    }

    private void initView(View view) {
        lv_comment = view.findViewById(R.id.lv_comment);
        btn_rate = view.findViewById(R.id.btn_rate);
        reviewAdapter = new ReviewAdapter(requireContext(), reviewProductList);
        lv_comment.setAdapter(reviewAdapter);
    }

    private void loadData() {
        ReviewProductService.api.getReviewProduct(Common.product.getID()).enqueue(new Callback<List<ReviewProduct>>() {
            @Override
            public void onResponse(Call<List<ReviewProduct>> call, Response<List<ReviewProduct>> response) {
                if (response.body() != null) {
                    reviewProductList.clear();
                    for (ReviewProduct reviewProduct : response.body()
                    ) {
                        reviewProductList.add(reviewProduct);
                        reviewAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ReviewProduct>> call, Throwable t) {

            }
        });
    }

    private void onClick() {
        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ReviewActivity.class));
            }
        });
    }
}