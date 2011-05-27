package com.knuchel.start.android;

//TUTO : http://www.samcoles.co.uk/mobile/android-implementing-a-dashboard-activity/

import com.knuchel.start.android.utils.Strings;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class DashboardActivity extends Activity {
	private Context c;
	// private Button retBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		c = getApplicationContext();
		setUp();
		onCLickValidate();

		// attach event handler to dash buttons
		DashboardClickListener dBClickListener = new DashboardClickListener();
		findViewById(R.id.dashboard_button_add).setOnClickListener(
				dBClickListener);
		findViewById(R.id.dashboard_button_viewall).setOnClickListener(
				dBClickListener);
		findViewById(R.id.dashboard_button_manage).setOnClickListener(
				dBClickListener);
		findViewById(R.id.dashboard_button_personalbests).setOnClickListener(
				dBClickListener);
	}

	protected void setUp() {
	}

	private void onCLickValidate() {
	}

	private class DashboardClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.dashboard_button_add:
				Toast.makeText(getApplicationContext(),
						Strings.get(c, R.string.DBA_add),
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.dashboard_button_viewall:
				Toast.makeText(getApplicationContext(),
						Strings.get(c, R.string.DBA_viewall),
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.dashboard_button_manage:
				Toast.makeText(getApplicationContext(),
						Strings.get(c, R.string.DBA_manage),
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.dashboard_button_personalbests:
				Toast.makeText(
						getApplicationContext(),
						Strings.get(c, R.string.DBA_personalbests),
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	}
}
