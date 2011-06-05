package com.lknuchel.sample.sqlite.ui.demo;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.lknuchel.sample.sqlite.R;

// TODO : editeur crud de la base Sqlite

public class Demo_Sqlite_HostActivity extends TabActivity {
    private Context c;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_sqlite_host);
	c = getApplicationContext();
	createAllTabs();
    }

    private void createAllTabs() {
	TabHost tabHost = getTabHost();
	TabSpec tabSpec;

	// onglet 1
	Intent intent = new Intent(this, Demo_Sqlite_CRUDActivity.class);
	intent.putExtra("dataType", "keyValue");
	tabSpec = tabHost.newTabSpec("tabCRUD1")
		.setIndicator(getResources().getString(R.string.DSHA_CRUD1))
		.setContent(intent);
	tabHost.addTab(tabSpec);

	// onglet 2
	Intent intent2 = new Intent(this, Demo_Sqlite_CRUDActivity.class);
	intent2.putExtra("dataType", "keyName");
	tabSpec = tabHost.newTabSpec("tabCRUD2")
		.setIndicator(getResources().getString(R.string.DSHA_CRUD2))
		.setContent(intent2);
	tabHost.addTab(tabSpec);

	// onglet 3
	Intent intent3 = new Intent(this, Demo_Sqlite_SelectActivity.class);
	tabSpec = tabHost.newTabSpec("tabSelect")
		.setIndicator(getResources().getString(R.string.DSHA_select))
		.setContent(intent3);
	tabHost.addTab(tabSpec);

	// onglet 4
	Intent intent4 = new Intent(this, Demo_Sqlite_AdminActivity.class);
	tabSpec = tabHost.newTabSpec("tabAdmin")
		.setIndicator(getResources().getString(R.string.DSHA_admin))
		.setContent(intent4);
	tabHost.addTab(tabSpec);
    }
}
