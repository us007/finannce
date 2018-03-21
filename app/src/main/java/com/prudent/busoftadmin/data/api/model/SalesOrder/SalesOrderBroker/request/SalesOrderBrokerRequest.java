package com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderBroker.request;

/**
 * Created by AFF41 on 7/29/2017.
 */

public class SalesOrderBrokerRequest {
    private String control;
    private String userid;
    private String corpcentre;
    private String type;

    public SalesOrderBrokerRequest(String control, String userid, String corpcentre, String type) {
        this.control = control;
        this.userid = userid;
        this.corpcentre = corpcentre;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
