package com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderSaveData.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SalesOrderSaveDataResponse {

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