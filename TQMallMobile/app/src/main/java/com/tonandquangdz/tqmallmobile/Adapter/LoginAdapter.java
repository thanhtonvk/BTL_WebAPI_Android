package com.tonandquangdz.tqmallmobile.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LoginAdapter extends FragmentStatePagerAdapter {
    public LoginAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
