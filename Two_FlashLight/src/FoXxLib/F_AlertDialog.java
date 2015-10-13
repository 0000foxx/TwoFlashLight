package FoXxLib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class F_AlertDialog extends Activity{

	
	void showAlertDialog(){

		AlertDialog ad = new AlertDialog.Builder(getBaseContext()).create();
		
		ad.setTitle("警告");//設定警告標題
		ad.setMessage("確定離開??");//設定警告內容
		ad.setButton("確定", new DialogInterface.OnClickListener() {//設定按鈕1
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//點選按鈕1後執行的動作
				//檢查網路狀態
				ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

				NetworkInfo ni = cm.getActiveNetworkInfo();
				if (ni != null && ni.isConnected()) {//若有網路先連結到外部網頁

					Uri uri = Uri.parse("http://vulpesadn.blogspot.tw/");
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);

					finish();//再結束程序
				} else if (ni == null) {//沒有網路

					finish();//結束程式
				}
			}
		});
		ad.setButton2("取消", new DialogInterface.OnClickListener() {//設定按鈕2
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//點選按鈕2後執行的動作
				//無動作
			}
		});
		
		ad.setCanceledOnTouchOutside(false);//當警告提示出現後,點選提示以外範圍,是否會取消提示,預設是true
		
		ad.setCancelable(false);//當警告提示出現後,點選其他實體按鈕(backkey等等),是否會取消提示,預設是true
		
		ad.show();//顯示按鈕
	}
}
