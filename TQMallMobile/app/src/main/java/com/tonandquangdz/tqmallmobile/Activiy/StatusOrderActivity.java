package com.tonandquangdz.tqmallmobile.Activiy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tonandquangdz.tqmallmobile.Adapter.TabLayoutAdapter;
import com.tonandquangdz.tqmallmobile.Fragment.OrderStatusFragment.CancelFragment;
import com.tonandquangdz.tqmallmobile.Fragment.OrderStatusFragment.PrepareFragment;
import com.tonandquangdz.tqmallmobile.Fragment.OrderStatusFragment.SuccessFragment;
import com.tonandquangdz.tqmallmobile.Fragment.OrderStatusFragment.TranspotFragment;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

public class StatusOrderActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_order);
        initView();
        setupViewPager();
        if (Common.status == 0) {
            viewPager.setCurrentItem(0);
        } else if (Common.status == 1) {
            viewPager.setCurrentItem(1);
        } else {
            viewPager.setCurrentItem(2);
        }
    }

    private void initView() {
        tabLayout = findViewById(R.id.tab_order);
        viewPager = findViewById(R.id.view_order);
    }

    private void setupViewPager() {
        TabLayoutAdapter adapter = new TabLayoutAdapter(getSupportFragmentManager());
        adapter.addFragment(new PrepareFragment(), "Đang chuẩn bị");
        adapter.addFragment(new TranspotFragment(), "Đang vận chuyển");
        adapter.addFragment(new SuccessFragment(), "Vận chuyển thành công");
        adapter.addFragment(new CancelFragment(), "Đã hủy");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}