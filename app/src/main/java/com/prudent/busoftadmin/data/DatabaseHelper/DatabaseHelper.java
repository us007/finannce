package com.prudent.busoftadmin.data.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.prudent.busoftadmin.data.db.Local.Login.SalesOrderData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Utsav on 8/2/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sales.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SALES_ORDER = "salesorder";
    private static final String SALES_SRNO = "Srno";
    private static final String SALES_SR = "sr";
    private static final String SALES_ITEMXCODE = "ItemXCode";
    private static final String SALES_QUANTY = "Qty";
    private static final String SALES_RATE = "Rate";
    private static final String SALES_AMOUNT = "Amount";
    private static final String SALES_ITEMNAME = "ItemName";

    private static final String CREATE_TABLE_SALES = "" +
            "create table " + TABLE_SALES_ORDER + " (" +
            SALES_SRNO + " TEXT, " +
            SALES_SR + " TEXT, " +
            SALES_ITEMXCODE + " TEXT, " +
            SALES_ITEMNAME + " TEXT, " +
            SALES_QUANTY + " TEXT, " +
            SALES_RATE + " TEXT, " +
            SALES_AMOUNT + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SALES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SALES);
        onCreate(db);
    }

    public void inserData(SalesOrderData salesOrderData) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SALES_AMOUNT, salesOrderData.getAmount());
        contentValues.put(SALES_RATE, salesOrderData.getRate());
        contentValues.put(SALES_QUANTY, salesOrderData.getQuantity());
        contentValues.put(SALES_ITEMNAME, salesOrderData.getItemName());
        contentValues.put(SALES_ITEMXCODE, salesOrderData.getItemXCode());
        contentValues.put(SALES_SR, salesOrderData.getSr());
        contentValues.put(SALES_SRNO, salesOrderData.getSrNo());
        database.insert(TABLE_SALES_ORDER, null, contentValues);
        database.close();
    }

    public ArrayList<SalesOrderData> getAllData() {
        ArrayList<SalesOrderData> expanseArrayList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from " + TABLE_SALES_ORDER, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            try {
                SalesOrderData salesOrderData = new SalesOrderData();
                salesOrderData.setAmount(cursor.getString(6));
                salesOrderData.setRate(cursor.getString(5));
                salesOrderData.setQuantity(cursor.getString(4));
                salesOrderData.setItemName(cursor.getString(3));
                salesOrderData.setItemXCode(cursor.getString(2));
                salesOrderData.setSr(String.valueOf(cursor.getInt(1)));
                salesOrderData.setSrNo(cursor.getString(0));

                expanseArrayList.add(salesOrderData);
            }catch (Exception e){
                Log.d("SALESORDER   ",e.getLocalizedMessage());}
        } while (cursor.moveToNext());
        cursor.close();
        database.close();
        return expanseArrayList;
    }

    public long getSalesOrderCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db, TABLE_SALES_ORDER);
        db.close();
        return cnt;
    }

    public void removeSingleSalesData(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_SALES_ORDER + " WHERE " + SALES_SR + "= '" + id + "'");
        database.close();
    }

    public void deleteAllData() {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_SALES_ORDER);
        database.close();
    }


}
