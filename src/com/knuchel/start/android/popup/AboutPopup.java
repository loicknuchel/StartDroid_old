package com.knuchel.start.android.popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.knuchel.start.android.R;
import com.knuchel.start.android.SettingsActivity;

public class AboutPopup extends Activity {
	/*
	 * NOT WORKING !!! 
	 * HOW TO DO ?
	 */
	public void display(Context c){
		LayoutInflater factory = LayoutInflater.from(c);
    	final View alertDialogView = factory.inflate(R.layout.alertdialogabout, null);
    	
    	AlertDialog.Builder adb = new AlertDialog.Builder(c);
    	adb.setView(alertDialogView)
    		.setTitle(getResources().getString(R.string.abouttitle))
    		.setIcon(R.drawable.about)
    		.setPositiveButton(getResources().getString(R.string.validate), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	
                }
    		})
    		.show();
	}
}
