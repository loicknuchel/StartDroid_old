package com.lknuchel.start.android.io.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BarcodeBdd extends SQLiteOpenHelper {
    private static final String CREATE_BDD = "CREATE TABLE "
	    + DbConstants.BARCODE_TABLE + "(" + DbConstants.BARCODE_COL_ID
	    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	    + DbConstants.BARCODE_COL_FORMAT + " TEXT NOT NULL, "
	    + DbConstants.BARCODE_COL_VALUE + " TEXT NOT NULL);";

    public BarcodeBdd(Context context, String name, CursorFactory factory,
	    int version) {
	super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
	db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE " + DbConstants.BARCODE_TABLE + ";");
	onCreate(db);
    }

}
