package com.knuchel.start.android.utils;

import com.knuchel.start.android.ActionBarHomeActivity;

import android.content.Context;
import android.content.Intent;

public class ExtraIntent {
    public static Intent shareIntent(String content) {
	final Intent intent = new Intent(Intent.ACTION_SEND);
	intent.setType("text/plain");
	intent.putExtra(Intent.EXTRA_TEXT, content);
	return Intent.createChooser(intent, "Share");
    }

    public static Intent homeIntent(Context context) {
	Intent i = new Intent(context, ActionBarHomeActivity.class);
	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	return i;
    }
}
