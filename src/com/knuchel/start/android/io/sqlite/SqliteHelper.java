package com.knuchel.start.android.io.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {
    private static final String CREATE_URI = "CREATE TABLE "
	    + SqliteConfig.URI_TABLE + "("
	    + SqliteConfig.URI_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	    + SqliteConfig.URI_COL_DATE + " INTEGER NOT NULL, "
	    + SqliteConfig.URI_COL_URI + " TEXT NOT NULL, "
	    + SqliteConfig.URI_COL_TYPE + " TEXT NOT NULL, "
	    + SqliteConfig.URI_COL_CONTENT + " TEXT NOT NULL);";

    private static final String CREATE_ARTICLE = "CREATE TABLE "
	    + SqliteConfig.ARTICLE_TABLE + "(" + SqliteConfig.ARTICLE_COL_ID
	    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	    + SqliteConfig.ARTICLE_COL_BARCODE + " TEXT NOT NULL, "
	    + SqliteConfig.ARTICLE_COL_BARCODEFORMAT + " TEXT NOT NULL, "
	    + SqliteConfig.ARTICLE_COL_NAME + " TEXT NOT NULL, "
	    + SqliteConfig.ARTICLE_COL_DATE + " INTEGER NOT NULL, "
	    + SqliteConfig.ARTICLE_COL_PRICE + " FLOAT NOT NULL);";

    public SqliteHelper(Context context, String name, CursorFactory factory,
	    int version) {
	super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
	db.execSQL(CREATE_URI);
	db.execSQL(CREATE_ARTICLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE " + SqliteConfig.URI_TABLE + ";");
	db.execSQL("DROP TABLE " + SqliteConfig.ARTICLE_TABLE + ";");
	onCreate(db);
    }

}
