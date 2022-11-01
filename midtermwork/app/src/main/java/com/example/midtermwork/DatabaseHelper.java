package com.example.midtermwork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "UserINFO";
    public static final String ID = "id";
    public static final String PASS_WORD = "passWord";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String PHONE_NUM = "phoneNum";
    //테이블 만드는 sql문
    public static final String CREATE_TABLE =
            "create table " + TABLE_NAME + "("+
                    ID + " primary key, "+
                    PASS_WORD + " text, "+
                    NAME+ " text, "+
                    ADDRESS + " text, " +
                    PHONE_NUM + " text"+
                    ")";

    public static final String[] ALL_COLUMNS = {ID, PASS_WORD, NAME, ADDRESS, PHONE_NUM};


    public DatabaseHelper(@Nullable Context context) {
        //db 틀 생
        super(context, "UserControl", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
