package com.lknuchel.android.startdroidlight.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lknuchel.android.startdroidlight.R;
import com.lknuchel.android.startdroidlight.service.ScanService;
import com.lknuchel.android.startdroidlight.util.Config;
import com.lknuchel.android.startdroidlight.util.Dialog;

public class Demo_Services_MainActivity extends Activity {
    private Context c;
    private Button serviceScan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_services);
	c = getApplicationContext();
	setUp();
	onCLickValidate();
    }

    protected void setUp() {
	serviceScan = (Button) findViewById(R.id.serviceScan);
    }

    private void onCLickValidate() {
	serviceScan.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		ScanService.scan(Demo_Services_MainActivity.this,
			Config.SCANNER_CODE);
	    }
	});
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	if (requestCode == Config.SCANNER_CODE) {
	    if (resultCode == RESULT_OK) {
		String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
		String content = intent.getStringExtra("SCAN_RESULT");
		Dialog.display(Demo_Services_MainActivity.this, getResources()
			.getString(R.string.SA_result), getResources()
			.getString(R.string.SA_format)
			+ ": "
			+ format
			+ "\n"
			+ getResources().getString(R.string.SA_content)
			+ ": "
			+ content);
	    } else {
		Dialog.display(Demo_Services_MainActivity.this, getResources()
			.getString(R.string.SA_result), getResources()
			.getString(R.string.SA_cancelled));
	    }
	}
    }
}
