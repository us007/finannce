package com.prudent.busoftadmin.ui.salesorder;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.adapter.salesorderspinner.SalesOrderDataAdapter;
import com.prudent.busoftadmin.adapter.salesorderspinner.SalesOrderSpinnerAdapter;

import com.prudent.busoftadmin.data.DatabaseHelper.DatabaseHelper;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderBroker.response.SalesOrderBrokerResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderCostCenter.response.SalesOrderResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderItemName.response.SalesOrderItemNameResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderNetRate.response.SalesOrderNetRateResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderPartyName.response.SalesOrderPartyNameResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderSaveData.response.SalesOrderSaveDataResponse;
import com.prudent.busoftadmin.data.db.Local.Login.SalesOrderData;
import com.prudent.busoftadmin.data.db.Local.Login.SalesOrderJsonSerializer;
import com.prudent.busoftadmin.data.pref.SessionManager;
import com.prudent.busoftadmin.model.CustomSpinner;
import com.prudent.busoftadmin.utils.searchablespinner.SearchableSpinner;
import org.joda.time.DateTime;
import java.util.ArrayList;

public class SalesOrderActivity extends AppCompatActivity implements SalesOrderView {
    private Toolbar toolbar;
    private AppCompatEditText etRateCharge, etTaxRate, etNetRate, etNumber, etQuantity, etAmount;
    private AppCompatButton btnAdd, btnReset, btnSave, btnShow;
    private SearchableSpinner spnArea, spnPartyName, spnBroker, spnItemName;
    private SalesOrderPresenter salesOrderPresenter;
    private ProgressDialog progressDialog;
    private DateTime dateTime;
    private String dateValue;
    private ArrayList<CustomSpinner> costCenterSpinner = new ArrayList<>();
    private ArrayList<CustomSpinner> partyNameSpinner = new ArrayList<>();
    private ArrayList<CustomSpinner> brokerSpinner = new ArrayList<>();
    private ArrayList<CustomSpinner> itemNameSpinner = new ArrayList<>();
    private SessionManager sessionManager;
    private static final String TAG = "SalesOrderActivity";
    private String areaXcode, partyXcode, brokerXcode, itemXcode, itemName;
    private BottomSheetDialog bottomSheetDialog;
    private LinearLayout llSalesOrder;
    private RecyclerView rvSalesOrderData;
    private DatabaseHelper databaseHelper;
    private long count;
    private ArrayList<SalesOrderData> salesOrderDataArrayList = new ArrayList<>();
    private ArrayList<SalesOrderData> salesOrderDatas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);

        setupWindowAnimations();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findAllId();
        databaseHelper = new DatabaseHelper(this);
        count = databaseHelper.getSalesOrderCount();

        Log.d(TAG, "count  " + count);

        dateTime = DateTime.now();
        dateValue = dateTime.getDayOfMonth() + "-" + dateTime.getMonthOfYear() + "-" + dateTime.getYear();
        sessionManager = new SessionManager(this);
        reloadDatabase();
        setBottomSheet();

        initPresenter();
        onAttach();
        SetProgress();

        LoadBase();

        etAmount.setKeyListener(null);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseValidation();

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();

            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    Toast.makeText(SalesOrderActivity.this, "Add Data", Toast.LENGTH_SHORT).show();
                } else {
                    reloadDatabase();
                    SalesOrderDataAdapter salesOrderDataAdapter = new SalesOrderDataAdapter(salesOrderDataArrayList, SalesOrderActivity.this);
                    rvSalesOrderData.setAdapter(salesOrderDataAdapter);

                    bottomSheetDialog.show();
                }

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadDatabase();
                validation();

            }
        });
        etTaxRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etNetRate.setText(multipication());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etAmount.setText(amountCalculation());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
/*spnArea.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        isSpinnerTouched = true;
        return false;
    }
});*/
    }

    //    private void setupWindowAnimations() {
