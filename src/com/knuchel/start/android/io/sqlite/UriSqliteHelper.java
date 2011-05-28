package com.knuchel.start.android.io.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.knuchel.start.android.model.Uri;

public class UriSqliteHelper {
    private SqliteHelper bddHelper;
    private SQLiteDatabase bdd;
    private String table = SqliteConfig.URI_TABLE;
    private String idCol = SqliteConfig.URI_COL_ID;

    public UriSqliteHelper(Context context) {
	bddHelper = new SqliteHelper(context, SqliteConfig.NOM_BDD, null,
		SqliteConfig.VERSION_BDD);
    }

    public void open() {
	bdd = bddHelper.getWritableDatabase();
    }

    public void close() {
	bdd.close();
    }

    public SQLiteDatabase getBDD() {
	return bdd;
    }

    public long insert(Uri val) {
	return bdd.insert(table, null, createValues(val));
    }

    public int update(Uri val, long id) {
	return bdd.update(table, createValues(val), idCol + " = " + id, null);
    }

    public int update(Uri val) {
	return bdd.update(table, createValues(val),
		idCol + " = " + val.getId(), null);
    }

    public Uri getWithId(long id) {
	Cursor c = bdd.query(table, allColumns(), idCol + " = " + id, null,
		null, null, null);
	return cursorToObject(c);
    }

    public List<Uri> getWithDate(long date) {
	Cursor c = bdd.query(table, allColumns(), SqliteConfig.URI_COL_DATE + " = " + date, null,
		null, null, null);
	return cursorToObjectList(c);
    }

    public List<Uri> getWithUri(String uri) {
	Cursor c = bdd.query(table, allColumns(), SqliteConfig.URI_COL_URI + " = " + uri, null,
		null, null, null);
	return cursorToObjectList(c);
    }

    public int removeWithId(long id) {
	return bdd.delete(table, idCol + " = " + id, null);
    }

    public int removeAll() {
	return bdd.delete(table, null, null);
    }

    public boolean isIdExist(long id) {
	return getWithId(id) != null;
    }

    public long count() {
	return DatabaseUtils.queryNumEntries(bdd, table);
    }

    /*
     * PRIVATE
     */

    private Uri cursorToObject(Cursor c) {
	if (c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	Uri ret = affectCursor(c);
	c.close();

	return ret;
    }

    private List<Uri> cursorToObjectList(Cursor c) {
	int nbCursor = c.getCount();

	if (nbCursor == 0) {
	    return null;
	}

	List<Uri> ret = new ArrayList<Uri>();
	c.moveToFirst();

	for (int i = 0; i < nbCursor; i++) {
	    ret.add(affectCursor(c));
	    c.moveToNext();
	}
	c.close();

	return ret;
    }

    private ContentValues createValues(Uri val) {
	ContentValues values = new ContentValues();
	values.put(SqliteConfig.URI_COL_ID, val.id);
	values.put(SqliteConfig.URI_COL_DATE, val.date);
	values.put(SqliteConfig.URI_COL_URI, val.uri);
	values.put(SqliteConfig.URI_COL_TYPE, val.type);
	values.put(SqliteConfig.URI_COL_CONTENT, val.content);
	return values;
    }

    private Uri affectCursor(Cursor c) {
	Uri ret = new Uri();
	ret.id = c.getLong(SqliteConfig.URI_NUM_COL_ID);
	ret.date = c.getLong(SqliteConfig.URI_NUM_COL_DATE);
	ret.uri = c.getString(SqliteConfig.URI_NUM_COL_URI);
	ret.type = c.getString(SqliteConfig.URI_NUM_COL_TYPE);
	ret.content = c.getString(SqliteConfig.URI_NUM_COL_CONTENT);
	return ret;
    }

    private String[] allColumns() {
	return new String[] { 
		SqliteConfig.URI_COL_ID,
		SqliteConfig.URI_COL_DATE,
		SqliteConfig.URI_COL_URI,
		SqliteConfig.URI_COL_TYPE,
		SqliteConfig.URI_COL_CONTENT };
    }
}
