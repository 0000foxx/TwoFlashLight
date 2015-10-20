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
