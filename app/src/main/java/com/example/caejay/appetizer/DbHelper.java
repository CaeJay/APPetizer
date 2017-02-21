package com.example.caejay.appetizer;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import android.content.ContentValues;

/**
 * Created by caesar on 10/20/16.
 */

public class DbHelper extends SQLiteOpenHelper {
    // Creates new database or opens existing database
    private static final String DATABASE_NAME = "Restaurant.DB";
    private static final String TABLE_NAME = "TableRec";
    private static final int DATABASE_VERSION = 5;
    private static final String CREATE_QUERY="";




    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created / opened...");
    }

    //create db tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table TABLES (TNAME VARCHAR(15) PRIMARY KEY)");
        db.execSQL("create table ORDERS (ONUMBER INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "TNAME VARCHAR, ITEM VARCHAR(15), CUSTOM VARCAR(15),FOREIGN KEY (TNAME) REFERENCES TABLES)");
        db.execSQL("create table TODOS (TNUMBER INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "TNAME VARCHAR, TODO VARCHAR(15), FOREIGN KEY (TNAME) REFERENCES TABLES)");

    }

    //when creating new tablw
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+"ORDERS");
        db.execSQL("DROP TABLE IF EXISTS "+"TABLES");
        db.execSQL("DROP TABLE IF EXISTS "+"TODOS");


        onCreate(db);
    }

    //add restaurant table to database
    public void addTable(String tnames) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TNAME",tnames);


         db.insert("TABLES",null ,contentValues);

    }
    public void deleteOrder(Integer oNumber)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("ORDERS", "ONUMBER =" + Integer.toString(oNumber), null) ;

    }

    //add order to database
    public void addOrder(String tnames, String item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("TNAME",tnames);
        contentValues.put("ITEM",item);
        contentValues.put("CUSTOM","Custom Description");

        db.insert("ORDERS",null ,contentValues);

    }

    //update custom in order
    public void customOrder(String custom,Integer oNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        // custom+=Integer.toString(oNumber);
        ContentValues contentValues = new ContentValues();
        contentValues.put("CUSTOM",custom);
        db.update("ORDERS",contentValues,"ONUMBER="+Integer.toString(oNumber),null);

        // db.update("ORDERS",contentValues,"ONUMBER="+Integer.toString(oNumber),null);

    }


    //add todo to database
    public void addTodo(String tnames, String todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TNAME",tnames);
        contentValues.put("TODO",todo);


        db.insert("TODOS",null ,contentValues);

    }
    public void deleteTodo(String oNumber)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TODOS", "TNUMBER =" + oNumber, null) ;

    }



    //get table info
    public Cursor getTableInfo(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {"TNAME"};

        cursor = db.query("TABLES", projections, null, null, null, null,"TNAME");

        return cursor;
    }
    //get order info
    public Cursor getOrderInfo(SQLiteDatabase db,String tname) {
        Cursor cursor;
        String[] projections = {"ONUMBER","TNAME","ITEM","CUSTOM"};
        String[] whereArgs={tname};

        cursor = db.query("ORDERS", projections, "TNAME = ?", whereArgs, null, null,null);
        return cursor;


    }
    //get todos
    public Cursor getTodo(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {"TNUMBER","TNAME","TODO"};

        cursor = db.query("TODOS", projections, null, null, null, null,"TNAME");

        return cursor;


    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+"ORDERS",null);
        return res;
    }
    public void removeAll(SQLiteDatabase db)
    {
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        //db = helper.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete("ORDERS", null, null);
        //db.delete(DatabaseHelper.TAB_USERS_GROUP, null, null);
    }

    /*public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }*/

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});

    }
}
