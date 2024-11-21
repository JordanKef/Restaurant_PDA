package com.example.restaurantpda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlFinder extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db"; // Όνομα βάσης
    private static final int DATABASE_VERSION = 1; // Έκδοση βάσης

    public SqlFinder(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Δημιουργία πίνακα προϊόντων

        db.execSQL("CREATE TABLE items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tableNumber TEXT, " +
                "name TEXT, " +
                "quantity INTEGER, " +
                "category TEXT, " +
                "comment TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS items");
        onCreate(db);
    }
}
