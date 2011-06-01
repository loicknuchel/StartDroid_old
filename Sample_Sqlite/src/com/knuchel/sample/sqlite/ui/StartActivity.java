package com.knuchel.sample.sqlite.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.knuchel.sample.sqlite.R;

public class StartActivity extends Activity {
    private Context c;
    private Button crud;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_start);
	c = getApplicationContext();
	setUp();
	onCLickValidate();
    }

    protected void setUp() {
	crud = (Button) findViewById(R.activity_start.crud);
    }

    private void onCLickValidate() {
	crud.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Intent i = new Intent(c, CRUDActivity.class);
		startActivity(i);
	    }
	});
    }
}
