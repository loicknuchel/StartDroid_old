package com.lknuchel.android.startdroidlight.util;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.lknuchel.android.startdroidlight.R;
import com.lknuchel.android.startdroidlight.ui.SettingsActivity;

public class Menus {
    public static boolean classicCreate(Activity a, Menu menu) {
	MenuInflater inflater = a.getMenuInflater();
	inflater.inflate(R.menu.menu, menu);
	menu.getItem(1).getSubMenu().setHeaderIcon(R.drawable.help);
	return true;
    }

    public static boolean classicSelect(Activity a, MenuItem item) {
	Intent i;
	switch (item.getItemId()) {
	case R.id.menusettings:
	    i = new Intent(a.getBaseContext(), SettingsActivity.class);
	    a.startActivity(i);
	    return true;
	case R.id.menuhelp:
	    Toast.makeText(a.getBaseContext(),
		    a.getResources().getString(R.string.SAstart_MenuHelp),
		    Toast.LENGTH_SHORT).show();
	    return true;
	case R.id.sousmenufaq:
	    Toast.makeText(a.getBaseContext(),
		    a.getResources().getString(R.string.SAstart_MenuFAQ),
		    Toast.LENGTH_SHORT).show();
	    return true;
	case R.id.sousmenuabout:
	    Dialog.displayAbout(a);
	    return true;
	}
	return false;
    }
}
