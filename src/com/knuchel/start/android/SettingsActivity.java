package com.knuchel.start.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.knuchel.start.android.config.Config;
import com.knuchel.start.android.utils.Popup;

public class SettingsActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
		// Gestion des preferences custom
		Preference FAQPref = (Preference) findPreference("FAQPref");
		FAQPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Toast.makeText(getBaseContext(), "FAQPref clicked", Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		Preference AboutPref = (Preference) findPreference("AboutPref");
		AboutPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Popup.displayAbout(getApplicationContext(), SettingsActivity.this);
				return true;
			}
		});

		Preference FeedbackPref = (Preference) findPreference("FeedbackPref");
		FeedbackPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Toast.makeText(getBaseContext(), "FeedbackPref clicked", Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		Preference SharePref = (Preference) findPreference("SharePref");
		SharePref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Toast.makeText(getBaseContext(), "SharePref clicked", Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		Preference FriendPref = (Preference) findPreference("FriendPref");
		FriendPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Toast.makeText(getBaseContext(), "FriendPref clicked", Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		Preference InitPref = (Preference) findPreference("InitPref");
		InitPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				final SharedPreferences.Editor editor = getSharedPreferences(Config.PREFS, 0).edit();
		    	editor.putBoolean(Config.PREFS_SHOW_START_POPUP, true);
		    	editor.commit();
				Toast.makeText(getBaseContext(), getResources().getString(R.string.initappdone), Toast.LENGTH_LONG).show();
				return true;
			}
		});
		
		Preference ClosePref = (Preference) findPreference("ClosePref");
		ClosePref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Toast.makeText(getBaseContext(), "ClosePref clicked", Toast.LENGTH_SHORT).show();
//				SharedPreferences customSharedPreference = getSharedPreferences("myCustomSharedPrefs", Activity.MODE_PRIVATE);
//				SharedPreferences.Editor editor = customSharedPreference.edit();
//				editor.putString("myCustomPref", "The preference has been clicked");
//				editor.commit();
				
				System.exit(0);
				return true;
			}
		});
	}
}
