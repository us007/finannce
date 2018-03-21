package com.prudent.busoftadmin.data.api.model.formsummaryDrAccount.request;

/**
 * Created by AFF41 on 7/24/2017.
 */

public class FormSummaryDrAccountRequest {
    private String userid;
    private String corpcentre;
    private String type;
    private String formname;
    private String control;
    private String Para1;
    private String para2;
    private String Para3;
    private String Para4;
    private String Para5;
    private String Para6;

    public FormSummaryDrAccountRequest(String userid, String corpcentre, String type, String formname, String control, String para1, String para2, String para3, String para4, String para5, String para6) {
        this.userid = userid;
        this.corpcentre = corpcentre;
        this.type = type;
        this.formname = formname;
        this.control = control;
        Para1 = para1;
        this.para2 = para2;
        Para3 = para3;
        Para4 = para4;
        Para5 = para5;
        Para6 = para6;
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

    public String getPara3() {
        return Para3;
    }

    public void setPara3(String para3) {
        Para3 = para3;
    }

    public String getPara4() {
        return Para4;
    }

    public void setPara4(String para4) {
        Para4 = para4;
    }

    public String getPara5() {
        return Para5;
    }

    public void setPara5(String para5) {
        Para5 = para5;
    }

    public String getPara6() {
        return Para6;
    }

    public void setPara6(String para6) {
        Para6 = para6;
    }
}
