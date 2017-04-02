package com.example.sokol.yandexmobilization.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.provider.BaseColumns._ID;
import static com.example.sokol.yandexmobilization.data.TranslateContract.LangEntries.COLUMN_CODE;
import static com.example.sokol.yandexmobilization.data.TranslateContract.LangEntries.COLUMN_NAME;
import static com.example.sokol.yandexmobilization.data.TranslateContract.LangEntries.TABLE_NAME;

/**
 * Created by sokol on 22.03.2017.
 */

public class LangsRepositoryImpl implements LangsRepository {

    DBHelper dbHelper;

    public LangsRepositoryImpl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void setLangs(Map<String, String> langs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Iterator<Map.Entry<String, String>> iter = langs.entrySet().iterator();

        db.beginTransaction();
        try{
            db.delete(
                    TABLE_NAME,
                    null,
                    null);
            while (iter.hasNext()){
                Map.Entry<String, String> entry = iter.next();
                cv.put(COLUMN_CODE, entry.getKey());
                cv.put(COLUMN_NAME, entry.getValue());
                db.insert(
                        TABLE_NAME,
                        null,
                        cv);
            }
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
        db.close();
    }

    @Override
    public HashMap<String, String> getLangs() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor langsCursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_CODE, COLUMN_NAME},
                null,
                null,
                null,
                null,
                COLUMN_NAME);
        if (!langsCursor.moveToFirst()) {
            langsCursor.close();
            return null;
        }
        int nameColumnIndex = langsCursor.getColumnIndex(COLUMN_NAME);
        int codeColumnIndex = langsCursor.getColumnIndex(COLUMN_CODE);
        HashMap<String, String> langs = new HashMap<>();
        while (!langsCursor.isAfterLast()){
            langs.put(langsCursor.getString(codeColumnIndex), langsCursor.getString(nameColumnIndex));
            langsCursor.moveToNext();
        }
        langsCursor.close();
        db.close();
        return langs;
    }

    @Override
    public ArrayList<String> getLangNames() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor langNamesCursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_NAME},
                null,
                null,
                null,
                null,
                COLUMN_NAME);
        if (!langNamesCursor.moveToFirst()) {
            db.close();
            langNamesCursor.close();
            return null;
        }
        int nameColumnIndex = langNamesCursor.getColumnIndex(COLUMN_NAME);
        ArrayList<String> langNames = new ArrayList<>();
        while (!langNamesCursor.isAfterLast()){
            langNames.add(langNamesCursor.getString(nameColumnIndex));
            langNamesCursor.moveToNext();
        }
        langNamesCursor.close();
        db.close();
        return langNames;
    }

    @Override
    public String getLangName(String code) {
        return null;
    }

    @Override
    public String getLangCode(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor langCodeCursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_CODE},
                COLUMN_NAME + " = ?",
                new String[]{name},
                null,
                null,
                COLUMN_CODE);
        if (!langCodeCursor.moveToFirst()) {
            db.close();
            langCodeCursor.close();
            return null;
        }
        int codeColumnIndex = langCodeCursor.getColumnIndex(COLUMN_CODE);
        String langCode = langCodeCursor.getString(codeColumnIndex);
        langCodeCursor.close();
        db.close();
        return langCode;
    }
}
