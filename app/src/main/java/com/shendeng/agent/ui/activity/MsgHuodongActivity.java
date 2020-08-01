package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;

public class MsgHuodongActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.act_msg_im;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("精选活动");
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MsgHuodongActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}