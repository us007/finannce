package com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderSaveData.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

@SerializedName("success")
@Expose
private int success;
@SerializedName("message")
@Expose
private String message;

public int getSuccess() {
return success;
}

public void setSuccess(int success) {
this.success = success;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}