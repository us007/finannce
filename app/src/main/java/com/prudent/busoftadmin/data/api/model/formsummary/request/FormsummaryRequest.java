package com.prudent.busoftadmin.data.api.model.formsummary.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormsummaryRequest {
    public FormsummaryRequest(String userid, String corpcentre, String type) {
        this.userid = userid;
        this.corpcentre = corpcentre;
        this.type = type;
    }

    private String userid;
    private String corpcentre;
    private String type;




    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCorpcentre() {
        return corpcentre;
    }

    public void setCorpcentre(String corpcentre) {
        this.corpcentre = corpcentre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
