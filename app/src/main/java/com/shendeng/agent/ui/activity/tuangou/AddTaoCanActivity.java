package com.shendeng.agent.ui.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;

import butterknife.BindView;

public class AddTaoCanActivity extends BaseActivity {

    @BindView(R.id.tv_mingcheng)
    TextView tvMingcheng;
    @BindView(R.id.tv_shuliang)
    TextView tvShuliang;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.et_jiage)
    EditText etJiage;
    @BindView(R.id.cl_one)
    ConstraintLayout clOne;
    @BindView(R.id.ll_diercijinru)
    LinearLayout llDiercijinru;
    String type; // 0 第一次进入 1 第二次进入
    @BindView(R.id.tv_queding)
    TextView tvQueding;
    @BindView(R.id.tv_shanchu)
    TextView tvShanchu;
    @BindView(R.id.tv_queding1)
    TextView tvQueding1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getIntent().getStringExtra("type").equals("0")) {

            tvQueding.setVisibility(View.VISIBLE);
            llDiercijinru.setVisibility(View.GONE);
        } else {
            tvQueding.setVisibility(View.GONE);
            llDiercijinru.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("套餐管理");
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_add_tao_can;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String type) {
        Intent intent = new Intent();
        intent.setClass(context, AddTaoCanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

}
