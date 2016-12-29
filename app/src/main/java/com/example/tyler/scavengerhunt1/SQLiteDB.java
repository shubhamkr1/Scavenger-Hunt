package com.example.tyler.scavengerhunt1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tyler on 04-12-2016.
 */
public class SQLiteDB extends SQLiteOpenHelper {

    public static String dbname="ScavengerHunt", tablename="PlantInfo";
    public static String colName="Name", colSciName="ScientificName", colWaterPreference="WaterPreference", colUse="Uses", colNative="Nativ", colImgPath="ImagePath";
    String sql;

    public static String dbmaps="PlantMap", maptablename="maps_info";
    public static String pLat="Latitude", pLong="Longitude", pName="Name";

    public SQLiteDB(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists " + tablename + "(ID text, Name text, ScientificName text, WaterPreference text, Uses text, Nativ text, ImagePath text, Flag text)");
        sqLiteDatabase.execSQL("create table if not exists " + maptablename + "(Name text, Latitude double, Longitude double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addInfo(SQLiteDatabase  sq, String id, String name, String sciname, String waterpref, String uses, String nativ, String imgpath, String flag)
    {
        ContentValues cv = new ContentValues();
        cv.put("ID",id);
        cv.put("Name",name);
        cv.put("ScientificName",sciname);
        cv.put("WaterPreference",waterpref);
        cv.put("Uses",uses);
        cv.put("Nativ",nativ);
        cv.put("ImagePath",imgpath);
        cv.put("Flag",flag);
        sq.insert("PlantInfo",null,cv);
    }

    public Cursor getInfo(SQLiteDatabase  sq, String id)
    {
        Cursor c;
        sql = "select * from PlantInfo where ID='" + id + "'";
        c = sq.rawQuery(sql,null);
        return c;
    }

    //---------------Maps---------------------------------------
    public void addMapInfo(SQLiteDatabase  sq, String name,Double latitude, Double longitude)
    {
        ContentValues cv = new ContentValues();
        cv.put("Name",name);
        cv.put("Latitude",latitude);
        cv.put("Longitude",longitude);
        sq.insert("maps_info",null,cv);
        //Toast.makeText(new MockContext(), "Data added",Toast.LENGTH_LONG);
    }

    public Cursor getMapInfo(SQLiteDatabase  sq )
    {
        Cursor c= sq.query(maptablename,null,null,null,null,null,null);

        return c;
    }
}