package com.knuchel.sample.sqlite.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.knuchel.sample.sqlite.R;
import com.knuchel.sample.sqlite.io.sqlite.BarcodeHelper;
import com.knuchel.sample.sqlite.model.Barcode;

public class CRUDActivity extends Activity {
    private Context c;
    private Button createBtn;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_crud);
	c = getApplicationContext();
	setUp();
	onCLickValidate();
    }

    protected void setUp() {
	createBtn = (Button) findViewById(R.activity_crud.create);
    }

    private void onCLickValidate() {
	createBtn.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		long count = saveBarcodeInSqlite(new Barcode("EAN_13", "12536989257845"));
		Toast.makeText(c, "Barcode created. ("+count+" existing)", Toast.LENGTH_SHORT).show();
	    }
	});
    }
    
    private long saveBarcodeInSqlite(Barcode barcode) {
	long c;
	BarcodeHelper barcodeBdd = new BarcodeHelper(CRUDActivity.this);
	barcodeBdd.open();

	Barcode b = barcodeBdd.getBarcodeWithValue(barcode.getValue());
	if (b != null) {
	    barcodeBdd.removeBarcodeWithID(b.getId());
	}

	barcodeBdd.insertBarcode(barcode);
	c = barcodeBdd.countBarcode();

	barcodeBdd.close();
	return c;
    }
}
