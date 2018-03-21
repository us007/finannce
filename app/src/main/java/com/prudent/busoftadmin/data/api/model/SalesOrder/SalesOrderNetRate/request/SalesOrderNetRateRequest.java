package com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderNetRate.request;

public class SalesOrderNetRateRequest {

    private String control;
    private String userid;
    private String corpcentre;
    private String ip;
    private String para1;
    private String para2;
    private String para3;
    private String para4;
    private String para5;
    private String para6;
    private String para7;
    private String para8;
    private String para9;
    private String type;

    public SalesOrderNetRateRequest(String control, String userid, String corpcentre, String ip,
                                    String para1, String para2, String para3, String para4,
                                    String para5, String para6, String para7, String para8,
                                    String para9, String type) {
        this.control = control;
        this.userid = userid;
        this.corpcentre = corpcentre;
        this.ip = ip;
        this.para1 = para1;
        this.para2 = para2;
        this.para3 = para3;
        this.para4 = para4;
        this.para5 = para5;
        this.para6 = para6;
        this.para7 = para7;
        this.para8 = para8;
        this.para9 = para9;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getPara5() {
        return para5;
    }

    public void setPara5(String para5) {
        this.para5 = para5;
    }

    public String getPara6() {
        return para6;
    }

    public void setPara6(String para6) {
        this.para6 = para6;
    }

    public String getPara7() {
        return para7;
    }

    public void setPara7(String para7) {
        this.para7 = para7;
    }

    public String getPara8() {
        return para8;
    }

    public void setPara8(String para8) {
        this.para8 = para8;
    }

    public String getPara9() {
        return para9;
    }

    public void setPara9(String para9) {
        this.para9 = para9;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}