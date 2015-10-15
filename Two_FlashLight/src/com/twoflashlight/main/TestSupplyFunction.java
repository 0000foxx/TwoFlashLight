package com.twoflashlight.main;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class TestSupplyFunction {

	public TestSupplyFunction(Camera c, Parameters p){
		
	}
}

class OnOffLight extends TestSupplyFunction{

	public OnOffLight(Camera c, Parameters p) {
		super(c, p);
		String s = p.getFlashMode();
		if(s.equals(Parameters.FLASH_MODE_TORCH)){
			p.setFlashMode(Parameters.FLASH_MODE_OFF);
		}
		else if(s.equals(Parameters.FLASH_MODE_OFF)){
			p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		}
	}
	
}
