package com.lknuchel.sample.sqlite.io.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class V0_KeyValueBdd extends SQLiteOpenHelper {
    private static final String CREATE_BDD = "CREATE TABLE "
	    + DbConstants.KEYVALUE_TABLE + "(" + DbConstants.KEYVALUE_COL_ID
	    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	    + DbConstants.KEYVALUE_COL_KEY + " TEXT NOT NULL, "
	    + DbConstants.KEYVALUE_COL_VALUE + " TEXT NOT NULL);";

    public V0_KeyValueBdd(final Context context, final String name,
	    final CursorFactory factory, final int version) {
	super(context, name, factory, version);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
	db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
	    final int newVersion) {
	db.execSQL("DROP TABLE IF EXISTS " + DbConstants.KEYVALUE_TABLE + ";");
	onCreate(db);
    }

}
