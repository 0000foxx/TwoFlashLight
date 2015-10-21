package com.twoflashlight.utility;

import android.app.Activity;
import android.content.Context;
import android.os.PowerManager;

public class ScreenWakeLockManager
{
    private static final String DEBUG_TRACE_IDENTIFY = "ScreenWakeLockManager";
    
    public static void makeScreenWakeLock(Activity activity){
        PowerManager powerManager = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
        powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, DEBUG_TRACE_IDENTIFY).acquire();
    }
}
