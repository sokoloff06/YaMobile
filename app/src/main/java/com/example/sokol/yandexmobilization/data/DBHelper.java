package com.example.sokol.yandexmobilization.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sokol on 22.03.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "translate.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TranslateContract.LangEntries.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TranslateContract.LangEntries.SQL_DROP_TABLE);
        onCreate(db);
    }
}
