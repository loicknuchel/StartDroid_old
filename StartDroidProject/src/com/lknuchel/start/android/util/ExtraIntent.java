package com.lknuchel.start.android.util;

import android.content.Context;
import android.content.Intent;

import com.lknuchel.start.android.ui.demo.Demo_ActionBar_MainActivity;

public class ExtraIntent {
    public static Intent shareIntent(String content) {
	final Intent intent = new Intent(Intent.ACTION_SEND);
	intent.setType("text/plain");
	intent.putExtra(Intent.EXTRA_TEXT, content);
	return Intent.createChooser(intent, "Share");
    }

    public static Intent homeIntent(Context context) {
	Intent i = new Intent(context, Demo_ActionBar_MainActivity.class);
	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	return i;
    }
}
