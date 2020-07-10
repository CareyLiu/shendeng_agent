package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.adapter.WodeMingxiAdapter;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.MingxiModel;
import com.shendeng.agent.ui.view.SelectTabView;
import com.shendeng.agent.util.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WodeMingxiActivity extends BaseActivity {

    @BindView(R.id.tab_all)
    SelectTabView tab_all;
    @BindView(R.id.tab_shouru)
    SelectTabView tab_shouru;
    @BindView(R.id.tab_zhifu)
    SelectTabView tab_zhifu;
    @BindView(R.id.tab_tixian)
    SelectTabView tab_tixian;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private int pay_cost_type;
    private String pay_cost_id;

    private List<MingxiModel.DataBean> data = new ArrayList<>();
    private WodeMingxiAdapter mingxiAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.act_mine_mingxi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("收支明细");
        tv_title.setTextSize(17);
        tv_title.setTextColor(this.getResources().getColor(R.color.color_494949));
        tv_title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WodeMingxiActivity.class);
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
        tab_all.setTitle("全部");
        tab_shouru.setTitle("收入");
        tab_zhifu.setTitle("支出");
        tab_tixian.setTitle("提现");

        initAdapter();
        initSM();

        selectTab(0);
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });


        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getLoad();
            }
        });
    }

    private void initAdapter() {
        mingxiAdapter = new WodeMingxiAdapter(R.layout.item_mine_mingxi, data);
        rv_content.setAdapter(mingxiAdapter);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
    }


    /**
     * shop_pay_check 类型：0.全部1.收入 2.支出 3.提现
     */
    private void selectTab(int pay_cost_type) {
        this.pay_cost_type = pay_cost_type;

        tab_all.setSelect(false);
        tab_shouru.setSelect(false);
        tab_zhifu.setSelect(false);
        tab_tixian.setSelect(false);
        switch (pay_cost_type) {
            case 0:
                tab_all.setSelect(true);
                break;
            case 1:
                tab_shouru.setSelect(true);
                break;
            case 2:
                tab_zhifu.setSelect(true);
                break;
            case 3:
                tab_tixian.setSelect(true);
                break;
        }

        getNet();
    }

    private void getNet() {
        pay_cost_id = "";
        Map<String, String> map = new HashMap<>();
        map.put("code", "04333");
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("pay_cost_type", pay_cost_type + "");
        map.put("pay_cost_id", pay_cost_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<MingxiModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MingxiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MingxiModel.DataBean>> response) {
                        data = response.body().data;
                        pay_cost_id = data.get(data.size() - 1).getPay_cost_id();

                        mingxiAdapter.setNewData(data);
                        mingxiAdapter.notifyDataSetChanged();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }


    private void getLoad() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04333");
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("pay_cost_type", pay_cost_type + "");
        map.put("pay_cost_id", pay_cost_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<MingxiModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MingxiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MingxiModel.DataBean>> response) {
                        List<MingxiModel.DataBean> dataNew = response.body().data;
                        data.addAll(dataNew);
                        pay_cost_id = WodeMingxiActivity.this.data.get(WodeMingxiActivity.this.data.size() - 1).getPay_cost_id();

                        mingxiAdapter.setNewData(WodeMingxiActivity.this.data);
                        mingxiAdapter.notifyDataSetChanged();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    @OnClick({R.id.tab_all, R.id.tab_shouru, R.id.tab_zhifu, R.id.tab_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_all:
                selectTab(0);
                break;
            case R.id.tab_shouru:
                selectTab(1);
                break;
            case R.id.tab_zhifu:
                selectTab(2);
                break;
            case R.id.tab_tixian:
                selectTab(3);
                break;
        }
    }
}
