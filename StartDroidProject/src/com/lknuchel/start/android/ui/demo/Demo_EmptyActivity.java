package com.lknuchel.start.android.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.lknuchel.start.android.R;
import com.lknuchel.start.android.util.Config;

public class Demo_EmptyActivity extends Activity {
    private Context c;
    private Button serviceScan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_empty);
	c = getApplicationContext();
	setUp();
	onCLickValidate();
    }

    protected void setUp() {
    }

    private void onCLickValidate() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	if (requestCode == Config.SCANNER_CODE) {
	    if (resultCode == RESULT_OK) {
		
	    } else {
		
	    }
	}
    }
}
