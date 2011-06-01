package com.knuchel.start.android.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.knuchel.start.android.R;
import com.knuchel.start.android.ui.widget.ActionBar;
import com.knuchel.start.android.ui.widget.ActionBar.Action;
import com.knuchel.start.android.ui.widget.ActionBar.IntentAction;
import com.knuchel.start.android.util.ExtraIntent;
import com.knuchel.start.android.util.Strings;

public class Demo_ActionBar_MainActivity extends Activity {
    private Context c;
    private ActionBar actionBar;
    // private Action homeAction;
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
	setContentView(R.layout.activity_demo_actionbarhome);
	c = getApplicationContext();
	setUp();
	setContents();
	onCLickValidate();
    }

    protected void setUp() {
	actionBar = (ActionBar) findViewById(R.id.actionbar);
	// homeAction = new IntentAction(this, createHomeIntent(this),
	// R.drawable.ic_title_home_default);
	shareAction = new IntentAction(this, ExtraIntent.shareIntent(Strings
		.get(c, R.string.ABHA_share)),
		R.drawable.ic_title_share_default);
	otherAction = new IntentAction(this, new Intent(this,
		Demo_ActionBar_OtherActivity.class),
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
	actionBar.setTitle(Strings.get(c, R.string.ABHA_title));
	// actionBar.setHomeAction(homeAction); // facultatif !
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
		Toast.makeText(Demo_ActionBar_MainActivity.this,
			Strings.get(c, R.string.ABHA_remove),
			Toast.LENGTH_SHORT).show();
	    }
	});

	add_new_action.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View view) {
		actionBar.addAction(new Action() {
		    @Override
		    public void performAction(View view) {
			Toast.makeText(Demo_ActionBar_MainActivity.this,
				Strings.get(c, R.string.ABHA_add),
				Toast.LENGTH_SHORT).show();
		    }

		    @Override
		    public int getDrawable() {
			return R.drawable.ic_title_home_demo;
		    }
		});
	    }
	});
    }
}
