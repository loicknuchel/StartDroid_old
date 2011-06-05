package com.lknuchel.android.startdroidfull.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.lknuchel.android.startdroidfull.R;

public class ScanService {
    public static void scan(final Activity activity, final int resultCode) {
	scan(activity, resultCode,
		"PRODUCT_MODE | ONE_D_MODE | QR_CODE_MODE | DATA_MATRIX_MODE");
    }

    public static void scan(final Activity activity, final int resultCode,
	    String mode) {
	Intent i = new Intent("com.google.zxing.client.android.SCAN");
	i.putExtra("SCAN_MODE", mode);
	i.setPackage("com.google.zxing.client.android");

	try {
	    activity.startActivityForResult(i, resultCode);
	} catch (Exception e) {

	    AlertDialog.Builder builder = new AlertDialog.Builder(
		    activity.getBaseContext());
	    builder.setMessage(
		    activity.getBaseContext().getResources()
			    .getString(R.string.noScanerFound))
		    .setCancelable(false)
		    .setPositiveButton(
			    activity.getBaseContext().getResources()
				    .getString(R.string.yes),
			    new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,
					int which) {
				    dialog.cancel();
				    final Intent intent = new Intent(
					    Intent.ACTION_VIEW);
				    intent.setData(Uri
					    .parse("market://details?id=com.google.zxing.client.android"));
				    activity.startActivityForResult(intent, 1);
				    dialog.cancel();
				}
			    })
		    .setNegativeButton(
			    activity.getBaseContext().getResources()
				    .getString(R.string.no),
			    new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,
					int which) {
				    dialog.cancel();
				}
			    });

	    AlertDialog ScannerNotFound = builder.create();
	    ScannerNotFound.show();
	}
    }
}
