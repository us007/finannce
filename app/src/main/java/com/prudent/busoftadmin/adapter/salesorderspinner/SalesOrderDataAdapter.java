package com.prudent.busoftadmin.adapter.salesorderspinner;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.DatabaseHelper.DatabaseHelper;
import com.prudent.busoftadmin.data.db.Local.Login.SalesOrderData;
import java.util.ArrayList;

/**
 * Created by AFF41 on 8/1/2017.
 */


public class SalesOrderDataAdapter extends RecyclerView.Adapter<SalesOrderDataAdapter.SalesOrderDataViewHolder> {
    private ArrayList<SalesOrderData> salesOrderDataArrayList = new ArrayList<>();
    private Context context;
    private DatabaseHelper databaseHelper;
    private ProgressDialog progressDialog;

    private ViewGroup viewGroup;
    private long count;

    public SalesOrderDataAdapter(ArrayList<SalesOrderData> salesOrderDataArrayList, Context context) {
        this.salesOrderDataArrayList = salesOrderDataArrayList;
        this.context = context;
        databaseHelper = new DatabaseHelper(context);


    }

    @Override
    public SalesOrderDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_sales_order, parent, false);
        return new SalesOrderDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SalesOrderDataViewHolder holder, final int position) {
        final SalesOrderData salesOrderData = salesOrderDataArrayList.get(position);
        holder.tvName.setText(salesOrderData.getItemName());
        // holder.tvSrno.setText(salesOrderData.getSr());
        final String data = salesOrderData.getSr();
        holder.llSalesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_sales_order_detail, viewGroup);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
               // AppCompatTextView tvSr = (AppCompatTextView) view.findViewById(R.id.tv_sales_order_sr);
                AppCompatTextView tvAmount = (AppCompatTextView) view.findViewById(R.id.tv_sales_order_amount);
                AppCompatTextView tvItem = (AppCompatTextView) view.findViewById(R.id.tv_sales_order_item_name);
                AppCompatTextView tvRate = (AppCompatTextView) view.findViewById(R.id.tv_sales_order_rate);
                AppCompatTextView tvQuantity = (AppCompatTextView) view.findViewById(R.id.tv_sales_order_quantity);

                tvAmount.setText(salesOrderData.getAmount());
                //tvSr.setText(salesOrderData.getSr());
                tvItem.setText(salesOrderData.getItemName());
                tvRate.setText(salesOrderData.getRate());
                tvQuantity.setText(salesOrderData.getQuantity());
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeItem(position, data);
                Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                count = databaseHelper.getSalesOrderCount();
               // databaseHelper.updateDatabase(salesOrderDataArrayList);


            }
        });
    }

    @Override
    public int getItemCount() {
        return salesOrderDataArrayList.size();
    }

    public class SalesOrderDataViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView  tvName;
        LinearLayout llSalesOrder;
        AppCompatImageView ivDelete;

        public SalesOrderDataViewHolder(View itemView) {
            super(itemView);
            tvName = (AppCompatTextView) itemView.findViewById(R.id.tv_sales_order_name);
          //  tvSrno = (AppCompatTextView) itemView.findViewById(R.id.tv_sales_order_sr);
            llSalesOrder = (LinearLayout) itemView.findViewById(R.id.ll_sales_order);
            ivDelete = (AppCompatImageView) itemView.findViewById(R.id.iv_sales_order_delete);
        }
    }

    public void removeItem(int position, String data) {
        try {
            salesOrderDataArrayList.remove(position);
            notifyItemRemoved(position);
            // Add whatever you want to do when removing an Item
            databaseHelper.removeSingleSalesData(data);
        } catch (Exception e) {
        }

    }

}
