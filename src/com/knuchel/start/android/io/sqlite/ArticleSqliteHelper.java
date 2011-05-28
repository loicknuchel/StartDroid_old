package com.knuchel.start.android.io.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.knuchel.start.android.model.Article;

public class ArticleSqliteHelper {
    private SqliteHelper bddHelper;
    private SQLiteDatabase bdd;
    private String table = SqliteConfig.ARTICLE_TABLE;
    private String idCol = SqliteConfig.ARTICLE_COL_ID;

    public ArticleSqliteHelper(Context context) {
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

    public long insert(Article val) {
	return bdd.insert(table, null, createValues(val));
    }

    public int update(Article val, long id) {
	return bdd.update(table, createValues(val), idCol + " = " + id, null);
    }

    public int update(Article val) {
	return bdd.update(table, createValues(val),
		idCol + " = " + val.getId(), null);
    }

    public Article getWithId(long id) {
	Cursor c = bdd.query(table, allColumns(), idCol + " = " + id, null,
		null, null, null);
	return cursorToObject(c);
    }

    public List<Article> getWithBarcode(String barcode) {
	Cursor c = bdd.query(table, allColumns(), SqliteConfig.ARTICLE_COL_BARCODE
		+ " = " + barcode, null, null, null, SqliteConfig.ARTICLE_COL_DATE
		+ " DESC", null);
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

    private Article cursorToObject(Cursor c) {
	if (c.getCount() == 0) {
	    return null;
	}

	c.moveToFirst();
	Article ret = affectCursor(c);
	c.close();

	return ret;
    }

    private List<Article> cursorToObjectList(Cursor c) {
	int nbCursor = c.getCount();

	if (nbCursor == 0) {
	    return null;
	}

	List<Article> ret = new ArrayList<Article>();
	c.moveToFirst();

	for (int i = 0; i < nbCursor; i++) {
	    ret.add(affectCursor(c));
	    c.moveToNext();
	}
	c.close();

	return ret;
    }

    private ContentValues createValues(Article val) {
	ContentValues values = new ContentValues();
	values.put(SqliteConfig.ARTICLE_COL_BARCODE, val.getBarcode());
	values.put(SqliteConfig.ARTICLE_COL_BARCODEFORMAT, val.getBarcodeFormat());
	values.put(SqliteConfig.ARTICLE_COL_NAME, val.getName());
	values.put(SqliteConfig.ARTICLE_COL_DATE, val.getDate());
	values.put(SqliteConfig.ARTICLE_COL_PRICE, val.getPrice());
	return values;
    }

    private Article affectCursor(Cursor c) {
	Article ret = new Article();
	ret.setId(c.getLong(SqliteConfig.ARTICLE_NUM_COL_ID));
	ret.setBarcode(c.getString(SqliteConfig.ARTICLE_NUM_COL_BARCODE));
	ret.setBarcodeFormat(c
		.getString(SqliteConfig.ARTICLE_NUM_COL_BARCODEFORMAT));
	ret.setName(c.getString(SqliteConfig.ARTICLE_NUM_COL_NAME));
	ret.setDate(c.getLong(SqliteConfig.ARTICLE_NUM_COL_DATE));
	ret.setPrice(c.getFloat(SqliteConfig.ARTICLE_NUM_COL_PRICE));
	return ret;
    }

    private String[] allColumns() {
	return new String[] { SqliteConfig.ARTICLE_COL_ID,
		SqliteConfig.ARTICLE_COL_BARCODE,
		SqliteConfig.ARTICLE_COL_BARCODEFORMAT,
		SqliteConfig.ARTICLE_COL_NAME, SqliteConfig.ARTICLE_COL_DATE,
		SqliteConfig.ARTICLE_COL_PRICE };
    }
}
