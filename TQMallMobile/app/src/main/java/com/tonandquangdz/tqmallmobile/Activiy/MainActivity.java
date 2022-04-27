package com.tonandquangdz.tqmallmobile.Activiy;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tonandquangdz.tqmallmobile.Adapter.MainAdapter;
import com.tonandquangdz.tqmallmobile.R;

public class MainActivity extends AppCompatActivity {


    ViewPager view_main;
    BottomNavigationView view_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setViewLogin();
        setSwichNav();

    }

    private void init() {
        view_main = findViewById(R.id.view_main);
        view_nav = findViewById(R.id.nav_main);
    }

    private void setViewLogin() {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        view_main.setAdapter(adapter);
        view_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        view_nav.getMenu().findItem(R.id.item_home).setChecked(true);
                        break;
                    case 1:
                        view_nav.getMenu().findItem(R.id.item_history).setChecked(true);
                        break;
                    case 2:
                        view_nav.getMenu().findItem(R.id.item_cart).setChecked(true);
                        break;
                    case 3:
                        view_nav.getMenu().findItem(R.id.item_person).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setSwichNav() {
        view_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_home:
                        view_main.setCurrentItem(0);
                        break;
                    case R.id.item_history:
                        view_main.setCurrentItem(1);
                        break;
                    case R.id.item_cart:
                        view_main.setCurrentItem(2);
                        break;
                    case R.id.item_person:
                        view_main.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }
}