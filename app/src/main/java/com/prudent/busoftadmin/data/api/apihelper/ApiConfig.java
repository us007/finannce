package com.prudent.busoftadmin.data.api.apihelper;

import com.google.gson.JsonElement;
import com.prudent.busoftadmin.data.api.model.Calender.Request.CalenderRequest;
import com.prudent.busoftadmin.data.api.model.Calender.Response.CalenderResponse;
import com.prudent.busoftadmin.data.api.model.CheckReceiptAmountCalc.Request.CheckReceiptAmountRequest;
import com.prudent.busoftadmin.data.api.model.CheckReceiptAmountCalc.Response.ChequeReceiptAmountResponse;
import com.prudent.busoftadmin.data.api.model.CheckReceiptBase.Request.ChequeReceiptBaseRequest;
import com.prudent.busoftadmin.data.api.model.CheckReceiptBase.Response.ChequeReceiptBaseResponse;
import com.prudent.busoftadmin.data.api.model.CheckReceiptSave.Request.CheckReceiptSaveRequest;
import com.prudent.busoftadmin.data.api.model.CheckReceiptSave.Response.CheckReceiptSaveResponse;
import com.prudent.busoftadmin.data.api.model.DashboardData.Request.DashboardDataRequest;
import com.prudent.busoftadmin.data.api.model.DashboardData.Response.DashboardDataResponse;
import com.prudent.busoftadmin.data.api.model.DetailUploadDocument.Request.DetailUploadDocumentRequest;
import com.prudent.busoftadmin.data.api.model.DetailUploadDocument.Response.DetailUploadDocumentResponse;
import com.prudent.busoftadmin.data.api.model.FormSummarySaveData.request.FormSummarySaveRequest;
import com.prudent.busoftadmin.data.api.model.FormSummarySaveData.response.FormSummarySaveResponse;
import com.prudent.busoftadmin.data.api.model.Login.Request.LoginRequest;
import com.prudent.busoftadmin.data.api.model.Login.Response.LoginResponse;
import com.prudent.busoftadmin.data.api.model.Logout.Request.LogoutRequest;
import com.prudent.busoftadmin.data.api.model.Logout.Response.LogoutResponse;
import com.prudent.busoftadmin.data.api.model.Report2Detail.Request.Report2DetailResponse;
import com.prudent.busoftadmin.data.api.model.ReportDetail.Request.ReportDetailRequest;
import com.prudent.busoftadmin.data.api.model.ReportDetail.Response.ReportDetailResponse;
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
import com.prudent.busoftadmin.data.api.model.ShowDocument.Request.ShowDocumentRequest;
import com.prudent.busoftadmin.data.api.model.ShowDocument.Response.ShowDocumentResponse;
import com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Request.TranscationUploadDocumentRequest;
import com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Response.TranscationUploadDocumentResponse;
import com.prudent.busoftadmin.data.api.model.UploadDocument.Request.UploadDocumentRequest;
import com.prudent.busoftadmin.data.api.model.UploadDocument.Response.UploadDocumentResponse;
import com.prudent.busoftadmin.data.api.model.formsummary.request.FormsummaryRequest;
import com.prudent.busoftadmin.data.api.model.formsummary.response.FormSummaryResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryCrAccount.request.FormSummaryCrAccountRequest;
import com.prudent.busoftadmin.data.api.model.formsummaryCrAccount.response.FormSummaryCrAccountResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryDrAccount.request.FormSummaryDrAccountRequest;
import com.prudent.busoftadmin.data.api.model.formsummaryDrAccount.response.FormSummaryDrAccountResponse;
import com.prudent.busoftadmin.data.api.model.formsummaryvoucher.request.FormSummaryVoucherRequest;
import com.prudent.busoftadmin.data.api.model.formsummaryvoucher.response.FormSummaryVoucherResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Dharmik Patel on 19-May-17.
 */

public interface ApiConfig {

    // Login
    @POST("api/LoginIDocs")
    Call<LoginResponse> Login(@Body LoginRequest body);

    // Logout
    @POST("api/LoginIDocs")
    Call<LogoutResponse> Logout(@Body LogoutRequest body);

    // Transaction Dropdown
    @POST("api/UploadDocument")
    Call<TranscationUploadDocumentResponse> LoadTranscation(@Body TranscationUploadDocumentRequest body);

    // Detail Dropdown
    @POST("api/UploadDocument")
    Call<DetailUploadDocumentResponse> LoadDetail(@Body DetailUploadDocumentRequest body);

    // ShowDocument
    @POST("api/UploadDocument")
    Call<ShowDocumentResponse> ShowDocument(@Body ShowDocumentRequest body);

    // UploadDocument
    @POST("api/SaveDocumentUpload")
    Call<UploadDocumentResponse> UploadDocument(@Body UploadDocumentRequest body);

    // Calender
    @POST("api/ScheduledTask")
    Call<CalenderResponse> Calender(@Body CalenderRequest body);

    // Calender
    @POST("api/DashboardReport")
    Call<DashboardDataResponse> GetDashboard(@Body DashboardDataRequest body);

    @POST("api/DashboardReport")
    Call<ReportDetailResponse> GetReportDetail(@Body ReportDetailRequest body);

    @POST("api/DashboardReport")
    Call<JsonElement> GetReport2Detail(@Body Report2DetailResponse body);

    @POST("api/ChequeReceipt")
    Call<ChequeReceiptBaseResponse> GetChequeReceiptBase(@Body ChequeReceiptBaseRequest body);

    @POST("api/ChequeReceipt")
    Call<ChequeReceiptAmountResponse> GetChequeReceiptAmount(@Body CheckReceiptAmountRequest body);

    @POST("api/SaveChequeReceipt")
    Call<CheckReceiptSaveResponse> SaveChequeReceiptAmount(@Body CheckReceiptSaveRequest body);

    @POST("api/FormSummary")
    Call<FormSummaryResponse> FormSummary(@Body FormsummaryRequest body);


    @POST("api/FormSummary")
    Call<FormSummaryVoucherResponse> FormSummaryVoucher(@Body FormSummaryVoucherRequest body);

    @POST("api/FormSummary")
    Call<FormSummaryDrAccountResponse> FormSummaryDrAccount(@Body FormSummaryDrAccountRequest body);

    @POST("api/FormSummary")
    Call<FormSummaryCrAccountResponse> FormSummaryCreditAccount(@Body FormSummaryCrAccountRequest body);

    @POST("api/SummaryAccount")
    Call<FormSummarySaveResponse> FormSummarySaveData(@Body FormSummarySaveRequest body);

    @POST("api/FormSalesOrder")
    Call<SalesOrderResponse> GetSalesOrderCostCenter(@Body SalesOrderRequest body);

    @POST("api/FormSalesOrder")
    Call<SalesOrderPartyNameResponse> GetSalesPartyName(@Body SalesOrderPartyRequest body);

    @POST("api/FormSalesOrder")
    Call<SalesOrderBrokerResponse> GetSalesOrderBroker(@Body SalesOrderBrokerRequest body);

    @POST("api/FormSalesOrder")
    Call<SalesOrderItemNameResponse> GetSalesOrderItemName(@Body SalesOrderItemNameRequest body);

    @POST("api/FormSalesOrder")
    Call<SalesOrderNetRateResponse> GetSalesOrderNetRate(@Body SalesOrderNetRateRequest body);

    @POST("api/SalesOrder")
    Call<SalesOrderSaveDataResponse> SalesOrderSaveData(@Body SalesOrderSaveDataRequest body);

}
