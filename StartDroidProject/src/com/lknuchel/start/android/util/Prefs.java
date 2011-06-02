package com.lknuchel.start.android.util;

import android.app.Activity;
import android.content.SharedPreferences;


public class Prefs {
    private static final String p = Config.PREFS;

    public static boolean getBoolPref(Activity a, String pref) {
	return a.getSharedPreferences(p, 0).getBoolean(pref, false);
    }

    public static boolean getBoolPref(Activity a, String pref, boolean defValue) {
	return a.getSharedPreferences(p, 0).getBoolean(pref, defValue);
    }

    public static void setBoolPref(Activity a, String pref, boolean value) {
	SharedPreferences.Editor spe = a.getSharedPreferences(p, 0).edit();
	spe.putBoolean(pref, value);
	spe.commit();
    }

    public static String getStringPref(Activity a, String pref) {
	return a.getSharedPreferences(p, 0).getString(pref, "");
    }

    public static String getStringPref(Activity a, String pref, String defValue) {
	return a.getSharedPreferences(p, 0).getString(pref, defValue);
    }

    public static void setStringPref(Activity a, String pref, String value) {
	SharedPreferences.Editor spe = a.getSharedPreferences(p, 0).edit();
	spe.putString(pref, value);
	spe.commit();
    }

    public static int getIntPref(Activity a, String pref) {
	return a.getSharedPreferences(p, 0).getInt(pref, 0);
    }

    public static int getIntPref(Activity a, String pref, int defValue) {
	return a.getSharedPreferences(p, 0).getInt(pref, defValue);
    }

    public static void setIntPref(Activity a, String pref, int value) {
	SharedPreferences.Editor spe = a.getSharedPreferences(p, 0).edit();
	spe.putInt(pref, value);
	spe.commit();
    }

    public static float getFloatPref(Activity a, String pref) {
	return a.getSharedPreferences(p, 0).getFloat(pref, 0);
    }

    public static float getFloatPref(Activity a, String pref, float defValue) {
	return a.getSharedPreferences(p, 0).getFloat(pref, defValue);
    }

    public static void setFloatPref(Activity a, String pref, float value) {
	SharedPreferences.Editor spe = a.getSharedPreferences(p, 0).edit();
	spe.putFloat(pref, value);
	spe.commit();
    }

    public static long getLongPref(Activity a, String pref) {
	return a.getSharedPreferences(p, 0).getLong(pref, 0);
    }

    public static long getLongPref(Activity a, String pref, long defValue) {
	return a.getSharedPreferences(p, 0).getLong(pref, defValue);
    }

    public static void setLongPref(Activity a, String pref, long value) {
	SharedPreferences.Editor spe = a.getSharedPreferences(p, 0).edit();
	spe.putLong(pref, value);
	spe.commit();
    }

}
