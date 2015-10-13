package FoXxLib;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Vibrator;

public class F_Vibrator{
	
	public static void Vibrate(Context con,long time){
		
		Vibrator vi = (Vibrator)con.getSystemService(con.VIBRATOR_SERVICE);
		
		if (Build.VERSION.SDK_INT > 11 && vi.hasVibrator()) { // check vibrate(API 11)
			vi.vibrate(time); // start vibrate
			vi.cancel(); // cancel vibrate

		}
		
		
	}
}
