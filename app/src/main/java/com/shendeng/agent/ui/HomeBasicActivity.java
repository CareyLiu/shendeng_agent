package com.shendeng.agent.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.shendeng.agent.R;
import com.shendeng.agent.app.App;
import com.shendeng.agent.app.AppConfig;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.basicmvp.BasicFragment;
import com.shendeng.agent.ui.fragment.BottomDingDanFragment;
import com.shendeng.agent.ui.fragment.BottomShangPinFragment;
import com.shendeng.agent.ui.fragment.BottomWoDeFragment;
import com.shendeng.agent.ui.fragment.BottomXiaoXiFragment;
import com.shendeng.agent.ui.view.BottomBar;
import com.shendeng.agent.ui.view.BottomBarTab;
import com.shendeng.agent.ui.widget.DoubleClickExitHelper;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.Date;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class HomeBasicActivity extends BaseActivity {

    private static final String tag = HomeBasicActivity.class.getSimpleName();

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private final int VISIBLESTATE = 1;//可见
    private final int INVISIBLESTATE = 0;//不可见

    public static BottomBar mBottomBar;
    private BasicFragment[] mFragments = new BasicFragment[5];
    DoubleClickExitHelper doubleClick;
    private App app;
    public static HomeBasicActivity ac;

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HomeBasicActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_home_basic;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ac = this;
        BasicFragment firstFragment = findFragment(BottomDingDanFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = BottomDingDanFragment.newInstance();
            mFragments[SECOND] = BottomXiaoXiFragment.newInstance();
            mFragments[THIRD] = BottomShangPinFragment.newInstance();
            mFragments[FOURTH] = BottomWoDeFragment.newInstance();


            loadMultipleRootFragment(R.id.fl_container1, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(BottomXiaoXiFragment.class);
            mFragments[THIRD] = findFragment(BottomShangPinFragment.class);
            mFragments[FOURTH] = findFragment(BottomWoDeFragment.class);

        }

        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.show();
        mBottomBar
                .addItem(new BottomBarTab(this, R.mipmap.tab_icon_dingdan_nor, R.mipmap.tab_icon_dingdan_sel, "订单"))
                .addItem(new BottomBarTab(this, R.mipmap.tab_icon_xiaoxi_nor, R.mipmap.tab_icon_xiaoxi_sel, "消息"))
                .addItem(new BottomBarTab(this, R.mipmap.tab_icon_shangpin_nor, R.mipmap.tab_icon_shangpin_sel, "商品"))
                .addItem(new BottomBarTab(this, R.mipmap.tab_icon_mine_nor, R.mipmap.tab_icon_mine_sel, "我的"));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {


                showHideFragment(mFragments[position], mFragments[prePosition]);

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                switch (position) {

                }
                // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                // EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent(position));

            }
        });
        app = App.getInstance();
        doubleClick = new DoubleClickExitHelper(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        RxPermissions rxPermissions = new RxPermissions(HomeBasicActivity.this);
//        rxPermissions.request(AppConfig.BASIC_PERMISSIONS).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean granted) {
//                if (granted) { // 在android 6.0之前会默认返回true
//
//                } else {
//
//                }
//            }
//        });
        app = App.getInstance();

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            /*
             * 注意在if判断中要加一个event.getAction() == KeyEvent.ACTION_DOWN判断，
             * 因为按键有两个事件ACTION_DOWN和ACTION_UP，也就是按下和松开，
             * 如果不加这个判断，代码会执行两遍
             */
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                //exitApp();
                doubleClick.onKeyDown(event.getKeyCode(), event);
            }
            //实现只在冷启动时显示启动页，即点击返回键与点击HOME键退出效果一致
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            startActivity(intent);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
