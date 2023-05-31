package com.example.doantn.Activiy;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.doantn.API.ReviewProductService;
import com.example.doantn.Adapter.PatternCommentAdapter;
import com.example.doantn.Models.ReviewProduct;
import com.example.doantn.R;
import com.example.doantn.Utils.Common;

import org.lucasr.twowayview.TwoWayView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    Button btn_back;
    TextView btn_rate;
    ImageView img_product;
    TextView tv_name;
    ImageView btn_star1, btn_star2, btn_star3, btn_star4, btn_star5;
    EditText edt_content;
    TwoWayView lv_comment;
    PatternCommentAdapter adapter;
    int rate = 0;
    String content;
    String[] comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        initView();
        loadData();
        onClick();
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        btn_rate = findViewById(R.id.btn_rate);
        img_product = findViewById(R.id.image_product);
        tv_name = findViewById(R.id.tv_name);
        btn_star1 = findViewById(R.id.img_star_1);
        btn_star2 = findViewById(R.id.img_star_2);
        btn_star3 = findViewById(R.id.img_star_3);
        btn_star4 = findViewById(R.id.img_star_4);
        btn_star5 = findViewById(R.id.img_star_5);
        edt_content = findViewById(R.id.edt_content);
        lv_comment = findViewById(R.id.lv_comment);
    }

    private void loadData() {
        Glide.with(getApplicationContext()).load(Common.product.getImage()).into(img_product);
        tv_name.setText(Common.product.getName());
        comments = new String[]{"Đóng gói sản phẩm rất đẹp và chắc chắn", "Shop phục vụ rất tốt", "Rất đáng tiền", "Thời gian giao hành rất nhanh"};
        adapter = new PatternCommentAdapter(getApplicationContext(), comments);
        lv_comment.setAdapter(adapter);
    }

    private void onClick() {
        btn_star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_star1.setImageResource(R.drawable.ic_star_rate);
                btn_star2.setImageResource(R.drawable.ic_star_border);
                btn_star3.setImageResource(R.drawable.ic_star_border);
                btn_star4.setImageResource(R.drawable.ic_star_border);
                btn_star5.setImageResource(R.drawable.ic_star_border);
                rate = 1;
            }
        });
        btn_star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_star1.setImageResource(R.drawable.ic_star_rate);
                btn_star2.setImageResource(R.drawable.ic_star_rate);
                btn_star3.setImageResource(R.drawable.ic_star_border);
                btn_star4.setImageResource(R.drawable.ic_star_border);
                btn_star5.setImageResource(R.drawable.ic_star_border);
                rate = 2;
            }
        });
        btn_star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_star1.setImageResource(R.drawable.ic_star_rate);
                btn_star2.setImageResource(R.drawable.ic_star_rate);
                btn_star3.setImageResource(R.drawable.ic_star_rate);
                btn_star4.setImageResource(R.drawable.ic_star_border);
                btn_star5.setImageResource(R.drawable.ic_star_border);
                rate = 3;
            }
        });

        btn_star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_star1.setImageResource(R.drawable.ic_star_rate);
                btn_star2.setImageResource(R.drawable.ic_star_rate);
                btn_star3.setImageResource(R.drawable.ic_star_rate);
                btn_star4.setImageResource(R.drawable.ic_star_rate);
                btn_star5.setImageResource(R.drawable.ic_star_border);
                rate = 4;
            }
        });
        btn_star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_star1.setImageResource(R.drawable.ic_star_rate);
                btn_star2.setImageResource(R.drawable.ic_star_rate);
                btn_star3.setImageResource(R.drawable.ic_star_rate);
                btn_star4.setImageResource(R.drawable.ic_star_rate);
                btn_star5.setImageResource(R.drawable.ic_star_rate);
                rate = 5;
            }
        });
        lv_comment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                content = edt_content.getText().toString();
                content += comments[i] + " ";
                edt_content.setText(content);
            }
        });
        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = edt_content.getText().toString();
                ReviewProduct reviewProduct = new ReviewProduct(Common.username, Common.product.getID(), rate, content);
                ReviewProductService.api.addReview(reviewProduct).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() != null) {
                            if (response.body() > 0) {
                                Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Đánh giá không thành công", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });

    }
}