package com.nick.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.nick.ui.R;
import com.nick.ui.utils.Md5Utils;
import com.nick.ui.utils.SharedPreferencesHelper;
import com.nick.ui.view.LocusPassWordView;
import com.yalantis.phoenix.PullToRefreshView;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by ao on 2017/6/30.
 * User: kiky
 */

public class Passwd  extends Fragment implements ScreenShotable {

    private LocusPassWordView mPwdView;
    private Context mContext;

    public static Passwd newInstance() {
        Passwd contentFragment = new Passwd();
        return contentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferencesHelper sph = SharedPreferencesHelper.getInstance(getContext());
        sph.putString("password", null);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.draw_pwd, container, false);
        mContext = getContext();
        mPwdView = (LocusPassWordView) rootView.findViewById(R.id.mPassWordView);
        mPwdView.setOnCompleteListener(new LocusPassWordView.OnCompleteListener() {
            @Override
            public void onComplete(String mPassword) {
                SharedPreferencesHelper sph = SharedPreferencesHelper.getInstance(getContext());
                String pwd = sph.getString("password", "");
                Md5Utils md5 = new Md5Utils();
                boolean passed = false;
                if (pwd.length() == 0) {
                    sph.putString("password", md5.toMd5(mPassword, ""));
                    Toast.makeText(mContext, mContext.getString(R.string.pwd_setted), Toast.LENGTH_LONG).show();
                    mPwdView.clearPassword();
                    return;
                } else {
                    String encodedPwd = md5.toMd5(mPassword, "");
                    if (encodedPwd.equals(pwd)) {
                        passed = true;
                    } else {
                        mPwdView.markError();
                    }
                }

                if (passed) {
                    Log.d("hcj", "password is correct!");
                    Toast.makeText(mContext, mContext.getString(R.string.pwd_correct), Toast.LENGTH_LONG).show();
                    mPwdView.clearPassword();
                }
            }
        });
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
