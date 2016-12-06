package com.example.android.marvelheroes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luizramos on 23/11/16.
 */

public class CharacterDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Character_db";


    public CharacterDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ CharacterContract.TABLE_NAME + " (" +
                    CharacterContract._ID         +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    CharacterContract.NAME        + " TEXT NOT NULL, "+
                    CharacterContract.DESCRIPTION + " TEXT, "+
                    CharacterContract.PATH        + " TEXT, "+
                    CharacterContract.EXTENSION   + " TEXT, "+
                    CharacterContract.TYPE        + " TEXT, " +
                    CharacterContract.URL         + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
