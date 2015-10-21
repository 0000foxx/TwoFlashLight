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
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager.LayoutParams;

import com.twoflashlight.utility.AdmobManager;
import com.twoflashlight.utility.ScreenWakeLockManager;
import com.twoflashlight.utility.TraceLogger;
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
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        detBackLight();
        detFrontLight();
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
        countBackLightIndex();
        countFrontLightIndex();
        AdmobManager.showAdmob(this);
        ScreenWakeLockManager.makeScreenWakeLock(this);
        setScreenBrightness(0.1f);
    }

    private void countFrontLightIndex()
    {
        mScreenHeight = (getWindowManager().getDefaultDisplay().getHeight());
        mYCorrdinatesOfScreen = mScreenHeight / 2;
        mFrontLightIndex = 5;
    }

    private void countBackLightIndex()
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
	
    void detBackLight(){
    	
    	ArrayList<Integer> widthal = new ArrayList<Integer>();
    	
    	for(int i=0; i<4; i++){
    		widthal.add(i,mScreenWidth*i/3);
    	}
    	
    	for(int i=0; i<widthal.size()-1; i++){
    		if(mXCoordinatesOfScreen>=widthal.get(i) && mXCoordinatesOfScreen<=widthal.get(i+1)){
    			mBackLightIndex = i;
    		}
    	}
    	
    	TraceLogger.print("backLightIndex:"+mBackLightIndex);
    	
        if (mParameters != null) {
            if (mBackLightIndex == 0) {
                mParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
            } else if (mBackLightIndex == 1) {
                mParameters.setFocusMode("macro");
                mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);

            } else if (mBackLightIndex == 2) {
                mParameters.setFocusMode("auto");
                mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            }
            try {
                // because some system no supply "p.setFocusMode("macro");" so must try{}catch(){}
                mCamera.setParameters(mParameters);
            } catch (Exception e) {

            }
        }
    }
    
    void detFrontLight(){
    	ArrayList<Integer> heightal = new ArrayList<Integer>();
    	
    	for(int i=0; i<10; i++){
    		heightal.add(i,mScreenHeight*i/10);
    	}
    	
    	for(int i=0; i<heightal.size()-1; i++){
    		if(mYCorrdinatesOfScreen>=heightal.get(i) && mYCorrdinatesOfScreen<=heightal.get(i+1)){
    			mFrontLightIndex = i;
    		}
    	}
    	
    	mLayoutParams.screenBrightness = (float)mFrontLightIndex/10;
    	
    	if(mLayoutParams.screenBrightness<=0.1f){
    		mLayoutParams.screenBrightness =0.1f;
    	}
    	
    	TraceLogger.print("lp.screenBrightness:"+mLayoutParams.screenBrightness);
    	
    	getWindow().setAttributes(mLayoutParams);
    	
    }
	
	public boolean onTouchEvent(MotionEvent event){
		
		switch(event.getAction()){
		
		case MotionEvent.ACTION_DOWN:
			
			
			break;
			
		case MotionEvent.ACTION_UP:
			
			
			break;
			
		case MotionEvent.ACTION_MOVE:
			mXCoordinatesOfScreen = (int)event.getX();
			mYCorrdinatesOfScreen = (int)event.getY();
			
			detBackLight();
			detFrontLight();
			
			break;
			
		}
		
			return true;
	}
	
    private void showNotificationToTitleBar()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        Notification notification = new Notification();
        notification.tickerText = "TwoFlashLight!!!";
        notification.defaults = Notification.DEFAULT_ALL;
        notification.setLatestEventInfo(MainActivity.this, "TwoFlashLight", "Welcome to use!!!", pendingIntent);
        NotificationManager notificatoinManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificatoinManager.notify(0, notification);
    }
	
	public boolean onKeyDown(int keycode, KeyEvent event){
		
		TraceLogger.print("keycode:"+keycode);
		
		switch(keycode){
			
		// menu key
		case 82:
//			nmShow();
			//background use
			break;
		//back key
		case 4:
			//finish app
			showExitDialog();
			return false;
		}
		
		return super.onKeyDown(keycode, event);
	}
	
	
    private void showExitDialog()
    {
        Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Warning");
        dialog.setMessage("Exit Application??");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                showNotificationToTitleBar();
                MainActivity.this.finish();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });

        dialog.show();
    }
}
