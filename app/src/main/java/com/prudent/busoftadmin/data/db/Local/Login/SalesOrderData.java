package com.prudent.busoftadmin.data.db.Local.Login;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AFF41 on 8/1/2017.
 */

public class SalesOrderData implements Parcelable {
    private String srNo;
    private String sr;
    private String itemXCode;
    private String quantity;
    private String rate;
    private String amount;
    private String itemName;

    public SalesOrderData() {
    }

    public SalesOrderData(String srNo, String sr, String itemXCode, String quantity, String rate, String amount, String itemName) {
        this.srNo = srNo;
        this.sr = sr;
        this.itemXCode = itemXCode;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
        this.itemName = itemName;
    }
//    public SalesOrderData(String srNo, String sr, String itemXCode, String quantity, String rate, String amount) {
//        this.srNo = srNo;
//        this.sr = sr;
//        itemXCode = itemXCode;
//        this.quantity = quantity;
//        this.rate = rate;
//        this.amount = amount;
//    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getItemXCode() {
        return itemXCode;
    }

    public void setItemXCode(String itemXCode) {
        this.itemXCode = itemXCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.srNo);
        dest.writeString(this.sr);
        dest.writeString(this.itemXCode);
        dest.writeString(this.quantity);
        dest.writeString(this.rate);
        dest.writeString(this.amount);
        dest.writeString(this.itemName);
    }

    protected SalesOrderData(Parcel in) {
        this.srNo = in.readString();
        this.sr = in.readString();
        this.itemXCode = in.readString();
        this.quantity = in.readString();
        this.rate = in.readString();
        this.amount = in.readString();
        this.itemName = in.readString();
    }

    public static final Parcelable.Creator<SalesOrderData> CREATOR = new Parcelable.Creator<SalesOrderData>() {
        @Override
        public SalesOrderData createFromParcel(Parcel source) {
            return new SalesOrderData(source);
        }

        @Override
        public SalesOrderData[] newArray(int size) {
            return new SalesOrderData[size];
        }
    };
}
