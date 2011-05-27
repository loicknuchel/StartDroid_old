package com.knuchel.start.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Network {
    // need : <uses-permission
    // android:name="android.permission.ACCESS_NETWORK_STATE" />
    public static final boolean isNetworkAvailable(Context context) {
	ConnectivityManager connectivityManager = (ConnectivityManager) context
		.getSystemService(Context.CONNECTIVITY_SERVICE);
	return connectivityManager.getActiveNetworkInfo()
		.isConnectedOrConnecting();
	// NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
	// if (networkInfo != null) {
	// // System.out.println(networkInfo.getTypeName()); // mobile ou WIFI
	// State networkState = networkInfo.getState();
	// if (networkState.compareTo(State.CONNECTED) == 0) {
	// return true;
	// }
	// }
	// return false;
    }
}
