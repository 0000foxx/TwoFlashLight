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

    // NOTE: 請填入自己的 AdMob publisher / ad-unit ID。
    // 原始碼不再內嵌正式廣告 ID，避免 fork 後廣告流量計入他人帳號 (claude/2026-06)。
    private static final String AD_UNIT_ID = "YOUR_ADMOB_AD_UNIT_ID";

    public static void showAdmob(Activity activity)
    {
        AdView adView = new AdView(activity, AdSize.BANNER, AD_UNIT_ID);
        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.AdLayout);
        layout.addView(adView);

        // 預設以測試模式載入廣告，避免在開發/fork 環境產生無效曝光。
        AdRequest request = new AdRequest();
        request.addTestDevice(AdRequest.TEST_EMULATOR);
        adView.loadAd(request);
    }
}
