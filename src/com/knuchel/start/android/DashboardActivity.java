package com.knuchel.start.android;
//TUTO : http://www.samcoles.co.uk/mobile/android-implementing-a-dashboard-activity/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DashboardActivity extends Activity {
	private Button retBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		setUp();
		onCLickValidate();
		
		//attach event handler to dash buttons
	    DashboardClickListener dBClickListener = new DashboardClickListener();
	    findViewById(R.id.dashboard_button_add).setOnClickListener(dBClickListener);
	    findViewById(R.id.dashboard_button_viewall).setOnClickListener(dBClickListener);
	    findViewById(R.id.dashboard_button_manage).setOnClickListener(dBClickListener);
	    findViewById(R.id.dashboard_button_personalbests).setOnClickListener(dBClickListener);
	}

	protected void setUp() {
//    	retBtn = (Button) findViewById(R.id.dashboardRetBtn);
	}
	
	private void onCLickValidate(){
//		retBtn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent();
//				setResult(RESULT_OK, intent);
//				finish();
//			}
//		});
	}
	
	private class DashboardClickListener implements OnClickListener {
	    @Override
	    public void onClick(View v) {
	        switch (v.getId()) {
	            case R.id.dashboard_button_add:
	            	Toast.makeText(getApplicationContext(), "AddCapture.class", Toast.LENGTH_SHORT).show();
	                break;
	            case R.id.dashboard_button_viewall:
	            	Toast.makeText(getApplicationContext(), "ViewAll.class", Toast.LENGTH_SHORT).show();
	                break;
	            case R.id.dashboard_button_manage:
	            	Toast.makeText(getApplicationContext(), "Manage.class", Toast.LENGTH_SHORT).show();
	                break;
	            case R.id.dashboard_button_personalbests:
	            	Toast.makeText(getApplicationContext(), "PersonalBests.class", Toast.LENGTH_SHORT).show();
	                break;
	            default:
	                break;
	        }
	    }
	}
}
