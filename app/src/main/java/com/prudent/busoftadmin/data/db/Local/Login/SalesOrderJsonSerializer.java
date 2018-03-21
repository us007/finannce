package com.prudent.busoftadmin.data.db.Local.Login;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class SalesOrderJsonSerializer implements JsonSerializer<SalesOrderData> {


    @Override
    public JsonElement serialize(SalesOrderData src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.add("Srno", context.serialize(src.getSrNo()));
        object.add("Sr", context.serialize(src.getSr()));
        object.add("ItemName", context.serialize(src.getItemXCode()));
        object.add("Qty", context.serialize(src.getQuantity()));
        object.add("Rate", context.serialize(src.getRate()));
        object.add("Amount", context.serialize(src.getAmount()));

        return object;
    }
}