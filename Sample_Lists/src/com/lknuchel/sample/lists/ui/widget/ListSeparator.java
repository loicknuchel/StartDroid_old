package com.lknuchel.sample.lists.ui.widget;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.lknuchel.sample.lists.R;

public class ListSeparator extends BaseAdapter {

    public final Map<String, Adapter> sections = new LinkedHashMap<String, Adapter>();
    public final ArrayAdapter<String> headers;
    public final static int TYPE_SECTION_HEADER = 0;

    public ListSeparator(Context context) {
	headers = new ArrayAdapter<String>(context, R.layout.adapter_demo_header_complexlist);
    }

    // méthode pour ajouter dans le header le nom de ma catégorie et dans
    // sections le nom et un objet adapter
    public void addSection(String section, Adapter adapter) {
	this.headers.add(section);
	this.sections.put(section, adapter);
    }

    // Renvoi la position d'un clique
    public Object getItem(int position) {
	for (Object section : this.sections.keySet()) {
	    Adapter adapter = sections.get(section);
	    int size = adapter.getCount() + 1;

	    // récupération de la position dans la section
	    if (position == 0)
		return section;
	    if (position < size)
		return adapter.getItem(position - 1);

	    // passe à la section suivant
	    position -= size;
	}
	return null;
    }

    // renvoi le nombre d'item
    public int getCount() {
	// total de l'ensemble des sections, plus une pour chaque tête de
	// section
	int total = 0;
	for (Adapter adapter : this.sections.values())
	    total += adapter.getCount() + 1;
	return total;
    }

    public int getViewTypeCount() {
	int total = 1;
	for (Adapter adapter : this.sections.values())
	    total += adapter.getViewTypeCount();
	return total;
    }

    public int getItemViewType(int position) {
	int type = 1;
	for (Object section : this.sections.keySet()) {
	    Adapter adapter = sections.get(section);
	    int size = adapter.getCount() + 1;

	    // Récupération de la position dans la section
	    if (position == 0)
		return TYPE_SECTION_HEADER;
	    if (position < size)
		return type + adapter.getItemViewType(position - 1);

	    // passe a la section suivante moins un par l'entête
	    position -= size;
	    type += adapter.getViewTypeCount();
	}
	return -1;
    }

    public boolean areAllItemsSelectable() {
	return false;
    }

    public boolean isEnabled(int position) {
	return (getItemViewType(position) != TYPE_SECTION_HEADER);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
	int sectionnum = 0;
	for (Object section : this.sections.keySet()) {
	    Adapter adapter = sections.get(section);
	    int size = adapter.getCount() + 1;

	    // Récupération de la position dans la section
	    if (position == 0)
		return headers.getView(sectionnum, convertView, parent);
	    if (position < size)
		return adapter.getView(position - 1, convertView, parent);

	    // otherwise jump into next section
	    position -= size;
	    sectionnum++;
	}
	return null;
    }

    public long getItemId(int position) {
	return position;
    }
}
