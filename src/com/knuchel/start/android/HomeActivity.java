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
import com.knuchel.start.android.model.SamplePrefs;
import com.knuchel.start.android.utils.ExtraIntent;
import com.knuchel.start.android.utils.Menus;
import com.knuchel.start.android.utils.Network;
import com.knuchel.start.android.utils.Popup;
import com.knuchel.start.android.utils.Strings;
import com.knuchel.start.android.widget.ActionBar;
import com.knuchel.start.android.widget.ActionBar.Action;
import com.knuchel.start.android.widget.ActionBar.IntentAction;

/*
 * TODO :
 * 	- Pensez à modifier le nom de package !!!
 */

public class HomeActivity extends Activity {
    private Context c;
    private ActionBar actionBar;
    // private Action homeAction;
    private Action shareAction;
    private Action otherAction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
	c = getApplicationContext();
	doAction();
	Popup.displayRatingApp(getApplicationContext(), HomeActivity.this, 6,
		false);
	Popup.displayIfFirstUse(getApplicationContext(), HomeActivity.this);
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
	shareAction = new IntentAction(this, ExtraIntent.shareIntent(Strings
		.get(c, R.string.ABHA_share)),
		R.drawable.ic_title_share_default);
	otherAction = new IntentAction(this, new Intent(this,
		ActionBarOtherActivity.class),
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
	actionBar.setTitle(Strings.get(c, R.string.ABHA_title));
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
			Strings.get(c, R.string.HA_layout_btn),
			Toast.LENGTH_SHORT).show();
		break;
	    case R.id.home_services_btn:
		Toast.makeText(getApplicationContext(),
			Strings.get(c, R.string.HA_services_btn),
			Toast.LENGTH_SHORT).show();
		break;
	    case R.id.home_1_btn:
		Toast.makeText(getApplicationContext(),
			Strings.get(c, R.string.HA_1_btn), Toast.LENGTH_SHORT)
			.show();
		break;
	    case R.id.home_2_btn:
		Toast.makeText(getApplicationContext(),
			Strings.get(c, R.string.HA_2_btn), Toast.LENGTH_SHORT)
			.show();
		break;
	    case R.id.home_3_btn:
		Toast.makeText(getApplicationContext(),
			Strings.get(c, R.string.HA_3_btn), Toast.LENGTH_SHORT)
			.show();
		break;
	    case R.id.home_4_btn:
		Toast.makeText(getApplicationContext(),
			Strings.get(c, R.string.HA_4_btn), Toast.LENGTH_SHORT)
			.show();
		break;
	    default:
		break;
	    }
	}
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
	return Menus.classicCreate(HomeActivity.this, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
	return Menus.classicSelect(getApplicationContext(), HomeActivity.this,
		item);
    }

}