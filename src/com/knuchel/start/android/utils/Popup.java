package com.knuchel.start.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.knuchel.start.android.R;
import com.knuchel.start.android.config.Config;

public class Popup extends Activity {
	
	public static void display(Context context, Activity activity, String title, String message){
    	AlertDialog.Builder adb = new AlertDialog.Builder(activity);
    	adb.setTitle(title)
    		.setMessage(message)
    		.setPositiveButton(context.getResources().getString(R.string.validate), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	
                }
    		})
    		.show();
	}
	
	public static void displayAbout(Context context, Activity activity){
		LayoutInflater factory = LayoutInflater.from(context);
    	final View alertDialogView = factory.inflate(R.layout.alertdialogabout, null);
    	
    	AlertDialog.Builder adb = new AlertDialog.Builder(activity);
    	adb.setView(alertDialogView)
    		.setTitle(context.getResources().getString(R.string.abouttitle))
    		.setIcon(R.drawable.about)
    		.setPositiveButton(context.getResources().getString(R.string.validate), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	
                }
    		})
    		.show();
	}
	
	public static void displayIfFirstUse(final Context context, final Activity activity) {
		SharedPreferences prefs = context.getSharedPreferences(Config.PREFS, 0);
		
		if(prefs.getBoolean(Config.PREFS_SHOW_START_POPUP, true)){
			LayoutInflater factory = LayoutInflater.from(activity);
	    	final View alertDialogView = factory.inflate(R.layout.alertdialogstart, null);
	    	
	    	AlertDialog.Builder adb = new AlertDialog.Builder(activity);
	    	adb.setView(alertDialogView)
	    		.setTitle(context.getResources().getString(R.string.starttitle))
	    		.setIcon(R.drawable.about)
	    		.setPositiveButton(context.getResources().getString(R.string.validate), new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            	CheckBox noDisplay = (CheckBox) alertDialogView.findViewById(R.id.startcontentshow);
		    	    	if(noDisplay.isChecked()){
		    		    	final SharedPreferences.Editor editor = context.getSharedPreferences(Config.PREFS, 0).edit();
		    		    	editor.putBoolean(Config.PREFS_SHOW_START_POPUP, false);
		    		    	editor.commit();
		    	    	}
		            }
	    		})
	    		.show();
		}
	}
}
