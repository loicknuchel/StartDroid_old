package com.lknuchel.android.startdroidfull.io.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lknuchel.android.startdroidfull.model.KeyName;

public class KeyNameHelper extends DbAdapterImpl<KeyName> {

    public KeyNameHelper(Context context) {
	super(context);

	dbTable = DbConstants.KEYNAME_TABLE;
	idCol = DbConstants.KEYNAME_COL_ID;
    }

    public KeyName getWithKey(final String key) {
	Cursor c = mDb.query(dbTable, allColumns(), DbConstants.KEYNAME_COL_KEY
		+ " LIKE \"" + key + "\"", null, null, null, null);
	return cursorToObject(c);
    }

    public KeyName getWithName(final String name) {
	Cursor c = mDb.query(dbTable, allColumns(),
		DbConstants.KEYNAME_COL_NAME + " LIKE \"" + name + "\"", null,
		null, null, null);
	return cursorToObject(c);
    }

    @Override
    protected ContentValues createValues(KeyName val) {
	ContentValues values = new ContentValues();
	values.put(DbConstants.KEYNAME_COL_KEY, val.getKey());
	values.put(DbConstants.KEYNAME_COL_NAME, val.getName());
	return values;
    }

    @Override
    protected KeyName affectCursor(final Cursor c) {
	KeyName ret = new KeyName();
	ret.setId(c.getLong(DbConstants.KEYNAME_NUM_COL_ID));
	ret.setKey(c.getString(DbConstants.KEYNAME_NUM_COL_KEY));
	ret.setName(c.getString(DbConstants.KEYNAME_NUM_COL_NAME));
	return ret;
    }

    @Override
    protected String[] allColumns() {
	return new String[] { DbConstants.KEYNAME_COL_ID,
		DbConstants.KEYNAME_COL_KEY, DbConstants.KEYNAME_COL_NAME };
    }
}
