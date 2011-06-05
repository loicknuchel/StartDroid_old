package com.lknuchel.android.startdroidlight.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.lknuchel.android.startdroidlight.R;
import com.lknuchel.android.startdroidlight.util.Config;
import com.lknuchel.android.startdroidlight.util.Dialog;
import com.lknuchel.android.startdroidlight.util.ExtraIntent;

public class SettingsActivity extends PreferenceActivity {
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.preferences);
	c = getApplicationContext();

	// Gestion des preferences custom
	Preference FAQPref = (Preference) findPreference("FAQPref");
	FAQPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
	    public boolean onPreferenceClick(Preference preference) {
		Toast.makeText(getBaseContext(),
			getResources().getString(R.string.SApref_faqclicked),
			Toast.LENGTH_SHORT).show();
		return true;
	    }
	});

	Preference AboutPref = (Preference) findPreference("AboutPref");
	AboutPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
	    public boolean onPreferenceClick(Preference preference) {
		Dialog.displayAbout(SettingsActivity.this);
		return true;
	    }
	});

	Preference FeedbackPref = (Preference) findPreference("FeedbackPref");
	FeedbackPref
		.setOnPreferenceClickListener(new OnPreferenceClickListener() {
		    public boolean onPreferenceClick(Preference preference) {
			Dialog.displayRatingApp(SettingsActivity.this, -1, true);
			return true;
		    }
		});

	Preference SharePref = (Preference) findPreference("SharePref");
	SharePref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
	    public boolean onPreferenceClick(Preference preference) {
		startActivity(ExtraIntent.shareIntent(getResources().getString(
			R.string.SApref_shareapptext)));
		return true;
	    }
	});

	Preference FriendPref = (Preference) findPreference("FriendPref");
	FriendPref
		.setOnPreferenceClickListener(new OnPreferenceClickListener() {
		    public boolean onPreferenceClick(Preference preference) {
			Toast.makeText(
				getBaseContext(),
				getResources().getString(
					R.string.SApref_friendclicked),
				Toast.LENGTH_SHORT).show();
			return true;
		    }
		});

	Preference InitPref = (Preference) findPreference("InitPref");
	InitPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
	    public boolean onPreferenceClick(Preference preference) {
		final SharedPreferences.Editor editor = getSharedPreferences(
			Config.PREFS, 0).edit();
		editor.putBoolean(Config.PREFS_SHOW_START_POPUP, true);
		editor.putInt(Config.PREFS_START_COUNT, 0);
		editor.putBoolean(Config.PREFS_START_COUNT_SHOW, true);
		editor.commit();
		Toast.makeText(getBaseContext(),
			getResources().getString(R.string.SApref_initdone),
			Toast.LENGTH_LONG).show();
		return true;
	    }
	});

	Preference ClosePref = (Preference) findPreference("ClosePref");
	ClosePref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
	    public boolean onPreferenceClick(Preference preference) {
		Toast.makeText(getBaseContext(),
			getResources().getString(R.string.SApref_closeclicked),
			Toast.LENGTH_SHORT).show();
		// SharedPreferences customSharedPreference =
		// getSharedPreferences("myCustomSharedPrefs",
		// Activity.MODE_PRIVATE);
		// SharedPreferences.Editor editor =
		// customSharedPreference.edit();
		// editor.putString("myCustomPref",
		// "The preference has been clicked");
		// editor.commit();
		// System.exit(0);
		Intent i = new Intent(c, StartActivity.class);
		i.putExtra("action", "finish");
		startActivity(i);
		finish();
		return true;
	    }
	});
    }
}
