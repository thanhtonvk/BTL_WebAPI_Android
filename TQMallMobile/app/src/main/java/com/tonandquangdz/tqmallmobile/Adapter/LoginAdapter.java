package com.tonandquangdz.tqmallmobile.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.tonandquangdz.tqmallmobile.Fragment.LoginFragment.LoginFragment;
import com.tonandquangdz.tqmallmobile.Fragment.LoginFragment.RegisterFragment;

public class LoginAdapter extends FragmentStatePagerAdapter {
    public LoginAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoginFragment();
            case 1:
                return new RegisterFragment();
            default:
                return new LoginFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
