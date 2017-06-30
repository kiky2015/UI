package com.nick.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;
import com.nick.ui.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Oleksii Shliama.
 * User: kiky
 */
public class ListViewFragment extends Fragment implements ScreenShotable {
    public static final int REFRESH_DELAY = 2000;
    private PullToRefreshView mPullToRefreshView;

    public static ListViewFragment newInstance() {
        ListViewFragment contentFragment = new ListViewFragment();
        return contentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_view, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(getActivity()));

        mPullToRefreshView = (PullToRefreshView) rootView.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
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

    class MyAdapter extends BaseAdapter {
        private List<Integer> list = new ArrayList<>();
        private final LayoutInflater mInflater;

        public MyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            list.add(R.color.colorPrimary);
            list.add(R.color.colorPrimaryDark);
            list.add(R.color.colorAccent);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            convertView.setBackgroundResource(list.get(position));

            return convertView;
        }
    }

}
