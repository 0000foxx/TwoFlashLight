package com.twoflashlight.test;


import android.test.ActivityInstrumentationTestCase2;

import com.twoflashlight.main.MainActivity;
import com.twoflashlight.utility.TraceLogger;

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

    public void testTraceLoggerPrint()
    {
        TraceLogger.print("Test message from UnitTest");
    }

}
