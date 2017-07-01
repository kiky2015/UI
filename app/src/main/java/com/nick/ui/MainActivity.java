package com.nick.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nick.ui.fragment.BezierFragment;
import com.nick.ui.fragment.ChartsFragment;
import com.nick.ui.fragment.ContentFragment;
import com.nick.ui.fragment.ListViewFragment;
import com.nick.ui.fragment.Passwd;
import com.nick.ui.fragment.SwipeFragment;
import com.nick.ui.fragment.SwitchButtonFragment;
import com.nick.ui.fragment.TitanicFragment;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

public class MainActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener,
        OnMenuItemClickListener, OnMenuItemLongClickListener {
    private DrawerLayout drawerLayout;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private ViewAnimator viewAnimator;
    private int res = R.drawable.content_films;
    private LinearLayout linearLayout;
    private ImageView slidingMenu;
    private ImageView contextMenu;
    private TextView title;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        contentFragment = ContentFragment.newInstance(res);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        slidingMenu = (ImageView) findViewById(R.id.slidingMenu);
        contextMenu  = (ImageView) findViewById(R.id.contextMenu);
        title = (TextView) findViewById(R.id.title);
        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);

        initMenuFragment();
    }

    private void setActionBar() {
        slidingMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.REFRESH, R.drawable.icn_1);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.TITANIC, R.drawable.icn_2);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.SWIPE, R.drawable.icn_3);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.PWD, R.drawable.icn_4);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.SWITHBTN, R.drawable.icn_5);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.CHART, R.drawable.icn_6);
        list.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem(ContentFragment.BEZIER, R.drawable.icn_7);
        list.add(menuItem7);
    }


    private ScreenShotable replaceFragment(String item, int topPosition) {
        this.res = this.res == R.drawable.content_music ? R.drawable.content_films : R.drawable.content_music;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
        if(!ContentFragment.CHART.equals(item))
            animator.start();
        ScreenShotable contentFragment = null;

        switch (item) {
            case ContentFragment.REFRESH:
                contentFragment = ListViewFragment.newInstance();
                break;
            case ContentFragment.TITANIC:
                contentFragment = TitanicFragment.newInstance();
                break;
            case ContentFragment.SWIPE:
                contentFragment = SwipeFragment.newInstance();
                break;
            case ContentFragment.PWD:
                contentFragment = Passwd.newInstance();
                break;
            case ContentFragment.SWITHBTN:
                contentFragment = SwitchButtonFragment.newInstance();
                break;
            case ContentFragment.CHART:
                contentFragment = ChartsFragment.newInstance();
                break;
            case ContentFragment.BEZIER:
                contentFragment = BezierFragment.newInstance();
                break;
            default:
                contentFragment = ContentFragment.newInstance(this.res);
                break;
        }
        fragmentManager.beginTransaction().replace(R.id.content_frame, (Fragment) contentFragment).commit();
        return contentFragment;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            case ContentFragment.REFRESH:
                title.setText("下拉刷新");
                return replaceFragment(slideMenuItem.getName(), position);
            case ContentFragment.TITANIC:
                title.setText("波浪字体");
                return replaceFragment(slideMenuItem.getName(), position);
            case ContentFragment.SWIPE:
                title.setText("往复滑动");
                return replaceFragment(slideMenuItem.getName(), position);
            case ContentFragment.PWD:
                title.setText("密码键盘");
                return replaceFragment(slideMenuItem.getName(), position);
            case ContentFragment.SWITHBTN:
                title.setText("拖动按钮");
                return replaceFragment(slideMenuItem.getName(), position);
            case ContentFragment.CHART:
                title.setText("各种图表");
                return replaceFragment(slideMenuItem.getName(), position);
            case ContentFragment.BEZIER:
                title.setText("特效消息数");
                return replaceFragment(slideMenuItem.getName(), position);
            default:
                title.setText("");
                return replaceFragment(slideMenuItem.getName(), position);
        }
    }

    @Override
    public void disableHomeButton() {

    }

    @Override
    public void enableHomeButton() {
        drawerLayout.closeDrawers();
    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }


    private void initMenuFragment() {
        contextMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
            }
        });
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("下接刷新");
        send.setResource(R.drawable.icn_1);

        MenuObject like = new MenuObject("流浪字体");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icn_2);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("往复滑动");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("密码键盘");
        addFav.setResource(R.drawable.icn_4);

        MenuObject block = new MenuObject("拖动按钮");
        block.setResource(R.drawable.icn_5);

        MenuObject oumet = new MenuObject("各种图表");
        oumet.setResource(R.drawable.icn_6);

        MenuObject ndiy = new MenuObject("特效消息数");
        ndiy.setResource(R.drawable.icn_7);

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(block);
        menuObjects.add(oumet);
        menuObjects.add(ndiy);
        return menuObjects;
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        /*switch (position) {
            case 0:
                break;
            case 1:
                title.setText("下拉刷新");
                replaceFragment(ContentFragment.REFRESH, position);
            break;
            default:
                title.setText("");
                replaceFragment(null, position);
            break;
        }*/
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {

    }
}
