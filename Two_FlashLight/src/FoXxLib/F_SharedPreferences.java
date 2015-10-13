//This is sharedpreferences exam
package FoXxLib;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class F_SharedPreferences extends Activity{

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//get data 
		SharedPreferences sp = getPreferences(0);
		sp.getInt("key", 1);// for more getXXX function
		
		
		//put data
		SharedPreferences sp2 = getPreferences(0);
		SharedPreferences.Editor spe = sp2.edit();
		spe.putInt("key", 99);// for more putXXX function
		spe.commit();// this Important!!
	}
	
}
