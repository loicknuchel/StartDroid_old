package com.knuchel.start.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.knuchel.start.android.R;

public class Demo_TabActivity_FirstActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_tabfirst);

	// On récupère notre intent et la valeur que l'on affiche dans le
	// TextView
	String valeur = getIntent().getStringExtra("valeur");

	TextView textView = (TextView) findViewById(R.id.monTextView);
	textView.setText(valeur);
    }
}
