package com.twoflashlight.utility;

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
		
		ad.setTitle("ĵ�i");//�]�wĵ�i���D
		ad.setMessage("�T�w���}??");//�]�wĵ�i���e
		ad.setButton("�T�w", new DialogInterface.OnClickListener() {//�]�w���s1
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//�I����s1����檺�ʧ@
				//�ˬd�������A
				ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

				NetworkInfo ni = cm.getActiveNetworkInfo();
				if (ni != null && ni.isConnected()) {//�Y��������s����~������

					Uri uri = Uri.parse("http://vulpesadn.blogspot.tw/");
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);

					finish();//�A�����{��
				} else if (ni == null) {//�S������

					finish();//�����{��
				}
			}
		});
		ad.setButton2("���", new DialogInterface.OnClickListener() {//�]�w���s2
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//�I����s2����檺�ʧ@
				//�L�ʧ@
			}
		});
		
		ad.setCanceledOnTouchOutside(false);//��ĵ�i���ܥX�{��,�I�ﴣ�ܥH�~�d��,�O�_�|����,�w�]�Otrue
		
		ad.setCancelable(false);//��ĵ�i���ܥX�{��,�I���L������s(backkey����),�O�_�|����,�w�]�Otrue
		
		ad.show();//��ܫ��s
	}
}
