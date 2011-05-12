package com.knuchel.start.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.knuchel.start.android.config.Config;
import com.sfeir.android.friendapps.FriendListActivity;

public class HelpActivity extends Activity {
	private Button retBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		setUp();
		onCLickValidate();
	}

	protected void setUp() {
		retBtn = (Button) findViewById(R.id.helpRetBtn);
	}
	
	private void onCLickValidate(){
		retBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
	
	
}
