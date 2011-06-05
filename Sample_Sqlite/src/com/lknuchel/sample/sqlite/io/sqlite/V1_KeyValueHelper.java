package com.lknuchel.sample.sqlite.io.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.lknuchel.sample.sqlite.model.KeyValue;

public class V1_KeyValueHelper {
    private final String dbTable = DbConstants.KEYVALUE_TABLE;
    private final String idCol = DbConstants.KEYVALUE_COL_ID;
    private final String keyCol = DbConstants.KEYVALUE_COL_KEY;
    private final String valueCol = DbConstants.KEYVALUE_COL_VALUE;
    private V1_Bdd v1_Bdd;
    private SQLiteDatabase mDb;

    public V1_KeyValueHelper(final Context context) {
	v1_Bdd = new V1_Bdd(context, DbConstants.NOM_BDD, null,
		DbConstants.VERSION_BDD);
    }

    public void open() {
	mDb = v1_Bdd.getWritableDatabase();
    }

    public void close() {
	mDb.close();
    }

    public SQLiteDatabase getBDD() {
	return mDb;
    }

    public long insert(final KeyValue val) {
	return mDb.insert(dbTable, null, createValues(val));
    }

    public int update(final KeyValue val, final long id) {
	return mDb.update(dbTable, createValues(val), idCol + " = " + id, null);
    }

    public int update(final KeyValue val) {
	return mDb.update(dbTable, createValues(val),
		idCol + " = " + val.getId(), null);
    }

    public KeyValue get(final long id) {
	Cursor c = mDb.query(dbTable, allColumns(), idCol + " = " + id + "\"",
		null, null, null, null);
	return cursorToObject(c);
    }

    public KeyValue get(final KeyValue val) {
	Cursor c = mDb.query(dbTable, allColumns(), idCol + " = " + val.getId()
		+ "\"", null, null, null, null);
	return cursorToObject(c);
    }

    public KeyValue getWithKey(final String key) {
	Cursor c = mDb.query(dbTable, allColumns(), keyCol + " LIKE \"" + key
		+ "\"", null, null, null, null);
	return cursorToObject(c);
    }

    public KeyValue getWithValue(final String value) {
	Cursor c = mDb.query(dbTable, allColumns(), valueCol + " LIKE \""
		+ value + "\"", null, null, null, null);
	return cursorToObject(c);
    }

    public List<KeyValue> getAll() {
	Cursor c = mDb.query(dbTable, allColumns(), null, null, null, null,
		null, null);
	return cursorToObjectList(c);
    }

    public List<KeyValue> getAll(final String orderBy, final String limit) {
	Cursor c = mDb.query(dbTable, allColumns(), null, null, null, null,
		orderBy, limit);
	return cursorToObjectList(c);
    }

    public boolean delete(final long id) {
	return mDb.delete(dbTable, idCol + " = " + id, null) > 0;
    }

    public boolean deleteAll() {
	return mDb.delete(dbTable, null, null) > 0;
    }

    public boolean isIdExist(final long id) {
	return get(id) != null;
    }

    public Long count() {
	return DatabaseUtils.queryNumEntries(mDb, dbTable);
    }

    /*
     * PRIVATE
     */

    protected KeyValue cursorToObject(final Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	KeyValue ret = affectCursor(c);
	c.close();

	return ret;
    }

    protected List<KeyValue> cursorToObjectList(final Cursor c) {
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
	values.put(keyCol, val.getKey());
	values.put(valueCol, val.getValue());
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
	return new String[] { idCol, keyCol, valueCol };
    }

}
