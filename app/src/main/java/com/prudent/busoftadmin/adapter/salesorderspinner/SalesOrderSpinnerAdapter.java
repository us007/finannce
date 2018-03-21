package com.prudent.busoftadmin.adapter.salesorderspinner;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.model.CustomSpinner;

import java.util.ArrayList;

/**
 * Created by UTSAV SHAH on 7/29/2017.
 */

public class SalesOrderSpinnerAdapter extends ArrayAdapter<String> {
    private Context contextSpinner;
    private ArrayList<String> data;
    private LayoutInflater inflater;

    public SalesOrderSpinnerAdapter(Context context, ArrayList<String> resource) {
        super(context, R.layout.spinner_row, resource);
        contextSpinner = context;
        data = resource;
        inflater = (LayoutInflater) contextSpinner.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinnerrow_title, parent, false);
        TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);
        tvCategory.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_drop_down, 0);
        tvCategory.setText(data.get(position));
       // tvCategory.setTag(data.get(position).getmXcode());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            tvCategory.setTextAppearance(contextSpinner, android.support.v7.appcompat.R.style.Base_TextAppearance_AppCompat_Small);
        } else {
            tvCategory.setTextAppearance(android.support.v7.appcompat.R.style.Base_TextAppearance_AppCompat_Small);
        }
        tvCategory.setTextColor(Color.BLACK);

        return row;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);

        tvCategory.setText(data.get(position));
       // tvCategory.setTag(data.get(position).getmXcode());
        return row;
    }
}
