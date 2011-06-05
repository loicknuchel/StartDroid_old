package com.lknuchel.android.startdroidlight.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.lknuchel.android.startdroidlight.R;
import com.lknuchel.android.startdroidlight.util.AnalyticsUtils;

/*
 * TODO :
 * 	- Pensez à modifier le nom de package !!!
 */

public class HomeActivity extends Activity {
    private static final String TAG = "HomeActivity";
    private static final String URI = "/Home";

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	if (Log.isLoggable(TAG, Log.DEBUG)) {
	    Log.e(TAG, "Test log : onCreate HomeActivity"); // error
	    Log.w(TAG, "Test log : onCreate HomeActivity"); // warning
	    Log.d(TAG, "Test log : onCreate HomeActivity"); // debug
	    Log.i(TAG, "Test log : onCreate HomeActivity"); // info
	    Log.v(TAG, "Test log : onCreate HomeActivity"); // verbose
	}

	AnalyticsUtils.getInstance(this).trackPageView(URI);
	setContentView(R.layout.activity_home);
    }

}
