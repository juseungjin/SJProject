package com.example.juseungjin.sjproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by juseungjin on 2016. 5. 15..
 */
public class DBAdapter {
    private static final String DB_NAME="sjDB";
    private static final String DB_TABLE="TB_SJ";
    private static final int DB_VERSION = 1;
    private final Context mCtx;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    private static final String DB_CREATE="CREATE TABLE" +
            "( ID  INTEGER PRIMARY KEY AUTOINCREMENT, AGE INTEGER, PHONE TEXT, ADDR TEXT )";

    public DBAdapter(Context ctx){
        this.mCtx = ctx;
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper (Context context){
            super(context,DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            Log.d("TEST", "onCreate DATABASE_CREATE");
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public void open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx);
        mDb = mDBHelper.getWritableDatabase();
        Log.d("TEST", "open");
    }

    public void close(){
        mDBHelper.close();
    }

    //넣기
    public long insert(int age,  String phone, String addr, String name ) {
        ContentValues insertValues = new ContentValues();
        Log.d("TEST",phone);
        Log.d("TEST",addr);
        Log.d("TEST",name);




        insertValues.put("AGE", age);
        insertValues.put("PHONE", phone);
        insertValues.put("ADDR", addr);
        insertValues.put("NAME", name);
        Log.d("TEST","insert suc");
        return mDb.insert(DB_TABLE, null, insertValues);
    }
    //업데이트~
    public long update(String id, int age,  String phone, String addr, String name) {
        ContentValues updateValues = new ContentValues();
        updateValues.put("AGE", age);
        updateValues.put("PHONE", phone);
        updateValues.put("ADDR", addr);
        updateValues.put("NAME", name);
        return mDb.update(DB_TABLE, updateValues, "ID" + "=?", new String[]{id});
    }

    //한개씩삭제
    public boolean deleteRow(String id) {
        return mDb.delete(DB_TABLE, "ID" + "=?", new String[]{id}) > 0;

    }

    //다삭제
    public boolean deleteAll() {
        return mDb.delete(DB_TABLE, null, null) > 0;
    }

    public Cursor AllRows() {
        return mDb.query(DB_TABLE, null, null, null, null, null, null);

    }
}
