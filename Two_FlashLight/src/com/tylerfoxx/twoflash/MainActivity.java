package com.tylerfoxx.twoflash;

import java.util.ArrayList;

import FoXxLib.FP;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class MainActivity extends Activity 
//implements SurfaceHolder.Callback
{

	static Camera c;
    static Parameters p;
	
    public LayoutParams lp;
    public PowerManager powerm;
	public PowerManager.WakeLock WL;
    
//	ImageView backPic;
	
    public static int width;
	public static int height;
    
	public int moveX;
	public int backLightIndex;
	public int moveY;
	public int frontLightIndex;
	
//	SurfaceView sfc;
//	SurfaceHolder sfh;
	
    AdView adview;
    
    int testnum;
    
    boolean isCameraUse;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
        
    }
    
//    public void onPause(){
//    	super.onPause();
////    	c.release();
//    }
//	
//    public void onResume(){
//    	super.onResume();
//    	if(c!=null){
//    		c.release();
//    		c.open();
//    	}
//    	else{
//    		c.open();
//    	}
//    	
//    }
    
//    public void onDestroy(){
////    	super.onDestroy();
//    	FP.p("destroy");
//    	isCameraUse = false;
//    	SharedPreferences sp = getSharedPreferences("flash", 0);
//		SharedPreferences.Editor spe = sp.edit();
//		
//		spe.putBoolean("isCameraUse", isCameraUse);
//		spe.commit();
//    }
    
	void init(){
		
    	width = (getWindowManager().getDefaultDisplay().getWidth());
		
    	moveX = width/2;
    	backLightIndex = 1;
    	
		height = (getWindowManager().getDefaultDisplay().getHeight());
     
		moveY = height/2;
		frontLightIndex =5;
        showAdmob();
        

        
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
    	
    	powerm = (PowerManager)getSystemService(Context.POWER_SERVICE);
    	
    	WL = powerm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "BackLight");
    	
    	WL.acquire();
    	
    	lp = getWindow().getAttributes();
    	
    	lp.screenBrightness = 0.1f;
    	
    	getWindow().setAttributes(lp);
    	
    	System.out.println("lp.screenBrightness:"+lp.screenBrightness);
    	
