package com.shendeng.agent.ui.fragment;

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
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.bean.Notice;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.MessageModel;
import com.shendeng.agent.ui.adapter.MessageListAdapter;
import com.shendeng.agent.util.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BottomXiaoXiFragment extends BaseFragment {

    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    List<MessageModel.DataBean> mDatas = new ArrayList<>();
    String notifyId = "";
    @BindView(R.id.iv_none)
    ImageView ivNone;
    private MessageListAdapter messageListAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {

            }
        }));

    }

    public static BottomXiaoXiFragment newInstance() {
        Bundle args = new Bundle();
        BottomXiaoXiFragment fragment = new BottomXiaoXiFragment();
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
        Log.i("bottomxiaoxi", "onactivity_created");
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageListAdapter = new MessageListAdapter(R.layout.item_messagelist, mDatas);
        messageListAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(messageListAdapter);
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04341");
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());

        Gson gson = new Gson();
        OkGo.<AppResponse<MessageModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MessageModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MessageModel.DataBean>> response) {
                        if (response.body().data.size() > 0) {
                            if (StringUtils.isEmpty(notifyId)) {
                                mDatas.clear();
                                mDatas.addAll(response.body().data);
                                messageListAdapter.setNewData(mDatas);

                            } else {
                                srLSmart.setEnableLoadMore(true);
                                mDatas.addAll(response.body().data);
                            }

                            notifyId = mDatas.get(mDatas.size() - 1).getNotify_id();
                            messageListAdapter.notifyDataSetChanged();

                            recyclerView.setVisibility(View.VISIBLE);
                            ivNone.setVisibility(View.GONE);
                            srLSmart.setEnableLoadMore(true);
                        } else {
                            srLSmart.setEnableLoadMore(false);
                        }

                        if (mDatas.size() == 0) {
                            recyclerView.setVisibility(View.GONE);
                            ivNone.setVisibility(View.VISIBLE);
                        }
                        srLSmart.finishLoadMore();
                        srLSmart.finishRefresh();

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }


}
