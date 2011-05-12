package com.knuchel.start.android;

import com.knuchel.start.android.config.Config;
import com.sfeir.android.friendapps.FriendListActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
import android.widget.Toast;

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
				Toast.makeText(getBaseContext(), "AboutPref clicked", Toast.LENGTH_SHORT).show();
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
				Intent i = new Intent(getBaseContext(), FriendListActivity.class);
				i.putExtra("mail", Config.FA_MAIL);
				i.putExtra("listId", Config.FA_LIST);
				startActivity(i);
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