//        Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.sales_order_fade);
//        getWindow().setExitTransition(slide);
//    }
    private void SetProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
    }

    private void findAllId() {
        etAmount = (AppCompatEditText) findViewById(R.id.et_sales_order_amount);
        etQuantity = (AppCompatEditText) findViewById(R.id.et_sales_order_quantity);
        etNumber = (AppCompatEditText) findViewById(R.id.et_sales_order_number);
        etRateCharge = (AppCompatEditText) findViewById(R.id.et_sales_order_rate_charge);
        etTaxRate = (AppCompatEditText) findViewById(R.id.et_sales_order_tax_rate);
        etNetRate = (AppCompatEditText) findViewById(R.id.et_sales_order_net_rate);
        btnAdd = (AppCompatButton) findViewById(R.id.btn_sales_order_add);
        btnReset = (AppCompatButton) findViewById(R.id.btn_sales_order_reset);
        btnSave = (AppCompatButton) findViewById(R.id.btn_sales_order_save);
        btnShow = (AppCompatButton) findViewById(R.id.btn_sales_order_show);
        spnBroker = (SearchableSpinner) findViewById(R.id.spn_sales_order_broker);
        spnArea = (SearchableSpinner) findViewById(R.id.spn_sales_order_area);
        spnPartyName = (SearchableSpinner) findViewById(R.id.spn_sales_order_party_name);
        spnItemName = (SearchableSpinner) findViewById(R.id.spn_sales_order_item_name);

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    private void initPresenter() {
        salesOrderPresenter = new SalesOrderPresenter();
    }

    @Override
    public void onAttach() {
        salesOrderPresenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        onDetach();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        salesOrderPresenter.onDetach();
    }

    @Override
    public void ShowProgress() {
        progressDialog.show();
    }

    @Override
    public void HideProgress() {
        progressDialog.dismiss();

    }

    @Override
    public void Error(String Title, String Message, int ref) {

        ErrorDialog(Title, Message, ref);
    }

    @Override
    public void SalesOrderCostCenter(SalesOrderResponse salesOrderResponse) {
        if (salesOrderResponse.getTable().size() > 0) {
            for (int i = 0; i < salesOrderResponse.getTable().size(); i++) {
                costCenterSpinner.add(new CustomSpinner(salesOrderResponse.getTable().get(i).getXcode(), salesOrderResponse.getTable().get(i).getXname()));
            }
        } else {
        }
        areaSpinnerData();
    }

    @Override
    public void SalesOrderPartyName(SalesOrderPartyNameResponse salesOrderPartyNameResponse) {
        if (salesOrderPartyNameResponse.getTable().size() > 0) {
            for (int i = 0; i < salesOrderPartyNameResponse.getTable().size(); i++) {
                partyNameSpinner.add(new CustomSpinner(salesOrderPartyNameResponse.getTable().get(i).getXcode(), salesOrderPartyNameResponse.getTable().get(i).getXname()));
            }
        } else {
        }
        partySpinnerData();
    }

    @Override
    public void SalesOrderBroker(SalesOrderBrokerResponse salesOrderBrokerResponse) {
        if (salesOrderBrokerResponse.getTable().size() > 0) {
            for (int i = 0; i < salesOrderBrokerResponse.getTable().size(); i++) {
                brokerSpinner.add(new CustomSpinner(salesOrderBrokerResponse.getTable().get(i).getXcode(), salesOrderBrokerResponse.getTable().get(i).getXname()));
            }
        } else {
        }
        brokerSpinnerData();
    }

    @Override
    public void SalesOrderItemName(SalesOrderItemNameResponse salesOrderItemNameResponse) {
        if (salesOrderItemNameResponse.getTable().size() > 0) {
            for (int i = 0; i < salesOrderItemNameResponse.getTable().size(); i++) {
                itemNameSpinner.add(new CustomSpinner(salesOrderItemNameResponse.getTable().get(i).getXcode(), salesOrderItemNameResponse.getTable().get(i).getXname()));
            }
        } else {
        }
        itemNameSpinnerData();

    }

    @Override
    public void SalesOrderPartyNameChangeData(SalesOrderResponse salesOrderResponse) {
        if (salesOrderResponse.getTable().size() > 0) {
            if (partyNameSpinner.size() > 0) {
                partyNameSpinner.clear();
            }
            for (int i = 0; i < salesOrderResponse.getTable().size(); i++) {
                partyNameSpinner.add(new CustomSpinner(salesOrderResponse.getTable().get(i).getXcode(), salesOrderResponse.getTable().get(i).getXname()));
            }
        } else {
        }
        partySpinnerData();

    }

    @Override
    public void SalesOrderNetRateData(SalesOrderNetRateResponse salesOrderNetRateResponse) {
        if (salesOrderNetRateResponse.getTable().size() > 0) {
            for (int i = 0; i < salesOrderNetRateResponse.getTable().size(); i++) {
                try {
                    double netRate = salesOrderNetRateResponse.getTable().get(i).getRate();

                    String rateChargeValue = String.format("%1$,.2f", netRate);
                    etRateCharge.setText(rateChargeValue);
                    Log.d(TAG, "net value   " + rateChargeValue);
                } catch (Exception e) {

                    System.out.print(TAG + e.getLocalizedMessage());
                }

            }
        } else {
        }
    }

    @Override
    public void SaveSalesOrderData(SalesOrderSaveDataResponse salesOrderSaveDataResponse) {
        if (salesOrderSaveDataResponse.getTable().get(0).getSuccess() == 1) {

            Toast.makeText(this, "" + salesOrderSaveDataResponse.getTable().get(0).getMessage(), Toast.LENGTH_LONG).show();
            databaseHelper.deleteAllData();
            reset();

        } else {
            Toast.makeText(this, "" + salesOrderSaveDataResponse.getTable().get(0).getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void itemNameSpinnerData() {
        ArrayList<String> strings = new ArrayList<>();
        final ArrayList<String> stringXcode = new ArrayList<>();
        for (int i = 0; i < itemNameSpinner.size(); i++) {
            strings.add(itemNameSpinner.get(i).getmXname());
            stringXcode.add(itemNameSpinner.get(i).getmXcode());

        }
        SalesOrderSpinnerAdapter salesOrderSpinnerAdapter = new SalesOrderSpinnerAdapter(SalesOrderActivity.this, strings);
        spnItemName.setTitle("Select Item");

        spnItemName.setAdapter(salesOrderSpinnerAdapter);
        spnItemName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    itemXcode = stringXcode.get(position);

                } catch (Exception e) {
                    System.out.print("" + e.getLocalizedMessage());
                }

                itemName = ((TextView) view.findViewById(R.id.tvCategory)).getText().toString();
                Log.d(TAG, "item name " + itemName);
                itemratechange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void itemratechange() {
        salesOrderPresenter.ItemRateChangeData("drpItemname", sessionManager.getUserCode(), sessionManager.getCorpCode(), "", "", dateValue, "WSALEORDER", partyXcode, brokerXcode, "", "", "", itemXcode, "Parameter");

    }

    private void brokerSpinnerData() {
        ArrayList<String> strings = new ArrayList<>();
        final ArrayList<String> stringXcode = new ArrayList<>();
        for (int i = 0; i < brokerSpinner.size(); i++) {
            strings.add(brokerSpinner.get(i).getmXname());
            stringXcode.add(brokerSpinner.get(i).getmXcode());
        }
        SalesOrderSpinnerAdapter salesOrderSpinnerAdapter = new SalesOrderSpinnerAdapter(SalesOrderActivity.this, strings);
        spnBroker.setTitle("Select Item");

        spnBroker.setAdapter(salesOrderSpinnerAdapter);
        spnBroker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    brokerXcode = stringXcode.get(position);
                    Log.d(TAG, "broker xcode :" + brokerXcode);

                } catch (NullPointerException e) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void partySpinnerData() {
        ArrayList<String> strings = new ArrayList<>();
        final ArrayList<String> stringXcode = new ArrayList<>();
        for (int i = 0; i < partyNameSpinner.size(); i++) {
            strings.add(partyNameSpinner.get(i).getmXname());
            stringXcode.add(partyNameSpinner.get(i).getmXcode());
        }
        SalesOrderSpinnerAdapter salesOrderSpinnerAdapter = new SalesOrderSpinnerAdapter(SalesOrderActivity.this, strings);
        spnPartyName.setTitle("Select Item");

        spnPartyName.setAdapter(salesOrderSpinnerAdapter);
        spnPartyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

                    partyXcode = stringXcode.get(position);
                    Log.d(TAG, "party name xcode :" + partyXcode);

                } catch (NullPointerException e) {
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void areaSpinnerData() {
        ArrayList<String> strings = new ArrayList<>();
        final ArrayList<String> stringXcode = new ArrayList<>();
        for (int i = 0; i < costCenterSpinner.size(); i++) {
            strings.add(costCenterSpinner.get(i).getmXname());
            stringXcode.add(costCenterSpinner.get(i).getmXcode());
        }
        SalesOrderSpinnerAdapter salesOrderSpinnerAdapter = new SalesOrderSpinnerAdapter(SalesOrderActivity.this, strings);
        spnArea.setTitle("Select Item");
        spnArea.setAdapter(salesOrderSpinnerAdapter);

        spnArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                try {
                    areaXcode = stringXcode.get(position);
                    salesorderpartynamechange();
                    Log.d(TAG, "area xCode " + areaXcode);

                } catch (NullPointerException e) {
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void salesorderpartynamechange() {
        salesOrderPresenter.salesOrderPartyNameChange("drpParty", sessionManager.getUserCode(), sessionManager.getCorpCode(), "", dateValue, "WSALEORDER", areaXcode, "Parameter");

    }

    private void ErrorDialog(String Title, String Message, final int ref) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder.setMessage(Message);
        builder.setPositiveButton("RETRY",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        //  reset();
                        switch (ref) {
                            case 1:
                                salesordercost();
                                break;
                            case 2:
                                salesorderpartynamechange();
                                break;
                            case 3:
                                salesorderparty();
                                break;
                            case 4:
                                salesOrderbroker();
                                break;
                            case 5:
                                salesorderitem();
                                break;
                            case 6:
                                itemratechange();
                                break;
                            case 7:
                                validation();
                                break;
                        }
                    }
                });
        builder.show();
    }

    private void LoadBase() {
        salesordercost();
        salesorderparty();
        salesOrderbroker();
        salesorderitem();
    }

    private void salesordercost() {
        salesOrderPresenter.salesOrderCostCenter("drpLocation", sessionManager.getUserCode(), sessionManager.getCorpCode(), "", dateValue, "WSALEORDER", "Parameter");
    }

    private void salesorderparty() {
        salesOrderPresenter.salesOrderPartyName("drpParty", sessionManager.getUserCode(), sessionManager.getCorpCode(), "base");

    }

    private void salesOrderbroker() {
        salesOrderPresenter.salesOrderBroker("drpBroker", sessionManager.getUserCode(), sessionManager.getCorpCode(), "base");

    }

    private void salesorderitem() {
        salesOrderPresenter.salesOrderItemName("drpItemname", sessionManager.getUserCode(), sessionManager.getCorpCode(), "base");

    }

    private void reset() {

        costCenterSpinner.clear();
        partyNameSpinner.clear();
        brokerSpinner.clear();
        itemNameSpinner.clear();
        salesOrderDatas.clear();

        etNumber.setText("");
        etRateCharge.setText("");
        etAmount.setText("");
        etQuantity.setText("");
        etNetRate.setText("");
        etTaxRate.setText("");

        LoadBase();
    }

    private void validation() {
        String number = etNumber.getText().toString();
        String rateCharge = etRateCharge.getText().toString();
        String taxRate = etTaxRate.getText().toString();
        String netRate = etNetRate.getText().toString();
        String quantity = etQuantity.getText().toString();
        String amount = etAmount.getText().toString();
        for (int i = 0; i < salesOrderDataArrayList.size(); i++) {
            salesOrderDatas.add(new SalesOrderData("", "" + (i + 1), salesOrderDataArrayList.get(i).getItemXCode(), salesOrderDataArrayList.get(i).getQuantity(),
                    salesOrderDataArrayList.get(i).getRate(), salesOrderDataArrayList.get(i).getAmount(), salesOrderDataArrayList.get(i).getItemName()));
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(SalesOrderData.class, new SalesOrderJsonSerializer()).create();
        String json = gson.toJson(salesOrderDatas);
        if (TextUtils.isEmpty(number)) {
            etNumber.setError("Enter Number");
            return;
        }
        if (TextUtils.isEmpty(rateCharge)) {
            etRateCharge.setError("Enter RateCharge");
            return;
        }
        if (TextUtils.isEmpty(taxRate)) {
            etTaxRate.setError("Enter TaxRate");
            return;
        }
        if (TextUtils.isEmpty(netRate)) {
            etNetRate.setError("Enter NetRate");
            return;
        }
        if (TextUtils.isEmpty(quantity)) {
            etQuantity.setError("Enter Quantity");
            return;
        }
        if (TextUtils.isEmpty(amount)) {
            etAmount.setError("Enter Amount");
            return;
        }
        if (json.equals("[]")) {
            Toast.makeText(this, "save data", Toast.LENGTH_SHORT).show();
            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            v.vibrate(400);
            return;
        }
//        Gson gson = new GsonBuilder().registerTypeAdapter(SalesOrderData.class, new SalesOrderJsonSerializer()).create();
//        String json = gson.toJson(salesOrderDataArrayList);

        Log.d(TAG, "json format  " + json);
        salesOrderPresenter.saveSalesOrderData("", areaXcode, partyXcode, brokerXcode, number, sessionManager.getUserCode(), "", sessionManager.getUnitCode(), sessionManager.getCorpCode(), "", "", "", json);
    }

    private void setBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.layout_bottom_sheet_sales_order, null);
        bottomSheetDialog.setContentView(sheetView);
        llSalesOrder = (LinearLayout) sheetView.findViewById(R.id.layout_sales_order);
        rvSalesOrderData = (RecyclerView) sheetView.findViewById(R.id.rv_sales_order_data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SalesOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        rvSalesOrderData.setLayoutManager(layoutManager);
    }

    private void databaseValidation() {

        String quantity = etQuantity.getText().toString();
        String amount = etAmount.getText().toString();

        if (TextUtils.isEmpty(quantity)) {
            etQuantity.setError("Enter Quantity");
            return;
        }
        if (TextUtils.isEmpty(amount)) {
            etAmount.setError("Enter Amount");
            return;
        }

        SalesOrderData salesOrderData = new SalesOrderData();
        salesOrderData.setSrNo("");
        salesOrderData.setSr("" + (count + 1));
        salesOrderData.setAmount(etAmount.getText().toString());
        salesOrderData.setItemXCode("null");
        salesOrderData.setItemName(itemName);
        salesOrderData.setRate(etRateCharge.getText().toString());
        salesOrderData.setQuantity(etQuantity.getText().toString());
        databaseHelper.inserData(salesOrderData);
        count = databaseHelper.getSalesOrderCount();

//        etAmount.setText("");
//        etQuantity.setText("");
        Toast.makeText(this, "your data save", Toast.LENGTH_SHORT).show();
        // reset();
    }

    // item xcode null by user input in sqlite
    private String multipication() {
        float taxRate = 0.0f, taxValue = 0.0f, rateCharge = 0.0f, netRate = 0.0f;
        try {
            taxRate = Float.parseFloat(etTaxRate.getText().toString());
        } catch (NumberFormatException e) {
        }
        try {
            rateCharge = Float.parseFloat(etRateCharge.getText().toString());
        } catch (NumberFormatException e) {
        }
        taxValue = taxRate + 100;
        netRate = (float) (rateCharge * taxValue * 0.01);
        return String.format("%.2f", netRate);
    }

    private String amountCalculation() {
        float quantity = 0.0f, netAmount = 0.0f, rate = 0.0f;

        try {
            quantity = Float.parseFloat(etQuantity.getText().toString());
            rate = Float.parseFloat(multipication());
        } catch (NumberFormatException e) {
        }
        netAmount = quantity * rate;
        return String.format("%.2f", netAmount);

    }

    private void reloadDatabase() {
        if (count == 0) {

        } else {
            salesOrderDataArrayList = databaseHelper.getAllData();

        }
    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(3000);
        getWindow().setEnterTransition(fade);
    }
}
