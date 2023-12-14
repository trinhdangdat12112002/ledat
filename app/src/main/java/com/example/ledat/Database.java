package com.example.ledat;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="song";
    private static final String TABLE_NAME ="Baihat";
    private static final String ID ="ID";
    private static final String NAME ="NAME";
    private static final String SINGER ="SINGER";
    private static final String TIME ="TIME";


    private Context context;

    public Database(Context context) {
        super(context, DATABASE_NAME,null, 1);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                ID +" integer primary key  , "+
                NAME + " TEXT, "+
                SINGER + " TEXT, "+
                TIME +" float)";

        String sqlQuery1 = "INSERT INTO " + TABLE_NAME + " VALUES ( 1, 'Phút cuối', 'Bằng Kiều',  6.27)";
        String sqlQuery2 = "INSERT INTO " + TABLE_NAME + " VALUES ( 2, 'Bà tôi', 'Tùng Dương', 4.23)";
        String sqlQuery3 = "INSERT INTO " + TABLE_NAME + " VALUES ( 3, 'Tình cha', 'Ngọc Sơn', 2.13)";
        String sqlQuery4 = "INSERT INTO " + TABLE_NAME + " VALUES ( 4, 'Tình mẹ', 'Ngọc Sơn', 5.73)";
        String sqlQuery5 = "INSERT INTO " + TABLE_NAME + " VALUES ( 5, 'Giả vờ yêu', 'Ngô Kiến Huy', 3.23)";

        db.execSQL(sqlQuery);
        db.execSQL(sqlQuery1);
        db.execSQL(sqlQuery2);
        db.execSQL(sqlQuery3);
        db.execSQL(sqlQuery4);
        db.execSQL(sqlQuery5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public  void xoaToanBo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from Baihat");
    }

    public void updateSong(int id, String name, String singer, Float time) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME +
                " SET " + NAME + " = '" + name + "'" +
                " , " + SINGER + " = '" + singer + "'" +
                " , " + TIME + " = " + time +
                " WHERE " + ID + " = " + id;
        db.execSQL(sql);
    }
    public void deleteSong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_NAME +
                " WHERE " + ID + " = " + id;
        db.execSQL(sql);
    }


    //Add new a student
    public void addSong(Baihat baihat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, baihat.getName());
        values.put(SINGER, baihat.getSinger());
        values.put(TIME, baihat.getTime());

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public void QueryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }


    public List<Baihat> getAll() {
        List<Baihat> listSong = new ArrayList<Baihat>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME  ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Baihat baihat = new Baihat();
                baihat.setID(cursor.getInt(0));
                baihat.setName(cursor.getString(1));
                baihat.setSinger(cursor.getString(2));
                baihat.setTime(cursor.getFloat(3));
                listSong.add(baihat);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listSong;
    }


}
