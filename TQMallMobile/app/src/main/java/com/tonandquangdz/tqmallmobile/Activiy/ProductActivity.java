package com.tonandquangdz.tqmallmobile.Activiy;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.tabs.TabLayout;
import com.tonandquangdz.tqmallmobile.API.CartService;
import com.tonandquangdz.tqmallmobile.API.ProductDetailsService;
import com.tonandquangdz.tqmallmobile.API.ReviewProductService;
import com.tonandquangdz.tqmallmobile.Adapter.TabLayoutAdapter;
import com.tonandquangdz.tqmallmobile.Fragment.ProductFragment.CommentFragment;
import com.tonandquangdz.tqmallmobile.Fragment.ProductFragment.DescriptionFragment;
import com.tonandquangdz.tqmallmobile.Fragment.ProductFragment.DetailsFragment;
import com.tonandquangdz.tqmallmobile.Models.Cart;
import com.tonandquangdz.tqmallmobile.Models.ProductDetail;
import com.tonandquangdz.tqmallmobile.Models.ReviewProduct;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TextView tv_name, tv_cost;
    ImageSlider imageSlider;
    List<ProductDetail> productDetailList = new ArrayList<>();
    List<SlideModel> slideModels = new ArrayList<>();
    ImageView star1, star2, star3, star4, star5;
    TextView tv_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initView();
        setupViewPager();
        loadProductDetails();
        loadData();
        setRate();
        onClick();
    }

    private void initView() {
        tabLayout = findViewById(R.id.tab_product);
        viewPager = findViewById(R.id.view_product);
        tv_name = findViewById(R.id.tv_name);
        tv_cost = findViewById(R.id.tv_cost);
        imageSlider = findViewById(R.id.img_slider);
        star1 = findViewById(R.id.img_star_1);
        star2 = findViewById(R.id.img_star_2);
        star3 = findViewById(R.id.img_star_3);
        star4 = findViewById(R.id.img_star_4);
        star5 = findViewById(R.id.img_star_5);
        tv_quantity = findViewById(R.id.tv_quantity);
    }

    private void loadData() {
        tv_name.setText(Common.product.getName());
        tv_cost.setText(Common.formatMoney(Common.product.getCost()));
        tv_quantity.setText("Số lượng còn: " + Common.product.getQuantity());
    }

    private void setupViewPager() {
        TabLayoutAdapter adapter = new TabLayoutAdapter(getSupportFragmentManager());
        adapter.addFragment(new DescriptionFragment(), "Mô tả");
        adapter.addFragment(new DetailsFragment(), "Chi tiết");
        adapter.addFragment(new CommentFragment(), "Đánh giá");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void loadProductDetails() {
        slideModels.add(new SlideModel(Common.product.getImage(), ScaleTypes.CENTER_INSIDE));
        ProductDetailsService.api.getProductDetails(Common.product.getID()).enqueue(new Callback<List<ProductDetail>>() {
            @Override
            public void onResponse(Call<List<ProductDetail>> call, Response<List<ProductDetail>> response) {
                if (response.body() != null) {
                    for (ProductDetail productDetail : response.body()
                    ) {
                        slideModels.add(new SlideModel(productDetail.getImage(), ScaleTypes.CENTER_INSIDE));
                        productDetailList.add(productDetail);
                    }
                    if (slideModels.size() > 0) {
                        imageSlider.setImageList(slideModels);
                    }


                }
            }

            @Override
            public void onFailure(Call<List<ProductDetail>> call, Throwable t) {

            }
        });
    }

    int rate = 0;

    private void setRate() {
        ReviewProductService.api.getReviewProduct(Common.product.getID()).enqueue(new Callback<List<ReviewProduct>>() {
            @Override
            public void onResponse(Call<List<ReviewProduct>> call, Response<List<ReviewProduct>> response) {
                if (response.body() != null) {
                    for (ReviewProduct reviewProduct : response.body()
                    ) {
                        rate += reviewProduct.getRate();
                    }
                    int star = (int) ((double) rate / (double) response.body().size());
                    switch (star) {
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
                        default:
                            star1.setVisibility(View.VISIBLE);
                            star2.setVisibility(View.VISIBLE);
                            star3.setVisibility(View.VISIBLE);
                            star4.setVisibility(View.VISIBLE);
                            star5.setVisibility(View.VISIBLE);
                            star1.setImageResource(R.drawable.ic_star_border);
                            star2.setImageResource(R.drawable.ic_star_border);
                            star3.setImageResource(R.drawable.ic_star_border);
                            star4.setImageResource(R.drawable.ic_star_border);
                            star5.setImageResource(R.drawable.ic_star_border);
                            break;

                    }
                }
            }

            @Override
            public void onFailure(Call<List<ReviewProduct>> call, Throwable t) {

            }
        });
    }

    private void onClick() {
        findViewById(R.id.btn_add_to_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDialog();
            }
        });
        findViewById(R.id.btn_buy_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDialog();
            }
        });
    }

    int index = 0;
    int quantity = 1;
    ProductDetail productDetail;

    private void setDialog() {

        Dialog dialog = new Dialog(ProductActivity.this);
        dialog.setContentView(R.layout.dialog_choose_product);
        TextView tv_cost = dialog.findViewById(R.id.tv_cost);
        TextView tv_quantity = dialog.findViewById(R.id.tv_quantity);
        tv_quantity.setText("Kho: " + Common.product.getQuantity());
        ImageView img_product = dialog.findViewById(R.id.image_product);
        Spinner sp_product = dialog.findViewById(R.id.sp_choose);
        Button btn_down = dialog.findViewById(R.id.btn_down);
        Button btn_up = dialog.findViewById(R.id.btn_up);
        Button btn_add = dialog.findViewById(R.id.btn_add);
        TextView tv_quantity_buy = dialog.findViewById(R.id.tv_quantity_buy);
        productDetail = productDetailList.get(index);
        if (productDetail != null) {
            tv_cost.setText(Common.formatMoney(productDetail.getCost()));
            Glide.with(ProductActivity.this).load(productDetail.getImage()).into(img_product);
        }
        ArrayAdapter<ProductDetail> productDetailArrayAdapter = new ArrayAdapter<>(ProductActivity.this, android.R.layout.simple_spinner_dropdown_item, productDetailList);
        sp_product.setAdapter(productDetailArrayAdapter);
        sp_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                productDetail = productDetailList.get(i);
                if (productDetail != null) {
                    tv_cost.setText(Common.formatMoney(productDetail.getCost()));
                    Glide.with(ProductActivity.this).load(productDetail.getImage()).into(img_product);
                }
                index = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity--;
                if (quantity < 1) {
                    tv_quantity_buy.setText("1");
                    quantity = 1;
                } else {
                    tv_quantity_buy.setText(quantity + "");
                }
            }
        });
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                if (quantity > Common.product.getQuantity()) {
                    quantity = Common.product.getQuantity();
                    tv_quantity_buy.setText("" + quantity);
                } else {
                    tv_quantity_buy.setText(quantity + "");
                }
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart = new Cart(Common.username, productDetail.getID(), quantity);
                CartService.api.addCart(cart).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() != null) {
                            if (response.body() > 0) {
                                Toast.makeText(getApplicationContext(), "Thêm giỏ hàng thành công", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Thêm giỏ hàng không thành công", Toast.LENGTH_LONG).show();
                            }
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });
        dialog.show();
    }


}