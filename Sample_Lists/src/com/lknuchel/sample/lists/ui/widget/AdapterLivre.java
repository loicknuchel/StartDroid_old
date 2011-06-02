package com.lknuchel.sample.lists.ui.widget;

import java.util.List;

import com.lknuchel.sample.lists.R;
import com.lknuchel.sample.lists.model.Livre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterLivre extends BaseAdapter {
    private List<Livre> biblio;
    private LayoutInflater inflater;

    public AdapterLivre(Context context, List<Livre> biblio) {
	inflater = LayoutInflater.from(context);
	this.biblio = biblio;
    }

    @Override
    public int getCount() {
	return biblio.size();
    }

    @Override
    public Object getItem(int position) {
	return biblio.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    private class ViewHolder {
	TextView tvTitre;
	TextView tvAuteur;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder;
	if (convertView == null) {
	    holder = new ViewHolder();
	    convertView = inflater.inflate(R.layout.adapter_demo_itemlist_livre, null);
	    holder.tvTitre = (TextView) convertView
		    .findViewById(R.itemlist_demo_livre.tvTitre);
	    holder.tvAuteur = (TextView) convertView
		    .findViewById(R.itemlist_demo_livre.tvAuteur);
	    convertView.setTag(holder);
	} else {
	    holder = (ViewHolder) convertView.getTag();
	}
	holder.tvTitre.setText(biblio.get(position).getTitre());
	holder.tvAuteur.setText(biblio.get(position).getAuteur());
	return convertView;
    }

}
