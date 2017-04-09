package com.example.mahe.myapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAHE on 4/8/2017.
 */

public class ItemManager {
    static final String DATABASE_NAME = "item.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table " + "ITEM_TABLE" + "( "
            + "ID" + " integer primary key autoincrement,"
            + "ITEM  text); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;
    public List<String> list;
    public int i=0;
    public ItemManager(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public ItemManager open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(int id,String item) {
        ContentValues newValues = new ContentValues();
        newValues.put("ID", id);
        newValues.put("ITEM", item);
        db.insert("ITEM_TABLE", null, newValues);
    }

    public List<String> getAllEntries(){
        Cursor cursor = db.rawQuery("select * from ITEM_TABLE",null);
        list=new ArrayList<String>();
        if (cursor .moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                String name = cursor.getString(cursor.getColumnIndex("ITEM"));
                list.add(name);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    public void clearTable()
    {
        db.execSQL("delete * from ITEM_TABLE");
    }

    public String getID(String item){
        Cursor cursor = db.rawQuery("select ID from ITEM_TABLE WHERE ITEM='"+item+"'" ,null);
        int val=0;
        String ret=null;
        if(cursor!=null)
        {
         cursor.moveToFirst();
            val=cursor.getInt(0);
        ret=String.valueOf(val);
        }
        //cursor.close();
        return ret;
}
}

