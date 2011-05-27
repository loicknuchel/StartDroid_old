package com.knuchel.start.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.knuchel.start.android.config.Config;
import com.knuchel.start.android.demo.Demo_ActionBar_MainActivity;
import com.knuchel.start.android.demo.Demo_Dashboard_MainActivity;
import com.knuchel.start.android.demo.Demo_Services_MainActivity;
import com.knuchel.start.android.demo.Demo_Settings_MainActivity;
import com.knuchel.start.android.demo.Demo_TabActivity_HostActivity;
import com.knuchel.start.android.model.SamplePrefs;
import com.knuchel.start.android.utils.Menus;
import com.knuchel.start.android.utils.Network;
import com.knuchel.start.android.utils.Popup;
import com.knuchel.start.android.utils.Strings;

/*
 * TODO :
 * 	- Pensez à modifier le nom de package !!!
 */

public class StartActivity extends Activity {
    private Context c;
    private Button test;
    private Button goDashboard;
    private Button goActionBar;
    private Button goTabActivity;
    private Button goServicesActivity;
    private Button goSampleSettings;
    private Button readSampleSettings;
    private Button isNetworkAvailable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_start);
	c = getApplicationContext();
	doAction();
	Popup.displayRatingApp(getApplicationContext(), StartActivity.this, 6,
		false);
	Popup.displayIfFirstUse(getApplicationContext(), StartActivity.this);
	setUp();
	onCLickValidate();
    }

    protected void doAction() {
	String action = getIntent().getStringExtra("action");
	if (action != null) {
	    if (action.equals("finish")) {
		Toast.makeText(c, "FINISH", Toast.LENGTH_LONG).show();
		finish();
	    }
	}
    }

    protected void setUp() {
	test = (Button) findViewById(R.id.test);
	goDashboard = (Button) findViewById(R.id.goDashboard);
	goActionBar = (Button) findViewById(R.id.goActionBar);
	goTabActivity = (Button) findViewById(R.id.goTabActivity);
	goServicesActivity = (Button) findViewById(R.id.goServicesActivity);
	goSampleSettings = (Button) findViewById(R.id.goSampleSettings);
	readSampleSettings = (Button) findViewById(R.id.readSampleSettings);
	isNetworkAvailable = (Button) findViewById(R.id.isNetworkAvailable);
    }

    private void onCLickValidate() {
	test.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Toast.makeText(getBaseContext(),
			Strings.get(c, R.string.SAstart_testFeaturetexte),
			Toast.LENGTH_SHORT).show();
	    }
	});

	goDashboard.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(getBaseContext(),
			Demo_Dashboard_MainActivity.class);
		startActivity(i);
	    }
	});

	goActionBar.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(getBaseContext(),
			Demo_ActionBar_MainActivity.class);
		startActivity(i);
	    }
	});

	goTabActivity.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(getBaseContext(),
			Demo_TabActivity_HostActivity.class);
		startActivity(i);
	    }
	});

	goServicesActivity.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(getBaseContext(),
			Demo_Services_MainActivity.class);
		startActivity(i);
	    }
	});

	goSampleSettings.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(getBaseContext(),
			Demo_Settings_MainActivity.class);
		startActivity(i);
	    }
	});

	readSampleSettings.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		SamplePrefs samplePrefs = savePrefs();

		Toast.makeText(
			getBaseContext(),
			"CheckboxPreference: "
				+ Boolean.toString(samplePrefs
					.isCheckboxPreference())
				+ "\nListPreference: "
				+ samplePrefs.getListPreference()
				+ "\neditTextPreference: "
				+ samplePrefs.getEditTextPreference()
				+ "\nringtonePreference: "
				+ samplePrefs.getRingtonePreference()
				+ "\ncustomPref: "
				+ samplePrefs.getCustomPref()
				+ "\nsecondEditTextPreference: "
				+ samplePrefs.getSecondEditTextPreference(),
			Toast.LENGTH_LONG).show();
	    }
	});

	isNetworkAvailable.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Toast.makeText(
			getBaseContext(),
			Strings.get(c, R.string.SAstart_NetworkAvailable)
				+ " : "
				+ Network.isNetworkAvailable(getBaseContext()),
			Toast.LENGTH_LONG).show();
	    }
	});
    }

    private SamplePrefs savePrefs() {
	SamplePrefs samplePrefs = new SamplePrefs();

	SharedPreferences prefs = PreferenceManager
		.getDefaultSharedPreferences(getBaseContext());
	samplePrefs.setCheckboxPreference(prefs.getBoolean(
		Config.SAMPLE_PREFS_NAME_CHECKBOX,
		Config.SAMPLE_PREFS_DEFAULTVALUE_CHECKBOX));
	samplePrefs.setListPreference(prefs.getString(
		Config.SAMPLE_PREFS_NAME_LIST,
		Config.SAMPLE_PREFS_DEFAULTVALUE_LIST));
	samplePrefs.setEditTextPreference(prefs.getString(
		Config.SAMPLE_PREFS_NAME_EDITTEXT,
		Config.SAMPLE_PREFS_DEFAULTVALUE_EDITTEXT));
	samplePrefs.setRingtonePreference(prefs.getString(
		Config.SAMPLE_PREFS_NAME_RINGTONE,
		Config.SAMPLE_PREFS_DEFAULTVALUE_RINGTONE));
	samplePrefs.setSecondEditTextPreference(prefs.getString(
		Config.SAMPLE_PREFS_NAME_SECONDEDITTEXT,
		Config.SAMPLE_PREFS_DEFAULTVALUE_SECONDEDITTEXT));

	SharedPreferences mySharedPreferences = getSharedPreferences(
		Config.SAMPLE_PREFS2, Activity.MODE_PRIVATE);
	samplePrefs.setCustomPref(mySharedPreferences.getString(
		Config.SAMPLE_PREFS_NAME_CUSTOM,
		Config.SAMPLE_PREFS_DEFAULTVALUE_CUSTOM));

	return samplePrefs;
    }

    // gestion du menu
    public boolean onCreateOptionsMenu(Menu menu) {
	return Menus.classicCreate(StartActivity.this, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
	return Menus.classicSelect(getApplicationContext(), StartActivity.this,
		item);
    }

}