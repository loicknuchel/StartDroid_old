package com.knuchel.start.android;

import com.knuchel.start.android.widget.ActionBar;
import com.knuchel.start.android.widget.ActionBar.AbstractAction;
import com.knuchel.start.android.widget.ActionBar.Action;
import com.knuchel.start.android.widget.ActionBar.IntentAction;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ActionBarOtherActivity extends Activity {
	private ActionBar actionBar;
	private Action homeAction;
	private Action shareAction;
	private Action otherAction;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbarother);
		setUp();
		setContents();
		onCLickValidate();

	}

	protected void setUp() {
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		homeAction = new IntentAction(this,
				ActionBarHomeActivity.createHomeIntent(this),
				R.drawable.ic_title_home_default);
		shareAction = new IntentAction(this,
				createShareIntent("Shared from the ActionBar widget."),
				R.drawable.ic_title_share_default);
		otherAction = new ExampleAction();
	}

	protected void setContents() {
		// You can also assign the title programmatically by passing a
		// CharSequence or resource id.
		// actionBar.setTitle(R.string.some_title);
		actionBar.setHomeAction(homeAction);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.addAction(shareAction);
		actionBar.addAction(otherAction);
	}

	private void onCLickValidate() {
	}

	private Intent createShareIntent(String content) {
		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, content);
		return Intent.createChooser(intent, "Share");
	}

	private class ExampleAction extends AbstractAction {

		public ExampleAction() {
			super(R.drawable.ic_title_export_default);
		}

		@Override
		public void performAction(View view) {
			Toast.makeText(ActionBarOtherActivity.this, "Example action",
					Toast.LENGTH_SHORT).show();
		}

	}
}
