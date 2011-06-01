package com.knuchel.start.android.ui.demo;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.knuchel.start.android.R;
import com.knuchel.start.android.util.Strings;

// TODO : editeur crud de la base Sqlite

public class Demo_Sqlite_HostActivity extends TabActivity {
    private Context c;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_sqlitehost);
	c = getApplicationContext();
	createAllTabs();
    }

    private void createAllTabs() {
	TabHost tabHost = getTabHost(); // Conteneur des onglets. sert de lien
					// entre l'IHM et le code.
	TabSpec tabSpec; // spécifie les informations de l'onglet

	// creation d'un premier onglet
	Intent intent = new Intent(this, Demo_Sqlite_TestActivity.class);
	tabSpec = tabHost.newTabSpec("tabTests")
		.setIndicator(Strings.get(c, R.string.DSHA_tests))
		.setContent(intent);
	tabHost.addTab(tabSpec);

	// creation d'un second onglet
	Intent intent2 = new Intent(this, Demo_TabActivity_FirstActivity.class);
//	intent2.putExtra("valeur", Strings.get(c, R.string.THA_second_value));
	tabSpec = tabHost.newTabSpec("tabCreate")
		.setIndicator(Strings.get(c, R.string.DSHA_create))
		.setContent(intent2);
	tabHost.addTab(tabSpec);

	// creation d'un second onglet
	Intent intent3 = new Intent(this, Demo_TabActivity_SecondActivity.class);
//	intent3.putExtra("valeur", Strings.get(c, R.string.THA_third_value));
	tabSpec = tabHost
		.newTabSpec("tabSelect")
		.setIndicator(Strings.get(c, R.string.DSHA_select))
		.setContent(intent3);
	tabHost.addTab(tabSpec);
    }
}
