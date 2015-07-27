package com.qq;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

public class Util {

	private static final String	TAG	= "SDK_Sample.Util";

	private static Dialog		mProgressDialog;
	private static Toast		mToast;

	public static final void showResultDialog(Context context, String msg, String title) {
		if (msg == null)
			return;
		String rmsg = msg.replace(",", "\n");
		Log.d("Util", rmsg);
		new AlertDialog.Builder(context).setTitle(title).setMessage(rmsg).setNegativeButton("xxx", null).create().show();
	}

	public static final void dismissDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}

	private static final void toastMessage(final Activity activity, final String message, String logLevel) {
		if ("w".equals(logLevel)) {
			Log.w("sdkDemo", message);
		} else if ("e".equals(logLevel)) {
			Log.e("sdkDemo", message);
		} else {
			Log.d("sdkDemo", message);
		}
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mToast != null) {
					mToast.cancel();
					mToast = null;
				}
				mToast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
				mToast.show();
			}
		});
	}

	public static final void toastMessage(final Activity activity, final String message) {
		toastMessage(activity, message, null);
	}

	public static Bitmap getbitmap(String imageUri) {
		Log.v(TAG, "getbitmap:" + imageUri);
		Bitmap bitmap = null;
		try {
			URL myFileUrl = new URL(imageUri);
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();

			Log.v(TAG, "image download finished." + imageUri);
		} catch (IOException e) {
			e.printStackTrace();
			Log.v(TAG, "getbitmap bmp fail---");
			return null;
		}
		return bitmap;
	}
}
