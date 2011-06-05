package com.lknuchel.sample.sqlite.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lknuchel.sample.sqlite.R;
import com.lknuchel.sample.sqlite.io.sqlite.V4_KeyNameHelper;
import com.lknuchel.sample.sqlite.io.sqlite.V4_KeyValueHelper;
import com.lknuchel.sample.sqlite.model.KeyName;
import com.lknuchel.sample.sqlite.model.KeyValue;

public class Demo_Sqlite_AdminActivity extends Activity {
    private Context c;
    private Button fill;
    private Button drop;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_sqlite_admin);
	c = getApplicationContext();
	setUp();
	onCLickValidate();
    }

    protected void setUp() {
	fill = (Button) findViewById(R.activity_demo_sqlite_admin.fill);
	drop = (Button) findViewById(R.activity_demo_sqlite_admin.drop);
    }

    private void onCLickValidate() {
	fill.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		fillDb();
	    }
	});

	drop.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		dropDb();
	    }
	});
    }

    private void dropDb() {
	V4_KeyValueHelper db = new V4_KeyValueHelper(c);
	db.open();
	db.deleteAll();
	db.close();

	V4_KeyNameHelper db2 = new V4_KeyNameHelper(c);
	db2.open();
	db2.deleteAll();
	db2.close();
    }

    private void fillDb() {
	V4_KeyValueHelper db = new V4_KeyValueHelper(c);
	db.open();
	db.insert(new KeyValue("1", "voiture"));
	db.insert(new KeyValue("2", "table"));
	db.insert(new KeyValue("3", "stylo"));
	db.insert(new KeyValue("20", "verre"));
	db.insert(new KeyValue("5", "PC"));
	db.insert(new KeyValue("1", "poubelle"));
	db.insert(new KeyValue("1", "souris"));
	db.insert(new KeyValue("3", "chat"));
	db.insert(new KeyValue("3", "lampe"));
	db.insert(new KeyValue("1", "chaise"));
	db.close();

	V4_KeyNameHelper db2 = new V4_KeyNameHelper(c);
	db2.open();
	db2.insert(new KeyName("1", "loic"));
	db2.insert(new KeyName("2", "loic"));
	db2.insert(new KeyName("3", "claude"));
	db2.insert(new KeyName("20", "michel"));
	db2.insert(new KeyName("15", "john"));
	db2.insert(new KeyName("1", "lola"));
	db2.close();
    }
}
