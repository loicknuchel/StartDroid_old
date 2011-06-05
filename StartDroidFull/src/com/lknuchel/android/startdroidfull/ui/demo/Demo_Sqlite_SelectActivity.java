package com.lknuchel.android.startdroidfull.ui.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lknuchel.android.startdroidfull.R;
import com.lknuchel.android.startdroidfull.io.sqlite.KeyValueHelper;
import com.lknuchel.android.startdroidfull.model.KeyValue;

public class Demo_Sqlite_SelectActivity extends Activity {
    private Context c;
    private EditText nameEdit;
    private Button search;
    private ListView results;
    private List<String> ListViewString = null;
    private ArrayAdapter<String> ListViewAdapter = null;
    private List<KeyValue> ListViewValues = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_sqlite_select);
	c = getApplicationContext();
	setUp();
	onCLickValidate();
    }

    @Override
    public void onResume() {
	super.onResume();
	doRequestAndDisplayList();
    }

    protected void setUp() {
	nameEdit = (EditText) findViewById(R.activity_demo_sqlite_select.nameEdit);
	search = (Button) findViewById(R.activity_demo_sqlite_select.search);
	results = (ListView) findViewById(R.activity_demo_sqlite_select.results);
    }

    private void onCLickValidate() {
	search.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		doRequestAndDisplayList();
	    }
	});
    }

    protected void doRequestAndDisplayList() {
	String name = nameEdit.getText().toString();
	List<KeyValue> tmpRes = null;
	if (!name.equals("")) {
	    KeyValueHelper helper = new KeyValueHelper(
		    Demo_Sqlite_SelectActivity.this);
	    helper.open();

	    tmpRes = helper.getValuesLinkedWithName(name);

	    helper.close();

	    displayList(tmpRes, name);
	}
    }

    protected void displayList(List<KeyValue> tmpList, String name) {
	ListViewString = new ArrayList<String>();
	ListViewValues = new ArrayList<KeyValue>();

	if (tmpList != null) {
	    for (KeyValue b : tmpList) {
		ListViewString.add(name + " - " + b.getKey() + " - "
			+ b.getValue());
		ListViewValues.add(b);
	    }
	} else {
	    Toast.makeText(c, "No results found !!!", Toast.LENGTH_LONG).show();
	}

	ListViewAdapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, ListViewString);
	results.setAdapter(ListViewAdapter);
	ListViewAdapter.notifyDataSetChanged();
    }
}
