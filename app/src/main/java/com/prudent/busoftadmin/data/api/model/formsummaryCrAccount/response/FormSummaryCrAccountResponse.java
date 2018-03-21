package com.prudent.busoftadmin.data.api.model.formsummaryCrAccount.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prudent.busoftadmin.data.api.model.formsummaryDrAccount.response.*;


import java.util.ArrayList;

/**
 * Created by AFF41 on 7/24/2017.
 */

public class FormSummaryCrAccountResponse {
    @SerializedName("table")
    @Expose
    private ArrayList<Table> table = null;

    public ArrayList<Table> getTable() {
        return table;
    }

    public void setTable(ArrayList<Table> table) {
        this.table = table;
    }
}
