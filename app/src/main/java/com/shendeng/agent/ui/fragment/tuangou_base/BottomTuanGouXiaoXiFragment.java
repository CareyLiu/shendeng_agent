package com.shendeng.agent.ui.fragment.tuangou_base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.adapter.MsgAdapter;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.bean.Notice;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.MessageModel;
import com.shendeng.agent.model.MsgModel;
import com.shendeng.agent.ui.activity.MsgGonggaoActivity;
import com.shendeng.agent.ui.activity.MsgHuodongActivity;
import com.shendeng.agent.ui.activity.MsgIMActivity;
import com.shendeng.agent.ui.activity.MsgNewActivity;
import com.shendeng.agent.ui.activity.MsgOrderActivity;
import com.shendeng.agent.ui.adapter.MessageListAdapter;
import com.shendeng.agent.util.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BottomTuanGouXiaoXiFragment extends BaseFragment {

    @BindView(R.id.rv_view)
    RecyclerView rv_view;
    @BindView(R.id.rl_title)
    RelativeLayout rl_title;
    @BindView(R.id.view_line)
    View view_line;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {

            }
        }));
    }

    public static BottomTuanGouXiaoXiFragment newInstance() {
        Bundle args = new Bundle();
        BottomTuanGouXiaoXiFragment fragment = new BottomTuanGouXiaoXiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_mian_msg;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .titleBar(toolbar).fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .statusBarColor(R.color.white)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getNet();
        initAdapter();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initAdapter() {
        List<MsgModel> models = new ArrayList<>();
        models.add(new MsgModel(R.mipmap.xiaoxi_tongzhi_liaotianxiaoxi, "私聊消息"));
        models.add(new MsgModel(R.mipmap.xiaoxi_tongzhi_dingdanxiaoxi, "订单消息"));
        models.add(new MsgModel(R.mipmap.xiaoxi_tongzhi_juyijiagonggao, "聚易佳公告"));
        models.add(new MsgModel(R.mipmap.xiaoxi_tongzhi_jingxuanhuodong, "精选活动"));
        models.add(new MsgModel(R.mipmap.xiaoxi_tongzhi_xingongneng, "新功能"));

        MsgAdapter adapter = new MsgAdapter(R.layout.item_msg_xiaoxi, models);
        rv_view.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_view.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (models != null && models.size() > position) {
                    String name = models.get(position).getName();
                    switch (name) {
                        case "订单消息":
                            MsgOrderActivity.actionStart(getContext());
                            break;
                        case "聚易佳公告":
                            MsgGonggaoActivity.actionStart(getContext());
                            break;
                        case "精选活动":
                            MsgHuodongActivity.actionStart(getContext());
                            break;
                        case "新功能":
                            MsgNewActivity.actionStart(getContext());
                            break;
                        case "私聊消息":
                            MsgIMActivity.actionStart(getContext());
                            break;
                    }
                }
            }
        });
    }

    private void getNet() {

    }

}
