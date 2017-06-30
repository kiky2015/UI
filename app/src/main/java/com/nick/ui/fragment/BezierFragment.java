package com.nick.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nick.ui.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by ao on 2017/7/1.
 * user:kiky
 */

public class BezierFragment extends Fragment implements ScreenShotable {

    public static BezierFragment newInstance() {
        BezierFragment fragment = new BezierFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.bezier,container,false);
        return rootview;
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
