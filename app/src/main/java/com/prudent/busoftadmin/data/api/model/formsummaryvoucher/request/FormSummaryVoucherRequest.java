package com.prudent.busoftadmin.data.api.model.formsummaryvoucher.request;

/**
 * Created by AFF41 on 7/24/2017.
 */

public class FormSummaryVoucherRequest {

    private String userid;
    private String corpcentre;
    private String type;
    private String formname;
    private String control;
    private String Para1;
    private String para2;

    public FormSummaryVoucherRequest(String userid, String corpcentre, String type, String formname, String control, String para1, String para2) {
        this.userid = userid;
        this.corpcentre = corpcentre;
        this.type = type;
        this.formname = formname;
        this.control = control;
        Para1 = para1;
        this.para2 = para2;
    }

    public String getPara1() {
        return Para1;
    }

    public void setPara1(String para1) {
        Para1 = para1;
    }

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

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getPara2() {
        return para2;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }
}
