
package com.twoflashlight.main;

import java.util.ArrayList;

import com.twoflashlight.utility.AdmobManager;
import com.twoflashlight.utility.ScreenWakeLockManager;
import com.tylerfoxx.twoflash.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.MotionEvent;
import android.view.WindowManager.LayoutParams;

public class CameraPresenter
{
    private ICameraView mActivity;
    private LayoutParams mLayoutParams;
    private Camera mCamera;
    private int mXCoordinatesOfScreen;
    private int mYCorrdinatesOfScreen;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mBackLightIndex;
    private int mFrontLightIndex;
    private Parameters mCameraParameters;

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

    public CameraPresenter(ICameraView cameraActivity) {
        mActivity = cameraActivity;
    }

    public void adjustLight(MotionEvent event)
    {
        getCoordinatesOnScreen(event);
        changeBackLight();
        changeFrontLight();
    }

    public void changeFrontLight()
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

        ((Activity)mActivity).getWindow().setAttributes(mLayoutParams);
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

    public void init()
    {
        initBackLightIndex();
        initFrontLightIndex();
        AdmobManager.showAdmob((Activity)mActivity);
        ScreenWakeLockManager.makeScreenWakeLock((Activity)mActivity);
        setScreenBrightness(0.1f);
    }
    
    private void setScreenBrightness(float brightness)
    {
        mLayoutParams = ((Activity)mActivity).getWindow().getAttributes();
        mLayoutParams.screenBrightness = brightness;
        ((Activity)mActivity).getWindow().setAttributes(mLayoutParams);
    }
    
    private void initFrontLightIndex()
    {
        int frontLightIndexMax = 5;
        mScreenHeight = ((Activity)mActivity).getWindowManager().getDefaultDisplay().getHeight();
        mYCorrdinatesOfScreen = mScreenHeight / 2;
        mFrontLightIndex = frontLightIndexMax;
    }

    private void initBackLightIndex()
    {
        mScreenWidth = ((Activity)mActivity).getWindowManager().getDefaultDisplay().getWidth();
        mXCoordinatesOfScreen = mScreenWidth / 2;
        mBackLightIndex = 1;
    }
    
    private void getCoordinatesOnScreen(MotionEvent event)
    {
        mXCoordinatesOfScreen = (int) event.getX();
        mYCorrdinatesOfScreen = (int) event.getY();
    }

    public void changeBackLight()
    {
        calculateBackLightIndex();
        setBackLightFlashMode();
    }

    public void initCamera()
    {
        try {
            mCamera = Camera.open();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        if (mCamera != null) {
            mCameraParameters = mCamera.getParameters();
            mCameraParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(mCameraParameters);
        }
    }

    private void setBackLightFlashMode()
    {
        if (mCameraParameters != null) {
            if (mBackLightIndex == BackLightIndex.OFF.getIndex()) {
                mCameraParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
            } else if (mBackLightIndex == BackLightIndex.MACRO.getIndex()) {
                mCameraParameters.setFocusMode("macro");
                mCameraParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            } else if (mBackLightIndex == BackLightIndex.AUTO.getIndex()) {
                mCameraParameters.setFocusMode("auto");
                mCameraParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            }
            try {
                mCamera.setParameters(mCameraParameters);
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

    public void resumeComponents()
    {
        initCamera();
        changeBackLight();
        changeFrontLight();
    }

    public void releaseCamera()
    {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    private void showNotificationToTitleBar()
    {
        Resources res = mActivity.getContext().getResources();
        Intent intent = new Intent(mActivity.getContext(), CameraActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mActivity.getContext(), 0, intent, 0);
        Notification notification = new Notification();
        notification.tickerText = res.getString(R.string.notification_text);
        notification.defaults = Notification.DEFAULT_ALL;
        notification.setLatestEventInfo(mActivity.getContext(),
                res.getString(R.string.notification_text),
                res.getString(R.string.notification_welcomeUse), pendingIntent);
        NotificationManager notificatoinManager = (NotificationManager) mActivity.getContext().getSystemService(mActivity.getContext().NOTIFICATION_SERVICE);
        notificatoinManager.notify(0, notification);
    }
    
    public void showExitDialog()
    {
        Builder dialog = new AlertDialog.Builder(mActivity.getContext());
        dialog.setTitle(R.string.exit_dialog_title);
        dialog.setMessage(R.string.exit_dialog_message);
        dialog.setPositiveButton(R.string.exit_dialog_yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                showNotificationToTitleBar();
                ((Activity)mActivity).finish();
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
