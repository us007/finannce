
package com.prudent.busoftadmin.data.api.model.CheckReceiptBase.Response;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ChequeReceiptBaseResponse {

    @SerializedName("table")
    private List<Table> mTable;

    public List<Table> getTable() {
        return mTable;
    }

    public void setTable(List<Table> table) {
        mTable = table;
    }

}
