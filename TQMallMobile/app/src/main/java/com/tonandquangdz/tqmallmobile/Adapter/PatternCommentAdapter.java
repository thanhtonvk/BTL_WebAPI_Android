package com.tonandquangdz.tqmallmobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tonandquangdz.tqmallmobile.R;

public class PatternCommentAdapter extends ArrayAdapter<String> {
    String[] comments;
    Context context;

    public PatternCommentAdapter(@NonNull Context context, String[] comments) {
        super(context, 0, comments);
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_patern_comment, parent, false);
        }
        TextView tv = convertView.findViewById(R.id.tv_content);
        tv.setText(comments[position]);
        return convertView;
    }
}
