package com.example.onlineshopping.DateBaseSql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CategoryItemsDataBase extends SQLiteOpenHelper {

    private static String databaseName = "CategoryDatabase";
    SQLiteDatabase HelperDataBase;

    public CategoryItemsDataBase(@Nullable Context context) {
        super(context, databaseName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table categories(id integer primary key," +
                " categoryname text not null , categoryicon integer not null , categorybackground integer not null)");

        db.execSQL("Create table itemes(id integer primary key," +
                "itemname text not null ,itemdescription text not null, itemprice integer not null ," +
                " itempicture integer not null, itembackground integer not null ,itemCount integer not null,categorytype text not null," +
                "barcode text not null)");

        db.execSQL("Create table cartitems(id integer primary key," +
                "itemnamec text not null ,itemdescriptionc text not null, itempricec integer not null ," +
                " itempicturec integer not null, itembackgroundc integer not null ,itemCountc integer not null,categorytype text not null," +
                "barcode text not null,username text not null)");


        db.execSQL("Create table Feedback(id integer primary key," +
                " itemname text not null ,yourfeedback text not null,username text not null)");


        db.execSQL("Create table Reports(id integer primary key," +
                " username text not null, itemname text not null ,date text not null , time text not null)");

        db.execSQL("Create table bestselling(id integer primary key," +
                " itemname text not null, itemcount integer not null)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists categories");
        sqLiteDatabase.execSQL("drop table if exists itemes");
        sqLiteDatabase.execSQL("drop table if exists cartitems");
        sqLiteDatabase.execSQL("drop table if exists Feedback");
        sqLiteDatabase.execSQL("drop table if exists Reports");
        sqLiteDatabase.execSQL("drop table if exists bestselling");
    }


//Category
    public void CreateCategory(String categoryname , int categoryicon,int categorybackground){
        ContentValues catrow = new ContentValues();
        catrow.put("categoryname",categoryname);
        catrow.put("categoryicon",categoryicon);
        catrow.put("categorybackground",categorybackground);
        HelperDataBase = getWritableDatabase();
        HelperDataBase.insert("categories",null,catrow);
        HelperDataBase.close();
    }

    public void Delete_category(String name){
        HelperDataBase=getReadableDatabase();
        String[] arg={name};
        Cursor cursor = HelperDataBase.rawQuery("delete from categories where categoryname like ?",arg);
        cursor.moveToFirst();
        HelperDataBase.close();
    }

    public int get_category_id(String name){
        HelperDataBase=getReadableDatabase();
        String[] arg={name};
        Cursor cursor = HelperDataBase.rawQuery("Select id from categories where categoryname like ?",arg);
        cursor.moveToFirst();
        HelperDataBase.close();
        return cursor.getInt(0);
    }

    public void Update_Category_item(int id ,String categoryname , int categoryicon,int categorybackground){
        HelperDataBase=getReadableDatabase();
        String[] arg={id+""};
        ContentValues catemrow = new ContentValues();
        catemrow.put("categoryname",categoryname);
        catemrow.put("categoryicon",categoryicon);
        catemrow.put("categorybackground",categorybackground);
        HelperDataBase = getWritableDatabase();
        HelperDataBase.update("categories",catemrow,"id like?",arg);
        HelperDataBase.close();
    }

    public boolean checkCategory(){
        HelperDataBase = getReadableDatabase();
        String count = "Select count(*) From categories";
        Cursor cursor = HelperDataBase.rawQuery(count,null);
        cursor.moveToFirst();
        int cou = cursor.getInt(0);
        if (cou>0)
            return false;
        else
            return true;
    }

    public Cursor fetchAllCategories(){
        HelperDataBase = getReadableDatabase();
        String[] rowDetails={"categoryname","categoryicon","categorybackground"};
        Cursor cursor =HelperDataBase.query("categories",rowDetails,null,null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        HelperDataBase.close();
        return cursor;
    }



//Items
    public void CreateItem(String itemname , String itemdescription, int itemprice, int itempicture ,int itembackground,int itemCount ,String categorytype, String barcode){
        ContentValues itemrow = new ContentValues();
        itemrow.put("itemname",itemname);
        itemrow.put("itemdescription",itemdescription);
        itemrow.put("itemprice",itemprice);
        itemrow.put("itempicture",itempicture);
        itemrow.put("itembackground",itembackground);
        itemrow.put("itemCount",itemCount);
        itemrow.put("categorytype",categorytype);
        itemrow.put("barcode",barcode);
        HelperDataBase = getWritableDatabase();
        HelperDataBase.insert("itemes",null,itemrow);
        HelperDataBase.close();
    }

    public Cursor fetchAllItems(){
        HelperDataBase = getReadableDatabase();
        String[] rowDetails={"itemname","itemdescription","itemprice","itempicture","itembackground","itemCount","categorytype","barcode"};
        Cursor cursor =HelperDataBase.query("itemes",rowDetails,null,null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        HelperDataBase.close();
        return cursor;
    }

    public int get_item_id(String name){
        HelperDataBase=getReadableDatabase();
        String[] arg={name};
        Cursor cursor = HelperDataBase.rawQuery("Select id from itemes where itemname like ?",arg);
        cursor.moveToFirst();
        HelperDataBase.close();
        return cursor.getInt(0);
    }

    public void Update_item_Admin(int id ,String itemname , String itemdescription, int itemprice, int itempicture ,int itembackground,int itemCount ,String categorytype,String barcode){
        HelperDataBase=getReadableDatabase();
        String[] arg={id+""};
        ContentValues itemrow = new ContentValues();
        itemrow.put("itemname",itemname);
        itemrow.put("itemdescription",itemdescription);
        itemrow.put("itemprice",itemprice);
        itemrow.put("itempicture",itempicture);
        itemrow.put("itembackground",itembackground);
        itemrow.put("itemCount",itemCount);
        itemrow.put("categorytype",categorytype);
        itemrow.put("barcode",barcode);
        HelperDataBase = getWritableDatabase();
        HelperDataBase.update("itemes",itemrow,"id like?",arg);
        HelperDataBase.close();
    }

    public boolean checkItem(){
        HelperDataBase = getReadableDatabase();
        String count = "Select count(*) From itemes";
        Cursor cursor = HelperDataBase.rawQuery(count,null);
        cursor.moveToFirst();
        int cou = cursor.getInt(0);
        if (cou>0)
            return false;
        else
            return true;
    }

    public void Delete_item(String name){
        HelperDataBase=getReadableDatabase();
        String[] arg={name};
        Cursor cursor = HelperDataBase.rawQuery("delete from itemes where itemname like ?",arg);
        cursor.moveToFirst();
        HelperDataBase.close();
    }

    public void Update_count_item(String itemname , String itemdescription, int itemprice, int itempicture ,int itembackground,int itemCount ,String categorytype,String barcode){
        HelperDataBase=getReadableDatabase();
        String[] arg={itemname};
        ContentValues itemrow = new ContentValues();
        itemrow.put("itemname",itemname);
        itemrow.put("itemdescription",itemdescription);
        itemrow.put("itemprice",itemprice);
        itemrow.put("itempicture",itempicture);
        itemrow.put("itembackground",itembackground);
        itemrow.put("itemCount",itemCount);
        itemrow.put("categorytype",categorytype);
        itemrow.put("barcode",barcode);
        HelperDataBase = getWritableDatabase();
        HelperDataBase.update("itemes",itemrow,"itemname like?",arg);
        HelperDataBase.close();
    }



//cart functions
    public void CreateCartItem(String itemnamec , String itemdescriptionc, int itempricec, int itempicturec ,int itembackgroundc,int itemCountc,String categorytype, String barcode, String username ){
        ContentValues itemrow = new ContentValues();
        itemrow.put("itemnamec",itemnamec);
        itemrow.put("itemdescriptionc",itemdescriptionc);
        itemrow.put("itempricec",itempricec);
        itemrow.put("itempicturec",itempicturec);
        itemrow.put("itembackgroundc",itembackgroundc);
        itemrow.put("itemCountc",itemCountc);
        itemrow.put("categorytype",categorytype);
        itemrow.put("barcode",barcode);
        itemrow.put("username",username);
        HelperDataBase = getWritableDatabase();
        HelperDataBase.insert("cartitems",null,itemrow);
        HelperDataBase.close();
    }

    public Cursor fetchAllCartItems(){
        HelperDataBase = getReadableDatabase();
        String[] rowDetails={"itemnamec","itemdescriptionc","itempricec","itempicturec","itembackgroundc","itemCountc","categorytype","barcode","username"};
        Cursor cursor =HelperDataBase.query("cartitems",rowDetails,null,null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        HelperDataBase.close();
        return cursor;
    }

    public void Update_count_Cart(String itemnamec , String itemdescriptionc, int itempricec, int itempicturec ,int itembackgroundc,int itemCountc,String categorytype,String barcode,String username){
        HelperDataBase=getReadableDatabase();
        String[] arg={itemnamec};
        ContentValues itemrow = new ContentValues();
        itemrow.put("itemnamec",itemnamec);
        itemrow.put("itemdescriptionc",itemdescriptionc);
        itemrow.put("itempricec",itempricec);
        itemrow.put("itempicturec",itempicturec);
        itemrow.put("itembackgroundc",itembackgroundc);
        itemrow.put("itemCountc",itemCountc);
        itemrow.put("categorytype",categorytype);
        itemrow.put("barcode",barcode);
        itemrow.put("username",username);

        HelperDataBase = getWritableDatabase();
        HelperDataBase.update("cartitems",itemrow,"itemnamec like?",arg);
        HelperDataBase.close();
    }

    public void Delete_item_from(String name){
        HelperDataBase=getReadableDatabase();
        String[] arg={name};
        Cursor cursor = HelperDataBase.rawQuery("delete from cartitems where itemnamec like ?",arg);
        cursor.moveToFirst();
        HelperDataBase.close();
    }


    public void DeleteAllCart(){
        HelperDataBase=getReadableDatabase();
        String[] arg={};
        Cursor cursor = HelperDataBase.rawQuery("delete from cartitems",arg);
        cursor.moveToFirst();
        HelperDataBase.close();
    }


//feedback
    public void Create_feedback(String itemname , String Feedback , String username){
        ContentValues row =new ContentValues();
        row.put("itemname",itemname);
        row.put("yourfeedback",Feedback);
        row.put("username",username);
        HelperDataBase = getWritableDatabase();
        HelperDataBase.insert("Feedback",null,row);
        HelperDataBase.close();
    }

    public Cursor fetchAllFeedback(){
        HelperDataBase = getReadableDatabase();
        String[] rowDetails={"itemname","yourfeedback","username"};
        Cursor cursor =HelperDataBase.query("Feedback",rowDetails,null,null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        HelperDataBase.close();
        return cursor;
    }


//search
    public String[] Search(String name){
        HelperDataBase = getReadableDatabase();
        String[] arg={};
        Cursor cursor = HelperDataBase.rawQuery("Select * from itemes",arg);
        String[] Items = new String[cursor.getCount()];
        int index2=0;
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Items[index2] = cursor.getString(1);
                index2++;
                cursor.moveToNext();
            }
        }

        String [] Sname2 = new String[Items.length];
        int index = 0;
        for (int i = 0 ; i < Items.length ; i++) {
            if (Items[i].toLowerCase().contains(name))
            {
                Sname2[index] = Items[i];
                index++;
            }
        }
        String [] Sname = new String[index];
        for (int i = 0 ; i < Sname.length ; i++)
            Sname[i] = Sname2[i];

        HelperDataBase.close();
        return Sname;
    }


//Reports
    public void CreateReport(String username , String itemname,String date, String time){
        ContentValues reprow = new ContentValues();
        reprow.put("username",username);
        reprow.put("itemname",itemname);
        reprow.put("date",date);
        reprow.put("time",time);
        HelperDataBase = getWritableDatabase();
        HelperDataBase.insert("Reports",null,reprow);
        HelperDataBase.close();
    }

    public Cursor fetchAllReports(){
        HelperDataBase = getReadableDatabase();
        String[] rowDetails={"username","itemname","date","time"};
        Cursor cursor =HelperDataBase.query("Reports",rowDetails,null,null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        HelperDataBase.close();
        return cursor;
    }


//BestSelling

    public void CreateBestSell(String itemname , int itemcount){
        ContentValues bestrow = new ContentValues();
        bestrow.put("itemname",itemname);
        bestrow.put("itemcount",itemcount);
        HelperDataBase = getWritableDatabase();
        HelperDataBase.insert("bestselling",null,bestrow);
        HelperDataBase.close();
    }

    public int get_item_sell_id(String name){
        HelperDataBase=getReadableDatabase();
        String[] arg={name};
        Cursor cursor = HelperDataBase.rawQuery("Select id from bestselling where itemname like ?",arg);
        cursor.moveToFirst();
        HelperDataBase.close();
        return cursor.getInt(0);
    }

    public void Update_item_sell(int id ,String itemname , int itemcount){
        HelperDataBase=getReadableDatabase();
        String[] arg={id+""};
        ContentValues bestmrow = new ContentValues();
        bestmrow.put("itemname",itemname);
        bestmrow.put("itemcount",itemcount);
        HelperDataBase = getWritableDatabase();
        HelperDataBase.update("bestselling",bestmrow,"id like?",arg);
        HelperDataBase.close();
    }

    public Cursor fetchAllbestsell(){
        HelperDataBase = getReadableDatabase();
        String[] rowDetails={"itemname","itemcount"};
        Cursor cursor =HelperDataBase.query("bestselling",rowDetails,null,null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        HelperDataBase.close();
        return cursor;
    }


}
