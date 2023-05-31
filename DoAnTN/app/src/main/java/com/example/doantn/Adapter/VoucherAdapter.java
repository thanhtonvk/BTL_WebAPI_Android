package com.example.doantn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doantn.Models.Voucher;
import com.example.doantn.R;

import java.util.List;

public class VoucherAdapter extends ArrayAdapter<Voucher> {
    List<Voucher> voucherList;
    Context context;

    public VoucherAdapter(@NonNull Context context, List<Voucher> voucherList) {
        super(context, 0, voucherList);
        this.voucherList = voucherList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return voucherList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_voucher, parent, false);
        }
        TextView tv_voucher = convertView.findViewById(R.id.tv_voucher);
        TextView tv_quantity = convertView.findViewById(R.id.tv_quantity);
        TextView tv_username = convertView.findViewById(R.id.tv_username);
        Voucher voucher = voucherList.get(position);
        tv_voucher.setText("Voucher giảm " + voucher.getSale() * 100 + "%");
        tv_quantity.setText("Số lượng: " + voucher.getQuantity());
        tv_username.setText("Từ: " + voucher.getUsername());
        return convertView;

    }
}
