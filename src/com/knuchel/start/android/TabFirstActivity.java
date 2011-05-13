package com.knuchel.start.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TabFirstActivity extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabfirst);
        
    	// On récupère notre intent et la valeur que l'on affiche dans le TextView
    	String valeur = getIntent().getStringExtra("valeur");
    	
    	TextView textView = (TextView) findViewById(R.id.monTextView);
    	textView.setText(valeur);
    }
}
