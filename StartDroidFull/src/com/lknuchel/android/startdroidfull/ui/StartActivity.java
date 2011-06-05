package com.lknuchel.android.startdroidfull.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.lknuchel.android.startdroidfull.R;
import com.lknuchel.android.startdroidfull.model.SamplePrefs;
import com.lknuchel.android.startdroidfull.ui.demo.Demo_ActionBar_MainActivity;
import com.lknuchel.android.startdroidfull.ui.demo.Demo_Dashboard_MainActivity;
import com.lknuchel.android.startdroidfull.ui.demo.Demo_Services_MainActivity;
import com.lknuchel.android.startdroidfull.ui.demo.Demo_Settings_MainActivity;
import com.lknuchel.android.startdroidfull.ui.demo.Demo_Sqlite_HostActivity;
import com.lknuchel.android.startdroidfull.ui.demo.Demo_TabActivity_HostActivity;
import com.lknuchel.android.startdroidfull.util.Config;
import com.lknuchel.android.startdroidfull.util.Dialog;
import com.lknuchel.android.startdroidfull.util.DialogAdapter;
import com.lknuchel.android.startdroidfull.util.Menus;
import com.lknuchel.android.startdroidfull.util.Network;

/*
 * TODO :
 * 	- Pensez à modifier le nom de package !!!
 */

public class StartActivity extends Activity implements DialogAdapter {
    private Context c;
    private Activity a;
    private Button getCode;
    private Button goDashboard;
    private Button goActionBar;
    private Button goTabActivity;
    private Button goSqliteActivity;
    private Button goServicesActivity;
    private Button goSampleSettings;
    private Button readSampleSettings;
    private Button isNetworkAvailable;
    private final int getCodeRequest = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_start);
	c = getApplicationContext();
	a = StartActivity.this;
	doAction();
	// Popup.displayRatingApp(c, StartActivity.this, 6, false);
	// Popup.displayIfFirstUse(c, StartActivity.this);
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
	getCode = (Button) findViewById(R.activity_start.getCode);
	goDashboard = (Button) findViewById(R.activity_start.goDashboard);
	goActionBar = (Button) findViewById(R.activity_start.goActionBar);
	goTabActivity = (Button) findViewById(R.activity_start.goTabActivity);
	goSqliteActivity = (Button) findViewById(R.activity_start.goSqliteActivity);
	goServicesActivity = (Button) findViewById(R.activity_start.goServicesActivity);
	goSampleSettings = (Button) findViewById(R.activity_start.goSampleSettings);
	readSampleSettings = (Button) findViewById(R.activity_start.readSampleSettings);
	isNetworkAvailable = (Button) findViewById(R.activity_start.isNetworkAvailable);
    }

    public void onDialogResult(int requestCode, boolean result) {
	if (requestCode == getCodeRequest) {
	    if (result == true) {
		final Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(getResources().getString(
			R.string.codeLink)));
		startActivity(intent);
	    } else {

	    }
	}
    }

    private void onCLickValidate() {
	getCode.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Dialog.displayChoice(a, getCodeRequest, getResources()
			.getString(R.string.SAstart_codeAvaiable)
			+ "\n\n"
			+ getResources().getString(R.string.codeLink)
			+ "\n\n"
			+ getResources().getString(R.string.SAstart_seeCode));
	    }
	});

	goDashboard.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(c, Demo_Dashboard_MainActivity.class);
		startActivity(i);
	    }
	});

	goActionBar.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(c, Demo_ActionBar_MainActivity.class);
		startActivity(i);
	    }
	});

	goTabActivity.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(c, Demo_TabActivity_HostActivity.class);
		startActivity(i);
	    }
	});

	goSqliteActivity.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(c, Demo_Sqlite_HostActivity.class);
		startActivity(i);
	    }
	});

	goServicesActivity.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(c, Demo_Services_MainActivity.class);
		startActivity(i);
	    }
	});

	goSampleSettings.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(c, Demo_Settings_MainActivity.class);
		startActivity(i);
	    }
	});

	readSampleSettings.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		SamplePrefs samplePrefs = savePrefs();

		Toast.makeText(
			c,
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
			c,
			getResources().getString(
				R.string.SAstart_NetworkAvailable)
				+ " : " + Network.isNetworkAvailable(c),
			Toast.LENGTH_LONG).show();
	    }
	});
    }

    private SamplePrefs savePrefs() {
	SamplePrefs samplePrefs = new SamplePrefs();

	SharedPreferences prefs = PreferenceManager
		.getDefaultSharedPreferences(c);
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
	return Menus.classicSelect(StartActivity.this, item);
    }

}