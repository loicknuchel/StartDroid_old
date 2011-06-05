package com.lknuchel.sample.sqlite.io.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class V1_Bdd extends SQLiteOpenHelper {
    private static final String CREATE_VALUE_BDD = "CREATE TABLE "
	    + DbConstants.KEYVALUE_TABLE + "(" + DbConstants.KEYVALUE_COL_ID
	    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	    + DbConstants.KEYVALUE_COL_KEY + " TEXT NOT NULL, "
	    + DbConstants.KEYVALUE_COL_VALUE + " TEXT NOT NULL);";

    private static final String CREATE_NAME_BDD = "CREATE TABLE "
	    + DbConstants.KEYNAME_TABLE + "(" + DbConstants.KEYNAME_COL_ID
	    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	    + DbConstants.KEYNAME_COL_KEY + " TEXT NOT NULL, "
	    + DbConstants.KEYNAME_COL_NAME + " TEXT NOT NULL);";

    public V1_Bdd(final Context context, final String name,
	    final CursorFactory factory, final int version) {
	super(context, name, factory, version);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
	db.execSQL(CREATE_VALUE_BDD);
	db.execSQL(CREATE_NAME_BDD);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
	    final int newVersion) {
	db.execSQL("DROP TABLE IF EXISTS " + DbConstants.KEYVALUE_TABLE + ";");
	db.execSQL("DROP TABLE IF EXISTS " + DbConstants.KEYNAME_TABLE + ";");
	onCreate(db);
    }

}
