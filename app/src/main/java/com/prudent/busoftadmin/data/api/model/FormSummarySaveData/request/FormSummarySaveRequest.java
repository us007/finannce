package com.prudent.busoftadmin.data.api.model.FormSummarySaveData.request;

/**
 * Created by AFF41 on 7/26/2017.
 */

public class FormSummarySaveRequest {
    private String srno;
    private String voucher;
    private String date;
    private String voucherNo;
    private String instumentNo;
    private String drAccount;
    private String crAccount;
    private String accountType;
    private String grossAmount;
    private String taxRate;
    private String tax;
    private String totalAmount;
    private String remarks;
    private String isRcm;
    private String userId;
    private String ipaddress;
    private String unit;
    private String corpcentre;
    private String entrydatetime;

    public FormSummarySaveRequest(String srno, String voucher, String date, String voucherNo, String instumentNo, String drAccount, String crAccount, String accountType, String grossAmount, String taxRate, String tax, String totalAmount, String remarks, String isRcm, String userId, String ipaddress, String unit, String corpcentre, String entrydatetime) {
        this.srno = srno;
        this.voucher = voucher;
        this.date = date;
        this.voucherNo = voucherNo;
        this.instumentNo = instumentNo;
        this.drAccount = drAccount;
        this.crAccount = crAccount;
        this.accountType = accountType;
        this.grossAmount = grossAmount;
        this.taxRate = taxRate;
        this.tax = tax;
        this.totalAmount = totalAmount;
        this.remarks = remarks;
        this.isRcm = isRcm;
        this.userId = userId;
        this.ipaddress = ipaddress;
        this.unit = unit;
        this.corpcentre = corpcentre;
        this.entrydatetime = entrydatetime;
    }
}
