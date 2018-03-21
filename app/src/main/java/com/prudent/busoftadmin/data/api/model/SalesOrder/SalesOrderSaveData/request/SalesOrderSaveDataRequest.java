package com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderSaveData.request;

public class SalesOrderSaveDataRequest {

    private String srno;
    private String area;
    private String party;
    private String broker;
    private String orderNo;
    private String userId;
    private String ipaddress;
    private String unit;
    private String corpcentre;
    private String entryDateTime;
    private String corpcode;
    private String usercode;
    private String type;

    public SalesOrderSaveDataRequest(String srno, String area, String party,
                                     String broker, String orderNo, String userId,
                                     String ipaddress, String unit, String corpcentre,
                                     String entryDateTime, String corpcode, String usercode,
                                     String type) {
        this.srno = srno;
        this.area = area;
        this.party = party;
        this.broker = broker;
        this.orderNo = orderNo;
        this.userId = userId;
        this.ipaddress = ipaddress;
        this.unit = unit;
        this.corpcentre = corpcentre;
        this.entryDateTime = entryDateTime;
        this.corpcode = corpcode;
        this.usercode = usercode;
        this.type = type;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCorpcentre() {
        return corpcentre;
    }

    public void setCorpcentre(String corpcentre) {
        this.corpcentre = corpcentre;
    }

    public String getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(String entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}