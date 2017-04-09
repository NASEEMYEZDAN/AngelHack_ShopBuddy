package com.example.mahe.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by MAHE on 4/9/2017.
 */

public class ShopManager {
    static final String DATABASE_NAME = "shop.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table " + "SHOP_TABLE" + "( "
            + "ID" + " integer primary key autoincrement,"
            + "SHOP  text); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;
    public ShopManager(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public ShopManager open() throws SQLException {
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

    public void insertEntry(int id,String item) {
        ContentValues newValues = new ContentValues();
        newValues.put("ID", id);
        newValues.put("SHOP", item);
        db.insert("SHOP_TABLE", null, newValues);
    }

    public String getID(String item){
        Cursor cursor1 = db.rawQuery("select ID from SHOP_TABLE WHERE SHOP='"+item+"'" ,null);
        int val=0;
        String ret=null;
        if(cursor1!=null)
        {
            cursor1.moveToFirst();
            val=cursor1.getInt(0);
            ret=String.valueOf(val);
        }
        //cursor.close();
        return ret;
    }
}
