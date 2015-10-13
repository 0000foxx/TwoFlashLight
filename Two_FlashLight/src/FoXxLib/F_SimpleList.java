package FoXxLib;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import com.tlyerfoxx.sbcnote.R;

public class F_SimpleList extends Activity{

	
	String[] strArr = {"aaaaaa","bbbbbbb","ccccccc","ddddddd","eeeeeee"};
	
	ArrayAdapter<String> arrAdapt;
	
	ListView lv1;
	
	public F_SimpleList() {

		// use arrAdapt
//		arrAdapt = new ArrayAdapter<String>(this,R.layout.tabwidget4_textview, strArr);
//
//		lv1 = (ListView) findViewById(R.id.listview1);
//		lv1.setAdapter(arrAdapt);
	}
}
