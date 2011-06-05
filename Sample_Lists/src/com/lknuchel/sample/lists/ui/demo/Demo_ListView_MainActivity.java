package com.lknuchel.sample.lists.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lknuchel.sample.lists.R;

public class Demo_ListView_MainActivity extends Activity {
    private Activity a;
    private Context c;
    private ListView lvListe;
    private String[] listStrings = { "Basic list", "Adapter list", "Complex adapter list" };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_listview_main);
	a = Demo_ListView_MainActivity.this;
	c = getApplicationContext();
	setUp();
	onCLickValidate();
    }

    private void setUp() {
	lvListe = (ListView) findViewById(R.activity_demo_listview_main.lvListe);

	lvListe.setAdapter(new ArrayAdapter<String>(c,
		android.R.layout.simple_list_item_1, listStrings));
    }

    private void onCLickValidate() {
	lvListe.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(final AdapterView<?> a, final View v,
		    final int position, final long id) {
		Intent i;
		switch (position) {
		case 0:
		    i = new Intent(c, Demo_ListView_BasicActivity.class);
		    startActivity(i);
		    break;
		case 1:
		    i = new Intent(c, Demo_ListView_AdapterActivity.class);
		    startActivity(i);
		    break;
		case 2:
		    i = new Intent(c, Demo_ListView_ComplexAdapterActivity.class);
		    startActivity(i);
		    break;
		default:
		    break;
		}
	    }
	});
    }
}
