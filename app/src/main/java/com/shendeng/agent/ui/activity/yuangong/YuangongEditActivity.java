package com.shendeng.agent.ui.activity.yuangong;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;

public class YuangongEditActivity extends BaseActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.act_yuangong_add;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("修改员工信息");
        tv_rightTitle.setText("保存");
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setTextSize(17);
        tv_rightTitle.setTextColor(this.getResources().getColor(R.color.text_red));
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String of_user_id, String inst_id, String subsystem_id) {
        Intent intent = new Intent();
        intent.setClass(context, YuangongEditActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("of_user_id", of_user_id);
        intent.putExtra("inst_id", inst_id);
        intent.putExtra("subsystem_id", subsystem_id);
        context.startActivity(intent);
    }
}
