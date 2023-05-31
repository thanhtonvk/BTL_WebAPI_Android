package com.example.doantn.Activiy;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.doantn.Adapter.LoginAdapter;
import com.example.doantn.R;

public class LoginActivity extends AppCompatActivity {

    ViewPager view_login;
    BottomNavigationView view_nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setViewLogin();
        setSwichNav();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary));
        }
    }
    private void init(){
        view_login = findViewById(R.id.view_login);
        view_nav = findViewById(R.id.nav_login);
    }
    private void setViewLogin(){
        LoginAdapter adapter= new LoginAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        view_login.setAdapter(adapter);
        view_login.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        view_nav.getMenu().findItem(R.id.item_login).setChecked(true);
                        break;
                    case 1:
                        view_nav.getMenu().findItem(R.id.item_register).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setSwichNav(){
       view_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()){
                   case R.id.item_login:
                       view_login.setCurrentItem(0);
                       break;
                   case R.id.item_register:
                       view_login.setCurrentItem(1);
                       break;
               }
               return true;
           }
       });
        view_login.setOffscreenPageLimit(2);
    }
}