package com.example.doantn.Fragment.MainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantn.API.BrandService;
import com.example.doantn.API.CategoryService;
import com.example.doantn.API.ProductService;
import com.example.doantn.Activiy.BrandListActivity;
import com.example.doantn.Activiy.CategoryListActivity;
import com.example.doantn.Activiy.ClassifierActivity;
import com.example.doantn.Activiy.FlashSaleListActivity;
import com.example.doantn.Activiy.ProductActivity;
import com.example.doantn.Activiy.SearchActivity;
import com.example.doantn.Adapter.BrandAdapter;
import com.example.doantn.Adapter.CategoryAdapter;
import com.example.doantn.Adapter.ExpandableHeightGridView;
import com.example.doantn.Adapter.FlashSaleAdapter;
import com.example.doantn.Adapter.ProductAdapter;
import com.example.doantn.Models.Brand;
import com.example.doantn.Models.Category;
import com.example.doantn.Models.DataUser;
import com.example.doantn.Models.Product;
import com.example.doantn.R;
import com.example.doantn.Utils.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
        onClick();
    }

    TextView tv_search;
    Button btn_open_camera;
    TextView tv_view_all_category;
    TextView tv_view_all_flashsale;
    TextView tv_view_all_brand;


    RecyclerView recyclerView_category;
    RecyclerView recyclerView_flashsale;
    RecyclerView recyclerView_brand;

    CategoryAdapter categoryAdapter;
    FlashSaleAdapter flashSaleAdapter;
    BrandAdapter brandAdapter;
    List<Brand> brandList = new ArrayList<>();

    LinearLayoutManager HorizontalLayout;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;


    LinearLayoutManager horizontalLayoutFlashSale;
    RecyclerView.LayoutManager recyclerViewLayoutManagerFlashSale;

    LinearLayoutManager horizontalLayoutBrand;
    RecyclerView.LayoutManager recyclerViewLayoutManagerBrand;

    List<Category> categoryList = new ArrayList<>();
    List<Product> productFlashSaleList = new ArrayList<>();


    ExpandableHeightGridView gv_product;
    ProductAdapter productAdapter;
    List<Product> productList = new ArrayList<>();

    ScrollView scrollView;
    int page = 1;

    Button btn_more;
    private void initView(View view) {
        btn_more = view.findViewById(R.id.btn_more);
        tv_search = view.findViewById(R.id.tv_search);
        btn_open_camera = view.findViewById(R.id.btn_opencamera);
        tv_view_all_category = view.findViewById(R.id.tv_view_all_category);
        recyclerView_category = view.findViewById(R.id.recycler_category);
        tv_view_all_flashsale = view.findViewById(R.id.tv_view_all_flashsale);
        tv_view_all_brand = view.findViewById(R.id.tv_view_all_brand);


        recyclerView_flashsale = view.findViewById(R.id.recycler_flashsale);
        recyclerView_brand = view.findViewById(R.id.recycler_brand);
        recyclerViewLayoutManagerBrand = new LinearLayoutManager(getContext());
        recyclerView_brand.setLayoutManager(recyclerViewLayoutManagerBrand);
        horizontalLayoutBrand = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_brand.setLayoutManager(horizontalLayoutBrand);

        scrollView = view.findViewById(R.id.scrollView);
        gv_product = view.findViewById(R.id.gv_product);
        RecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView_category.setLayoutManager(
                RecyclerViewLayoutManager);

        HorizontalLayout
                = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView_category.setLayoutManager(HorizontalLayout);

        recyclerViewLayoutManagerFlashSale = new LinearLayoutManager(getContext());
        recyclerView_flashsale.setLayoutManager(recyclerViewLayoutManagerFlashSale);
        horizontalLayoutFlashSale = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView_flashsale.setLayoutManager(horizontalLayoutFlashSale);
        productAdapter = new ProductAdapter(getContext(), productList);
        gv_product.setAdapter(productAdapter);
        gv_product.setExpanded(true);


    }

    private void loadData() {
        CategoryService.api.getCategoryList().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        categoryList = response.body();
                        Collections.shuffle(categoryList);
                        List<Category> categories = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            categories.add(categoryList.get(i));
                        }
                        //  Toast.makeText(getContext(), categoryList.get(0).getImage(), Toast.LENGTH_LONG).show();
                        categoryAdapter = new CategoryAdapter(getContext(), categories);
                        recyclerView_category.setAdapter(categoryAdapter);
                        //Toast.makeText(getContext(), categoryList.size() + "", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
            }
        });


        ProductService.api.getFlashSales(1, 10).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.body() != null) {
                    productFlashSaleList = response.body();
                    //Toast.makeText(getContext(), productFlashSaleList.size() + "", Toast.LENGTH_LONG).show();
                    flashSaleAdapter = new FlashSaleAdapter(getContext(), productFlashSaleList);
                    recyclerView_flashsale.setAdapter(flashSaleAdapter);
                    // Toast.makeText(getContext(), productFlashSaleList.size() + "", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        ProductService.api.getProductList("", page, 10).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null) {
                    for (Product product : response.body()
                    ) {
                        productList.add(product);
                        productAdapter.notifyDataSetChanged();
                    }

                } else {
                    Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        BrandService.api.getBrandList().enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                if (response.body() != null) {
                    List<Brand> brands = response.body();
                    Collections.shuffle(brands);
                    for (int i = 0; i < 8; i++) {
                        brandList.add(brands.get(i));
                    }
                    brandAdapter = new BrandAdapter(getContext(), brandList);
                    recyclerView_brand.setAdapter(brandAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {

            }
        });

    }

    private void onClick() {
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProduct();
            }
        });
        tv_view_all_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BrandListActivity.class));
            }
        });
        tv_view_all_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CategoryListActivity.class));
            }
        });
        tv_view_all_flashsale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FlashSaleListActivity.class));
            }
        });
        gv_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.product = productList.get(i);
                Common.writeUser(new DataUser(Common.product.getID(),0,0,"null"));
                startActivity(new Intent(getContext(), ProductActivity.class));
            }
        });
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });
        btn_open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ClassifierActivity.class));
            }
        });

    }

    private void loadProduct() {
        page++;
        ProductService.api.getProductList("", page, 10).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null) {
                    for (int i = ((page - 1) * 10); i < response.body().size(); i++) {
                        productList.add(response.body().get(i));
                        productAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}