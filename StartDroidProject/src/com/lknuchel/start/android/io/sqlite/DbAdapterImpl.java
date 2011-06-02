package com.lknuchel.start.android.io.sqlite;

// http://stackoverflow.com/questions/4063510/multiple-table-sqlite-db-adapters-in-android
// T class has to implement DbAdapter interface

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DbAdapterImpl<T> {
    protected static String dbTable;
    protected static String idColName;
    protected DatabaseHelper mDbHelper;
    protected SQLiteDatabase mDb;
    protected final Context mCtx;

    protected static class DatabaseHelper extends SQLiteOpenHelper {
	protected static String CREATE_TABLE;
	protected static String TABLE_NAME;

	public DatabaseHelper(Context context) {
	    super(context, DbConstants.NOM_BDD, null, DbConstants.VERSION_BDD);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
	    onCreate(db);
	}
    }

    public DbAdapterImpl(Context context) {
	mCtx = context;
    }

    public void open() {
	mDbHelper = new DatabaseHelper(mCtx);
	mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
	mDbHelper.close();
    }

    public SQLiteDatabase getBDD() {
	return mDb;
    }

    public long insert(T val) {
	return mDb.insert(dbTable, null, createValues(val));
    }

    public int update(T val, long id) {
	return mDb.update(dbTable, createValues(val), idColName + " = " + id,
		null);
    }

    public int update(T val) {
	if (val instanceof DbAdapter) {
	    return mDb.update(dbTable, createValues(val), idColName + " = "
		    + ((DbAdapter) val).getId(), null);
	}
	throw new IllegalArgumentException(
		"T.class has to implement DbAdapter interface");
    }

    public T get(long id) {
	Cursor c = mDb.query(dbTable, allColumns(), idColName + " = " + id,
		null, null, null, null);
	return cursorToObject(c);
    }

    public T get(T val) {
	if (val instanceof DbAdapter) {
	    Cursor c = mDb.query(dbTable, allColumns(), idColName + " = "
		    + ((DbAdapter) val).getId(), null, null, null, null);
	    return cursorToObject(c);
	}
	throw new IllegalArgumentException(
		"T.class has to implement DbAdapter interface");
    }

    public List<T> getAll() {
	Cursor c = mDb.query(dbTable, allColumns(), null, null, null, null,
		null);
	return cursorToObjectList(c);
    }

    public List<T> getAll(String orderBy, String limit) {
	Cursor c = mDb.query(dbTable, allColumns(), null, null, null, null, orderBy, limit);
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

    protected T cursorToObject(Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	T ret = affectCursor(c);
	c.close();

	return ret;
    }

    protected List<T> cursorToObjectList(Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}
	int nbCursor = c.getCount();

	List<T> ret = new ArrayList<T>();
	c.moveToFirst();

	for (int i = 0; i < nbCursor; i++) {
	    ret.add(affectCursor(c));
	    c.moveToNext();
	}
	c.close();

	return ret;
    }

    protected abstract ContentValues createValues(T val);

    // ContentValues values = new ContentValues();
    // values.put(DbConstants.ARTICLE_COL_BARCODE, val.getBarcode());
    // values.put(DbConstants.ARTICLE_COL_BARCODEFORMAT,
    // val.getBarcodeFormat());
    // values.put(DbConstants.ARTICLE_COL_NAME, val.getName());
    // values.put(DbConstants.ARTICLE_COL_DATE, val.getDate());
    // values.put(DbConstants.ARTICLE_COL_PRICE, val.getPrice());
    // return values;
    // }

    protected abstract T affectCursor(Cursor c);

    // Article ret = new Article();
    // ret.setId(c.getLong(DbConstants.ARTICLE_NUM_COL_ID));
    // ret.setBarcode(c.getString(DbConstants.ARTICLE_NUM_COL_BARCODE));
    // ret.setBarcodeFormat(c
    // .getString(DbConstants.ARTICLE_NUM_COL_BARCODEFORMAT));
    // ret.setName(c.getString(DbConstants.ARTICLE_NUM_COL_NAME));
    // ret.setDate(c.getLong(DbConstants.ARTICLE_NUM_COL_DATE));
    // ret.setPrice(c.getFloat(DbConstants.ARTICLE_NUM_COL_PRICE));
    // return ret;
    // }

    protected abstract String[] allColumns();
    // return new String[] { DbConstants.ARTICLE_COL_ID,
    // DbConstants.ARTICLE_COL_BARCODE,
    // DbConstants.ARTICLE_COL_BARCODEFORMAT,
    // DbConstants.ARTICLE_COL_NAME, DbConstants.ARTICLE_COL_DATE,
    // DbConstants.ARTICLE_COL_PRICE };
    // }
}
