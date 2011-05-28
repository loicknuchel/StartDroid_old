package com.knuchel.start.android.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.knuchel.start.android.R;
import com.knuchel.start.android.config.Config;
import com.knuchel.start.android.util.Strings;

public class Demo_Settings_MainActivity extends PreferenceActivity {
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.preferences_sample);
	c = getApplicationContext();
	// Get the custom preference
	Preference customPref = (Preference) findPreference(Config.SAMPLE_PREFS);

	customPref
		.setOnPreferenceClickListener(new OnPreferenceClickListener() {
		    public boolean onPreferenceClick(Preference preference) {
			Toast.makeText(
				getBaseContext(),
				Strings.get(c, R.string.SSA_custom_btn_clicked),
				Toast.LENGTH_LONG).show();

			SharedPreferences customSharedPreference = getSharedPreferences(
				Config.SAMPLE_PREFS2, Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = customSharedPreference
				.edit();
			editor.putString(Config.SAMPLE_PREFS_NAME_CUSTOM,
				Strings.get(c, R.string.SSA_btn_clicked));
			editor.commit();
			return true;
		    }
		});
    }
}
