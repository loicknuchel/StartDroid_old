package com.lknuchel.sample.lists.ui.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lknuchel.sample.lists.R;
import com.lknuchel.sample.lists.model.Livre;
import com.lknuchel.sample.lists.ui.widget.AdapterLivre;

public class Demo_ListView_BasicActivity extends Activity {
    private Activity a;
    private Context c;
    private ListView lvListe;
    private String[] listeStrings = { "France", "Allemagne", "Russie" };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_listview_basic);
	a = Demo_ListView_BasicActivity.this;
	c = getApplicationContext();
	setUp();
	onCLickValidate();
    }

    private void setUp() {
	lvListe = (ListView) findViewById(R.activity_demo_listview_main.lvListe);
	lvListe.setAdapter(new ArrayAdapter<String>(c,
		android.R.layout.simple_list_item_1, listeStrings));
	// lvListe.setAdapter(new ArrayAdapter<String>(c,
	// android.R.layout.simple_list_item_checked, listeStrings));
	// lvListe.setAdapter(new ArrayAdapter<String>(c,
	// android.R.layout.simple_list_item_multiple_choice, listeStrings));
	// lvListe.setAdapter(new ArrayAdapter<String>(c,
	// android.R.layout.simple_list_item_single_choice, listeStrings));
    }

    private void onCLickValidate() {

    }
}
