package com.knuchel.sample.sqlite.io.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.knuchel.sample.sqlite.model.ConfigBdd;

public class BarcodeBdd extends SQLiteOpenHelper {
    private static final String CREATE_BDD = "CREATE TABLE "
	    + ConfigBdd.BARCODE_TABLE + "(" + ConfigBdd.BARCODE_COL_ID
	    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	    + ConfigBdd.BARCODE_COL_FORMAT + " TEXT NOT NULL, "
	    + ConfigBdd.BARCODE_COL_VALUE + " TEXT NOT NULL);";

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
	db.execSQL("DROP TABLE " + ConfigBdd.BARCODE_TABLE + ";");
	onCreate(db);
    }

}
