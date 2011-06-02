package com.lknuchel.start.android.io.sqlite;

// http://stackoverflow.com/questions/4063510/multiple-table-sqlite-db-adapters-in-android
// http://droidreign.com/2010/10/dev-tutorials-android-sqlite-database-basics/

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lknuchel.start.android.model.Uri;

public class UriDbAdapter {
    private static final String dbTable = DbConstants.URI_TABLE;
    private static final String idColName = DbConstants.URI_COL_ID;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {
	private static final String CREATE_URI_TABLE = "CREATE TABLE "
		+ DbConstants.URI_TABLE + "(" + DbConstants.URI_COL_ID
		+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ DbConstants.URI_COL_DATE + " INTEGER NOT NULL, "
		+ DbConstants.URI_COL_URI + " TEXT NOT NULL, "
		+ DbConstants.URI_COL_TYPE + " TEXT NOT NULL, "
		+ DbConstants.URI_COL_CONTENT + " TEXT NOT NULL);";

	public DatabaseHelper(Context context) {
	    super(context, DbConstants.NOM_BDD, null, DbConstants.VERSION_BDD);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(CREATE_URI_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    db.execSQL("DROP TABLE IF EXISTS " + DbConstants.URI_TABLE + ";");
	    onCreate(db);
	}
    }

    public UriDbAdapter(Context context) {
	mCtx = context;
    }

    public void open() throws SQLException {
	mDbHelper = new DatabaseHelper(mCtx);
	mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
	mDbHelper.close();
    }

    public SQLiteDatabase getBDD() {
	return mDb;
    }

    public long insert(Uri val) {
	return mDb.insert(dbTable, null, createValues(val));
    }

    public int update(Uri val, long id) {
	return mDb.update(dbTable, createValues(val), idColName + " = " + id,
		null);
    }

    public int update(Uri val) {
	return mDb.update(dbTable, createValues(val),
		idColName + " = " + val.getId(), null);
    }

    public Uri get(long id) {
	Cursor c = mDb.query(dbTable, allColumns(), idColName + " = " + id,
		null, null, null, null);
	return cursorToObject(c);
    }

    public Uri get(Uri uri) {
	Cursor c = mDb.query(dbTable, allColumns(),
		idColName + " = " + uri.getId(), null, null, null, null);
	return cursorToObject(c);
    }

    public List<Uri> getWithDate(long date) {
	Cursor c = mDb.query(dbTable, allColumns(), DbConstants.URI_COL_DATE
		+ " = " + date, null, null, null, null);
	return cursorToObjectList(c);
    }

    public List<Uri> getWithUri(String uri) {
	Cursor c = mDb.query(dbTable, allColumns(), DbConstants.URI_COL_URI
		+ " = " + uri, null, null, null, null);
	return cursorToObjectList(c);
    }

    public List<Uri> getAll() {
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

    private Uri cursorToObject(Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	Uri ret = affectCursor(c);
	c.close();

	return ret;
    }

    private List<Uri> cursorToObjectList(Cursor c) {
	if (c == null || c.getCount() == 0) {
	    return null;
	}
	int nbCursor = c.getCount();

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
	values.put(DbConstants.URI_COL_ID, val.id);
	values.put(DbConstants.URI_COL_DATE, val.date);
	values.put(DbConstants.URI_COL_URI, val.uri);
	values.put(DbConstants.URI_COL_TYPE, val.type);
	values.put(DbConstants.URI_COL_CONTENT, val.content);
	return values;
    }

    private Uri affectCursor(Cursor c) {
	Uri ret = new Uri();
	ret.id = c.getLong(DbConstants.URI_NUM_COL_ID);
	ret.date = c.getLong(DbConstants.URI_NUM_COL_DATE);
	ret.uri = c.getString(DbConstants.URI_NUM_COL_URI);
	ret.type = c.getString(DbConstants.URI_NUM_COL_TYPE);
	ret.content = c.getString(DbConstants.URI_NUM_COL_CONTENT);
	return ret;
    }

    private String[] allColumns() {
	return new String[] { DbConstants.URI_COL_ID, DbConstants.URI_COL_DATE,
		DbConstants.URI_COL_URI, DbConstants.URI_COL_TYPE,
		DbConstants.URI_COL_CONTENT };
    }
}
