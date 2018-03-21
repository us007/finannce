package com.prudent.busoftadmin.ui.formsummary;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

//import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.adapter.formsummaryspinner.FormSumaryTypeSpinner;
import com.prudent.busoftadmin.data.api.model.FormSummarySaveData.response.FormSummarySaveResponse;
import com.prudent.busoftadmin.data.api.model.formsummary.response.FormSummaryResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryCrAccount.response.FormSummaryCrAccountResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryDrAccount.response.FormSummaryDrAccountResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryvoucher.response.FormSummaryVoucherResponse;
import com.prudent.busoftadmin.data.pref.SessionManager;
import com.prudent.busoftadmin.model.CustomSpinner;
import com.prudent.busoftadmin.utils.searchablespinner.SearchableSpinner;

import org.joda.time.DateTime;

import java.util.ArrayList;

//import com.prudent.busoftadmin.adapter.formsummaryspinner.FormSumaryCreditAccSpinner;
//import com.prudent.busoftadmin.adapter.formsummaryspinner.FormSummaryDrAccSpinner;
//import com.prudent.busoftadmin.adapter.formsummaryspinner.FormSummaryVoucherTypeSpinner;

public class FormSummaryActivity extends AppCompatActivity implements FormSummaryView {
    private AppCompatEditText etVoucherNo, etPartyReference, etGrossAmount, etTaxRate, etTax, etNetAmount, etRemark;
    private AppCompatTextView tvSelectDate;
    private SearchableSpinner spnVoucher, spnDrAccount, spnCrAccount, spnType;
    private DateTime dateTime;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    private FormSummaryPresenter formSummaryPresenter;
    private ArrayList<CustomSpinner> voucherTypeSpinner = new ArrayList<>();
    private ArrayList<CustomSpinner> drAccountSpinner = new ArrayList<>();
    private ArrayList<CustomSpinner> creditAccountSpinner = new ArrayList<>();
    private ArrayList<CustomSpinner> typeSpinner = new ArrayList<>();
    private AppCompatButton btnAdd, btnReset;
    private AppCompatCheckBox cbRcm;

    private String mDrAccount;
    private String mCreditAccount;
    private String mType;
    private Toolbar toolbar;
    private static final String TAG = "FormSummaryActivity";
    private String reverseDate;
    private String mVoucher;
//    private boolean isSpinnerVoucherTouched = false;
//    private boolean isSpinnerDrAccountTouched = false;
//    private boolean isSpinnerCrAccountTouched = false;
//    private boolean isSpinnerTypeTouched = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_summary);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sessionManager = new SessionManager(this);
        registerAllView();
        dateTime = DateTime.now();

        tvSelectDate.setText(dateTime.getDayOfMonth() + "/" + dateTime.getMonthOfYear() + "/" + dateTime.getYear());
        reverseDate = dateTime.year().get() + "-" + dateTime.monthOfYear().get() + "-" + dateTime.dayOfMonth().get();

        initPresenter();
        onAttach();

        SetProgress();

        LoadBase();


        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectdate();
            }
        });
        etNetAmount.setKeyListener(null);
        etTax.setKeyListener(null);
        etGrossAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etTax.setText(multiplication());
                etNetAmount.setText(summation());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etTaxRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                etTax.setText(multiplication());
                etNetAmount.setText(summation());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidity();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });
