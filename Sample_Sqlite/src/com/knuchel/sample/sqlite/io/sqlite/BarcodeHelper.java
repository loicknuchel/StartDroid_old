package com.knuchel.sample.sqlite.io.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.knuchel.sample.sqlite.model.Barcode;
import com.knuchel.sample.sqlite.model.ConfigBdd;

public class BarcodeHelper {
    private BarcodeBdd barcodeBdd;
    private SQLiteDatabase bdd;

    public BarcodeHelper(Context context) {
	barcodeBdd = new BarcodeBdd(context, ConfigBdd.BARCODE_NOM_BDD, null,
		ConfigBdd.BARCODE_VERSION_BDD);
    }

    public void open() {
	bdd = barcodeBdd.getWritableDatabase();
    }

    public void close() {
	bdd.close();
    }

    public SQLiteDatabase getBDD() {
	return bdd;
    }

    public long insertBarcode(Barcode barcode) {
	ContentValues values = new ContentValues();
	values.put(ConfigBdd.BARCODE_COL_FORMAT, barcode.getFormat());
	values.put(ConfigBdd.BARCODE_COL_VALUE, barcode.getValue());
	return bdd.insert(ConfigBdd.BARCODE_TABLE, null, values);
    }

    public int updateBarcode(Long id, Barcode barcode) {
	ContentValues values = new ContentValues();
	values.put(ConfigBdd.BARCODE_COL_FORMAT, barcode.getFormat());
	values.put(ConfigBdd.BARCODE_COL_VALUE, barcode.getValue());
	return bdd.update(ConfigBdd.BARCODE_TABLE, values,
		ConfigBdd.BARCODE_COL_ID + " = " + id, null);
    }

    public Barcode getBarcodeWithValue(String value) {
	Cursor c = bdd.query(ConfigBdd.BARCODE_TABLE, new String[] {
		ConfigBdd.BARCODE_COL_ID, ConfigBdd.BARCODE_COL_FORMAT,
		ConfigBdd.BARCODE_COL_VALUE }, ConfigBdd.BARCODE_COL_VALUE
		+ " LIKE \"" + value + "\"", null, null, null, null);
	return cursorToBarcode(c);
    }

    public List<Barcode> getAllBarcode(String orderBy, String limit) {
	Cursor c = bdd.query(ConfigBdd.BARCODE_TABLE, new String[] {
		ConfigBdd.BARCODE_COL_ID, ConfigBdd.BARCODE_COL_FORMAT,
		ConfigBdd.BARCODE_COL_VALUE }, null, null, null, null, orderBy,
		limit);
	return cursorToBarcodeList(c);
    }

    public int removeBarcodeWithID(Long id) {
	return bdd.delete(ConfigBdd.BARCODE_TABLE, ConfigBdd.BARCODE_COL_ID
		+ " = " + id, null);
    }

    public int removeAllBarcode() {
	return bdd.delete(ConfigBdd.BARCODE_TABLE, null, null);
    }

    public Long countBarcode() {
	return DatabaseUtils.queryNumEntries(bdd, ConfigBdd.BARCODE_TABLE);
    }

    private Barcode cursorToBarcode(Cursor c) {
	if (c.getCount() == 0)
	    return null;

	c.moveToFirst();
	Barcode barcode = affectCursorToBarcode(c);
	c.close();

	return barcode;
    }

    private List<Barcode> cursorToBarcodeList(Cursor c) {
	int nbBarcode = c.getCount();

	if (nbBarcode == 0)
	    return null;

	List<Barcode> barcodeList = new ArrayList<Barcode>();
	c.moveToFirst();

	for (int i = 0; i < nbBarcode; i++) {
	    barcodeList.add(affectCursorToBarcode(c));

	    c.moveToNext();
	}

	c.close();

	return barcodeList;
    }

    private Barcode affectCursorToBarcode(Cursor c) {
	Barcode barcode = new Barcode();
	barcode.setId(c.getLong(ConfigBdd.BARCODE_NUM_COL_ID));
	barcode.setFormat(c.getString(ConfigBdd.BARCODE_NUM_COL_FORMAT));
	barcode.setValue(c.getString(ConfigBdd.BARCODE_NUM_COL_VALUE));
	return barcode;
    }

}
