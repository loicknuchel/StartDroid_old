package com.knuchel.start.android;

import com.knuchel.start.android.config.Config;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
import android.widget.Toast;

public class SampleSettingsActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_sample);
		// Get the custom preference
		Preference customPref = (Preference) findPreference(Config.SAMPLE_PREFS);
		
		customPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Toast.makeText(getBaseContext(), "The custom preference has been clicked", Toast.LENGTH_LONG).show();
				
				SharedPreferences customSharedPreference = getSharedPreferences(Config.SAMPLE_PREFS2, Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = customSharedPreference.edit();
				editor.putString(Config.SAMPLE_PREFS_NAME_CUSTOM, "The preference has been clicked");
				editor.commit();
				return true;
			}
		});
	}
}
