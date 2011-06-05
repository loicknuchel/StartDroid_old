package com.lknuchel.sample.lists.ui.demo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lknuchel.sample.lists.R;
import com.lknuchel.sample.lists.ui.widget.ListSeparator;

public class Demo_ListView_ComplexAdapterActivity extends Activity {
    public final static String ITEM_TITLE = "title";
    public final static String ITEM_CAPTION = "caption";
//    LecteurFlux objLectFlux = new LecteurFlux();

    @Override
    public void onCreate(Bundle icicle) {
	super.onCreate(icicle);

	List<Map<String, ?>> security = new LinkedList<Map<String, ?>>();
	security.add(createItem("titre 1 ", "sous titre du titre1"));
	security.add(createItem("titre 2", "Sous titre du titre 2"));
	security.add(createItem("Titre 3",
		"sous titre du titre 3 un peu lon pour avoir un retour à la ligne"));

	// creation de nom objet de type ListSeparer
	ListSeparator adapter = new ListSeparator(this);

	// ajoute d'un objet adapter nom de la catégorie Array Test avec deux
	// items first item et item two
	adapter.addSection("Array test", new ArrayAdapter<String>(this,
		R.layout.adapter_demo_complexitemlist_1, new String[] { "First item", "item two" }));

	// ajout d'un autre adapter avec entete plux complex et des items sur
	// deux lignes
	adapter.addSection("Plus complex", new SimpleAdapter(this, security,
		R.layout.adapter_demo_complexitemlist_2,
		new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] {
			R.adapter_demo_complexitemlist_2.list_complex_title, R.adapter_demo_complexitemlist_2.list_complex_caption }));

//	ListSeparator adapter = objLectFlux.liste_course_periode(this);
	ListView list = new ListView(this);
	list.setAdapter(adapter);
	this.setContentView(list);

    }
    


    public Map<String, ?> createItem(String title, String caption) {
	Map<String, String> item = new HashMap<String, String>();
	item.put(ITEM_TITLE, title);
	item.put(ITEM_CAPTION, caption);
	return item;
    }

}
