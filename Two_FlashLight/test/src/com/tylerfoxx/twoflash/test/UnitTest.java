package com.tylerfoxx.twoflash.test;


import android.test.ActivityInstrumentationTestCase2;

import com.twoflashlight.main.MainActivity;

public class UnitTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private MainActivity mMainActivity;
    
    public UnitTest(){
        super(MainActivity.class);
    }
    
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        setActivityInitialTouchMode(true);
        mMainActivity = getActivity();
    }
    
    public void testMainActivityNotNull(){
        assertNotNull(mMainActivity);
    }


}
