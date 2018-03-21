package com.prudent.busoftadmin.data.api.model.formsummaryDrAccount.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AFF41 on 7/24/2017.
 */

public class Table {
    @SerializedName("control")
    @Expose
    private String control;
    @SerializedName("xcode")
    @Expose
    private String xcode;
    @SerializedName("xname")
    @Expose
    private String xname;

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getXcode() {
        return xcode;
    }

    public void setXcode(String xcode) {
        this.xcode = xcode;
    }

    public String getXname() {
        return xname;
    }

    public void setXname(String xname) {
        this.xname = xname;
    }

}
