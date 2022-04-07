package com.tonandquangdz.tqmallmobile.Fragment.LoginFragment;

import android.app.ProgressDialog;
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
import com.tonandquangdz.tqmallmobile.Models.Account;
import com.tonandquangdz.tqmallmobile.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        onClick();
    }

    EditText edt_username, edt_password, edt_name, edt_phone;
    Button btn_register;

    private void initView(View view) {
        edt_username = view.findViewById(R.id.edt_username);
        edt_password = view.findViewById(R.id.edt_password);
        edt_name = view.findViewById(R.id.edt_name);
        edt_phone = view.findViewById(R.id.edt_phone);
        btn_register = view.findViewById(R.id.btn_register);
    }
    private void onClick(){
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    private void register() {
        String username = edt_username.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        String name = edt_name.getText().toString().trim();
        String phone = edt_phone.getText().toString().trim();
        if (!username.equals("") || !password.equals("") || !name.equals("") || !phone.equals("")) {
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(password);
            account.setFullName(name);
            account.setPhoneNumber(phone);
            account.setStatus(Short.parseShort(1 + ""));
            ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Đang đăng ký...");
            progressDialog.show();
            AccountService.api.register(account).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        if (response.body() != null) {
                            int rs = response.body();
                            if (rs > 0) {
                                Toast.makeText(getContext(), "Thành công", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getContext(), "Đăng ký không thành công, kiểm tra lại tài khoản", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
        } else {
            Toast.makeText(getContext(), "Các trường không được để trống", Toast.LENGTH_LONG).show();
        }
    }

}