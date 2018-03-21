package com.prudent.busoftadmin.data.api.model.FormSummarySaveData.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AFF41 on 7/26/2017.
 */

public class FormSummarySaveResponse {
    @SerializedName("table")
    @Expose
    private ArrayList<TableSave> table = null;

    public ArrayList<TableSave> getTable() {
        return table;
    }

    public void setTable(ArrayList<TableSave> table) {
        this.table = table;
    }
}
