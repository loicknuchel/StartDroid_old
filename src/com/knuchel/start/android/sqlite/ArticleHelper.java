package com.knuchel.start.android.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.knuchel.start.android.model.Article;

public class ArticleHelper {
    private ArticleBdd articleBdd;
    private SQLiteDatabase bdd;
    private String table = ConfigBdd.ARTICLE_TABLE;
    private String idCol = ConfigBdd.ARTICLE_COL_ID;

    public ArticleHelper(Context context) {
	articleBdd = new ArticleBdd(context, ConfigBdd.NOM_BDD, null,
		ConfigBdd.VERSION_BDD);
    }

    public void open() {
	bdd = articleBdd.getWritableDatabase();
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
	Cursor c = bdd.query(table, allColumns(), ConfigBdd.ARTICLE_COL_BARCODE
		+ " = " + barcode, null, null, null, ConfigBdd.ARTICLE_COL_DATE
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
	values.put(ConfigBdd.ARTICLE_COL_BARCODE, val.getBarcode());
	values.put(ConfigBdd.ARTICLE_COL_BARCODEFORMAT, val.getBarcodeFormat());
	values.put(ConfigBdd.ARTICLE_COL_NAME, val.getName());
	values.put(ConfigBdd.ARTICLE_COL_DATE, val.getDate());
	values.put(ConfigBdd.ARTICLE_COL_PRICE, val.getPrice());
	return values;
    }

    private Article affectCursor(Cursor c) {
	Article ret = new Article();
	ret.setId(c.getLong(ConfigBdd.ARTICLE_NUM_COL_ID));
	ret.setBarcode(c.getString(ConfigBdd.ARTICLE_NUM_COL_BARCODE));
	ret.setBarcodeFormat(c
		.getString(ConfigBdd.ARTICLE_NUM_COL_BARCODEFORMAT));
	ret.setName(c.getString(ConfigBdd.ARTICLE_NUM_COL_NAME));
	ret.setDate(c.getLong(ConfigBdd.ARTICLE_NUM_COL_DATE));
	ret.setPrice(c.getFloat(ConfigBdd.ARTICLE_NUM_COL_PRICE));
	return ret;
    }

    private String[] allColumns() {
	return new String[] { ConfigBdd.ARTICLE_COL_ID,
		ConfigBdd.ARTICLE_COL_BARCODE,
		ConfigBdd.ARTICLE_COL_BARCODEFORMAT,
		ConfigBdd.ARTICLE_COL_NAME, ConfigBdd.ARTICLE_COL_DATE,
		ConfigBdd.ARTICLE_COL_PRICE };
    }
}
