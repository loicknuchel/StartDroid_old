package com.knuchel.start.android.ui.demo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.knuchel.start.android.R;
import com.knuchel.start.android.io.sqlite.BarcodeHelper;
import com.knuchel.start.android.io.sqlite.EmplyeDbAdapter;
import com.knuchel.start.android.model.Barcode;
import com.knuchel.start.android.model.Employe;

public class Demo_Sqlite_TestActivity extends Activity {
    private Context c;
    private Button launchTest;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_sqlitetest);
	c = getApplicationContext();
	setUp();
	onCLickValidate();
//	saveBarcodeInSqlite(new Barcode("titi", "toto"));
    }

    protected void setUp() {
	launchTest = (Button) findViewById(R.activity_demo_sqlite.launch_test);
    }

    private void onCLickValidate() {
	launchTest.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
//		EmplyeDbAdapter empDb = new EmplyeDbAdapter(c);
//		empDb.open();
////		Toast.makeText(c, empDb.count()+" employes enregistres", Toast.LENGTH_SHORT).show();
//		Employe emp = new Employe();
//		emp.name = "toto";
//		emp.job = "stagiaire";
//		emp.company = "sfeir";
//		empDb.insert(emp);
////		Toast.makeText(c, empDb.count()+" employes enregistres", Toast.LENGTH_SHORT).show();
//		List<Employe> l = empDb.getAll();
//		Toast.makeText(c, "nb: "+l.size(), Toast.LENGTH_SHORT).show();
//		empDb.close();
		
		
		
//		ArticleDbAdapter artDb = new ArticleDbAdapter(c);
//		artDb.open();
//		Toast.makeText(c, artDb.count()+" articles enregistres", Toast.LENGTH_SHORT).show();
//		Article art = new Article();
//		art.setName("Art1");
//		artDb.insert(art);
//		Toast.makeText(c, artDb.count()+" articles enregistres", Toast.LENGTH_SHORT).show();
//		artDb.close();

		Barcode barcode = new Barcode();
		barcode.setFormat("toto");
		barcode.setValue("tata");
		BarcodeHelper barcodeBdd = new BarcodeHelper(Demo_Sqlite_TestActivity.this);
		barcodeBdd.open();
		Toast.makeText(c, barcodeBdd.countBarcode()+" barcode enregistres", Toast.LENGTH_SHORT).show();
//		Barcode b = barcodeBdd.getBarcodeWithValue(barcode.getValue());
//		if (b != null) {
//		    barcodeBdd.removeBarcodeWithID(b.getId());
//		}
		barcodeBdd.insertBarcode(barcode);
		Toast.makeText(c, barcodeBdd.countBarcode()+" barcode enregistres", Toast.LENGTH_SHORT).show();
		barcodeBdd.close();
	    }
	});
    }
    
    private void saveBarcodeInSqlite(Barcode barcode) {
	BarcodeHelper barcodeBdd = new BarcodeHelper(Demo_Sqlite_TestActivity.this);
	barcodeBdd.open();

	Barcode b = barcodeBdd.getBarcodeWithValue(barcode.getValue());
	if (b != null) {
	    barcodeBdd.removeBarcodeWithID(b.getId());
	}

	barcodeBdd.insertBarcode(barcode);

	barcodeBdd.close();
    }

}
