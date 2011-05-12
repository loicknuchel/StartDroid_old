package com.knuchel.start.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.knuchel.start.android.config.Config;
import com.knuchel.start.android.model.SamplePrefs;
import com.knuchel.start.android.utils.Network;
import com.sfeir.android.friendapps.FriendListActivity;

public class StartActivity extends Activity {
	private Button goSampleSettings;
	private Button readSampleSettings;
	private Button isNetworkAvailable;
	
	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.start);
			setUp();
			onCLickValidate();
    }
	
	protected void setUp() {
		goSampleSettings = (Button) findViewById(R.id.goSampleSettings);
		readSampleSettings = (Button) findViewById(R.id.readSampleSettings);
		isNetworkAvailable = (Button) findViewById(R.id.isNetworkAvailable);
	}
	
	private void onCLickValidate(){
		goSampleSettings.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(getBaseContext(), SampleSettingsActivity.class);
    			startActivity(i);
            }
        });
		
		readSampleSettings.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	SamplePrefs samplePrefs = savePrefs();
            	
            	Toast.makeText(getBaseContext(), 
            		"CheckboxPreference: "+Boolean.toString(samplePrefs.isCheckboxPreference()) +
            		"\nListPreference: "+samplePrefs.getListPreference() +
            		"\neditTextPreference: "+samplePrefs.getEditTextPreference() +
            		"\nringtonePreference: "+samplePrefs.getRingtonePreference() +
            		"\ncustomPref: "+samplePrefs.getCustomPref() +
            		"\nsecondEditTextPreference: "+samplePrefs.getSecondEditTextPreference(), Toast.LENGTH_LONG).show();
            }
        });
		
		isNetworkAvailable.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Toast.makeText(getBaseContext(), "network available : "+Network.isNetworkAvailable(getBaseContext()), Toast.LENGTH_LONG).show();
            }
		});
	}
	
    // lecture des preferences
    private SamplePrefs savePrefs() {
    	SamplePrefs samplePrefs = new SamplePrefs();
    	
        // Get the xml/preferences.xml preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        samplePrefs.setCheckboxPreference(prefs.getBoolean(Config.SAMPLE_PREFS_NAME_CHECKBOX, Config.SAMPLE_PREFS_DEFAULTVALUE_CHECKBOX));
        samplePrefs.setListPreference(prefs.getString(Config.SAMPLE_PREFS_NAME_LIST, Config.SAMPLE_PREFS_DEFAULTVALUE_LIST));
        samplePrefs.setEditTextPreference(prefs.getString(Config.SAMPLE_PREFS_NAME_EDITTEXT, Config.SAMPLE_PREFS_DEFAULTVALUE_EDITTEXT));
        samplePrefs.setRingtonePreference(prefs.getString(Config.SAMPLE_PREFS_NAME_RINGTONE, Config.SAMPLE_PREFS_DEFAULTVALUE_RINGTONE));
        samplePrefs.setSecondEditTextPreference(prefs.getString(Config.SAMPLE_PREFS_NAME_SECONDEDITTEXT, Config.SAMPLE_PREFS_DEFAULTVALUE_SECONDEDITTEXT));
        // Get the custom preference
        SharedPreferences mySharedPreferences = getSharedPreferences(Config.SAMPLE_PREFS2, Activity.MODE_PRIVATE);
        samplePrefs.setCustomPref(mySharedPreferences.getString(Config.SAMPLE_PREFS_NAME_CUSTOM, Config.SAMPLE_PREFS_DEFAULTVALUE_CUSTOM));
        
        return samplePrefs;
    }
	
    
    
	// gestion du menu
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		menu.getItem(1).getSubMenu().setHeaderIcon(R.drawable.help);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch (item.getItemId()) {
		case R.id.menusettings:
			i = new Intent(getBaseContext(), SettingsActivity.class);
			startActivity(i);
			return true;
		case R.id.menuhelp:
			Toast.makeText(getBaseContext(), "menuhelp", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.sousmenufaq:
			Toast.makeText(getBaseContext(), "sousmenufaq", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.sousmenuabout:
			Toast.makeText(getBaseContext(), "sousmenuabout", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}
}