/**
 *   TwoFlashLight Android application
 *
 *   @author foxxtseng
 *   Copyright (C) 2015-2015  foxxtseng
 *   
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License version 2,
 *   as published by the Free Software Foundation.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
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
