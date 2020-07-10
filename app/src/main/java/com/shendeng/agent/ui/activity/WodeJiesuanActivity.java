package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shendeng.agent.R;
import com.shendeng.agent.adapter.WodeJiesuanAdapter;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.JiesuanModel;
import com.shendeng.agent.model.MingxiModel;
import com.shendeng.agent.util.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WodeJiesuanActivity extends BaseActivity {

    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    private String shop_form_id;
    private List<JiesuanModel.DataBean> data = new ArrayList<>();
    private WodeJiesuanAdapter jiesuanAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.act_mine_jiesuan;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("待结算");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WodeJiesuanActivity.class);
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
        initAdapter();
        getNet();
    }

    private void initAdapter() {
        jiesuanAdapter = new WodeJiesuanAdapter(R.layout.item_mine_jiesuan, data);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
        rv_content.setAdapter(jiesuanAdapter);
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04335");
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("shop_form_id", shop_form_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<JiesuanModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<JiesuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<JiesuanModel.DataBean>> response) {
                        data = response.body().data;
                        jiesuanAdapter.setNewData(data);
                        jiesuanAdapter.notifyDataSetChanged();
                    }
                });
    }

}
