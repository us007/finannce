package com.prudent.busoftadmin.ui.salesorder;

import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderBroker.response.SalesOrderBrokerResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderCostCenter.response.SalesOrderResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderItemName.response.SalesOrderItemNameResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderNetRate.response.SalesOrderNetRateResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderPartyName.response.SalesOrderPartyNameResponse;
import com.prudent.busoftadmin.data.api.model.SalesOrder.SalesOrderSaveData.response.SalesOrderSaveDataResponse;
import com.prudent.busoftadmin.ui.base.View;

/**
 * Created by AFF41 on 7/25/2017.
 */

public interface SalesOrderView extends View {
    void ShowProgress();

    void HideProgress();

    void Error(String Title, String Message,int ref);

    void SalesOrderCostCenter(SalesOrderResponse salesOrderResponse);

    void SalesOrderPartyName(SalesOrderPartyNameResponse salesOrderPartyNameResponse);

    void SalesOrderBroker(SalesOrderBrokerResponse salesOrderBrokerResponse);

    void SalesOrderItemName(SalesOrderItemNameResponse salesOrderItemNameResponse);

    void SalesOrderPartyNameChangeData(SalesOrderResponse salesOrderResponse);

    void SalesOrderNetRateData(SalesOrderNetRateResponse salesOrderNetRateResponse);

    void SaveSalesOrderData(SalesOrderSaveDataResponse salesOrderSaveDataResponse);
}
