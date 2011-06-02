package com.lknuchel.sample.sqlite.io.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.lknuchel.sample.sqlite.model.KeyValue;

public class V0_KeyValueHelper {
    private final String dbTable = DbConstants.KEYVALUE_TABLE;
    private final String idCol = DbConstants.KEYVALUE_COL_ID;
    private final String keyCol = DbConstants.KEYVALUE_COL_KEY;
    private final String valueCol = DbConstants.KEYVALUE_COL_VALUE;
    private V1_KeyValueBdd v1_KeyValueBdd;
    private SQLiteDatabase mDb;

    public V0_KeyValueHelper(final Context context) {
	v1_KeyValueBdd = new V1_KeyValueBdd(context, DbConstants.NOM_BDD, null,
		DbConstants.VERSION_BDD);
    }

    public void open() {
	mDb = v1_KeyValueBdd.getWritableDatabase();
    }

    public void close() {
	mDb.close();
    }

    public SQLiteDatabase getBDD() {
	return mDb;
    }

    public long insert(final KeyValue val) {
	ContentValues values = new ContentValues();
	values.put(keyCol, val.getKey());
	values.put(valueCol, val.getValue());
	return mDb.insert(dbTable, null, values);
    }

    public int update(final KeyValue val, final long id) {
	ContentValues values = new ContentValues();
	values.put(keyCol, val.getKey());
	values.put(valueCol, val.getValue());
	return mDb.update(dbTable, values, idCol + " = " + id, null);
    }

    public int update(final KeyValue val) {
	ContentValues values = new ContentValues();
	values.put(keyCol, val.getKey());
	values.put(valueCol, val.getValue());
	return mDb.update(dbTable, values, idCol + " = " + val.getId(),
		null);
    }

    public KeyValue get(final long id) {
	Cursor c = mDb.query(dbTable,
		new String[] { idCol, keyCol, valueCol }, idCol + " = " + id
			+ "\"", null, null, null, null);

	if (c == null || c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	KeyValue ret = new KeyValue();
	ret.setId(c.getLong(DbConstants.KEYVALUE_NUM_COL_ID));
	ret.setKey(c.getString(DbConstants.KEYVALUE_NUM_COL_KEY));
	ret.setValue(c.getString(DbConstants.KEYVALUE_NUM_COL_VALUE));
	c.close();

	return ret;
    }

    public KeyValue get(final KeyValue val) {
	Cursor c = mDb.query(dbTable,
		new String[] { idCol, keyCol, valueCol },
		idCol + " = " + val.getId() + "\"", null, null, null, null);

	if (c == null || c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	KeyValue ret = new KeyValue();
	ret.setId(c.getLong(DbConstants.KEYVALUE_NUM_COL_ID));
	ret.setKey(c.getString(DbConstants.KEYVALUE_NUM_COL_KEY));
	ret.setValue(c.getString(DbConstants.KEYVALUE_NUM_COL_VALUE));
	c.close();

	return ret;
    }

    public KeyValue getWithKey(final String key) {
	Cursor c = mDb.query(dbTable,
		new String[] { idCol, keyCol, valueCol }, keyCol + " LIKE \""
			+ key + "\"", null, null, null, null);

	if (c == null || c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	KeyValue ret = new KeyValue();
	ret.setId(c.getLong(DbConstants.KEYVALUE_NUM_COL_ID));
	ret.setKey(c.getString(DbConstants.KEYVALUE_NUM_COL_KEY));
	ret.setValue(c.getString(DbConstants.KEYVALUE_NUM_COL_VALUE));
	c.close();

	return ret;
    }

    public KeyValue getWithValue(final String value) {
	Cursor c = mDb.query(dbTable,
		new String[] { idCol, keyCol, valueCol }, valueCol + " LIKE \""
			+ value + "\"", null, null, null, null);

	if (c == null || c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	KeyValue ret = new KeyValue();
	ret.setId(c.getLong(DbConstants.KEYVALUE_NUM_COL_ID));
	ret.setKey(c.getString(DbConstants.KEYVALUE_NUM_COL_KEY));
	ret.setValue(c.getString(DbConstants.KEYVALUE_NUM_COL_VALUE));
	c.close();

	return ret;
    }

    public List<KeyValue> getAll() {
	Cursor c = mDb.query(dbTable,
		new String[] { idCol, keyCol, valueCol }, null, null, null,
		null, null, null);

	if (c == null || c.getCount() == 0) {
	    return null;
	}
	int nbCursor = c.getCount();

	List<KeyValue> ret = new ArrayList<KeyValue>();
	c.moveToFirst();

	for (int i = 0; i < nbCursor; i++) {
	    KeyValue keyValue = new KeyValue();
	    keyValue.setId(c.getLong(DbConstants.KEYVALUE_NUM_COL_ID));
	    keyValue.setKey(c.getString(DbConstants.KEYVALUE_NUM_COL_KEY));
	    keyValue.setValue(c.getString(DbConstants.KEYVALUE_NUM_COL_VALUE));
	    ret.add(keyValue);
	    c.moveToNext();
	}
	c.close();

	return ret;
    }

    public List<KeyValue> getAll(final String orderBy, final String limit) {
	Cursor c = mDb.query(dbTable,
		new String[] { idCol, keyCol, valueCol }, null, null, null,
		null, orderBy, limit);

	if (c == null || c.getCount() == 0) {
	    return null;
	}
	int nbCursor = c.getCount();

	List<KeyValue> ret = new ArrayList<KeyValue>();
	c.moveToFirst();

	for (int i = 0; i < nbCursor; i++) {
	    KeyValue keyValue = new KeyValue();
	    keyValue.setId(c.getLong(DbConstants.KEYVALUE_NUM_COL_ID));
	    keyValue.setKey(c.getString(DbConstants.KEYVALUE_NUM_COL_KEY));
	    keyValue.setValue(c.getString(DbConstants.KEYVALUE_NUM_COL_VALUE));
	    ret.add(keyValue);
	    c.moveToNext();
	}
	c.close();

	return ret;
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
}
