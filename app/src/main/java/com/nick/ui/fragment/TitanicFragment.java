package com.nick.ui.fragment;

import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nick.ui.R;
import com.nick.ui.titanic.Titanic;
import com.nick.ui.titanic.TitanicTextView;
import com.nick.ui.utils.Typefaces;
import com.yalantis.phoenix.PullToRefreshView;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by ao on 2017/6/30.
 * User: kiky
 */

public class TitanicFragment extends Fragment implements ScreenShotable {

    public static TitanicFragment newInstance() {
        TitanicFragment contentFragment = new TitanicFragment();
        return contentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.titanic, container, false);
        TitanicTextView tv = (TitanicTextView) rootView.findViewById(R.id.titanic_tv);

        // set fancy typeface
        tv.setTypeface(Typefaces.get(getActivity(), "Satisfy-Regular.ttf"));

        // start animation
        new Titanic().start(tv);

        return rootView;
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
