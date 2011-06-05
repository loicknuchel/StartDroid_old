package com.lknuchel.sample.lists.ui.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.lknuchel.sample.lists.R;
import com.lknuchel.sample.lists.model.Livre;
import com.lknuchel.sample.lists.ui.widget.AdapterLivre;

public class Demo_ListView_AdapterActivity extends Activity {
    private Activity a;
    private Context c;
    private ListView lvListe;
    private List<Livre> maBibliotheque = new ArrayList<Livre>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_listview_adapter);
	a = Demo_ListView_AdapterActivity.this;
	c = getApplicationContext();
	setUp();
	onCLickValidate();
    }

    private void setUp() {
	lvListe = (ListView) findViewById(R.activity_demo_listview_adapter.lvListe);
	RemplirLaBibliotheque();

	AdapterLivre livreadapter = new AdapterLivre(c, maBibliotheque);
	lvListe.setAdapter(livreadapter);
    }

    private void onCLickValidate() {

    }

    private void RemplirLaBibliotheque() {
	maBibliotheque.clear();
	maBibliotheque.add(new Livre("Starcraft 2 : Les diables du ciel",
		"William-C Dietz"));
	maBibliotheque.add(new Livre("L'art du développement Android",
		"Mark Murphy"));
	maBibliotheque.add(new Livre("Le seuil des ténèbres", "Karen Chance"));
    }
}
