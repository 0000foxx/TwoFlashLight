package com.twoflashlight.utility;

import android.util.Log;

public class FP {

	public static final String TLog = "Trace Log"; 
	
	public static void p(String msg){
		Log.v(TLog, msg);
	}
}
