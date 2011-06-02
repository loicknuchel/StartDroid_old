package com.lknuchel.start.android.io.sqlite;

// http://stackoverflow.com/questions/4063510/multiple-table-sqlite-db-adapters-in-android
// http://droidreign.com/2010/10/dev-tutorials-android-sqlite-database-basics/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.widget.Toast;

import com.lknuchel.start.android.model.Employe;

public class EmplyeDbAdapter extends DbAdapterImpl<Employe> {

    public EmplyeDbAdapter(Context context) {
	super(context);

	dbTable = DbConstants.EMPLOYE_TABLE;
	idColName = DbConstants.EMPLOYE_COL_ID;

	DatabaseHelper.CREATE_TABLE = "CREATE TABLE "
		+ DbConstants.EMPLOYE_TABLE + "(" + DbConstants.EMPLOYE_COL_ID
		+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ DbConstants.EMPLOYE_COL_NAME + " TEXT NOT NULL, "
		+ DbConstants.EMPLOYE_COL_JOB + " TEXT NOT NULL, "
		+ DbConstants.EMPLOYE_COL_COMPANY + " TEXT NOT NULL);";

	DatabaseHelper.TABLE_NAME = dbTable;
    }

    @Override
    protected ContentValues createValues(Employe val) {
	ContentValues values = new ContentValues();
	values.put(DbConstants.EMPLOYE_COL_ID, val.id);
	values.put(DbConstants.EMPLOYE_COL_NAME, val.name);
	values.put(DbConstants.EMPLOYE_COL_JOB, val.job);
	values.put(DbConstants.EMPLOYE_COL_COMPANY, val.company);
	return values;
    }

    @Override
    protected Employe affectCursor(Cursor c) {
	Employe ret = new Employe();
	ret.id = c.getLong(DbConstants.EMPLOYE_NUM_COL_ID);
	ret.name = c.getString(DbConstants.EMPLOYE_NUM_COL_NAME);
	ret.job = c.getString(DbConstants.EMPLOYE_NUM_COL_JOB);
	ret.company = c.getString(DbConstants.EMPLOYE_NUM_COL_COMPANY);
	return ret;
    }

    @Override
    protected String[] allColumns() {
	return new String[] { DbConstants.EMPLOYE_COL_ID,
		DbConstants.EMPLOYE_COL_NAME, DbConstants.EMPLOYE_COL_JOB,
		DbConstants.EMPLOYE_COL_COMPANY };
    }
}
