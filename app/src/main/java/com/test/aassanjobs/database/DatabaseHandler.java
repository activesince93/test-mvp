package com.test.aassanjobs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.aassanjobs.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Darshan on 23-12-2016.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private AtomicInteger mOpenCounter = new AtomicInteger();
    private static DatabaseHandler mDatabaseHandler;
    private static SQLiteDatabase mSQLiteDatabase;

    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    public static final String DATABASE_NAME = "test_db";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Get database instance
     *
     * @param context context
     * @return database handler instance
     */
    public static DatabaseHandler getInstance(Context context) {
        if (mDatabaseHandler == null) {
            mDatabaseHandler = new DatabaseHandler(context);
        }
        return mDatabaseHandler;
    }

    /**
     * Open database
     *
     * @return sqLiteDatabase object
     */
    private synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            mSQLiteDatabase = this.getWritableDatabase();
        }
        return mSQLiteDatabase;
    }

    /**
     * Close database
     */
    private synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mSQLiteDatabase.close();
        }
    }

    /**
     * Check if database in use before deleting database
     *
     * @return true if database in use
     */
    private boolean isDatabaseInUse() {
        return (mOpenCounter.intValue() != 0);
    }

    // TABLES
    private static final String TABLE_CITY = "table_city";

    // TABLE_GROUP columns
    private static final String CITY_ID = "city_id";
    private static final String CITY_NAME = "city_name";
    private static final String CITY_SLUG = "city_slug";

    private String CREATE_TABLE_CITY = "CREATE TABLE IF NOT EXISTS " + TABLE_CITY + "("
            + CITY_ID + " INTEGER PRIMARY KEY,"
            + CITY_NAME + " TEXT,"
            + CITY_SLUG + " TEXT"
            + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CITY);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        // Added to check ON DELETE CASCADE
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCities(List<City> cityList) {
        SQLiteDatabase db = openDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (City city : cityList) {
                values.put(CITY_ID, city.getId());
                values.put(CITY_NAME, city.getName());
                values.put(CITY_SLUG, city.getSlug());
                db.insert(TABLE_CITY, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        closeDatabase();
    }

    public List<City> getCityList() {
        List<City> cityList = new ArrayList<>();
        SQLiteDatabase db = openDatabase();
        String query = "SELECT * FROM " + TABLE_CITY;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(Long.parseLong(cursor.getString(cursor.getColumnIndex(CITY_ID))));
                city.setName(cursor.getString(cursor.getColumnIndex(CITY_NAME)));
                city.setSlug(cursor.getString(cursor.getColumnIndex(CITY_SLUG)));
                cityList.add(city);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return cityList;
    }

}