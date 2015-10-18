package com.twoflashlight.utility;

import android.util.Log;

public class TraceLogger {

	private static final String LOG_IDENTIFY = "Trace Log";

	public static void print(String msg) {

		Log.v(LOG_IDENTIFY, msg);
	}
}
