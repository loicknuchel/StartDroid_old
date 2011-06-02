package com.lknuchel.sample.sqlite.io.sqlite;

// http://stackoverflow.com/questions/4063510/multiple-table-sqlite-db-adapters-in-android
// T class has to implement DbAdapter interface

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class V4_DbAdapterImpl<T> {
    protected static String dbTable;
    protected static String idCol;
    protected DatabaseHelper mDbHelper;
    protected SQLiteDatabase mDb;
    protected final Context mCtx;

    protected static class DatabaseHelper extends SQLiteOpenHelper {
	protected static String CREATE_TABLE;

	public DatabaseHelper(Context context) {
	    super(context, DbConstants.NOM_BDD, null, DbConstants.VERSION_BDD);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    db.execSQL("DROP TABLE IF EXISTS " + dbTable + ";");
	    onCreate(db);
	}
    }

    public V4_DbAdapterImpl(Context context) {
	mCtx = context;
    }
    
    private void generateTable(){
	
    }

    public void open() {
	mDbHelper = new DatabaseHelper(mCtx);
	mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
	mDb.close();
    }

    public SQLiteDatabase getBDD() {
	return mDb;
    }

    public long insert(T val) {
	return mDb.insert(dbTable, null, createValues(val));
    }

    public int update(T val, long id) {
	return mDb.update(dbTable, createValues(val), idCol + " = " + id, null);
    }

    public int update(T val) {
	if (val instanceof DbAdapter) {
	    return mDb.update(dbTable, createValues(val), idCol + " = "
		    + ((DbAdapter) val).getId(), null);
	} else {
	    throw new IllegalArgumentException(
		    "T.class has to implement DbAdapter interface");
	}
    }

    public T get(long id) {
	Cursor c = mDb.query(dbTable, allColumns(), idCol + " = " + id, null,
		null, null, null);
	return cursorToObject(c);
    }

    public T get(T val) {
	if (val instanceof DbAdapter) {
	    Cursor c = mDb.query(dbTable, allColumns(), idCol + " = "
		    + ((DbAdapter) val).getId(), null, null, null, null);
	    return cursorToObject(c);
	} else {
	    throw new IllegalArgumentException(
		    "T.class has to implement DbAdapter interface");
	}
    }

    public List<T> getAll() {
	Cursor c = mDb.query(dbTable, allColumns(), null, null, null, null,
		null);
	return cursorToObjectList(c);
    }

    public List<T> getAll(String orderBy, String limit) {
	Cursor c = mDb.query(dbTable, allColumns(), null, null, null, null,
		orderBy, limit);
	return cursorToObjectList(c);
    }

    public boolean delete(long id) {
	return mDb.delete(dbTable, idCol + " = " + id, null) > 0;
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

    // protected ContentValues createValues(KeyValue val) {
    // ContentValues values = new ContentValues();
    // values.put(DbConstants.KEYVALUE_COL_KEY, val.getKey());
    // values.put(DbConstants.KEYVALUE_COL_VALUE, val.getValue());
    // return values;
    // }

    protected abstract T affectCursor(Cursor c);

    // protected KeyValue affectCursor(final Cursor c) {
    // KeyValue keyValue = new KeyValue();
    // keyValue.setId(c.getLong(DbConstants.KEYVALUE_NUM_COL_ID));
    // keyValue.setKey(c.getString(DbConstants.KEYVALUE_NUM_COL_KEY));
    // keyValue.setValue(c.getString(DbConstants.KEYVALUE_NUM_COL_VALUE));
    // return keyValue;
    // }

    protected abstract String[] allColumns();

    // private String[] allColumns() {
    // return new String[] { DbConstants.KEYVALUE_COL_ID,
    // DbConstants.KEYVALUE_COL_KEY, DbConstants.KEYVALUE_COL_VALUE };
    // }
}
