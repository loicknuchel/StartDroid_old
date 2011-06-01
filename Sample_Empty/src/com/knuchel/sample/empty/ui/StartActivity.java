package com.knuchel.sample.empty.ui;

import com.knuchel.sample.empty.R;
import com.knuchel.sample.empty.R.layout;

import android.app.Activity;
import android.os.Bundle;

public class StartActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}