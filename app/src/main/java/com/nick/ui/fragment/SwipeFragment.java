package com.nick.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.nick.ui.R;
import com.nick.ui.titanic.Titanic;
import com.nick.ui.titanic.TitanicTextView;
import com.nick.ui.utils.Typefaces;
import com.nineoldandroids.view.ViewHelper;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by ao on 2017/6/30.
 * User: kiky
 */

public class SwipeFragment  extends Fragment implements ScreenShotable {

    private SwipeLayout sample1 = null;
    private SwipeLayout sample2 = null;
    private SwipeLayout sample3 = null;

    public static SwipeFragment newInstance() {
        SwipeFragment contentFragment = new SwipeFragment();
        return contentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.swiperlayoutfragment, container, false);

        sample1 = (SwipeLayout) rootView.findViewById(R.id.sample1);
        sample1.setShowMode(SwipeLayout.ShowMode.PullOut);
        View starBottView = sample1.findViewById(R.id.starbott);
        sample1.addDrag(SwipeLayout.DragEdge.Left, sample1.findViewById(R.id.bottom_wrapper));
        sample1.addDrag(SwipeLayout.DragEdge.Right, sample1.findViewById(R.id.bottom_wrapper_2));
        sample1.addDrag(SwipeLayout.DragEdge.Top, starBottView);
        sample1.addDrag(SwipeLayout.DragEdge.Bottom, starBottView);
        sample1.addRevealListener(R.id.delete, new SwipeLayout.OnRevealListener() {
            @Override
            public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {

            }
        });

        sample1.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Click on surface", Toast.LENGTH_SHORT).show();
            }
        });
        sample1.getSurfaceView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity(), "longClick on surface", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        sample1.findViewById(R.id.star2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Star", Toast.LENGTH_SHORT).show();
            }
        });

        sample1.findViewById(R.id.trash2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Trash Bin", Toast.LENGTH_SHORT).show();
            }
        });

        sample1.findViewById(R.id.magnifier2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Magnifier", Toast.LENGTH_SHORT).show();
            }
        });

        sample1.addRevealListener(R.id.starbott, new SwipeLayout.OnRevealListener() {
            @Override
            public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {
                View star = child.findViewById(R.id.star);
                float d = child.getHeight() / 2 - star.getHeight() / 2;
                ViewHelper.setTranslationY(star, d * fraction);
                ViewHelper.setScaleX(star, fraction + 0.6f);
                ViewHelper.setScaleY(star, fraction + 0.6f);
            }
        });

        //sample2

        sample2 = (SwipeLayout) rootView.findViewById(R.id.sample2);
        sample2.setShowMode(SwipeLayout.ShowMode.LayDown);
        sample2.addDrag(SwipeLayout.DragEdge.Right, sample2.findViewWithTag("Bottom2"));
//        sample2.setShowMode(SwipeLayout.ShowMode.PullOut);
        sample2.findViewById(R.id.star).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Star", Toast.LENGTH_SHORT).show();
            }
        });

        sample2.findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Trash Bin", Toast.LENGTH_SHORT).show();
            }
        });

        sample2.findViewById(R.id.magnifier).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Magnifier", Toast.LENGTH_SHORT).show();
            }
        });

        sample2.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Yo", Toast.LENGTH_SHORT).show();
            }
        });
        sample2.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Click on surface", Toast.LENGTH_SHORT).show();
            }
        });

        //sample3

        sample3 = (SwipeLayout) rootView.findViewById(R.id.sample3);
        sample3.addDrag(SwipeLayout.DragEdge.Top, sample3.findViewWithTag("Bottom3"));
        sample3.addRevealListener(R.id.bottom_wrapper_child1, new SwipeLayout.OnRevealListener() {
            @Override
            public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {
                View star = child.findViewById(R.id.star);
                float d = child.getHeight() / 2 - star.getHeight() / 2;
                ViewHelper.setTranslationY(star, d * fraction);
                ViewHelper.setScaleX(star, fraction + 0.6f);
                ViewHelper.setScaleY(star, fraction + 0.6f);
                int c = (Integer) evaluate(fraction, Color.parseColor("#dddddd"), Color.parseColor("#4C535B"));
                child.setBackgroundColor(c);
            }
        });
        sample3.findViewById(R.id.bottom_wrapper_child1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Yo!", Toast.LENGTH_SHORT).show();
            }
        });
        sample3.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Click on surface", Toast.LENGTH_SHORT).show();
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

    /*
    Color transition method.
     */
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                (int) ((startB + (int) (fraction * (endB - startB))));
    }
}
