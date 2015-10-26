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
import android.widget.LinearLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.tylerfoxx.twoflash.R;

public class AdmobManager
{

    private static final String AD_UNIT_ID = "a150c81d1f5acca";

    public static void showAdmob(Activity activity)
    {
        AdView adView = new AdView(activity, AdSize.BANNER, AD_UNIT_ID);
        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.AdLayout);
        layout.addView(adView);
        adView.loadAd(new AdRequest());
    }
}
