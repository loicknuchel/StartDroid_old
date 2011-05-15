package com.knuchel.start.android.utils;

import android.content.Context;

public class Strings {
	public static String get(Context c, int id) {
		return c.getResources().getString(id);
	}
}
