package com.knuchel.start.android.io.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {
    private static final String CREATE_URI_TABLE = "CREATE TABLE "
	+ DbConstants.URI_TABLE + "(" + DbConstants.URI_COL_ID
	+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ DbConstants.URI_COL_DATE + " INTEGER NOT NULL, "
	+ DbConstants.URI_COL_URI + " TEXT NOT NULL, "
	+ DbConstants.URI_COL_TYPE + " TEXT NOT NULL, "
	+ DbConstants.URI_COL_CONTENT + " TEXT NOT NULL);";
    
    private static final String CREATE_ARTICLE_TABLE = "CREATE TABLE "
	+ DbConstants.ARTICLE_TABLE + "("
	+ DbConstants.ARTICLE_COL_ID
	+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ DbConstants.ARTICLE_COL_BARCODE + " TEXT NOT NULL, "
	+ DbConstants.ARTICLE_COL_BARCODEFORMAT + " TEXT NOT NULL, "
	+ DbConstants.ARTICLE_COL_NAME + " TEXT NOT NULL, "
	+ DbConstants.ARTICLE_COL_DATE + " INTEGER NOT NULL, "
	+ DbConstants.ARTICLE_COL_PRICE + " FLOAT NOT NULL);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    /**
     * Constructor
     * 
     * @param ctx
     */
    public DbAdapter(Context ctx) {
	this.context = ctx;
	this.DBHelper = new DatabaseHelper(this.context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
	DatabaseHelper(Context context) {
	    super(context, DbConstants.NOM_BDD, null, DbConstants.VERSION_BDD);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(CREATE_URI_TABLE);
	    db.execSQL(CREATE_ARTICLE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    // Adding any table mods to this guy here
	    db.execSQL("DROP TABLE " + DbConstants.URI_TABLE + ";");
	    db.execSQL("DROP TABLE " + DbConstants.ARTICLE_TABLE + ";");
	    onCreate(db);
	}
    }

    /**
     * open the db
     * 
     * @return this
     * @throws SQLException
     *             return type: DBAdapter
     */
    public DbAdapter open() throws SQLException {
	this.db = this.DBHelper.getWritableDatabase();
	return this;
    }

    /**
     * close the db return type: void
     */
    public void close() {
	this.DBHelper.close();
    }
}
