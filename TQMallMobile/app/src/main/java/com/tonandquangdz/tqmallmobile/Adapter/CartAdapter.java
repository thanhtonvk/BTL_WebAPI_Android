package com.tonandquangdz.tqmallmobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.tonandquangdz.tqmallmobile.API.CartService;
import com.tonandquangdz.tqmallmobile.API.ProductDetailsService;
import com.tonandquangdz.tqmallmobile.API.ProductService;
import com.tonandquangdz.tqmallmobile.Activiy.ProductActivity;
import com.tonandquangdz.tqmallmobile.Models.Cart;
import com.tonandquangdz.tqmallmobile.Models.Product;
import com.tonandquangdz.tqmallmobile.Models.ProductDetail;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends ArrayAdapter<Cart> {
    List<Cart> cartList;
    Context context;

    public CartAdapter(@NonNull Context context, List<Cart> cartList) {
        super(context, 0, cartList);
        this.cartList = cartList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cartList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }
        final int[] quantity = new int[1];
        final ProductDetail[] productDetail = new ProductDetail[1];
        final Product[] product = new Product[1];
        Cart cart = cartList.get(position);
        ImageView img_product = convertView.findViewById(R.id.image_product);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_cost = convertView.findViewById(R.id.tv_cost);
        Button btn_down = convertView.findViewById(R.id.btn_down);
        Button btn_up = convertView.findViewById(R.id.btn_up);
        EditText edt_quantity = convertView.findViewById(R.id.edt_quantity);
        quantity[0] = cart.getQuantity();
        edt_quantity.setText(quantity[0] + "");
        ProductDetailsService.api.getProductDetailsById(cart.getIDProductDetails()).enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                if (response != null) {
                    productDetail[0] = response.body();
                    Log.e("TAG", productDetail[0].getName());
                    tv_name.setText(productDetail[0].getName());
                    Glide.with(context).load(productDetail[0].getImage()).into(img_product);
                    Common.sum = productDetail[0].getCost() * cart.getQuantity();
                    Log.e("TAG", "Sum  = " + Common.sum);
                    ProductService.api.getProductById(productDetail[0].getIDProduct()).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            if (response.body() != null) {
                                product[0] = response.body();
                                tv_cost.setText(Common.formatMoney(productDetail[0].getCost()));
                            }
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {

            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetailsService.api.getProductDetailsById(cart.getIDProductDetails()).enqueue(new Callback<ProductDetail>() {
                    @Override
                    public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                        if (response.body() != null) {
                            ProductService.api.getProductById(response.body().getIDProduct()).enqueue(new Callback<Product>() {
                                @Override
                                public void onResponse(Call<Product> call, Response<Product> response) {
                                    if (response.body() != null) {
                                        Common.product = response.body();
                                        context.startActivity(new Intent(getContext(), ProductActivity.class));
                                    }
                                }

                                @Override
                                public void onFailure(Call<Product> call, Throwable t) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductDetail> call, Throwable t) {

                    }
                });
            }
        });
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity[0]--;
                if (quantity[0] < 1) {
                    CartService.api.deleteCart(cart.getID()).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                           if(response.body()!=null){
                               cartList.remove(cart);
                               notifyDataSetInvalidated();
                           }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {

                        }
                    });
                } else {
                    edt_quantity.setText(quantity[0] + "");
                }
                CartService.api.updateCart(cart.getID(), quantity[0]).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() != null) {
                            Log.e("TAG", response.body() + "");
                            Common.sum = productDetail[0].getCost() * cart.getQuantity();
                            Log.e("TAG", "Sum  = " + Common.sum);
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("TAG", t.getMessage());
                    }
                });
            }
        });
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity[0]++;
                if (product[0].getQuantity() < quantity[0]) {
                    quantity[0] = product[0].getQuantity();
                    edt_quantity.setText(quantity[0] + "");
                } else {
                    edt_quantity.setText(quantity[0] + "");
                }
                CartService.api.updateCart(cart.getID(), quantity[0]).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Log.e("TAG", response.body() + "");
                        Common.sum = productDetail[0].getCost() * cart.getQuantity();
                        Log.e("TAG", "Sum  = " + Common.sum);
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("TAG", t.getMessage());
                    }
                });
            }
        });

        return convertView;
    }
}
