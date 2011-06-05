package com.lknuchel.android.startdroidlight.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lknuchel.android.startdroidlight.R;
import com.lknuchel.android.startdroidlight.ui.widget.ActionBar;
import com.lknuchel.android.startdroidlight.ui.widget.ActionBar.AbstractAction;
import com.lknuchel.android.startdroidlight.ui.widget.ActionBar.Action;
import com.lknuchel.android.startdroidlight.ui.widget.ActionBar.IntentAction;
import com.lknuchel.android.startdroidlight.util.ExtraIntent;

public class Demo_ActionBar_OtherActivity extends Activity {
    private Context c;
    private ActionBar actionBar;
    private Action homeAction;
    private Action shareAction;
    private Action otherAction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_actionbarother);
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
		ExtraIntent.shareIntent(getResources().getString(
			R.string.ABOA_share)),
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

    private class ExampleAction extends AbstractAction {

	public ExampleAction() {
	    super(R.drawable.ic_title_export_default);
	}

	@Override
	public void performAction(View view) {
	    Toast.makeText(Demo_ActionBar_OtherActivity.this,
		    getResources().getString(R.string.ABOA_exemple),
		    Toast.LENGTH_SHORT).show();
	}

    }
}
