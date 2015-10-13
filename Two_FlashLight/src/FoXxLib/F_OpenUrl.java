package FoXxLib;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class F_OpenUrl{

	static void openUrl(String urlstr,Activity act){
		
		Uri uri = Uri.parse(urlstr);
		Intent intent = new Intent(Intent.ACTION_VIEW,uri);
		act.startActivity(intent);
		
	}
}
