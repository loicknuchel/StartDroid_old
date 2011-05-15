package com.knuchel.start.android;

import com.knuchel.start.android.widget.ActionBar;
import com.knuchel.start.android.widget.ActionBar.Action;
import com.knuchel.start.android.widget.ActionBar.IntentAction;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ActionBarHomeActivity extends Activity {
	private ActionBar actionBar;
	private Action homeAction;
	private Action shareAction;
	private Action otherAction;

	private Button startProgress;
	private Button stopProgress;
	private Button remove_all_actions;
	private Button restaure_all_actions;
	private Button remove_share_action;
	private Button add_share_action;
	private Button remove_other_action;
	private Button remove_last_action;
	private Button add_new_action;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbarhome);
		setUp();
		setContents();
		onCLickValidate();
	}

	protected void setUp() {
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		homeAction = new IntentAction(this, createHomeIntent(this),
				R.drawable.ic_title_home_default);
		shareAction = new IntentAction(this,
				createShareIntent("Shared from the ActionBar widget."),
				R.drawable.ic_title_share_default);
		otherAction = new IntentAction(this, new Intent(this,
				ActionBarOtherActivity.class),
				R.drawable.ic_title_export_default);

		startProgress = (Button) findViewById(R.id.start_progress);
		stopProgress = (Button) findViewById(R.id.stop_progress);
		remove_all_actions = (Button) findViewById(R.id.remove_all_actions);
		restaure_all_actions = (Button) findViewById(R.id.restaure_all_actions);
		remove_share_action = (Button) findViewById(R.id.remove_share_action);
		add_share_action = (Button) findViewById(R.id.add_share_action);
		remove_other_action = (Button) findViewById(R.id.remove_other_action);
		remove_last_action = (Button) findViewById(R.id.remove_last_action);
		add_new_action = (Button) findViewById(R.id.add_new_action);
	}

	protected void setContents() {
		// action bar content
		actionBar.setTitle("Home");
		//actionBar.setHomeAction(homeAction); // icone sur la gauche : facultatif
		actionBar.addAction(shareAction);
		actionBar.addAction(otherAction);
	}

	private void onCLickValidate() {
		startProgress.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				actionBar.setProgressBarVisibility(View.VISIBLE);
			}
		});

		stopProgress.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				actionBar.setProgressBarVisibility(View.GONE);
			}
		});

		remove_all_actions.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				actionBar.removeAllActions();
			}
		});

		restaure_all_actions.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				actionBar.removeAllActions();
				actionBar.addAction(shareAction);
				actionBar.addAction(otherAction);
			}
		});

		remove_share_action.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				actionBar.removeAction(shareAction);
			}
		});

		add_share_action.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				actionBar.addAction(shareAction);
			}
		});

		remove_other_action.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				actionBar.removeAction(otherAction);
			}
		});

		remove_last_action.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				int actionCount = actionBar.getActionCount();
				actionBar.removeActionAt(actionCount - 1);
				Toast.makeText(ActionBarHomeActivity.this, "Removed action.",
						Toast.LENGTH_SHORT).show();
			}
		});

		add_new_action.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				actionBar.addAction(new Action() {
					@Override
					public void performAction(View view) {
						Toast.makeText(ActionBarHomeActivity.this,
								"Added action.", Toast.LENGTH_SHORT).show();
					}

					@Override
					public int getDrawable() {
						return R.drawable.ic_title_home_demo;
					}
				});
			}
		});
	}

	public static Intent createHomeIntent(Context context) {
		Intent i = new Intent(context, ActionBarHomeActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return i;
	}

	private Intent createShareIntent(String content) {
		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, content);
		return Intent.createChooser(intent, "Share");
	}
}
