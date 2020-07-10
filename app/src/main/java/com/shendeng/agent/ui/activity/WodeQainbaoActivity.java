package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.QainbaoModel;
import com.shendeng.agent.model.WodeModel;
import com.shendeng.agent.util.Urls;

import java.util.HashMap;
import java.util.Map;

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
    private QainbaoModel.DataBean dataBean;

    @Override
    public int getContentViewResId() {
        return R.layout.act_mine_qianbao;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public boolean showToolBarLine() {
        return false;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("我的钱包");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WodeQainbaoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getNet();
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04332");
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<QainbaoModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<QainbaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<QainbaoModel.DataBean>> response) {
                        dataBean = response.body().data.get(0);
                        tv_money.setText(dataBean.getInst_money_access());
                        tv_jiesuan_money.setText(dataBean.getInst_money_ready());
                    }
                });
    }

    @OnClick({R.id.ll_mingxi, R.id.bt_tixian, R.id.ll_jiesuan, R.id.ll_zhanghaoset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_mingxi:
                WodeMingxiActivity.actionStart(this);
                break;
            case R.id.bt_tixian:
                break;
            case R.id.ll_jiesuan:
                WodeJiesuanActivity.actionStart(this);
                break;
            case R.id.ll_zhanghaoset:
                break;
        }
    }
}
