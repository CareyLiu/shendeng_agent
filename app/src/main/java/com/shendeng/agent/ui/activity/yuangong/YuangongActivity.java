package com.shendeng.agent.ui.activity.yuangong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.WodeModel;
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

public class YuangongActivity extends BaseActivity {

    @BindView(R.id.tv_zaizhi)
    TextView tv_zaizhi;
    @BindView(R.id.tv_lizhi)
    TextView tv_lizhi;
    @BindView(R.id.rv_yuangong)
    RecyclerView rv_yuangong;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_no_data)
    LinearLayout ll_no_data;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    private YuangongAdapter adapter;
    private String of_user_id;
    private String subsystem_id;
    private String inst_id;
    private String sub_state;

    @Override
    public int getContentViewResId() {
        return R.layout.act_yuangong;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("员工管理");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String of_user_id, String inst_id, String subsystem_id) {
        Intent intent = new Intent();
        intent.setClass(context, YuangongActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("of_user_id", of_user_id);
        intent.putExtra("inst_id", inst_id);
        intent.putExtra("subsystem_id", subsystem_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        inst_id = getIntent().getStringExtra("inst_id");
        of_user_id = getIntent().getStringExtra("of_user_id");
        subsystem_id = getIntent().getStringExtra("subsystem_id");

        initAdapter();
        initSM();
        getNet();
    }

    private void initAdapter() {
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");

        adapter = new YuangongAdapter(R.layout.item_yuangong, strings);
        rv_yuangong.setLayoutManager(new LinearLayoutManager(mContext));
        rv_yuangong.setAdapter(adapter);
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04219);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("of_user_id", of_user_id);
        map.put("subsystem_id", subsystem_id);
        map.put("inst_id", inst_id);
        map.put("sub_state", sub_state);
        Gson gson = new Gson();
        OkGo.<AppResponse<WodeModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<WodeModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<WodeModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    @OnClick({R.id.tv_zaizhi, R.id.tv_lizhi, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zaizhi:
                break;
            case R.id.tv_lizhi:
                break;
            case R.id.iv_add:
                YuangongAddActivity.actionStart(mContext,of_user_id,inst_id,subsystem_id);
                break;
        }
    }
}
