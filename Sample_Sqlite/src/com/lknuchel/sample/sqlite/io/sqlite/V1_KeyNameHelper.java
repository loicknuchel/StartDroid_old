package com.lknuchel.sample.sqlite.io.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.lknuchel.sample.sqlite.model.KeyName;

public class V1_KeyNameHelper {
    private final String dbTable = DbConstants.KEYNAME_TABLE;
    private final String idCol = DbConstants.KEYNAME_COL_ID;
    private final String keyCol = DbConstants.KEYNAME_COL_KEY;
    private final String nameCol = DbConstants.KEYNAME_COL_NAME;
    private V1_Bdd v1_Bdd;
    private SQLiteDatabase mDb;

    public V1_KeyNameHelper(final Context context) {
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

    public long insert(final KeyName val) {
	return mDb.insert(dbTable, null, createValues(val));
    }

    public int update(final KeyName val, final long id) {
	return mDb.update(dbTable, createValues(val), idCol + " = " + id, null);
    }

    public int update(final KeyName val) {
	return mDb.update(dbTable, createValues(val),
		idCol + " = " + val.getId(), null);
    }

    public KeyName get(final long id) {
	Cursor c = mDb.query(dbTable, allColumns(), idCol + " = " + id + "\"",
		null, null, null, null);
	return cursorToObject(c);
    }

    public KeyName get(final KeyName val) {
	Cursor c = mDb.query(dbTable, allColumns(), idCol + " = " + val.getId()
		+ "\"", null, null, null, null);
	return cursorToObject(c);
    }

    public KeyName getWithKey(final String key) {
	Cursor c = mDb.query(dbTable, allColumns(), keyCol + " LIKE \"" + key
		+ "\"", null, null, null, null);
	return cursorToObject(c);
    }

    public KeyName getWithName(final String value) {
	Cursor c = mDb.query(dbTable, allColumns(), nameCol + " LIKE \""
		+ value + "\"", null, null, null, null);
	return cursorToObject(c);
    }

    public List<KeyName> getAll() {
	Cursor c = mDb.query(dbTable, allColumns(), null, null, null, null,
		null, null);
	return cursorToObjectList(c);
    }

    public List<KeyName> getAll(final String orderBy, final String limit) {
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

    protected KeyName cursorToObject(final Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	KeyName ret = affectCursor(c);
	c.close();

	return ret;
    }

    protected List<KeyName> cursorToObjectList(final Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}
	int nbCursor = c.getCount();

	List<KeyName> ret = new ArrayList<KeyName>();
	c.moveToFirst();

	for (int i = 0; i < nbCursor; i++) {
	    ret.add(affectCursor(c));
	    c.moveToNext();
	}
	c.close();

	return ret;
    }

    private ContentValues createValues(KeyName val) {
	ContentValues values = new ContentValues();
	values.put(keyCol, val.getKey());
	values.put(nameCol, val.getName());
	return values;
    }

    private KeyName affectCursor(final Cursor c) {
	KeyName ret = new KeyName();
	ret.setId(c.getLong(DbConstants.KEYNAME_NUM_COL_ID));
	ret.setKey(c.getString(DbConstants.KEYNAME_NUM_COL_KEY));
	ret.setName(c.getString(DbConstants.KEYNAME_NUM_COL_NAME));
	return ret;
    }

    private String[] allColumns() {
	return new String[] { idCol, keyCol, nameCol };
    }

}
