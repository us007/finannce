package com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderNetRate.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

@SerializedName("description")
@Expose
private String description;
@SerializedName("rate")
@Expose
private double rate;

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public double getRate() {
return rate;
}

public void setRate(double rate) {
this.rate = rate;
}

}