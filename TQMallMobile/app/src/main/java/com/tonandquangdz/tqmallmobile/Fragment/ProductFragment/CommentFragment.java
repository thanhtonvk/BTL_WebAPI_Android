package com.tonandquangdz.tqmallmobile.Fragment.ProductFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tonandquangdz.tqmallmobile.API.ReviewProductService;
import com.tonandquangdz.tqmallmobile.Adapter.ReviewAdapter;
import com.tonandquangdz.tqmallmobile.Models.ReviewProduct;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentFragment extends Fragment {


    ListView lv_comment;
    ReviewAdapter reviewAdapter;
    List<ReviewProduct> reviewProductList = new ArrayList<>();

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
    }

    private void initView(View view) {
        lv_comment = view.findViewById(R.id.lv_comment);
        reviewAdapter = new ReviewAdapter(getContext(), reviewProductList);
        lv_comment.setAdapter(reviewAdapter);
    }

    private void loadData() {
        ReviewProductService.api.getReviewProduct(Common.product.getID()).enqueue(new Callback<List<ReviewProduct>>() {
            @Override
            public void onResponse(Call<List<ReviewProduct>> call, Response<List<ReviewProduct>> response) {
                if (response.body() != null) {
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
}