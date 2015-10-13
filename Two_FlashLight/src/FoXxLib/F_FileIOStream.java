package FoXxLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class F_FileIOStream extends Activity{

	File file;
	FileOutputStream fos;
	FileInputStream fis;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		// file name
		file = new File("test_file");
		
		// write data
		try {
			file = new File("test_file");
		
			FileOutputStream fos = openFileOutput(file.getPath(), Context.MODE_PRIVATE);
			String tempstr = "abc";
			int tempint = 123;
			float tempfloat = 0.88f;
			
			fos.write(tempstr.getBytes());
			fos.write(String.valueOf(tempint).getBytes());
			fos.write(String.valueOf(tempfloat).getBytes());
			
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// get data
		try {
			
			FileInputStream fis = openFileInput(file.getPath());
			
			int temp;
			
			while((temp = fis.read()) !=-1){
				FP.p(String.valueOf(((char)temp)));
			}
			
			fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
