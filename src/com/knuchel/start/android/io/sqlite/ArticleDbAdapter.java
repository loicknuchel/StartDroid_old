package com.knuchel.start.android.io.sqlite;

// http://stackoverflow.com/questions/4063510/multiple-table-sqlite-db-adapters-in-android

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.knuchel.start.android.model.Article;

public class ArticleDbAdapter {
    private static final String dbTable = DbConstants.URI_TABLE;
    private static final String idColName = DbConstants.URI_COL_ID;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {
	private static final String CREATE_ARTICLE_TABLE = "CREATE TABLE "
		+ DbConstants.ARTICLE_TABLE + "(" + DbConstants.ARTICLE_COL_ID
		+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ DbConstants.ARTICLE_COL_BARCODE + " TEXT NOT NULL, "
		+ DbConstants.ARTICLE_COL_BARCODEFORMAT + " TEXT NOT NULL, "
		+ DbConstants.ARTICLE_COL_NAME + " TEXT NOT NULL, "
		+ DbConstants.ARTICLE_COL_DATE + " INTEGER NOT NULL, "
		+ DbConstants.ARTICLE_COL_PRICE + " FLOAT NOT NULL);";

	public DatabaseHelper(Context context, String name, CursorFactory factory,
		    int version) {
	    super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(CREATE_ARTICLE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    db.execSQL("DROP TABLE IF EXISTS " + DbConstants.ARTICLE_TABLE
		    + ";");
	    onCreate(db);
	}
    }

    public ArticleDbAdapter(Context context) {
	mCtx = context;
    }

    public void open() throws SQLException {
	mDbHelper = new DatabaseHelper(mCtx, DbConstants.NOM_BDD, null, DbConstants.VERSION_BDD);
	mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
	mDbHelper.close();
    }

    public SQLiteDatabase getBDD() {
	return mDb;
    }

    public long insert(Article val) {
	return mDb.insert(dbTable, null, createValues(val));
    }

    public int update(Article val, long id) {
	return mDb.update(dbTable, createValues(val), idColName + " = " + id,
		null);
    }

    public int update(Article val) {
	return mDb.update(dbTable, createValues(val),
		idColName + " = " + val.getId(), null);
    }

    public Article get(long id) {
	Cursor c = mDb.query(dbTable, allColumns(), idColName + " = " + id,
		null, null, null, null);
	return cursorToObject(c);
    }

    public Article get(Article art) {
	Cursor c = mDb.query(dbTable, allColumns(),
		idColName + " = " + art.getId(), null, null, null, null);
	return cursorToObject(c);
    }

    public List<Article> getWithBarcode(String barcode) {
	Cursor c = mDb.query(dbTable, allColumns(),
		DbConstants.ARTICLE_COL_BARCODE + " = " + barcode, null, null,
		null, DbConstants.ARTICLE_COL_DATE + " DESC", null);
	return cursorToObjectList(c);
    }

    public List<Article> getAll() {
	Cursor c = mDb.query(dbTable, allColumns(), null, null, null, null,
		null);
	return cursorToObjectList(c);
    }

    public boolean delete(long id) {
	return mDb.delete(dbTable, idColName + " = " + id, null) > 0;
    }

    public boolean deleteAll() {
	return mDb.delete(dbTable, null, null) > 0;
    }

    public boolean isIdExist(long id) {
	return get(id) != null;
    }

    public long count() {
	return DatabaseUtils.queryNumEntries(mDb, dbTable);
    }

    /*
     * PRIVATE
     */

    private Article cursorToObject(Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	Article ret = affectCursor(c);
	c.close();

	return ret;
    }

    private List<Article> cursorToObjectList(Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}
	int nbCursor = c.getCount();

	List<Article> ret = new ArrayList<Article>();
	c.moveToFirst();

	for (int i = 0; i < nbCursor; i++) {
	    ret.add(affectCursor(c));
	    c.moveToNext();
	}
	c.close();

	return ret;
    }

    private ContentValues createValues(Article val) {
	ContentValues values = new ContentValues();
	values.put(DbConstants.ARTICLE_COL_BARCODE, val.getBarcode());
	values.put(DbConstants.ARTICLE_COL_BARCODEFORMAT,
		val.getBarcodeFormat());
	values.put(DbConstants.ARTICLE_COL_NAME, val.getName());
	values.put(DbConstants.ARTICLE_COL_DATE, val.getDate());
	values.put(DbConstants.ARTICLE_COL_PRICE, val.getPrice());
	return values;
    }

    private Article affectCursor(Cursor c) {
	Article ret = new Article();
	ret.setId(c.getLong(DbConstants.ARTICLE_NUM_COL_ID));
	ret.setBarcode(c.getString(DbConstants.ARTICLE_NUM_COL_BARCODE));
	ret.setBarcodeFormat(c
		.getString(DbConstants.ARTICLE_NUM_COL_BARCODEFORMAT));
	ret.setName(c.getString(DbConstants.ARTICLE_NUM_COL_NAME));
	ret.setDate(c.getLong(DbConstants.ARTICLE_NUM_COL_DATE));
	ret.setPrice(c.getFloat(DbConstants.ARTICLE_NUM_COL_PRICE));
	return ret;
    }

    private String[] allColumns() {
	return new String[] { DbConstants.ARTICLE_COL_ID,
		DbConstants.ARTICLE_COL_BARCODE,
		DbConstants.ARTICLE_COL_BARCODEFORMAT,
		DbConstants.ARTICLE_COL_NAME, DbConstants.ARTICLE_COL_DATE,
		DbConstants.ARTICLE_COL_PRICE };
    }
}
