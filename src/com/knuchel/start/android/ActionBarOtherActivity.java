package com.knuchel.start.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.knuchel.start.android.utils.ExtraIntent;
import com.knuchel.start.android.utils.Strings;
import com.knuchel.start.android.widget.ActionBar;
import com.knuchel.start.android.widget.ActionBar.AbstractAction;
import com.knuchel.start.android.widget.ActionBar.Action;
import com.knuchel.start.android.widget.ActionBar.IntentAction;

public class ActionBarOtherActivity extends Activity {
	private Context c;
	private ActionBar actionBar;
	private Action homeAction;
	private Action shareAction;
	private Action otherAction;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actionbarother);
		c = getApplicationContext();
		setUp();
		setContents();
		onCLickValidate();

	}

	protected void setUp() {
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		homeAction = new IntentAction(this, ExtraIntent.homeIntent(this),
				R.drawable.ic_title_home_default);
		shareAction = new IntentAction(this,
				ExtraIntent.shareIntent(Strings.get(c, R.string.ABOA_share)), R.drawable.ic_title_share_default);
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

	private class ExampleAction extends AbstractAction {

		public ExampleAction() {
			super(R.drawable.ic_title_export_default);
		}

		@Override
		public void performAction(View view) {
			Toast.makeText(ActionBarOtherActivity.this, Strings.get(c, R.string.ABOA_exemple),
					Toast.LENGTH_SHORT).show();
		}

	}
}
