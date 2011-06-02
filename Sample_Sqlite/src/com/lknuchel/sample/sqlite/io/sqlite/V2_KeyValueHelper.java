package com.lknuchel.sample.sqlite.io.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.lknuchel.sample.sqlite.model.KeyValue;

public class V2_KeyValueHelper {
    private final String dbTable = DbConstants.KEYVALUE_TABLE;
    private final String idCol = DbConstants.KEYVALUE_COL_ID;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {
	private static final String CREATE_BDD = "CREATE TABLE "
		+ DbConstants.KEYVALUE_TABLE + "("
		+ DbConstants.KEYVALUE_COL_ID
		+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ DbConstants.KEYVALUE_COL_KEY + " TEXT NOT NULL, "
		+ DbConstants.KEYVALUE_COL_VALUE + " TEXT NOT NULL);";

	public DatabaseHelper(Context context, String name,
		CursorFactory factory, int version) {
	    super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(CREATE_BDD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    db.execSQL("DROP TABLE IF EXISTS " + DbConstants.KEYVALUE_TABLE
		    + ";");
	    onCreate(db);
	}
    }

    public V2_KeyValueHelper(Context context) {
	mCtx = context;
    }

    public void open() {
	mDbHelper = new DatabaseHelper(mCtx, DbConstants.NOM_BDD, null,
		DbConstants.VERSION_BDD);
	mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
	mDb.close();
    }

    public SQLiteDatabase getBDD() {
	return mDb;
    }

    public long insert(KeyValue val) {
	return mDb.insert(dbTable, null, createValues(val));
    }

    public int update(KeyValue val, long id) {
	return mDb.update(dbTable, createValues(val), idCol + " = " + id, null);
    }

    public int update(KeyValue val) {
	return mDb.update(dbTable, createValues(val),
		idCol + " = " + val.getId(), null);
    }

    public KeyValue get(long id) {
	Cursor c = mDb.query(dbTable, allColumns(), idCol + " = " + id, null,
		null, null, null);
	return cursorToObject(c);
    }

    public KeyValue get(KeyValue art) {
	Cursor c = mDb.query(dbTable, allColumns(),
		idCol + " = " + art.getId(), null, null, null, null);
	return cursorToObject(c);
    }

    public KeyValue getWithKey(final String key) {
	Cursor c = mDb.query(dbTable, allColumns(),
		DbConstants.KEYVALUE_COL_KEY + " LIKE \"" + key + "\"", null,
		null, null, null);
	return cursorToObject(c);
    }

    public KeyValue getWithValue(final String value) {
	Cursor c = mDb.query(dbTable, allColumns(),
		DbConstants.KEYVALUE_COL_VALUE + " LIKE \"" + value + "\"",
		null, null, null, null);
	return cursorToObject(c);
    }

    public List<KeyValue> getAll() {
	Cursor c = mDb.query(dbTable, allColumns(), null, null, null, null,
		null);
	return cursorToObjectList(c);
    }

    public List<KeyValue> getAll(final String orderBy, final String limit) {
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

    private KeyValue cursorToObject(Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	KeyValue ret = affectCursor(c);
	c.close();

	return ret;
    }

    private List<KeyValue> cursorToObjectList(Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}
	int nbCursor = c.getCount();

	List<KeyValue> ret = new ArrayList<KeyValue>();
	c.moveToFirst();

	for (int i = 0; i < nbCursor; i++) {
	    ret.add(affectCursor(c));
	    c.moveToNext();
	}
	c.close();

	return ret;
    }

    private ContentValues createValues(KeyValue val) {
	ContentValues values = new ContentValues();
	values.put(DbConstants.KEYVALUE_COL_KEY, val.getKey());
	values.put(DbConstants.KEYVALUE_COL_VALUE, val.getValue());
	return values;
    }

    private KeyValue affectCursor(final Cursor c) {
	KeyValue keyValue = new KeyValue();
	keyValue.setId(c.getLong(DbConstants.KEYVALUE_NUM_COL_ID));
	keyValue.setKey(c.getString(DbConstants.KEYVALUE_NUM_COL_KEY));
	keyValue.setValue(c.getString(DbConstants.KEYVALUE_NUM_COL_VALUE));
	return keyValue;
    }

    private String[] allColumns() {
	return new String[] { DbConstants.KEYVALUE_COL_ID,
		DbConstants.KEYVALUE_COL_KEY, DbConstants.KEYVALUE_COL_VALUE };
    }
}
