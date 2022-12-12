package com.jaroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqlHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "city_manager.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "City";
    public static final String CITY_NAME_COLUMN = "city_name";
    public static final String CITY_ID_COLUMN = "id";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (\n" +
            " " + CITY_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            " " + CITY_NAME_COLUMN + " TEXT NOT NULL " +
            ");";

    public SqlHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCity(City city) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CITY_NAME_COLUMN, city.getCityName());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public void updateCity(City city) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CITY_NAME_COLUMN, city.getCityName());
        String whereClause = CITY_ID_COLUMN + "=?";
        String whereArgs[] = {city.getId() + ""};
        sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause, whereArgs);
        sqLiteDatabase.close();
    }

    public void deleteCity(City city) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = CITY_ID_COLUMN + "=?";
        String whereArgs[] = {city.getId() + ""};
        sqLiteDatabase.delete(TABLE_NAME, whereClause, whereArgs);
        sqLiteDatabase.close();
    }

    public ArrayList<City> getListCity() {
        ArrayList<City> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                City city = new City();
                int idIndex = cursor.getColumnIndex(CITY_ID_COLUMN);
                if (idIndex != -1) {
                    city.setId(cursor.getInt(idIndex));
                }
                int cityNameIndex = cursor.getColumnIndex(CITY_NAME_COLUMN);
                if (cityNameIndex != -1) {
                    city.setCityName(cursor.getString(cityNameIndex));
                }
                list.add(city);
            }
            sqLiteDatabase.close();
            return list;
        }
        sqLiteDatabase.close();
        return null;
    }

}
