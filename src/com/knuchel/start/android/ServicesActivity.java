package com.knuchel.start.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.knuchel.start.android.config.Config;
import com.knuchel.start.android.utils.ExtraService;
import com.knuchel.start.android.utils.Popup;

public class ServicesActivity extends Activity {
	private Button serviceScan;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.services);
		setUp();
		onCLickValidate();
	}

	protected void setUp() {
		serviceScan = (Button) findViewById(R.id.serviceScan);
	}

	private void onCLickValidate() {
		serviceScan.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ExtraService.scan(getApplicationContext(),
						ServicesActivity.this, Config.SCANNER_CODE);
			}
		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == Config.SCANNER_CODE) {
			if (resultCode == RESULT_OK) {
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				String content = intent.getStringExtra("SCAN_RESULT");
				Popup.display(getApplicationContext(), ServicesActivity.this,
						"Scanner result", "format:" + format + "\ncontent:"
								+ content);
			} else {
				Popup.display(getApplicationContext(), ServicesActivity.this,
						"Scanner result", "Scan annulé");
			}
		}
	}
}
