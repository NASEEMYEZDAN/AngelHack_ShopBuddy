package com.example.mahe.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by MAHE on 4/9/2017.
 */

public class ItemAndShopManager {
    static final String DATABASE_NAME = "item_shop.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table " + "ITEM_SHOP_TABLE" + "( "
            + "ITEM_ID" + " integer ,"+"SHOP_ID" + " integer ,"
            + "PRICE  integer); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;
    public int[] shop,price;
    int i=0;
    public ItemAndShopManager(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public ItemAndShopManager open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void clearTable()
    {
        db.execSQL("delete * from SHOP_TABLE");
    }

    public void insertEntry(int item,int shop,int price) {
        ContentValues newValues = new ContentValues();
        newValues.put("ITEM_ID", item);
        newValues.put("SHOP_ID", shop);
        newValues.put("PRICE", price);
        db.insert("ITEM_SHOP_TABLE", null, newValues);
    }




    public int[] getPrices(long id){
        Cursor cursor = db.rawQuery("select SHOP_ID,PRICE from ITEM_SHOP_TABLE WHERE ITEM_ID='"+id+"'" ,null);
        /*int s;
        price=new int[5];
        for(int i=0;i<5;i++) price[i]=0;*/
        price=new int[6];
        shop=new int[6];
        for(int i=1;i<6;i++) price[i]=0;
        int s;
        if (cursor.moveToFirst()) {
            do {
                s=cursor.getInt(0);
                price[s] += cursor.getInt(1);
                //Log.d("hello",price[s]+"");
              /*  s=cursor.getInt(0);
                price[s]=cursor.getInt(1);
               */
            } while (cursor.moveToNext());
        }
        return price;
    }
}