//        spnDrAccount.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                isSpinnerDrAccountTouched = true;
//                return false;
//            }
//        });
//        spnVoucher.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                isSpinnerVoucherTouched = true;
//                return false;
//            }
//        });
//        spnType.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                isSpinnerTypeTouched = true;
//                return false;
//            }
//        });
//        spnCrAccount.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                isSpinnerCrAccountTouched = true;
//                return false;
//            }
//        });
    }

    private void SetProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
    }

    public void registerAllView() {
        cbRcm = (AppCompatCheckBox) findViewById(R.id.ckb_account_rcm);
        btnAdd = (AppCompatButton) findViewById(R.id.btn_account_summary_add);
        btnReset = (AppCompatButton) findViewById(R.id.btn_account_summary_reset);
        tvSelectDate = (AppCompatTextView) findViewById(R.id.tv_account_select_date);
        etGrossAmount = (AppCompatEditText) findViewById(R.id.et_account_gross_amount);
        etNetAmount = (AppCompatEditText) findViewById(R.id.et_net_amount);
        etPartyReference = (AppCompatEditText) findViewById(R.id.et_account_party_ref);
        etRemark = (AppCompatEditText) findViewById(R.id.et_account_remark);
        etTax = (AppCompatEditText) findViewById(R.id.et_account_tax);
        etTaxRate = (AppCompatEditText) findViewById(R.id.et_account_tax_rate);
        etVoucherNo = (AppCompatEditText) findViewById(R.id.et_account_voucher);
        spnVoucher = (SearchableSpinner) findViewById(R.id.spn_account_voucher);
        spnDrAccount = (SearchableSpinner) findViewById(R.id.spn_dr_account);
        spnCrAccount = (SearchableSpinner) findViewById(R.id.spn_cr_account);
        spnType = (SearchableSpinner) findViewById(R.id.spn_account_type);

    }

    void selectdate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(FormSummaryActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvSelectDate.setText("" + dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, dateTime.getYear(), dateTime.getMonthOfYear() - 1, dateTime.getDayOfMonth());
        datePickerDialog.show();
    }

    private void initPresenter() {
        formSummaryPresenter = new FormSummaryPresenter();
    }

    @Override
    public void onAttach() {
        formSummaryPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        formSummaryPresenter.onDetach();
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
    public void Error(String Title, String Message) {
        ErrorDialog(Title, Message);
    }

    @Override
    protected void onDestroy() {
        onDetach();
        super.onDestroy();
    }

    @Override
    public void ShowFormSummary(FormSummaryResponse formSummaryResponse) {
        if (formSummaryResponse.getTable().size() > 0) {
            for (int i = 0; i < formSummaryResponse.getTable().size(); i++) {
                if (formSummaryResponse.getTable().get(i).getControl().equals("VoucherType")) {
                    voucherTypeSpinner.add(new CustomSpinner(formSummaryResponse.getTable().get(i).getXcode(), formSummaryResponse.getTable().get(i).getXname()));
                }
                if (formSummaryResponse.getTable().get(i).getControl().equals("Account")) {
                    drAccountSpinner.add(new CustomSpinner(formSummaryResponse.getTable().get(i).getXcode(), formSummaryResponse.getTable().get(i).getXname()));
                    creditAccountSpinner.add(new CustomSpinner(formSummaryResponse.getTable().get(i).getXcode(), formSummaryResponse.getTable().get(i).getXname()));
                }
                if (formSummaryResponse.getTable().get(i).getControl().equals("AcType")) {
                    typeSpinner.add(new CustomSpinner(formSummaryResponse.getTable().get(i).getXcode(), formSummaryResponse.getTable().get(i).getXname()));
                }
            }
        }
        VoucherSpinnerData();
        drAccountSpinnerData();
        creditAccountSpinner();
        typeSpinner();
    }

    @Override
    public void ShowFormSummaryVoucher(FormSummaryVoucherResponse formSummaryVoucherResponse) {

        if (formSummaryVoucherResponse.getTable().size() > 0) {
            if (drAccountSpinner.size() > 0) {
                drAccountSpinner.clear();
            }
            for (int i = 0; i < formSummaryVoucherResponse.getTable().size(); i++) {
                drAccountSpinner.add(new CustomSpinner(formSummaryVoucherResponse.getTable().get(i).getXcode(), formSummaryVoucherResponse.getTable().get(i).getXname()));
            }
            drAccountSpinnerData();

        }

    }

    @Override
    public void ShowFormSummaryDrAccount(FormSummaryDrAccountResponse formSummaryDrAccountResponse) {
        if (formSummaryDrAccountResponse.getTable().size() > 0) {
            if (creditAccountSpinner.size() > 0) {
                creditAccountSpinner.clear();
            }
            for (int i = 0; i < formSummaryDrAccountResponse.getTable().size(); i++) {
                creditAccountSpinner.add(new CustomSpinner(formSummaryDrAccountResponse.getTable().get(i).getXcode(), formSummaryDrAccountResponse.getTable().get(i).getXname()));
            }
            creditAccountSpinner();
        }
    }

    @Override
    public void ShowFormSummaryCreditAccount(FormSummaryCrAccountResponse formSummaryCrAccountResponse) {
        if (formSummaryCrAccountResponse.getTable().size() > 0) {
            if (typeSpinner.size() > 0) {
                typeSpinner.clear();
            }
            for (int i = 0; i < formSummaryCrAccountResponse.getTable().size(); i++) {
                typeSpinner.add(new CustomSpinner(formSummaryCrAccountResponse.getTable().get(i).getXcode(), formSummaryCrAccountResponse.getTable().get(i).getXname()));
            }
            typeSpinner();
        }


    }

    @Override
    public void SaveFormSummaryData(FormSummarySaveResponse summarySaveResponse) {
        if (summarySaveResponse.getTable().size() > 0) {
            for (int i = 0; i < summarySaveResponse.getTable().size(); i++) {

                Toast.makeText(this, "" + summarySaveResponse.getTable().get(i).getMessage(), Toast.LENGTH_LONG).show();
                if (summarySaveResponse.getTable().get(i).getSuccess() == 1) {
                    LoadBase();
                    etGrossAmount.setText("");
                    etTax.setText("");
                    etNetAmount.setText("");
                    etTaxRate.setText("");
                    etRemark.setText("");
                    etPartyReference.setText("");
                    etVoucherNo.setText("");

                } else {
                    Toast.makeText(this, "" + summarySaveResponse.getTable().get(i).getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void typeSpinner() {
        ArrayList<String> strings = new ArrayList<>();
        final ArrayList<String> stringXcode = new ArrayList<>();
        for (int i = 0; i < typeSpinner.size(); i++) {
            strings.add(typeSpinner.get(i).getmXname());
            stringXcode.add(typeSpinner.get(i).getmXcode());

        }

        FormSumaryTypeSpinner sumaryTypeSpinner = new FormSumaryTypeSpinner(FormSummaryActivity.this, strings);
        spnType.setAdapter(sumaryTypeSpinner);
        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             //   if (!isSpinnerTypeTouched) return;
                try {
                    mType = stringXcode.get(position);
                    Log.d(TAG, "type :" + mType);

                } catch (NullPointerException e) {
                    System.out.print(e.getLocalizedMessage());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void creditAccountSpinner() {
        ArrayList<String> strings = new ArrayList<>();
        final ArrayList<String> stringXcode = new ArrayList<>();
        for (int i = 0; i < creditAccountSpinner.size(); i++) {
            strings.add(creditAccountSpinner.get(i).getmXname());
            stringXcode.add(creditAccountSpinner.get(i).getmXcode());

        }
        FormSumaryTypeSpinner creditAccSpinner = new FormSumaryTypeSpinner(FormSummaryActivity.this, strings);
        spnCrAccount.setAdapter(creditAccSpinner);
        spnCrAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             //   if (!isSpinnerCrAccountTouched) return;
                try {
                    mCreditAccount = stringXcode.get(position);
                    Log.d(TAG, "Credit Account :" + mCreditAccount);
                    formSummaryPresenter.FormSummaryCreditAccount(sessionManager.getUserCode(), sessionManager.getCorpCode(), "Parameter", "SummaryAccount", "AcType", "", mVoucher, reverseDate, etVoucherNo.getText().toString(), etPartyReference.getText().toString(), mDrAccount, mCreditAccount);
                } catch (NullPointerException e) {
                    System.out.print(e.getLocalizedMessage());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void drAccountSpinnerData() {
        ArrayList<String> strings = new ArrayList<>();
        final ArrayList<String> stringXcode = new ArrayList<>();
        for (int i = 0; i < drAccountSpinner.size(); i++) {
            strings.add(drAccountSpinner.get(i).getmXname());
            stringXcode.add(drAccountSpinner.get(i).getmXcode());

        }
        FormSumaryTypeSpinner drAccSpinner = new FormSumaryTypeSpinner(FormSummaryActivity.this, strings);
        spnDrAccount.setAdapter(drAccSpinner);
        spnDrAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
            //    if (!isSpinnerDrAccountTouched) return;
                try {
                    mDrAccount = stringXcode.get(position);
                    Log.d(TAG, "Dr Account :" + mDrAccount);
                    formSummaryPresenter.FormSummaryDrAccount(sessionManager.getUserCode(), sessionManager.getCorpCode(), "Parameter", "SummaryAccount", "CrAccount", "", mVoucher, reverseDate, etVoucherNo.getText().toString(), etPartyReference.getText().toString(), mDrAccount);

                } catch (NullPointerException e) {
                    System.out.print(e.getLocalizedMessage());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void VoucherSpinnerData() {
        ArrayList<String> strings = new ArrayList<>();
        final ArrayList<String> stringXcode = new ArrayList<>();
        for (int i = 0; i < voucherTypeSpinner.size(); i++) {
            strings.add(voucherTypeSpinner.get(i).getmXname());
            stringXcode.add(voucherTypeSpinner.get(i).getmXcode());

        }
        FormSumaryTypeSpinner spinner = new FormSumaryTypeSpinner(FormSummaryActivity.this, strings);
        spnVoucher.setAdapter(spinner);
        spnVoucher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //  if (!isSpinnerVoucherTouched) return;
                try {
                    mVoucher = stringXcode.get(position);
                    Log.d(TAG, "voucher :" + mVoucher);
                    formSummaryPresenter.FormSummaryVoucher(sessionManager.getUserCode(), sessionManager.getCorpCode(), "Parameter", "SummaryAccount", "DrAccount", "", mVoucher);

                } catch (Exception e) {
                    System.out.print("big error" + e.getLocalizedMessage());
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void ErrorDialog(String Title, String Message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder.setMessage(Message);
        builder.setPositiveButton("RETRY",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Reset();
                    }
                });
        builder.show();
    }

    private void Reset() {
        if (voucherTypeSpinner.size() > 0) {
            voucherTypeSpinner.clear();
        }
        if (drAccountSpinner.size() > 0) {
            drAccountSpinner.clear();
        }
        if (creditAccountSpinner.size() > 0) {
            creditAccountSpinner.clear();
        }
        if (typeSpinner.size() > 0) {
            typeSpinner.clear();
        }
        etPartyReference.setText("");
        etGrossAmount.setText("");
        etNetAmount.setText("");
        etVoucherNo.setText("");
        etRemark.setText("");
        etTax.setText("");
        etTaxRate.setText("");
        tvSelectDate.setText(dateTime.getDayOfMonth() + "/" + dateTime.getMonthOfYear() + "/" + dateTime.getYear());

        LoadBase();
    }

    private void LoadBase() {
        formSummaryPresenter.FormSummarySentData("base", sessionManager.getCorpCode(), sessionManager.getUserCode());
    }

    private void checkValidity() {
        String srno = "";
        String voucher = mVoucher;
        String date = reverseDate;
        String voucherNo = etVoucherNo.getText().toString();
        String instumentNo = etPartyReference.getText().toString();
        String drAccount = mDrAccount;
        String crAccount = mCreditAccount;
        String accountType = mType;
        String grossAmount = etGrossAmount.getText().toString();
        String taxRate = etTaxRate.getText().toString();
        String tax = etTax.getText().toString();
        String totalAmount = etNetAmount.getText().toString();
        String remarks = etRemark.getText().toString();
        String isRcm;
        String userId = sessionManager.getUserCode();
        String ipaddress = "";
        String unit = "";
        String corpcentre = sessionManager.getCorpCode();
        String entrydatetime = "";

        if (TextUtils.isEmpty(voucherNo)) {
            etVoucherNo.setError("Enter voucher no");
            return;
        }
        if (TextUtils.isEmpty(instumentNo)) {
            etPartyReference.setError("Enter Party Reference");
            return;
        }
        if (TextUtils.isEmpty(grossAmount)) {
            etGrossAmount.setError("Enter Gross Amount");
            return;
        }
        if (TextUtils.isEmpty(tax)) {
            etTax.setError("Enter tax amount");
            return;
        }
        if (cbRcm.isChecked()) {
            isRcm = "1";
        } else {
            isRcm = "0";

        }
        formSummaryPresenter.FormSummarySaveData(srno, voucher, date, voucherNo, instumentNo, drAccount, crAccount,
                accountType, grossAmount, taxRate, tax, totalAmount, remarks, isRcm, userId,
                ipaddress, unit, corpcentre, entrydatetime);

    }

    private String multiplication() {
        float grossAmount = 0.0f, taxRate = 0.0f, taxValue = 0.0f;
        try {
            grossAmount = Float.parseFloat(etGrossAmount.getText().toString());

            Log.e(TAG, "ga  :" + grossAmount + "");

        } catch (Exception e) {
        }
        try {
            taxRate = Float.parseFloat(etTaxRate.getText().toString());

        } catch (Exception e) {
        }

        taxValue = (float) (grossAmount * taxRate * .01);
        return String.format("%.2f", taxValue);
    }

    private String summation() {
        float grossAmount = 0.0f, netAmount = 0.0f;
        float taxValue = Float.parseFloat(multiplication());
        try {
            grossAmount = Float.parseFloat(etGrossAmount.getText().toString());
            netAmount = grossAmount + taxValue;

        } catch (Exception e) {
        }

        return String.format("%.2f", netAmount);
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
}
