package com.knuchel.start.android;

import com.knuchel.start.android.config.Config;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.View;
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
				LayoutInflater factory = LayoutInflater.from(SettingsActivity.this);
            	final View alertDialogView = factory.inflate(R.layout.alertdialogabout, null);
            	
            	AlertDialog.Builder adb = new AlertDialog.Builder(SettingsActivity.this);
            	adb.setView(alertDialogView)
            		.setTitle(getResources().getString(R.string.abouttitle))
            		.setIcon(R.drawable.about)
            		.setPositiveButton(getResources().getString(R.string.validate), new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                    	
	                    }
            		})
            		.show();
				
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