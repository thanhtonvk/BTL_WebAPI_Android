package com.example.doantn.Fragment.MainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doantn.Activiy.StatusOrderActivity;
import com.example.doantn.R;
import com.example.doantn.Utils.Common;

public class PersonFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        onClick();
    }

    LinearLayout btn_user, btn_prepare, btn_transit, btn_review;
    TextView btn_logout;

    private void onClick() {
        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_prepare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.status = 0;
                startActivity(new Intent(getContext(), StatusOrderActivity.class));
            }
        });
        btn_transit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.status = 1;
                startActivity(new Intent(getContext(), StatusOrderActivity.class));
            }
        });
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.status = 2;
                startActivity(new Intent(getContext(), StatusOrderActivity.class));
            }
        });
    }

    private void initView(View view) {
        btn_user = view.findViewById(R.id.btn_user);
        btn_prepare = view.findViewById(R.id.btn_prepare);
        btn_transit = view.findViewById(R.id.btn_transit);
        btn_review = view.findViewById(R.id.btn_review);
        btn_logout = view.findViewById(R.id.btn_logout);
    }
}