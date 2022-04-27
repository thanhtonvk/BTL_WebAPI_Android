package com.tonandquangdz.tqmallmobile.Fragment.LoginFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tonandquangdz.tqmallmobile.API.AccountService;
import com.tonandquangdz.tqmallmobile.Activiy.MainActivity;
import com.tonandquangdz.tqmallmobile.Models.Account;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        onClick();
    }

    EditText edt_username, edt_password;
    Button btn_login;

    private void onClick() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void initView(View view) {
        edt_username = view.findViewById(R.id.edt_username);
        edt_password = view.findViewById(R.id.edt_password);
        btn_login = view.findViewById(R.id.btn_login);
    }

    private void login() {
        String username = edt_username.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        //Params: Context, View Type, Text Message
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Đang đăng nhập");
        if (!username.equals("") || !password.equals("")) {
            progressDialog.show();
            AccountService.api.login(username, password).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            int rs = response.body();
                            if (rs == 1) {
                                startActivity(new Intent(getContext(), MainActivity.class));
                                Common.username  = username;
                                AccountService.api.getAccount(Common.username).enqueue(new Callback<Account>() {
                                    @Override
                                    public void onResponse(Call<Account> call, Response<Account> response) {
                                        if (response.body() != null) {
                                            Common.account = response.body();

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Account> call, Throwable t) {

                                    }
                                });
                                getActivity().finish();
                            } else {
                                //không hợp lệ
                                Toast.makeText(getContext(), "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
        }
    }

}