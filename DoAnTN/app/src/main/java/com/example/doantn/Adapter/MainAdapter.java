package com.example.doantn.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.doantn.Fragment.MainFragment.CartFragment;
import com.example.doantn.Fragment.MainFragment.HistoryFragment;
import com.example.doantn.Fragment.MainFragment.HomeFragment;
import com.example.doantn.Fragment.MainFragment.PersonFragment;


public class MainAdapter extends FragmentStatePagerAdapter {
    public MainAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new HistoryFragment();
            case 2:
                return new CartFragment();
            case 3:
                return new PersonFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
