package com.lknuchel.start.android.io.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.lknuchel.start.android.model.Barcode;

public class BarcodeHelper {
	private BarcodeBdd barcodeBdd;
	private SQLiteDatabase bdd;
 
	public BarcodeHelper(Context context){
		//On créer la BDD et sa table
		barcodeBdd = new BarcodeBdd(context, DbConstants.NOM_BDD, null, DbConstants.VERSION_BDD);
	}
 
	
	public void open(){
		//on ouvre la BDD en écriture
		bdd = barcodeBdd.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
 
	
	public long insertBarcode(Barcode barcode){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(DbConstants.BARCODE_COL_FORMAT, barcode.getFormat());
		values.put(DbConstants.BARCODE_COL_VALUE, barcode.getValue());
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insert(DbConstants.BARCODE_TABLE, null, values);
	}
 
	public int updateBarcode(Long id, Barcode barcode){
		//La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
		ContentValues values = new ContentValues();
		values.put(DbConstants.BARCODE_COL_FORMAT, barcode.getFormat());
		values.put(DbConstants.BARCODE_COL_VALUE, barcode.getValue());
		return bdd.update(DbConstants.BARCODE_TABLE, values, DbConstants.BARCODE_COL_ID + " = " +id, null);
	}
 
	public Barcode getBarcodeWithValue(String value){
		//Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
		Cursor c = bdd.query(DbConstants.BARCODE_TABLE, new String[] {DbConstants.BARCODE_COL_ID, DbConstants.BARCODE_COL_FORMAT, DbConstants.BARCODE_COL_VALUE}, DbConstants.BARCODE_COL_VALUE + " LIKE \"" + value +"\"", null, null, null, null);
		return cursorToBarcode(c);
	}
	
	public List<Barcode> getAllBarcode(String orderBy, String limit){
		Cursor c = bdd.query(DbConstants.BARCODE_TABLE, new String[] {DbConstants.BARCODE_COL_ID, DbConstants.BARCODE_COL_FORMAT, DbConstants.BARCODE_COL_VALUE}, null, null, null, null, orderBy, limit);
//		 clause limit : nbRet || offset, nbRet
//		bdd.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		return cursorToBarcodeList(c);
	}
 
	public int removeBarcodeWithID(Long id){
		//Suppression d'un livre de la BDD grâce à l'ID
		return bdd.delete(DbConstants.BARCODE_TABLE, DbConstants.BARCODE_COL_ID + " = " +id, null);
	}
 
	public int removeAllBarcode(){
		//Suppression d'un livre de la BDD grâce à l'ID
		return bdd.delete(DbConstants.BARCODE_TABLE, null, null);
	} 
	
	public Long countBarcode(){
		return DatabaseUtils.queryNumEntries(bdd, DbConstants.BARCODE_TABLE);
	}
	
	
	//Cette méthode permet de convertir un cursor en un livre
	private Barcode cursorToBarcode(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		Barcode barcode = affectCursorToBarcode(c);
		//On ferme le cursor
		c.close();
 
		return barcode;
	}
 
	//Cette méthode permet de convertir un cursor en un livre
	private List<Barcode> cursorToBarcodeList(Cursor c){
		int nbBarcode = c.getCount();
		
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (nbBarcode == 0)
			return null;
 
		List<Barcode> barcodeList = new ArrayList<Barcode>();
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		for(int i=0; i<nbBarcode; i++){
			barcodeList.add(affectCursorToBarcode(c));
			
			c.moveToNext();
		}
		
		
		//On ferme le cursor
		c.close();
 
		return barcodeList;
	}

	private Barcode affectCursorToBarcode(Cursor c){
		Barcode barcode = new Barcode();
		barcode.setId(c.getLong(DbConstants.BARCODE_NUM_COL_ID));
		barcode.setFormat(c.getString(DbConstants.BARCODE_NUM_COL_FORMAT));
		barcode.setValue(c.getString(DbConstants.BARCODE_NUM_COL_VALUE));
		return barcode;
	}
	
}
