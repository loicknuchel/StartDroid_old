package com.knuchel.start.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.knuchel.start.android.R;

public class ExtraService {
	public static void scan(final Context context, final Activity activity, final int resultCode) {
		Intent i = new Intent("com.google.zxing.client.android.SCAN");
		i.setPackage("com.google.zxing.client.android");
		
		try {
			activity.startActivityForResult(i, resultCode);
		} catch (Exception e) {

			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(
					context.getResources().getString(R.string.noScanerFound))
					.setCancelable(false)
					.setPositiveButton(context.getResources().getString(R.string.yes),
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.cancel();
									final Intent intent = new Intent(Intent.ACTION_VIEW);
									intent.setData(Uri.parse("market://details?id=com.google.zxing.client.android"));
									activity.startActivityForResult(intent, 1);
									dialog.cancel();
								}
							})
					.setNegativeButton(context.getResources().getString(R.string.no),
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.cancel();
								}
							});

			AlertDialog ScannerNotFound = builder.create();
			ScannerNotFound.show();
		}
	}
	
}
