package com.example.doantn.Activiy;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.example.doantn.API.CartService;
import com.example.doantn.API.OrderDetailsService;
import com.example.doantn.API.OrderService;
import com.example.doantn.API.VoucherService;
import com.example.doantn.Adapter.CartAdapter;
import com.example.doantn.Adapter.ExpandableHeightGridView;
import com.example.doantn.Adapter.VoucherAdapter;
import com.example.doantn.Models.Cart;
import com.example.doantn.Models.Order;
import com.example.doantn.Models.Voucher;
import com.example.doantn.R;
import com.example.doantn.Utils.Common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayActivity extends AppCompatActivity {
    private FusedLocationProviderClient locationProviderClient;
    private Geocoder geocoder;
    private List<Address> addresses;
    double latitude, longitude;
    EditText edt_address;
    EditText edt_phonenumber;
    EditText edt_note;
    Button btn_position;
    TextView tv_sum_product, tv_sum_product_foot, tv_transpot, tv_voucher, tv_sum_pay, tv_sum_final;
    Button btn_buy;
    ExpandableHeightGridView lv_product, lv_voucher;
    List<Voucher> voucherList = new ArrayList<>();
    VoucherAdapter voucherAdapter;
    CartAdapter cartAdapter;
    List<Cart> cartList = new ArrayList<>();
    Voucher voucher;
    int sum = 0;
    int sale = 0;
    TextView tv_count;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        requestPermission();
        initView();
        onClick();
        loadData();
    }

    private void loadData() {
        voucherAdapter = new VoucherAdapter(this, voucherList);
        lv_voucher.setAdapter(voucherAdapter);
        cartAdapter = new CartAdapter(this, cartList);
        lv_product.setAdapter(cartAdapter);
        CartService.api.getCartList(Common.username).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.body() != null) {
                    for (Cart cart : response.body()
                    ) {
                        cartList.add(cart);
                        cartAdapter.notifyDataSetChanged();
                        tv_count.setText("Tổng số tiền(" + cartList.size() + " sản phẩm)");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
        VoucherService.api.getVoucher().enqueue(new Callback<List<Voucher>>() {
            @Override
            public void onResponse(Call<List<Voucher>> call, Response<List<Voucher>> response) {
                if (response.body() != null) {
                    for (Voucher voucher : response.body()
                    ) {
                        voucherList.add(voucher);
                        voucherAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Voucher>> call, Throwable t) {

            }
        });
        lv_product.setExpanded(true);
        lv_voucher.setExpanded(true);
        CartService.api.getSummary(Common.username).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() != null) {
                    sum = response.body();
                    tv_sum_product.setText(Common.formatMoney(sum));
                    tv_sum_product_foot.setText(Common.formatMoney(sum));
                    tv_transpot.setText(Common.formatMoney(50000));
                    tv_voucher.setText("đ0");
                    tv_sum_pay.setText(Common.formatMoney(sum + 50000));
                    tv_sum_final.setText(Common.formatMoney(sum + 50000));

                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                CartService.api.getSummary(Common.username).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() != null) {
                            sum = response.body();
                            tv_sum_product.setText(Common.formatMoney(sum));
                            tv_sum_product_foot.setText(Common.formatMoney(sum));
                            tv_transpot.setText(Common.formatMoney(50000));
                            if (voucher != null) {
                                sale = (int) (sum * voucher.getSale());
                                tv_voucher.setText("-" + Common.formatMoney(sale));
                            }
                            tv_sum_pay.setText(Common.formatMoney(sum + 50000 - sale));
                            tv_sum_final.setText(Common.formatMoney(sum + 50000 - sale));
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);
        lv_voucher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                voucher = voucherList.get(i);
            }
        });

    }

    private void onClick() {
        btn_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentAddress();
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_address.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ", Toast.LENGTH_LONG).show();
                } else if (edt_phonenumber.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_LONG).show();
                } else {
                    ProgressDialog dialog = new ProgressDialog(PayActivity.this);
                    dialog.setTitle("Vui lòng chờ!!! Quang đang xử lý...");
                    dialog.show();
                    String dateNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                    Order order = new Order(Common.username, dateNow, edt_phonenumber.getText().toString(), edt_address.getText().toString(), Short.parseShort("0"));
                    OrderService.api.addOrder(order).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            if (response.body() != null) {
                                OrderDetailsService.api.addOrderDetails(Common.username).enqueue(new Callback<Integer>() {
                                    @Override
                                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                                        if(response.body()!=null){
                                            dialog.dismiss();
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Integer> call, Throwable t) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        edt_address = findViewById(R.id.edt_address);
        btn_position = findViewById(R.id.btn_position);
        edt_phonenumber = findViewById(R.id.edt_phone);
        edt_note = findViewById(R.id.edt_note);
        tv_sum_product = findViewById(R.id.tv_sum_product);
        tv_sum_product_foot = findViewById(R.id.tv_sum_product_foot);
        tv_transpot = findViewById(R.id.tv_transpot);
        tv_voucher = findViewById(R.id.tv_voucher);
        tv_sum_pay = findViewById(R.id.tv_sum_pay);
        tv_sum_final = findViewById(R.id.tv_sum_final);
        btn_buy = findViewById(R.id.btn_buy);
        lv_product = findViewById(R.id.lv_product);
        lv_voucher = findViewById(R.id.lv_voucher);
        tv_count = findViewById(R.id.tv_count);

    }

    private void getCurrentAddress() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        geocoder = new Geocoder(this, Locale.getDefault());

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    try {
                        addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        String addressLine1 = addresses.get(0).getAddressLine(0);
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String pinCode = addresses.get(0).getPostalCode();
                        String fullAddress = addressLine1 + ",  " + city + ",  " + state + ",  " + pinCode;
                        edt_address.setText(fullAddress);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }
}