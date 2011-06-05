package com.lknuchel.android.startdroidfull.ui.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lknuchel.android.startdroidfull.R;
import com.lknuchel.android.startdroidfull.io.sqlite.DbConstants;
import com.lknuchel.android.startdroidfull.io.sqlite.KeyNameHelper;
import com.lknuchel.android.startdroidfull.io.sqlite.KeyValueHelper;
import com.lknuchel.android.startdroidfull.model.KeyName;
import com.lknuchel.android.startdroidfull.model.KeyValue;

public class Demo_Sqlite_CRUDActivity extends Activity {
    private Context c;
    private EditText keyEdit;
    private EditText valueEdit;
    private Button createBtn;
    private ListView keyValueListView;
    private String intentData = "keyValue";
    private List<KeyValue> keyValueList = null;
    private List<KeyName> keyNameList = null;
    private List<String> keyValueListString = null;
    private ArrayAdapter<String> keyValueListAdapter = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_sqlite_crud);
	c = getApplicationContext();
	intentData = getIntent().getStringExtra("dataType");
	setUp();
	onCLickValidate();
	// testsRelexivite();
    }

    @Override
    public void onResume() {
	super.onResume();
	displayList(intentData);
    }

    protected void setUp() {
	keyEdit = (EditText) findViewById(R.activity_demo_sqlite_crud.keyEdit);
	valueEdit = (EditText) findViewById(R.activity_demo_sqlite_crud.valueEdit);
	createBtn = (Button) findViewById(R.activity_demo_sqlite_crud.createBtn);
	keyValueListView = (ListView) findViewById(R.activity_demo_sqlite_crud.keyValueList);
	if (intentData.equals("keyName")) {
	    valueEdit.setHint(getResources().getString(R.string.DSCA_name));
	}
    }

    private void onCLickValidate() {
	createBtn.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		saveInSqlite(keyEdit.getText().toString(), valueEdit.getText()
			.toString(), intentData);
		keyEdit.setText("");
		valueEdit.setText("");
		displayList(intentData);
		Toast.makeText(c,
			getResources().getString(R.string.DSCA_created),
			Toast.LENGTH_SHORT).show();
	    }
	});

	keyValueListView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(final AdapterView<?> a, final View v,
		    final int position, final long id) {
		Long objId;
		if (intentData.equals("keyValue")) {
		    objId = keyValueList.get(position).getId();
		} else {
		    objId = keyNameList.get(position).getId();
		}
		Toast.makeText(
			c,
			getResources().getString(R.string.DSCA_positionClicked)
				+ " " + position + " ("
				+ getResources().getString(R.string.DSCA_id)
				+ " " + objId + ")", Toast.LENGTH_SHORT).show();
	    }
	});

	keyValueListView
		.setOnItemLongClickListener(new OnItemLongClickListener() {
		    @Override
		    public boolean onItemLongClick(AdapterView<?> parent,
			    View view, final int position, long id) {
			if (intentData.equals("keyValue")) {
			    Toast.makeText(
				    c,
				    getResources().getString(
					    R.string.DSCA_keyvalue)
					    + " "
					    + keyValueList.get(position)
						    .getKey()
					    + " / "
					    + keyValueList.get(position)
						    .getValue()
					    + " "
					    + getResources().getString(
						    R.string.DSCA_removed)
					    + ".", Toast.LENGTH_SHORT).show();
			    removeInSqlite(keyValueList.get(position).getId(),
				    intentData);
			} else {
			    Toast.makeText(
				    c,
				    getResources().getString(
					    R.string.DSCA_keyvalue)
					    + " "
					    + keyNameList.get(position)
						    .getKey()
					    + " / "
					    + keyNameList.get(position)
						    .getName()
					    + " "
					    + getResources().getString(
						    R.string.DSCA_removed)
					    + ".", Toast.LENGTH_SHORT).show();
			    removeInSqlite(keyNameList.get(position).getId(),
				    intentData);

			}
			displayList(intentData);
			return true;
		    }
		});
    }

    private void saveInSqlite(String key, String content, String type) {
	if (type.equals("keyValue")) {
	    KeyValue val = new KeyValue(key, content);
	    KeyValueHelper KeyValueHelper = new KeyValueHelper(
		    Demo_Sqlite_CRUDActivity.this);
	    KeyValueHelper.open();

	    KeyValue b = KeyValueHelper.getWithValue(val.getValue());
	    if (b != null) {
		KeyValueHelper.delete(b.getId());
	    }

	    KeyValueHelper.insert(val);

	    KeyValueHelper.close();
	} else {
	    KeyName val = new KeyName(key, content);
	    KeyNameHelper helper = new KeyNameHelper(
		    Demo_Sqlite_CRUDActivity.this);
	    helper.open();

	    KeyName b = helper.getWithName(val.getName());
	    if (b != null) {
		helper.delete(b.getId());
	    }

	    helper.insert(val);

	    helper.close();
	}
    }

    private boolean removeInSqlite(long id, String type) {
	if (type.equals("keyValue")) {
	    KeyValueHelper helper = new KeyValueHelper(
		    Demo_Sqlite_CRUDActivity.this);
	    helper.open();
	    helper.delete(id);
	    helper.close();
	} else {

	    KeyNameHelper helper = new KeyNameHelper(
		    Demo_Sqlite_CRUDActivity.this);
	    helper.open();
	    helper.delete(id);
	    helper.close();
	}
	return true;
    }

    private void displayList(String type) {
	List<KeyValue> tmpList = null;
	List<KeyName> tmpList2 = null;

	keyValueListString = new ArrayList<String>();
	keyValueList = new ArrayList<KeyValue>();
	keyNameList = new ArrayList<KeyName>();

	if (type.equals("keyValue")) {
	    KeyValueHelper helper = new KeyValueHelper(
		    Demo_Sqlite_CRUDActivity.this);
	    helper.open();

	    tmpList = helper.getAll(DbConstants.KEYVALUE_COL_KEY + " DESC",
		    null);

	    helper.close();

	    if (tmpList != null) {
		for (KeyValue b : tmpList) {
		    keyValueListString.add(b.getKey() + " - " + b.getValue());
		    keyValueList.add(b);
		}
	    }
	} else {
	    KeyNameHelper helper = new KeyNameHelper(
		    Demo_Sqlite_CRUDActivity.this);
	    helper.open();

	    tmpList2 = helper.getAll(DbConstants.KEYNAME_COL_KEY + " DESC",
		    null);

	    helper.close();

	    if (tmpList2 != null) {
		for (KeyName b : tmpList2) {
		    keyValueListString.add(b.getKey() + " - " + b.getName());
		    keyNameList.add(b);
		}
	    }
	}

	keyValueListAdapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, keyValueListString);
	keyValueListView.setAdapter(keyValueListAdapter);
	keyValueListAdapter.notifyDataSetChanged();
    }

    private void testsRelexivite() {
	KeyValue tmp = new KeyValue();
	// Toast.makeText(c,
	// "CanonicalName: "+tmp.getClass().getCanonicalName(),
	// Toast.LENGTH_LONG).show();
	// Class cl = KeyValue.class;
	Class cl = KeyName.class;

	Field fs[] = cl.getDeclaredFields();
	Toast.makeText(c, fs.length + " field found", Toast.LENGTH_LONG).show();
	for (Field f : fs) {
	    Toast.makeText(
		    c,
		    "field: " + f.getName() + " / type: "
			    + f.getGenericType().toString(), Toast.LENGTH_LONG)
		    .show();
	}

	Class[] interfaces = cl.getInterfaces();
	Toast.makeText(c, interfaces.length + " interface found",
		Toast.LENGTH_LONG).show();
	for (Class inter : interfaces) {
	    Toast.makeText(c, "interface: " + inter.getName(),
		    Toast.LENGTH_LONG).show();
	}
    }
}
