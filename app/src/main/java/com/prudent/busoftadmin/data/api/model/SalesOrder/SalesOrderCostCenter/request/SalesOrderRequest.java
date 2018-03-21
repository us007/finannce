package com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderCostCenter.request;

public class SalesOrderRequest {

    private String control;

    private String userid;

    private String corpcentre;

    private String para1;

    private String para2;

    private String para3;

    private String para4;

    private String type;

    public SalesOrderRequest(String control, String userid, String corpcentre, String para1, String para2, String para3, String para4, String type) {
        this.control = control;
        this.userid = userid;
        this.corpcentre = corpcentre;
        this.para1 = para1;
        this.para2 = para2;
        this.para3 = para3;
        this.para4 = para4;
        this.type = type;
    }

    public SalesOrderRequest(String control, String userid, String corpcentre, String para1, String para2, String para3, String type) {
        this.control = control;
        this.userid = userid;
        this.corpcentre = corpcentre;
        this.para1 = para1;
        this.para2 = para2;
        this.para3 = para3;
        this.type = type;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
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

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public String getPara2() {
        return para2;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }

    public String getPara3() {
        return para3;
    }

    public void setPara3(String para3) {
        this.para3 = para3;
    }

    public String getPara4() {
        return para4;
    }

    public void setPara4(String para4) {
        this.para4 = para4;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}