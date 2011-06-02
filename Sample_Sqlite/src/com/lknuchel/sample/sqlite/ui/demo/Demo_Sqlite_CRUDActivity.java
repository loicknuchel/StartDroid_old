package com.lknuchel.sample.sqlite.ui.demo;

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

import com.lknuchel.sample.sqlite.R;
import com.lknuchel.sample.sqlite.io.sqlite.DbConstants;
import com.lknuchel.sample.sqlite.io.sqlite.V3_KeyValueHelper;
import com.lknuchel.sample.sqlite.model.KeyValue;

public class Demo_Sqlite_CRUDActivity extends Activity {
    private Context c;
    private EditText keyEdit;
    private EditText valueEdit;
    private Button createBtn;
    private ListView keyValueListView;
    private List<KeyValue> keyValueList = null;
    private List<String> keyValueListString = null;
    private ArrayAdapter<String> keyValueListAdapter = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_sqlite_crud);
	c = getApplicationContext();
	setUp();
	onCLickValidate();
	displayList();
    }

    protected void setUp() {
	keyEdit = (EditText) findViewById(R.activity_demo_sqlite_crud.keyEdit);
	valueEdit = (EditText) findViewById(R.activity_demo_sqlite_crud.valueEdit);
	createBtn = (Button) findViewById(R.activity_demo_sqlite_crud.createBtn);
	keyValueListView = (ListView) findViewById(R.activity_demo_sqlite_crud.keyValueList);
    }

    private void onCLickValidate() {
	createBtn.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		saveInSqlite(new KeyValue(keyEdit.getText().toString(),
			valueEdit.getText().toString()));
		keyEdit.setText("");
		valueEdit.setText("");
		displayList();
		Toast.makeText(c,
			getResources().getString(R.string.DSCA_created),
			Toast.LENGTH_SHORT).show();
	    }
	});

	keyValueListView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(final AdapterView<?> a, final View v,
		    final int position, final long id) {
		Long objId = keyValueList.get(position).getId();
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
			Toast.makeText(
				c,
				getResources()
					.getString(R.string.DSCA_keyvalue)
					+ " "
					+ keyValueList.get(position).getKey()
					+ " / "
					+ keyValueList.get(position).getValue()
					+ " "
					+ getResources().getString(
						R.string.DSCA_removed) + ".",
				Toast.LENGTH_SHORT).show();
			removeInSqlite(keyValueList.get(position).getId());
			displayList();
			return true;
		    }
		});
    }

    private void saveInSqlite(KeyValue keyValue) {
	V3_KeyValueHelper KeyValueHelper = new V3_KeyValueHelper(
		Demo_Sqlite_CRUDActivity.this);
	KeyValueHelper.open();

	KeyValue b = KeyValueHelper.getWithValue(keyValue.getValue());
	if (b != null) {
	    KeyValueHelper.delete(b.getId());
	}

	KeyValueHelper.insert(keyValue);

	KeyValueHelper.close();
    }

    private boolean removeInSqlite(long id) {
	V3_KeyValueHelper KeyValueHelper = new V3_KeyValueHelper(
		Demo_Sqlite_CRUDActivity.this);
	KeyValueHelper.open();
	KeyValueHelper.delete(id);
	KeyValueHelper.close();
	return true;
    }

    private void displayList() {
	List<KeyValue> tmpList = null;

	V3_KeyValueHelper KeyValueHelper = new V3_KeyValueHelper(
		Demo_Sqlite_CRUDActivity.this);
	KeyValueHelper.open();

	tmpList = KeyValueHelper.getAll(DbConstants.KEYVALUE_COL_KEY + " DESC",
		null);

	KeyValueHelper.close();

	keyValueListString = new ArrayList<String>();
	keyValueList = new ArrayList<KeyValue>();

	if (tmpList != null) {
	    for (KeyValue b : tmpList) {
		keyValueListString.add(b.getId() + " / " + b.getKey() + " - "
			+ b.getValue());
		keyValueList.add(b);
	    }
	}

	keyValueListAdapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, keyValueListString);
	keyValueListView.setAdapter(keyValueListAdapter);
	keyValueListAdapter.notifyDataSetChanged();
    }
}
