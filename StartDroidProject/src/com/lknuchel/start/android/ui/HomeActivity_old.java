package com.lknuchel.start.android.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.lknuchel.start.android.R;
import com.lknuchel.start.android.ui.demo.Demo_ActionBar_OtherActivity;
import com.lknuchel.start.android.ui.widget.ActionBar;
import com.lknuchel.start.android.ui.widget.ActionBar.Action;
import com.lknuchel.start.android.ui.widget.ActionBar.IntentAction;
import com.lknuchel.start.android.util.ExtraIntent;
import com.lknuchel.start.android.util.Menus;

/*
 * TODO :
 * 	- Pensez à modifier le nom de package !!!
 */

public class HomeActivity_old extends Activity {
    private Context c;
    private ActionBar actionBar;
    // private Action homeAction;
    private Action shareAction;
    private Action otherAction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home_old);
	c = getApplicationContext();
	doAction();
	// Popup.displayRatingApp(c, HomeActivity.this, 6, false);
	// Popup.displayIfFirstUse(c, HomeActivity.this);
	setUp();
	setContents();
	onCLickValidate();
    }

    protected void doAction() {
    }

    protected void setUp() {
	actionBar = (ActionBar) findViewById(R.id.homeactionbar);
	// homeAction = new IntentAction(this, createHomeIntent(this),
	// R.drawable.ic_title_home_default);
	shareAction = new IntentAction(this,
		ExtraIntent.shareIntent(getResources().getString(
			R.string.ABHA_share)),
		R.drawable.ic_title_share_default);
	otherAction = new IntentAction(this, new Intent(this,
		Demo_ActionBar_OtherActivity.class),
		R.drawable.ic_title_export_default);

	// attach event handler to dash buttons
	DashboardClickListener dBClickListener = new DashboardClickListener();
	findViewById(R.id.home_layout_btn).setOnClickListener(dBClickListener);
	findViewById(R.id.home_services_btn)
		.setOnClickListener(dBClickListener);
	findViewById(R.id.home_1_btn).setOnClickListener(dBClickListener);
	findViewById(R.id.home_2_btn).setOnClickListener(dBClickListener);
	findViewById(R.id.home_3_btn).setOnClickListener(dBClickListener);
	findViewById(R.id.home_4_btn).setOnClickListener(dBClickListener);
    }

    protected void setContents() {
	// action bar content
	actionBar.setTitle(getResources().getString(R.string.ABHA_title));
	// actionBar.setHomeAction(homeAction); // facultatif !
	actionBar.addAction(shareAction);
	actionBar.addAction(otherAction);
    }

    private void onCLickValidate() {
    }

    private class DashboardClickListener implements OnClickListener {
	@Override
	public void onClick(View v) {
	    switch (v.getId()) {
	    case R.id.home_layout_btn:
		Toast.makeText(getApplicationContext(),
			getResources().getString(R.string.HA_layout_btn),
			Toast.LENGTH_SHORT).show();
		break;
	    case R.id.home_services_btn:
		Toast.makeText(getApplicationContext(),
			getResources().getString(R.string.HA_services_btn),
			Toast.LENGTH_SHORT).show();
		break;
	    case R.id.home_1_btn:
		Toast.makeText(getApplicationContext(),
			getResources().getString(R.string.HA_1_btn),
			Toast.LENGTH_SHORT).show();
		break;
	    case R.id.home_2_btn:
		Toast.makeText(getApplicationContext(),
			getResources().getString(R.string.HA_2_btn),
			Toast.LENGTH_SHORT).show();
		break;
	    case R.id.home_3_btn:
		Toast.makeText(getApplicationContext(),
			getResources().getString(R.string.HA_3_btn),
			Toast.LENGTH_SHORT).show();
		break;
	    case R.id.home_4_btn:
		Toast.makeText(getApplicationContext(),
			getResources().getString(R.string.HA_4_btn),
			Toast.LENGTH_SHORT).show();
		break;
	    default:
		break;
	    }
	}
    }

    // private SamplePrefs savePrefs() {
    // SamplePrefs samplePrefs = new SamplePrefs();
    //
    // SharedPreferences prefs = PreferenceManager
    // .getDefaultSharedPreferences(getBaseContext());
    // samplePrefs.setCheckboxPreference(prefs.getBoolean(
    // Config.SAMPLE_PREFS_NAME_CHECKBOX,
    // Config.SAMPLE_PREFS_DEFAULTVALUE_CHECKBOX));
    // samplePrefs.setListPreference(prefs.getString(
    // Config.SAMPLE_PREFS_NAME_LIST,
    // Config.SAMPLE_PREFS_DEFAULTVALUE_LIST));
    // samplePrefs.setEditTextPreference(prefs.getString(
    // Config.SAMPLE_PREFS_NAME_EDITTEXT,
    // Config.SAMPLE_PREFS_DEFAULTVALUE_EDITTEXT));
    // samplePrefs.setRingtonePreference(prefs.getString(
    // Config.SAMPLE_PREFS_NAME_RINGTONE,
    // Config.SAMPLE_PREFS_DEFAULTVALUE_RINGTONE));
    // samplePrefs.setSecondEditTextPreference(prefs.getString(
    // Config.SAMPLE_PREFS_NAME_SECONDEDITTEXT,
    // Config.SAMPLE_PREFS_DEFAULTVALUE_SECONDEDITTEXT));
    //
    // SharedPreferences mySharedPreferences = getSharedPreferences(
    // Config.SAMPLE_PREFS2, Activity.MODE_PRIVATE);
    // samplePrefs.setCustomPref(mySharedPreferences.getString(
    // Config.SAMPLE_PREFS_NAME_CUSTOM,
    // Config.SAMPLE_PREFS_DEFAULTVALUE_CUSTOM));
    //
    // return samplePrefs;
    // }

    // gestion du menu
    public boolean onCreateOptionsMenu(Menu menu) {
	return Menus.classicCreate(HomeActivity_old.this, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
	return Menus.classicSelect(HomeActivity_old.this, item);
    }

}