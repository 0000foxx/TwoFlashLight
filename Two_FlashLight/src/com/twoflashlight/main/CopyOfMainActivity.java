package com.twoflashlight.main;

import java.util.ArrayList;
import java.util.List;

import com.twoflashlight.utility.FP;
import com.tylerfoxx.twoflash.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;

public class CopyOfMainActivity extends Activity 
//implements SurfaceHolder.Callback
{

	Camera c;
    Parameters p;
	
//	SurfaceView sfc;
//	SurfaceHolder sfh;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        c = Camera.open();
//        c.setDisplayOrientation(1);
//        sfc = (SurfaceView)findViewById(R.id.surfaceView1);
//        sfh = sfc.getHolder();
//        sfh.addCallback(this);
//        sfh.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
 
        p = c.getParameters();
        p.setFlashMode(Parameters.FLASH_MODE_TORCH);
        
        c.setParameters(p);
       
//        c.startPreview();
    }

//    @Override
//	public void surfaceChanged(SurfaceHolder holder, int format, int width,
//			int height) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void surfaceCreated(SurfaceHolder holder) {
//		// TODO Auto-generated method stub
//		try {
//			c.setPreviewDisplay(holder);
////			sfh.addCallback(MainActivity.this);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//
//	@Override
//	public void surfaceDestroyed(SurfaceHolder holder) {
//		// TODO Auto-generated method stub
//		
//	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

   
    
    @TargetApi(14)
	public boolean onTouchEvent(MotionEvent me){
    	
    	switch(me.getAction()){
    	
    	case MotionEvent.ACTION_DOWN:
    		
//    		String s = p.getFlashMode();
//    		if(s.equals(Parameters.FLASH_MODE_TORCH)){
//    			p.setFlashMode(Parameters.FLASH_MODE_OFF);
//    		}
//    		else if(s.equals(Parameters.FLASH_MODE_OFF)){
//    			p.setFlashMode(Parameters.FLASH_MODE_TORCH);
//    		}
    		
    		testSupplyFunction();
    		
    		break;
    	}
    	
		return true;
    	
    }
    
    int testindex =0;
    @TargetApi(14)
	void testSupplyFunction(Object... o){
    	
    	p.setAutoWhiteBalanceLock(false);
		
		List<String> autowhitelist = new ArrayList<String>();
		
		autowhitelist = p.getSupportedWhiteBalance();
		
//		for(String s : autowhitelist){
//			FP.p(""+s);
//		}
		
		if(testindex<autowhitelist.size())
		{
			FP.p("autowhitelist.get(tempindex): "+autowhitelist.get(testindex));
			p.setWhiteBalance(autowhitelist.get(testindex++));
			
		}
		else{
			testindex =0;
		}
		
		
		c.setParameters(p);
    	
    }
    
}
