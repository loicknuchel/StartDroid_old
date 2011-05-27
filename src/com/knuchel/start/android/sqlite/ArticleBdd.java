package com.knuchel.start.android.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ArticleBdd extends SQLiteOpenHelper {
    private static final String CREATE_BDD = "CREATE TABLE "
	    + ConfigBdd.ARTICLE_TABLE + "(" + ConfigBdd.ARTICLE_COL_ID
	    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	    + ConfigBdd.ARTICLE_COL_BARCODE + " TEXT NOT NULL, "
	    + ConfigBdd.ARTICLE_COL_BARCODEFORMAT + " TEXT NOT NULL, "
	    + ConfigBdd.ARTICLE_COL_NAME + " TEXT NOT NULL, "
	    + ConfigBdd.ARTICLE_COL_DATE + " INTEGER NOT NULL, "
	    + ConfigBdd.ARTICLE_COL_PRICE + " FLOAT NOT NULL);";

    public ArticleBdd(Context context, String name, CursorFactory factory,
	    int version) {
	super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
	db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE " + ConfigBdd.ARTICLE_TABLE + ";");
	onCreate(db);
    }

}