//    	backPic = (ImageView)findViewById(R.id.backpic);
    	
    	detBackLight();
    	
    	detFrontLight();
    }
    
    void detBackLight(){
    	
    	ArrayList<Integer> widthal = new ArrayList<Integer>();
    	
    	for(int i=0; i<4; i++){
    		widthal.add(i,width*i/3);
//    		FP.p(""+widthal.get(i));
    	}
    	
    	for(int i=0; i<widthal.size()-1; i++){
    		if(moveX>=widthal.get(i) && moveX<=widthal.get(i+1)){
    			backLightIndex = i;
    		}
    	}
    	
    	FP.p("backLightIndex:"+backLightIndex);
    	
    	if(backLightIndex==0){
    		p.setFlashMode(Parameters.FLASH_MODE_OFF);
    		
    	}
    	else if(backLightIndex==1){
    		
    		p.setFocusMode("macro");
    		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
    		
    	}
    	else if(backLightIndex==2){
    		p.setFocusMode("auto");
    		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
    	}
    	
    	try{
    		//because some system no supply "p.setFocusMode("macro");" so must try{}catch(){}
    		c.setParameters(p);
    	}
    	catch(Exception e){
    		
    	}
    	
    	
    }
    
    void detFrontLight(){
    	ArrayList<Integer> heightal = new ArrayList<Integer>();
    	
    	for(int i=0; i<10; i++){
    		heightal.add(i,height*i/10);
//    		FP.p(""+widthal.get(i));
    	}
    	
    	for(int i=0; i<heightal.size()-1; i++){
    		if(moveY>=heightal.get(i) && moveY<=heightal.get(i+1)){
    			frontLightIndex = i;
    		}
    	}
    	
//    	FP.p("frontLightIndex:"+frontLightIndex);
    	
    	lp.screenBrightness = (float)frontLightIndex/10;
    	
    	if(lp.screenBrightness<=0.1f){
    		lp.screenBrightness =0.1f;
    	}
    	
    	FP.p("lp.screenBrightness:"+lp.screenBrightness);
    	
    	getWindow().setAttributes(lp);
    	
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
    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
//
//   
//    
   
    
//    int testindex =0;
//    @TargetApi(14)
//	void testSupplyFunction(int type){
//    	
//    	if(type==0){
//    		String s = p.getFlashMode();
//    		if(s.equals(Parameters.FLASH_MODE_TORCH)){
//    			p.setFlashMode(Parameters.FLASH_MODE_OFF);
//    		}
//    		else if(s.equals(Parameters.FLASH_MODE_OFF)){
//    			p.setFlashMode(Parameters.FLASH_MODE_TORCH);
//    		}
//    	}
//    	else if(type==1){
//        	p.setAutoWhiteBalanceLock(false);
//    		
//    		List<String> autowhitelist = new ArrayList<String>();
//    		
//    		autowhitelist = p.getSupportedWhiteBalance();
//    		
////    		for(String s : autowhitelist){
////    			FP.p(""+s);
////    		}
//    		
//    		if(testindex<autowhitelist.size())
//    		{
//    			FP.p("autowhitelist.get(tempindex): "+autowhitelist.get(testindex));
//    			p.setWhiteBalance(autowhitelist.get(testindex++));
//    			
//    		}
//    		else{
//    			testindex =0;
//    		}
//    	}
//    	else if(type==2){
//    		//antibind is null
//    		List<String> antibindlist = new ArrayList<String>();
//    		antibindlist = p.getSupportedAntibanding();
//    		
////    		FP.p("antiblist:"+antibindlist);
//    		
////    		for(String s : antibindlist){
////			FP.p(""+s);
////    		}
//    		
//    		if(testindex<antibindlist.size()){
//    			FP.p("antiband:"+antibindlist.get(testindex));
//    			p.setAntibanding(antibindlist.get(testindex++));
//    		}
//    		else
//    			testindex =0;
//    	}
//    	else if(type==3){
//    		int maxec = p.getMaxExposureCompensation();
//    		int minec = p.getMinExposureCompensation();
////    		FP.p(""+maxec);
////    		FP.p(""+minec);
//    		if(testindex<=maxec){
//    			p.setExposureCompensation(testindex++);
//    			FP.p("testindex:"+testindex);
//    		}
//    		else{
//    			testindex = minec;
//    		}
//    		
//    	}
//    	else if(type==4){
//    		//null
//    		List<Camera.Area> calist = new ArrayList<Camera.Area>();
//    		calist = p.getMeteringAreas();
//    		FP.p("callist:"+calist);
//    		for(Camera.Area ca : calist){
//    			FP.p("weight"+ca.weight+" ca.rect.width:"+ca.rect.width()+" ca.rect.height:"+ca.rect.height());
//    		}
//    		
////    		p.setMeteringAreas(calist.get(testindex));
//    	}
//    	else if(type==5){
//    		List<String> colorEffList = new ArrayList<String>();
//    		colorEffList = p.getSupportedColorEffects();
//    		
////    		for(String s : colorEffList){
////    			FP.p("coloreff:"+s);
////    		}
//    		
//    		if(testindex<colorEffList.size()){
//    			FP.p("testindex:"+colorEffList.get(testindex));
//    		p.setColorEffect(colorEffList.get(testindex++));
//    		
//    		}
//    		else
//    			testindex =0;
//    		
//    	}
//    	else if(type==6){
//    		List<String> flashList = new ArrayList<String>();
//    		flashList = p.getSupportedFlashModes();
//    		
//    		for(String s : flashList){
//    			FP.p(""+s);
//    		}
//    		
//    		if(testindex<flashList.size()){
//    			p.setFlashMode(flashList.get(testindex++));
//    		}
//    		else
//    			testindex =0;
//    		
//    	}
//    	else if(type==7){
//    		List<String> fList = new ArrayList<String>();
//    		fList = p.getSupportedFocusModes();
////    		for(String s : fList)
////    			FP.p("flist:"+s);
//    		
//    		if(testindex<fList.size()){
//    			FP.p(""+fList.get(testindex));
//    			p.setFocusMode(fList.get(testindex++));
////    			p.setFocusMode("macro");
////    			p.setFocusMode("auto");
//    		}
//    		else
//    			testindex =0;
//    	}
//    	else if(type==8){
//    		List<String> sList = new ArrayList<String>();
//    		sList = p.getSupportedSceneModes();
//    		
////    		for(String s : sList){
////    			FP.p(""+s);
////    		}
//    		
//    		if(testindex<sList.size()){
//    			FP.p(""+sList.get(testindex));
//    			p.setSceneMode(sList.get(testindex++));
//    		}
//    		else
//    			testindex =0;
//    		
//    	}
//    	
//		c.setParameters(p);
//    	
//    }
    
    
	void showAdmob(){
		
	AdView adView;
		
//  	  Create the adView     
   	adView = new AdView(this, AdSize.BANNER, "a150c81d1f5acca");      
 
   	LinearLayout layout = (LinearLayout)findViewById(R.id.AdLayout);      
   	   
   	layout.addView(adView);
   	
   	adView.loadAd(new AdRequest()); 
	}
	
	public boolean onTouchEvent(MotionEvent event){
//		FP.p("in onTouchEvent");
		
		
		switch(event.getAction()){
		
		case MotionEvent.ACTION_DOWN:
			
			
			break;
			
		case MotionEvent.ACTION_UP:
			
			
			break;
			
		case MotionEvent.ACTION_MOVE:
			moveX = (int)event.getX();
			moveY = (int)event.getY();
			
//			FP.p("moveX:"+moveX+" moveY:"+moveY);
			
			detBackLight();
			detFrontLight();
			
			break;
			
		}
		
			return true;
	}
	
	void nmShow(){
		
//		// get data for accountdetail
//		SharedPreferences sp = getSharedPreferences("bcn", 0);
//		SharedPreferences.Editor spe3 = sp.edit();
//
//		// get accountdetail count
//		int tempi = sp.getInt("adplaynum", playNum);
		
		Intent it = new Intent(this,MainActivity.class);
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, it, 0);
		
		Notification nn = new Notification();
//		nn.icon = R.drawable.notpic;
		nn.tickerText = "歡迎使用雙面手電筒!!!";
		
//		if(tempi==1)
//		{
			nn.defaults = Notification.DEFAULT_ALL;
//		}
		
		
		nn.setLatestEventInfo(MainActivity.this, "雙面手電筒", "歡迎使用雙面手電筒!!!", pi);

		NotificationManager nm;
		nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		nm.notify(0, nn);
	}
	
	public boolean onKeyDown(int keycode, KeyEvent event){
		
		FP.p("keycode:"+keycode);
		
		switch(keycode){
			
		// menu key
		case 82:
//			nmShow();
			//background use
			break;
		//back key
		case 4:
			//finish app
			finishApp();
			return false;
		}
		
		return super.onKeyDown(keycode, event);
	}
	
	
	public void finishApp() {
		// TODO Auto-generated method stub
		
//		// exit AlertDialog
		Builder ad = new AlertDialog.Builder(MainActivity.this);
		ad.setTitle("警告");//設定警告標題
		ad.setMessage("確定離開??");//設定警告內容
		ad.setPositiveButton("確定", new DialogInterface.OnClickListener() {//設定按鈕1
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				nmShow();
				
				System.exit(0);
				MainActivity.this.finish(); // exit program
				
//				//點選按鈕1後執行的動作
//				//檢查網路狀態
//				ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//				NetworkInfo ni = cm.getActiveNetworkInfo();
//				if (ni == null) {//沒有網路
//
////					CopyRightFlow.this.finish();//結束程式
//					System.exit(0);
//				}
//				else if (ni != null) {//若有網路先連結到外部網頁
//
//					if( ni.isConnected()){
//						
//					
////					Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Vulpse");
////					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
////					startActivity(intent);
//
////					CopyRightFlow.this.finish();//再結束程序
//					System.exit(0);
//					}
//				}
//				
//				All_List.this.finish(); // exit program
				
			}
		});
		ad.setNegativeButton("取消", new DialogInterface.OnClickListener() {//設定按鈕2
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			
			}
		});
		
		ad.show();
		
//		System.exit(0);
		
	}
}
