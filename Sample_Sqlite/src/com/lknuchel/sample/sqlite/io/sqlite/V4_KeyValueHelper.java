package com.lknuchel.sample.sqlite.io.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lknuchel.sample.sqlite.model.KeyValue;

public class V4_KeyValueHelper extends V3_DbAdapterImpl<KeyValue> {

    public V4_KeyValueHelper(Context context) {
	super(context);

	dbTable = DbConstants.KEYVALUE_TABLE;
	idCol = DbConstants.KEYVALUE_COL_ID;

	DatabaseHelper.CREATE_TABLE = "CREATE TABLE " + dbTable + "(" + idCol
		+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ DbConstants.KEYVALUE_COL_KEY + " TEXT NOT NULL, "
		+ DbConstants.KEYVALUE_COL_VALUE + " TEXT NOT NULL);";
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

    @Override
    protected ContentValues createValues(KeyValue val) {
	ContentValues values = new ContentValues();
	values.put(DbConstants.KEYVALUE_COL_KEY, val.getKey());
	values.put(DbConstants.KEYVALUE_COL_VALUE, val.getValue());
	return values;
    }

    @Override
    protected KeyValue affectCursor(final Cursor c) {
	KeyValue keyValue = new KeyValue();
	keyValue.setId(c.getLong(DbConstants.KEYVALUE_NUM_COL_ID));
	keyValue.setKey(c.getString(DbConstants.KEYVALUE_NUM_COL_KEY));
	keyValue.setValue(c.getString(DbConstants.KEYVALUE_NUM_COL_VALUE));
	return keyValue;
    }

    @Override
    protected String[] allColumns() {
	return new String[] { DbConstants.KEYVALUE_COL_ID,
		DbConstants.KEYVALUE_COL_KEY, DbConstants.KEYVALUE_COL_VALUE };
    }
}
