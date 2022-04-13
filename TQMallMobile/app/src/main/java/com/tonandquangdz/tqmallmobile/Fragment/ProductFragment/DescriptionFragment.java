package com.tonandquangdz.tqmallmobile.Fragment.ProductFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tonandquangdz.tqmallmobile.API.ProductService;
import com.tonandquangdz.tqmallmobile.Models.Product;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    WebView webView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = view.findViewById(R.id.web_view);
        loadProduct();
    }

    private void loadProduct() {
        ProductService.api.getProductById(Common.product.getID()).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.body() != null) {
                    webView.loadData(response.body().getDescription(), "text/html", "UTF-8");
                    webView.setInitialScale(100);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
}