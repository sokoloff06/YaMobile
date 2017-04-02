package com.example.sokol.yandexmobilization.data;

import android.provider.BaseColumns;

/**
 * Created by sokol on 22.03.2017.
 */

public class TranslateContract {

    TranslateContract(){
    }

    class LangEntries implements BaseColumns {

        static final String TABLE_NAME = "langs";
        static final String COLUMN_CODE = "code";
        static final String COLUMN_NAME = "name";

        static final String SQL_CREATE_TABLE =
                "CREATE TABLE " +
                        TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_CODE + " TEXT, " +
                        COLUMN_NAME + " TEXT);";

        static final String SQL_DROP_TABLE =
                "DROP TABLE IF EXISTS " +
                        TABLE_NAME;
    }
}
