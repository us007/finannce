package com.prudent.busoftadmin.ui.salesorder;

import com.prudent.busoftadmin.data.api.apihelper.ApiConfig;
import com.prudent.busoftadmin.data.api.apihelper.AppConfig;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderBroker.request.SalesOrderBrokerRequest;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderBroker.response.SalesOrderBrokerResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderCostCenter.request.SalesOrderRequest;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderCostCenter.response.SalesOrderResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderItemName.request.SalesOrderItemNameRequest;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderItemName.response.SalesOrderItemNameResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderNetRate.request.SalesOrderNetRateRequest;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderNetRate.response.SalesOrderNetRateResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderPartyName.request.SalesOrderPartyRequest;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderPartyName.response.SalesOrderPartyNameResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderSaveData.request.SalesOrderSaveDataRequest;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderSaveData.response.SalesOrderSaveDataResponse;
import com.prudent.busoftadmin.ui.base.Presenter;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AFF41 on 7/25/2017.
 */

public class SalesOrderPresenter implements Presenter<SalesOrderView> {
    private SalesOrderView salesOrderView;

    @Override
    public void onAttach(SalesOrderView view) {
        salesOrderView = view;
    }

    @Override
    public void onDetach() {
        salesOrderView = null;
    }

    public void salesOrderCostCenter(String control, String userid, String corpcentre,
                                     String para1, String para2, String para3, String type) {
        salesOrderView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<SalesOrderResponse> call = getResponse.GetSalesOrderCostCenter(new SalesOrderRequest(control, userid, corpcentre, para1, para2, para3, type));
        call.enqueue(new Callback<SalesOrderResponse>() {
            @Override
            public void onResponse(Call<SalesOrderResponse> call, Response<SalesOrderResponse> response) {
                SalesOrderResponse getServerResponse = response.body();
                salesOrderView.HideProgress();
                if (response.isSuccessful()) {
                    salesOrderView.SalesOrderCostCenter(getServerResponse);
                } else {
                    salesOrderView.Error("Try Again", "Something went wrong",1);
                }
            }

            @Override
            public void onFailure(Call<SalesOrderResponse> call, Throwable t) {
                salesOrderView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",1);
                } else if (t instanceof SocketTimeoutException) {
                    salesOrderView.Error("Timeout Error", "Please, Try again!",1);
                } else {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",1);
                }
            }
        });
    }

    public void salesOrderPartyNameChange(String control, String userid, String corpcentre,
                                          String para1, String para2, String para3,
                                          String para4, String type) {
        salesOrderView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<SalesOrderResponse> call = getResponse.GetSalesOrderCostCenter(new SalesOrderRequest(control, userid, corpcentre, para1, para2, para3, para4, type));
        call.enqueue(new Callback<SalesOrderResponse>() {
            @Override
            public void onResponse(Call<SalesOrderResponse> call, Response<SalesOrderResponse> response) {
                SalesOrderResponse getServerResponse = response.body();
                salesOrderView.HideProgress();
                if (response.isSuccessful()) {
                    salesOrderView.SalesOrderPartyNameChangeData(getServerResponse);
                } else {
                    salesOrderView.Error("Try Again", "Something went wrong",2);
                }
            }

            @Override
            public void onFailure(Call<SalesOrderResponse> call, Throwable t) {
                salesOrderView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",2);
                } else if (t instanceof SocketTimeoutException) {
                    salesOrderView.Error("Timeout Error", "Please, Try again!",2);
                } else {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",2);
                }
            }
        });
    }

    public void salesOrderPartyName(String control, String userid, String corpcentre, String type) {
        salesOrderView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<SalesOrderPartyNameResponse> call = getResponse.GetSalesPartyName(new SalesOrderPartyRequest(control, userid, corpcentre, type));
        call.enqueue(new Callback<SalesOrderPartyNameResponse>() {
            @Override
            public void onResponse(Call<SalesOrderPartyNameResponse> call, Response<SalesOrderPartyNameResponse> response) {
                SalesOrderPartyNameResponse getServerResponse = response.body();
                salesOrderView.HideProgress();
                if (response.isSuccessful()) {
                    salesOrderView.SalesOrderPartyName(getServerResponse);
                } else {
                    salesOrderView.Error("Try Again", "Something went wrong",3);
                }
            }

            @Override
            public void onFailure(Call<SalesOrderPartyNameResponse> call, Throwable t) {
                salesOrderView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",3);
                } else if (t instanceof SocketTimeoutException) {
                    salesOrderView.Error("Timeout Error", "Please, Try again!",3);
                } else {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",3);
                }
            }
        });
    }

    public void salesOrderBroker(String control, String userid, String corpcentre, String type) {
        salesOrderView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<SalesOrderBrokerResponse> call = getResponse.GetSalesOrderBroker(new SalesOrderBrokerRequest(control, userid, corpcentre, type));
        call.enqueue(new Callback<SalesOrderBrokerResponse>() {
            @Override
            public void onResponse(Call<SalesOrderBrokerResponse> call, Response<SalesOrderBrokerResponse> response) {
                SalesOrderBrokerResponse getServerResponse = response.body();
                salesOrderView.HideProgress();
                if (response.isSuccessful()) {
                    salesOrderView.SalesOrderBroker(getServerResponse);
                } else {
                    salesOrderView.Error("Try Again", "Something went wrong",4);
                }
            }

            @Override
            public void onFailure(Call<SalesOrderBrokerResponse> call, Throwable t) {
                salesOrderView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",4);
                } else if (t instanceof SocketTimeoutException) {
                    salesOrderView.Error("Timeout Error", "Please, Try again!",4);
                } else {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",4);
                }
            }
        });
    }

    public void salesOrderItemName(String control, String userid, String corpcentre, String type) {
        salesOrderView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<SalesOrderItemNameResponse> call = getResponse.GetSalesOrderItemName(new SalesOrderItemNameRequest(control, userid, corpcentre, type));
        call.enqueue(new Callback<SalesOrderItemNameResponse>() {
            @Override
            public void onResponse(Call<SalesOrderItemNameResponse> call, Response<SalesOrderItemNameResponse> response) {
                SalesOrderItemNameResponse getServerResponse = response.body();
                salesOrderView.HideProgress();
                if (response.isSuccessful()) {
                    salesOrderView.SalesOrderItemName(getServerResponse);
                } else {
                    salesOrderView.Error("Try Again", "Something went wrong",5);
                }
            }

            @Override
            public void onFailure(Call<SalesOrderItemNameResponse> call, Throwable t) {
                salesOrderView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",5);
                } else if (t instanceof SocketTimeoutException) {
                    salesOrderView.Error("Timeout Error", "Please, Try again!",5);
                } else {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",5);
                }
            }
        });
    }

    public void ItemRateChangeData(String control, String userid, String corpcentre, String ip,
                                   String para1, String para2, String para3, String para4,
                                   String para5, String para6, String para7, String para8,
                                   String para9, String type) {
        salesOrderView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<SalesOrderNetRateResponse> call = getResponse.GetSalesOrderNetRate(new SalesOrderNetRateRequest(control, userid, corpcentre, ip, para1, para2, para3, para4, para5, para6, para7, para8, para9, type));
        call.enqueue(new Callback<SalesOrderNetRateResponse>() {
            @Override
            public void onResponse(Call<SalesOrderNetRateResponse> call, Response<SalesOrderNetRateResponse> response) {
                SalesOrderNetRateResponse getServerResponse = response.body();
                salesOrderView.HideProgress();
                if (response.isSuccessful()) {
                    salesOrderView.SalesOrderNetRateData(getServerResponse);
                } else {
                    salesOrderView.Error("Try Again", "Something went wrong",6);
                }
            }

            @Override
            public void onFailure(Call<SalesOrderNetRateResponse> call, Throwable t) {
                salesOrderView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",6);
                } else if (t instanceof SocketTimeoutException) {
                    salesOrderView.Error("Timeout Error", "Please, Try again!",6);
                } else {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",6);
                }
            }
        });
    }

    public void saveSalesOrderData(String srno, String area, String party,
                                   String broker, String orderNo, String userId,
                                   String ipaddress, String unit, String corpcentre,
                                   String entryDateTime, String corpcode, String usercode,
                                   String type) {
        salesOrderView.ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<SalesOrderSaveDataResponse> call = getResponse.SalesOrderSaveData(new SalesOrderSaveDataRequest(srno, area, party, broker, orderNo, userId, ipaddress, unit, corpcentre, entryDateTime, corpcode, usercode, type));
        call.enqueue(new Callback<SalesOrderSaveDataResponse>() {
            @Override
            public void onResponse(Call<SalesOrderSaveDataResponse> call, Response<SalesOrderSaveDataResponse> response) {
                SalesOrderSaveDataResponse getServerResponse = response.body();
                salesOrderView.HideProgress();
                if (response.isSuccessful()) {
                    salesOrderView.SaveSalesOrderData(getServerResponse);
                } else {
                    salesOrderView.Error("Try Again", "Something went wrong",7);
                }
            }

            @Override
            public void onFailure(Call<SalesOrderSaveDataResponse> call, Throwable t) {
                salesOrderView.HideProgress();
                if (t instanceof java.net.ConnectException) {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",7);
                } else if (t instanceof SocketTimeoutException) {
                    salesOrderView.Error("Timeout Error", "Please, Try again!",7);
                } else {
                    salesOrderView.Error("Internet Error", "Please, Check your Internet Connection!",7);
                }
            }
        });
    }
}
