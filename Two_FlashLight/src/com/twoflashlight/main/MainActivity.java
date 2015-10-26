
package com.twoflashlight.main;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager.LayoutParams;

import com.twoflashlight.utility.AdmobManager;
import com.twoflashlight.utility.ScreenWakeLockManager;
import com.tylerfoxx.twoflash.R;

public class MainActivity extends Activity
{

    private Camera mCamera;
    private Parameters mParameters;

    private LayoutParams mLayoutParams;

    private int mScreenWidth;
    private int mScreenHeight;

    private int mXCoordinatesOfScreen;
    private int mYCorrdinatesOfScreen;
    private int mBackLightIndex;
    private int mFrontLightIndex;

    private enum BackLightIndex {
        OFF(0), MACRO(1), AUTO(2);
        private int mIndex;

        private BackLightIndex(int index) {
            mIndex = index;
        }

        public int getIndex()
        {
            return mIndex;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    @Override
    protected void onDestroy()
    {
        super.onPause();
        mCamera.release();
        mCamera = null;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        initCamera();
        changeBackLight();
        changeFrontLight();
    }

    private void initCamera()
    {
        try {
            mCamera = Camera.open();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        if (mCamera != null) {
            mParameters = mCamera.getParameters();
            mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(mParameters);
        }
    }

    private void init()
    {
        initBackLightIndex();
        initFrontLightIndex();
        AdmobManager.showAdmob(this);
        ScreenWakeLockManager.makeScreenWakeLock(this);
        setScreenBrightness(0.1f);
    }

    private void initFrontLightIndex()
    {
        int frontLightIndexMax = 5;
        mScreenHeight = (getWindowManager().getDefaultDisplay().getHeight());
        mYCorrdinatesOfScreen = mScreenHeight / 2;
        mFrontLightIndex = frontLightIndexMax;
    }

    private void initBackLightIndex()
    {
        mScreenWidth = (getWindowManager().getDefaultDisplay().getWidth());
        mXCoordinatesOfScreen = mScreenWidth / 2;
        mBackLightIndex = 1;
    }

    private void setScreenBrightness(float brightness)
    {
        mLayoutParams = getWindow().getAttributes();
        mLayoutParams.screenBrightness = brightness;
        getWindow().setAttributes(mLayoutParams);
    }

    private void changeBackLight()
    {
        calculateBackLightIndex();
        setBackLightFlashMode();
    }

    private void setBackLightFlashMode()
    {
        if (mParameters != null) {
            if (mBackLightIndex == BackLightIndex.OFF.getIndex()) {
                mParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
            } else if (mBackLightIndex == BackLightIndex.MACRO.getIndex()) {
                mParameters.setFocusMode("macro");
                mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            } else if (mBackLightIndex == BackLightIndex.AUTO.getIndex()) {
                mParameters.setFocusMode("auto");
                mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            }
            try {
                mCamera.setParameters(mParameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void calculateBackLightIndex()
    {
        ArrayList<Integer> screenWidthQuarterIndex = new ArrayList<Integer>();
        int screenWidthQuarterMax = 4;
        for (int i = 0; i < screenWidthQuarterMax; ++i) {
            screenWidthQuarterIndex.add(mScreenWidth * i / screenWidthQuarterMax - 1);
        }

        for (int i = 0; i < screenWidthQuarterIndex.size() - 1; ++i) {
            if (mXCoordinatesOfScreen >= screenWidthQuarterIndex.get(i)
                    && mXCoordinatesOfScreen <= screenWidthQuarterIndex.get(i + 1)) {
                mBackLightIndex = i;
            }
        }
    }

    private void changeFrontLight()
    {
        calculateFrontLightIndex();
        setScreenBrightness();
    }

    private void setScreenBrightness()
    {
        float screenBrightnessMin = 0.1f;
        if (mLayoutParams.screenBrightness <= screenBrightnessMin) {
            mLayoutParams.screenBrightness = screenBrightnessMin;
        }

        getWindow().setAttributes(mLayoutParams);
    }

    private void calculateFrontLightIndex()
    {
        ArrayList<Integer> screenHeightQuarterIndex = new ArrayList<Integer>();
        int screenHeightQuarterMax = 10;
        for (int i = 0; i < screenHeightQuarterMax; i++) {
            screenHeightQuarterIndex.add(mScreenHeight * i / screenHeightQuarterMax);
        }

        for (int i = 0; i < screenHeightQuarterIndex.size() - 1; i++) {
            if (mYCorrdinatesOfScreen >= screenHeightQuarterIndex.get(i)
                    && mYCorrdinatesOfScreen <= screenHeightQuarterIndex.get(i + 1)) {
                mFrontLightIndex = i;
            }
        }
        mLayoutParams.screenBrightness = (float) mFrontLightIndex / screenHeightQuarterMax;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                adjustLight(event);
                break;
        }
        return true;
    }

    private void adjustLight(MotionEvent event)
    {
        getCoordinatesOnScreen(event);
        changeBackLight();
        changeFrontLight();
    }

    private void getCoordinatesOnScreen(MotionEvent event)
    {
        mXCoordinatesOfScreen = (int) event.getX();
        mYCorrdinatesOfScreen = (int) event.getY();
    }

    private void showNotificationToTitleBar()
    {
        Resources res = getResources();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        Notification notification = new Notification();
        notification.tickerText = res.getString(R.string.notification_text);
        notification.defaults = Notification.DEFAULT_ALL;
        notification.setLatestEventInfo(MainActivity.this,
                res.getString(R.string.notification_text),
                res.getString(R.string.notification_welcomeUse), pendingIntent);
        NotificationManager notificatoinManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificatoinManager.notify(0, notification);
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event)
    {
        super.onKeyDown(keycode, event);
        switch (keycode) {
            case KeyEvent.KEYCODE_BACK:
                showExitDialog();
                return true;
        }
        return false;
    }

    private void showExitDialog()
    {
        Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle(R.string.exit_dialog_title);
        dialog.setMessage(R.string.exit_dialog_message);
        dialog.setPositiveButton(R.string.exit_dialog_yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                showNotificationToTitleBar();
                MainActivity.this.finish();
            }
        });
        dialog.setNegativeButton(R.string.exit_dialog_no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });

        dialog.show();
    }
}
