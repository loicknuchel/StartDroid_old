package com.knuchel.start.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.knuchel.start.android.config.Config;
import com.knuchel.start.android.model.SamplePrefs;
import com.knuchel.start.android.utils.Network;

/*
 * TODO :
 * 	- Pensez à modifier le nom de package !!!
 */

public class StartActivity extends Activity {
	private Button test;
	private Button goDashboard;
	private Button goTabActivity;
	private Button goSampleSettings;
	private Button readSampleSettings;
	private Button isNetworkAvailable;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.start);
			displayFirstUseDialogBox();
			setUp();
			onCLickValidate();
    }
	
	protected void setUp() {
		test = (Button) findViewById(R.id.test);
		goDashboard = (Button) findViewById(R.id.goDashboard);
		goTabActivity = (Button) findViewById(R.id.goTabActivity);
		goSampleSettings = (Button) findViewById(R.id.goSampleSettings);
		readSampleSettings = (Button) findViewById(R.id.readSampleSettings);
		isNetworkAvailable = (Button) findViewById(R.id.isNetworkAvailable);
	}
	
	private void onCLickValidate(){
		test.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Toast.makeText(getBaseContext(), "Test !!!", Toast.LENGTH_SHORT).show();
            }
		});
		
		goDashboard.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(getBaseContext(), DashboardActivity.class);
    			startActivity(i);
            }
        });
		
		goTabActivity.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(getBaseContext(), TabHostActivity.class);
    			startActivity(i);
            }
        });

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
	
	
    private SamplePrefs savePrefs() {
    	SamplePrefs samplePrefs = new SamplePrefs();
    	
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        samplePrefs.setCheckboxPreference(prefs.getBoolean(Config.SAMPLE_PREFS_NAME_CHECKBOX, Config.SAMPLE_PREFS_DEFAULTVALUE_CHECKBOX));
        samplePrefs.setListPreference(prefs.getString(Config.SAMPLE_PREFS_NAME_LIST, Config.SAMPLE_PREFS_DEFAULTVALUE_LIST));
        samplePrefs.setEditTextPreference(prefs.getString(Config.SAMPLE_PREFS_NAME_EDITTEXT, Config.SAMPLE_PREFS_DEFAULTVALUE_EDITTEXT));
        samplePrefs.setRingtonePreference(prefs.getString(Config.SAMPLE_PREFS_NAME_RINGTONE, Config.SAMPLE_PREFS_DEFAULTVALUE_RINGTONE));
        samplePrefs.setSecondEditTextPreference(prefs.getString(Config.SAMPLE_PREFS_NAME_SECONDEDITTEXT, Config.SAMPLE_PREFS_DEFAULTVALUE_SECONDEDITTEXT));
        
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
			LayoutInflater factory = LayoutInflater.from(StartActivity.this);
        	final View alertDialogView = factory.inflate(R.layout.alertdialogabout, null);
        	
        	AlertDialog.Builder adb = new AlertDialog.Builder(StartActivity.this);
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
		return false;
	}
	
	private void displayFirstUseDialogBox() {
		SharedPreferences prefs = getSharedPreferences(Config.PREFS, 0);
		
		if(prefs.getBoolean(Config.PREFS_SHOW_START_POPUP, true)){
			LayoutInflater factory = LayoutInflater.from(StartActivity.this);
	    	final View alertDialogView = factory.inflate(R.layout.alertdialogstart, null);
	    	
	    	AlertDialog.Builder adb = new AlertDialog.Builder(StartActivity.this);
	    	adb.setView(alertDialogView)
	    		.setTitle(getResources().getString(R.string.starttitle))
	    		.setIcon(R.drawable.about)
	    		.setPositiveButton(getResources().getString(R.string.validate), new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            	CheckBox noDisplay = (CheckBox) alertDialogView.findViewById(R.id.startcontentshow);
		    	    	if(noDisplay.isChecked()){
		    		    	final SharedPreferences.Editor editor = getSharedPreferences(Config.PREFS, 0).edit();
		    		    	editor.putBoolean(Config.PREFS_SHOW_START_POPUP, false);
		    		    	editor.commit();
		    	    	}
		            }
	    		})
	    		.show();
		}
	}
}