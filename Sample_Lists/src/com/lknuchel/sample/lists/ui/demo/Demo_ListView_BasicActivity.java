package com.lknuchel.sample.lists.ui.demo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.lknuchel.sample.lists.R;
import com.lknuchel.sample.lists.model.Livre;

public class Demo_ListView_BasicActivity extends Activity {
    private Activity a;
    private Context c;
    private Spinner listStyles;
    private Spinner nbLivresSpinner;
    private ListView lvListe;
    private ArrayAdapter<String> lvListeAdapter;
    private List<Livre> listLivres;
    private int nbLivres = 10;
    private int listLayout = android.R.layout.simple_list_item_1;

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
	lvListe = (ListView) findViewById(R.activity_demo_listview_basic.lvListe);
	listStyles = (Spinner) findViewById(R.activity_demo_listview_basic.listStyle);
	nbLivresSpinner = (Spinner) findViewById(R.activity_demo_listview_basic.nbLivres);

	listLivres = Livre.generate10Livres();
    }

    private String[] listToStrings(List<Livre> l) {
	String[] s = new String[l.size()];

	for (int i = 0; i < l.size(); ++i) {
	    s[i] = l.get(i).getTitre();
	}

	return s;
    }

    private void onCLickValidate() {
	listStyles.setOnItemSelectedListener(new OnItemSelectedListener() {
	    @Override
	    public void onItemSelected(AdapterView<?> arg0, View arg1,
		    int arg2, long arg3) {
		switch (listStyles.getSelectedItemPosition()) {
		case 0:
		    listLayout = android.R.layout.simple_list_item_1;
		    break;
		case 1:
		    listLayout = android.R.layout.simple_list_item_checked;
		    break;
		case 2:
		    listLayout = android.R.layout.simple_list_item_multiple_choice;
		    break;
		case 3:
		    listLayout = android.R.layout.simple_list_item_single_choice;
		    break;
		default:
		    listLayout = android.R.layout.simple_list_item_1;
		    break;
		}
		lvListeAdapter = new ArrayAdapter<String>(c, listLayout,
			listToStrings(listLivres));
		lvListe.setAdapter(lvListeAdapter);
	    }

	    @Override
	    public void onNothingSelected(AdapterView<?> arg0) {
		listLayout = android.R.layout.simple_list_item_1;
		lvListeAdapter = new ArrayAdapter<String>(c, listLayout,
			listToStrings(listLivres));
		lvListe.setAdapter(lvListeAdapter);
	    }
	});

	nbLivresSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	    @Override
	    public void onItemSelected(AdapterView<?> arg0, View arg1,
		    int arg2, long arg3) {
		switch (nbLivresSpinner.getSelectedItemPosition()) {
		case 0:
		    nbLivres = 10;
		    break;
		case 1:
		    nbLivres = 8;
		    break;
		case 2:
		    nbLivres = 5;
		    break;
		case 3:
		    nbLivres = 2;
		    break;
		default:
		    nbLivres = 10;
		    break;
		}
		listLivres = Livre.generateLivres(nbLivres);
		lvListeAdapter = new ArrayAdapter<String>(c, listLayout,
			listToStrings(listLivres));
		lvListe.setAdapter(lvListeAdapter);
	    }

	    @Override
	    public void onNothingSelected(AdapterView<?> arg0) {
		nbLivres = 10;
		listLivres = Livre.generateLivres(nbLivres);
		lvListeAdapter = new ArrayAdapter<String>(c, listLayout,
			listToStrings(listLivres));
		lvListe.setAdapter(lvListeAdapter);
	    }
	});
    }
}
