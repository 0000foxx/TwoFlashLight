/**
 *   TwoFlashLight Android application
 *
 *   @author foxxtseng
 *   Copyright (C) 2015-2015  foxxtseng
 *   
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License version 2,
 *   as published by the Free Software Foundation.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.twoflashlight.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.tylerfoxx.twoflash.R;

public class CameraActivity extends Activity implements ICameraView
{
    private CameraPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new CameraPresenter(this);
        mPresenter.init();
    }

    @Override
    protected void onDestroy()
    {
        super.onPause();
        mPresenter.releaseCamera();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mPresenter.resumeComponents();
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mPresenter.adjustLight(event);
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event)
    {
        super.onKeyDown(keycode, event);
        switch (keycode) {
            case KeyEvent.KEYCODE_BACK:
                mPresenter.showExitDialog();
                return true;
        }
        return false;
    }

    @Override
    public Context getContext()
    {
        return this;
    }

}
