package com.lknuchel.android.startdroidfull.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.lknuchel.android.startdroidfull.R;

// TODO ajouter de la généricité et factoriser le code !!!

public class Dialog extends Activity {

    public static void display(final Activity a, final String title,
	    final String message) {
	AlertDialog.Builder adb = new AlertDialog.Builder(a);
	adb.setTitle(title)
		.setMessage(message)
		.setPositiveButton(
			a.getResources().getString(R.string.validate),
			new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,
				    int which) {

			    }
			});
	adb.show();
    }

    public static void display(final Activity a, final String message) {
	AlertDialog.Builder adb = new AlertDialog.Builder(a);
	adb.setMessage(message).setPositiveButton(
		a.getResources().getString(R.string.validate),
		new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {

		    }
		});
	adb.show();
    }

    public static void displayChoice(final Activity a, final String title,
	    final int requestCode, final String message) {
	AlertDialog.Builder adb = new AlertDialog.Builder(a);
	adb.setTitle(title)
		.setMessage(message)
		.setPositiveButton(a.getResources().getString(R.string.yes),
			new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,
				    int which) {
				if (a instanceof DialogAdapter) {
				    ((DialogAdapter) a).onDialogResult(
					    requestCode, true);
				} else {
				    throw new IllegalArgumentException(
					    "Activity has to implement DialogAdapter interface");
				}
			    }
			})
		.setNegativeButton(a.getResources().getString(R.string.no),
			new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,
				    int which) {
				if (a instanceof DialogAdapter) {
				    ((DialogAdapter) a).onDialogResult(
					    requestCode, false);
				} else {
				    throw new IllegalArgumentException(
					    "Activity has to implement DialogAdapter interface");
				}
			    }
			});
	adb.show();
    }

    public static void displayChoice(final Activity a, final int requestCode,
	    final String message) {
	AlertDialog.Builder adb = new AlertDialog.Builder(a);
	adb.setMessage(message)
		.setPositiveButton(a.getResources().getString(R.string.yes),
			new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,
				    int which) {
				if (a instanceof DialogAdapter) {
				    ((DialogAdapter) a).onDialogResult(
					    requestCode, true);
				} else {
				    throw new IllegalArgumentException(
					    "Activity has to implement DialogAdapter interface");
				}
			    }
			})
		.setNegativeButton(a.getResources().getString(R.string.no),
			new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,
				    int which) {
				if (a instanceof DialogAdapter) {
				    ((DialogAdapter) a).onDialogResult(
					    requestCode, false);
				} else {
				    throw new IllegalArgumentException(
					    "Activity has to implement DialogAdapter interface");
				}
			    }
			});
	adb.show();
    }

    public static void displayAbout(final Activity a) {
	LayoutInflater factory = LayoutInflater.from(a.getBaseContext());
	final View alertDialogView = factory.inflate(R.layout.dialog_about,
		null);

	AlertDialog.Builder adb = new AlertDialog.Builder(a);
	adb.setView(alertDialogView)
		.setTitle(a.getResources().getString(R.string.abouttitle))
		.setIcon(R.drawable.about)
		.setPositiveButton(
			a.getResources().getString(R.string.validate),
			new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,
				    int which) {

			    }
			}).show();
    }

    public static void displayIfFirstUse(final Activity a) {
	SharedPreferences prefs = a.getBaseContext().getSharedPreferences(
		Config.PREFS, 0);

	if (prefs.getBoolean(Config.PREFS_SHOW_START_POPUP, true)) {
	    LayoutInflater factory = LayoutInflater.from(a);
	    final View alertDialogView = factory.inflate(R.layout.dialog_start,
		    null);

	    AlertDialog.Builder adb = new AlertDialog.Builder(a);
	    adb.setView(alertDialogView)
		    .setTitle(a.getResources().getString(R.string.starttitle))
		    .setIcon(R.drawable.about)
		    .setPositiveButton(
			    a.getResources().getString(R.string.validate),
			    new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
					int which) {
				    CheckBox noDisplay = (CheckBox) alertDialogView
					    .findViewById(R.id.startcontentshow);
				    if (noDisplay.isChecked()) {
					final SharedPreferences.Editor editor = a
						.getBaseContext()
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

    public static void displayRatingApp(final Activity a, final int startCount,
	    final Boolean alwaysShow) {
	int nbStart = 0;
	SharedPreferences prefs = a.getBaseContext().getSharedPreferences(
		Config.PREFS, 0);
	nbStart = prefs.getInt(Config.PREFS_START_COUNT, 1);
	final SharedPreferences.Editor editor = prefs.edit();
	editor.putInt(Config.PREFS_START_COUNT, nbStart + 1);
	editor.commit();
	// Toast.makeText(context, "start no "+nbStart+" (< "+startCount+" ?)",
	// Toast.LENGTH_SHORT).show();

	if (nbStart > startCount
		&& (prefs.getBoolean(Config.PREFS_START_COUNT_SHOW, true) || alwaysShow)) {
	    LayoutInflater factory = LayoutInflater.from(a);
	    final View alertDialogView = factory.inflate(
		    R.layout.dialog_rateapp, null);

	    AlertDialog.Builder adb = new AlertDialog.Builder(a);
	    adb.setView(alertDialogView)
		    .setTitle(a.getResources().getString(R.string.ratingtitle))
		    .setIcon(R.drawable.about)
		    .setNegativeButton(a.getResources().getString(R.string.no),
			    new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
					int which) {
				    CheckBox noDisplay = (CheckBox) alertDialogView
					    .findViewById(R.id.ratingcontentshow);
				    if (noDisplay.isChecked()) {
					final SharedPreferences.Editor editor = a
						.getBaseContext()
						.getSharedPreferences(
							Config.PREFS, 0).edit();
					editor.putBoolean(
						Config.PREFS_START_COUNT_SHOW,
						false);
					editor.commit();
				    }
				}
			    })
		    .setPositiveButton(
			    a.getResources().getString(R.string.yes),
			    new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
					int which) {
				    CheckBox noDisplay = (CheckBox) alertDialogView
					    .findViewById(R.id.ratingcontentshow);
				    if (noDisplay.isChecked()) {
					final SharedPreferences.Editor editor = a
						.getBaseContext()
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
				    a.startActivity(intent);
				}
			    }).show();
	}
    }
}
