package com.knuchel.start.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.knuchel.start.android.R;
import com.knuchel.start.android.SettingsActivity;

public class Menus {
	public static boolean classicCreate(Activity activity, Menu menu) {
		MenuInflater inflater = activity.getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		menu.getItem(1).getSubMenu().setHeaderIcon(R.drawable.help);
		return true;
	}

	public static boolean classicSelect(Context context, Activity activity,
			MenuItem item) {
		Intent i;
		switch (item.getItemId()) {
		case R.id.menusettings:
			i = new Intent(context, SettingsActivity.class);
			activity.startActivity(i);
			return true;
		case R.id.menuhelp:
			Toast.makeText(context,
					Strings.get(context, R.string.SAstart_MenuHelp),
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.sousmenufaq:
			Toast.makeText(context,
					Strings.get(context, R.string.SAstart_MenuFAQ),
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.sousmenuabout:
			Popup.displayAbout(context, activity);
			return true;
		}
		return false;
	}
}
