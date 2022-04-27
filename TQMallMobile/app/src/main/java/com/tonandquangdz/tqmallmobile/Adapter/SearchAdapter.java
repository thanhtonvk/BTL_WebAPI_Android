package com.tonandquangdz.tqmallmobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tonandquangdz.tqmallmobile.Models.DataUser;
import com.tonandquangdz.tqmallmobile.R;
import com.tonandquangdz.tqmallmobile.Utils.Common;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends ArrayAdapter implements Filterable {
    List<String> searchStrings = new ArrayList<>();

    public SearchFragment(@NonNull Context context) {
        super(context, 0);
        List<DataUser> dataUsers = Common.toListObject(Common.account.getDataUser());
        for (DataUser dataUser : dataUsers
        ) {
            if (!dataUser.getSearch().equals("")) {
                searchStrings.add(dataUser.getSearch());
            }
        }

    }

    @Override
    public int getCount() {
        return searchStrings.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_search, parent, false);
        }
        TextView tv_search = convertView.findViewById(R.id.tv_search);
        tv_search.setText(searchStrings.get(position));
        return convertView;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return searchStrings.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return super.getFilter();
    }
    class ArrayFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        }
    }
}
