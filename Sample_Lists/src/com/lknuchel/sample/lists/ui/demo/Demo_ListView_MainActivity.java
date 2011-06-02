package com.lknuchel.sample.lists.ui.demo;


import com.lknuchel.sample.lists.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class Demo_ListView_MainActivity extends Activity {
    private Activity a;
    private Context c;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_demo_listview_main);
	a = Demo_ListView_MainActivity.this;
	c = getApplicationContext();
	setUp();
	onCLickValidate();
    }
    
    private void setUp(){
	
    }
    
    private void onCLickValidate(){
	
    }
}
