package com.knuchel.start.android.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.knuchel.start.android.R;

public class Popup extends Activity {

    public static void display(Context context, Activity activity,
	    String title, String message) {
	AlertDialog.Builder adb = new AlertDialog.Builder(activity);
	adb.setTitle(title)
		.setMessage(message)
		.setPositiveButton(Strings.get(context, R.string.validate),
			new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,
				    int which) {

			    }
			}).show();
    }

    public static void displayAbout(Context context, Activity activity) {
	LayoutInflater factory = LayoutInflater.from(context);
	final View alertDialogView = factory.inflate(R.layout.dialog_about,
		null);

	AlertDialog.Builder adb = new AlertDialog.Builder(activity);
	adb.setView(alertDialogView)
		.setTitle(Strings.get(context, R.string.abouttitle))
		.setIcon(R.drawable.about)
		.setPositiveButton(Strings.get(context, R.string.validate),
			new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,
				    int which) {

			    }
			}).show();
    }

    public static void displayIfFirstUse(final Context context,
	    final Activity activity) {
	SharedPreferences prefs = context.getSharedPreferences(Config.PREFS, 0);

	if (prefs.getBoolean(Config.PREFS_SHOW_START_POPUP, true)) {
	    LayoutInflater factory = LayoutInflater.from(activity);
	    final View alertDialogView = factory.inflate(R.layout.dialog_start,
		    null);

	    AlertDialog.Builder adb = new AlertDialog.Builder(activity);
	    adb.setView(alertDialogView)
		    .setTitle(Strings.get(context, R.string.starttitle))
		    .setIcon(R.drawable.about)
		    .setPositiveButton(Strings.get(context, R.string.validate),
			    new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
					int which) {
				    CheckBox noDisplay = (CheckBox) alertDialogView
					    .findViewById(R.id.startcontentshow);
				    if (noDisplay.isChecked()) {
					final SharedPreferences.Editor editor = context
						.getSharedPreferences(
							Config.PREFS, 0).edit();
					editor.putBoolean(
						Config.PREFS_SHOW_START_POPUP,
						false);
					editor.commit();
				    }
				}
			    }).show();
	}
    }

    public static void displayRatingApp(final Context context,
	    final Activity activity, int startCount, Boolean alwaysShow) {
	int nbStart = 0;
	SharedPreferences prefs = context.getSharedPreferences(Config.PREFS, 0);
	nbStart = prefs.getInt(Config.PREFS_START_COUNT, 1);
	final SharedPreferences.Editor editor = prefs.edit();
	editor.putInt(Config.PREFS_START_COUNT, nbStart + 1);
	editor.commit();
	// Toast.makeText(context, "start no "+nbStart+" (< "+startCount+" ?)",
	// Toast.LENGTH_SHORT).show();

	if (nbStart > startCount
		&& (prefs.getBoolean(Config.PREFS_START_COUNT_SHOW, true) || alwaysShow)) {
	    LayoutInflater factory = LayoutInflater.from(activity);
	    final View alertDialogView = factory.inflate(
		    R.layout.dialog_rateapp, null);

	    AlertDialog.Builder adb = new AlertDialog.Builder(activity);
	    adb.setView(alertDialogView)
		    .setTitle(Strings.get(context, R.string.ratingtitle))
		    .setIcon(R.drawable.about)
		    .setNegativeButton(Strings.get(context, R.string.no),
			    new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
					int which) {
				    CheckBox noDisplay = (CheckBox) alertDialogView
					    .findViewById(R.id.ratingcontentshow);
				    if (noDisplay.isChecked()) {
					final SharedPreferences.Editor editor = context
						.getSharedPreferences(
							Config.PREFS, 0).edit();
					editor.putBoolean(
						Config.PREFS_START_COUNT_SHOW,
						false);
					editor.commit();
				    }
				}
			    })
		    .setPositiveButton(Strings.get(context, R.string.yes),
			    new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
					int which) {
				    CheckBox noDisplay = (CheckBox) alertDialogView
					    .findViewById(R.id.ratingcontentshow);
				    if (noDisplay.isChecked()) {
					final SharedPreferences.Editor editor = context
						.getSharedPreferences(
							Config.PREFS, 0).edit();
					editor.putBoolean(
						Config.PREFS_START_COUNT_SHOW,
						false);
					editor.commit();
				    }
				    final Intent intent = new Intent(
					    Intent.ACTION_VIEW);
				    intent.setData(Uri
					    .parse("market://details?id="
						    + Config.APP_PACKAGE));
				    activity.startActivity(intent);
				}
			    }).show();
	}
    }
}
