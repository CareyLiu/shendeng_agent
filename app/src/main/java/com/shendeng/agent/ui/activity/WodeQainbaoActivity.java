package com.shendeng.agent.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WodeQainbaoActivity extends BaseActivity {

    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.ll_mingxi)
    LinearLayout ll_mingxi;
    @BindView(R.id.bt_tixian)
    Button bt_tixian;
    @BindView(R.id.tv_jiesuan_money)
    TextView tv_jiesuan_money;
    @BindView(R.id.ll_jiesuan)
    LinearLayout ll_jiesuan;
    @BindView(R.id.ll_zhanghaoset)
    LinearLayout ll_zhanghaoset;

    @Override
    public int getContentViewResId() {
        return R.layout.act_mine_qianbao;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        iv_leftTitle.setVisibility(View.VISIBLE);
        tv_title.setText("我的钱包");
        tv_title.setTextSize(17);
        tv_title.setTextColor(this.getResources().getColor(R.color.color_494949));
        tv_title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_mingxi, R.id.bt_tixian, R.id.ll_jiesuan, R.id.ll_zhanghaoset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_mingxi:
                break;
            case R.id.bt_tixian:
                break;
            case R.id.ll_jiesuan:
                break;
            case R.id.ll_zhanghaoset:
                break;
        }
    }
}
