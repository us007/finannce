package com.prudent.busoftadmin.ui.formsummary;

import android.util.Log;
import android.widget.Toast;

import com.prudent.busoftadmin.data.api.apihelper.ApiConfig;
import com.prudent.busoftadmin.data.api.apihelper.AppConfig;
import com.prudent.busoftadmin.data.api.model.FormSummarySaveData.request.FormSummarySaveRequest;
import com.prudent.busoftadmin.data.api.model.FormSummarySaveData.response.FormSummarySaveResponse;
import com.prudent.busoftadmin.data.api.model.formsummary.request.FormsummaryRequest;
import com.prudent.busoftadmin.data.api.model.formsummary.response.FormSummaryResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryCrAccount.request.FormSummaryCrAccountRequest;
import com.prudent.busoftadmin.data.api.model.formsummaryCrAccount.response.FormSummaryCrAccountResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryDrAccount.request.FormSummaryDrAccountRequest;
import com.prudent.busoftadmin.data.api.model.formsummaryDrAccount.response.FormSummaryDrAccountResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryvoucher.request.FormSummaryVoucherRequest;
import com.prudent.busoftadmin.data.api.model.formsummaryvoucher.response.FormSummaryVoucherResponse;
import com.prudent.busoftadmin.ui.base.Presenter;

import java.net.SocketTimeoutException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AFF41 on 7/21/2017.
 */

public class FormSummaryPresenter implements Presenter<FormSummaryView> {
    private FormSummaryView formSummaryView;

    @Override
    public void onAttach(FormSummaryView view) {
        formSummaryView = view;
    }

    @Override
    public void onDetach() {
        formSummaryView = null;
    }

    public void FormSummarySentData(String type, String CorpCenter, String userId) {
        formSummaryView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<FormSummaryResponse> call = getResponse.FormSummary(new FormsummaryRequest(userId, CorpCenter, type));
        call.enqueue(new Callback<FormSummaryResponse>() {
            @Override
            public void onResponse(Call<FormSummaryResponse> call, Response<FormSummaryResponse> response) {
                FormSummaryResponse serverResponse = response.body();

                formSummaryView.HideProgress();
                if (response.isSuccessful()) {
                    formSummaryView.ShowFormSummary(serverResponse);
                } else {
                    formSummaryView.Error("Try Again", "Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<FormSummaryResponse> call, Throwable t) {
                formSummaryView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    formSummaryView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    formSummaryView.Error("Timeout Error", "Please, Try again!");
                } else {
                    formSummaryView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    // form data distributor
    public void FormSummaryVoucher(String userid, String corpcentre, String type, String formname, String control, String para1, String para2) {
        formSummaryView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<FormSummaryVoucherResponse> call = getResponse.FormSummaryVoucher(new FormSummaryVoucherRequest(userid, corpcentre, type, formname, control, para1, para2));
        call.enqueue(new Callback<FormSummaryVoucherResponse>() {
            @Override
            public void onResponse(Call<FormSummaryVoucherResponse> call, Response<FormSummaryVoucherResponse> response) {
                FormSummaryVoucherResponse serverResponse = response.body();

                formSummaryView.HideProgress();
                if (response.isSuccessful()) {
                    formSummaryView.ShowFormSummaryVoucher(serverResponse);
                } else {
                    formSummaryView.Error("Try Again", "Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<FormSummaryVoucherResponse> call, Throwable t) {
                formSummaryView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    formSummaryView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    formSummaryView.Error("Timeout Error", "Please, Try again!");
                } else {
                    formSummaryView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    public void FormSummaryDrAccount(String userid, String corpcentre, String type, String formname, String control, String para1, String para2, String para3, String para4, String para5, String para6) {
        formSummaryView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<FormSummaryDrAccountResponse> call = getResponse.FormSummaryDrAccount(new FormSummaryDrAccountRequest(userid, corpcentre, type, formname, control, para1, para2, para3, para4, para5, para6));
        call.enqueue(new Callback<FormSummaryDrAccountResponse>() {
            @Override
            public void onResponse(Call<FormSummaryDrAccountResponse> call, Response<FormSummaryDrAccountResponse> response) {
                FormSummaryDrAccountResponse serverResponse = response.body();
                formSummaryView.HideProgress();
                if (response.isSuccessful()) {
                    formSummaryView.ShowFormSummaryDrAccount(serverResponse);
                } else {
                    formSummaryView.Error("Try Again", "Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<FormSummaryDrAccountResponse> call, Throwable t) {
                formSummaryView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    formSummaryView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    formSummaryView.Error("Timeout Error", "Please, Try again!");
                } else {
                    formSummaryView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    public void FormSummaryCreditAccount(String userid, String corpcentre, String type, String formname, String control, String para1, String para2, String para3, String para4, String para5, String para6, String para7) {
        formSummaryView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<FormSummaryCrAccountResponse> call = getResponse.FormSummaryCreditAccount(new FormSummaryCrAccountRequest(userid, corpcentre, type, formname, control, para1, para2, para3, para4, para5, para6, para7));
        call.enqueue(new Callback<FormSummaryCrAccountResponse>() {
            @Override
            public void onResponse(Call<FormSummaryCrAccountResponse> call, Response<FormSummaryCrAccountResponse> response) {
                FormSummaryCrAccountResponse serverResponse = response.body();
                formSummaryView.HideProgress();
                if (response.isSuccessful()) {
                    formSummaryView.ShowFormSummaryCreditAccount(serverResponse);
                } else {
                    formSummaryView.Error("Try Again", "Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<FormSummaryCrAccountResponse> call, Throwable t) {
                formSummaryView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    formSummaryView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    formSummaryView.Error("Timeout Error", "Please, Try again!");
                } else {
                    formSummaryView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    public void FormSummarySaveData(String srno, String voucher, String date,
                                    String voucherNo, String instumentNo,
                                    String drAccount, String crAccount,
                                    String accountType, String grossAmount,
                                    String taxRate, String tax, String totalAmount,
                                    String remarks, String isRcm, String userId,
                                    String ipaddress, String unit, String corpcentre,
                                    String entrydatetime) {
        formSummaryView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<FormSummarySaveResponse> call = getResponse.FormSummarySaveData(
                new FormSummarySaveRequest(srno, voucher, date, voucherNo,
                        instumentNo, drAccount, crAccount, accountType,
                        grossAmount, taxRate, tax, totalAmount, remarks,
                        isRcm, userId, ipaddress, unit, corpcentre, entrydatetime));
        call.enqueue(new Callback<FormSummarySaveResponse>() {
            @Override
            public void onResponse(Call<FormSummarySaveResponse> call, Response<FormSummarySaveResponse> response) {
                FormSummarySaveResponse serverResponse = response.body();
                formSummaryView.HideProgress();
                if (response.isSuccessful()) {
                    formSummaryView.SaveFormSummaryData(serverResponse);
                } else {
                    formSummaryView.Error("Try Again", "Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<FormSummarySaveResponse> call, Throwable t) {
                formSummaryView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    formSummaryView.Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    formSummaryView.Error("Timeout Error", "Please, Try again!");
                } else {
                    formSummaryView.Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }
}
