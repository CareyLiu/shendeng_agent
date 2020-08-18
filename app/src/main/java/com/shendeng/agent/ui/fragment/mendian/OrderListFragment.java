package com.shendeng.agent.ui.fragment.mendian;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.adapter.TuanGouDingDanListAdapter;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.OrderModel;
import com.shendeng.agent.model.TuanGouOrderListModel;
import com.shendeng.agent.ui.activity.tuangou.TuanGouDingDanDetails;
import com.shendeng.agent.ui.fragment.BottomDingDanFragment;
import com.shendeng.agent.util.UIHelper;
import com.shendeng.agent.util.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class OrderListFragment extends BaseFragment {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    List<TuanGouOrderListModel.DataBean> mDatas;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    TuanGouDingDanListAdapter tuanGouDingDanListAdapter;

    @Override
    protected void initLogic() {
        String str = getArguments().getString("title");
        mDatas = new ArrayList<>();

        tuanGouDingDanListAdapter = new TuanGouDingDanListAdapter(R.layout.item_tuangou_dingdan, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(tuanGouDingDanListAdapter);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getOrderList("");
            }
        });

        tuanGouDingDanListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        //  UIHelper.ToastMessage(getActivity(), "点击了");
                        TuanGouDingDanDetails.actionStart(getActivity());
                        break;
                }
            }
        });

        getOrderList("");

    }


    @Override
    protected int getLayoutRes() {
        return R.layout.layout_order_list;
    }

    @Override
    protected void initView(View rootView) {

    }


    private void getOrderList(String shop_pay_check) {
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04200);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("shop_pay_check", "0");

        Gson gson = new Gson();
        OkGo.<AppResponse<TuanGouOrderListModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouOrderListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouOrderListModel.DataBean>> response) {
                        //data = response.body().data;

                        if (response.body().data.size() > 0) {
                            mDatas.addAll(response.body().data);
                            tuanGouDingDanListAdapter.setNewData(mDatas);
                            tuanGouDingDanListAdapter.notifyDataSetChanged();
                        }
                        smartRefreshLayout.finishLoadMore();
                        smartRefreshLayout.finishRefresh();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
//
//                        if (data.size() > 0) {
//                            form_id = BottomDingDanFragment.this.data.get(BottomDingDanFragment.this.data.size() - 1).getForm_id();
//                            ll_no_data.setVisibility(View.GONE);
//                        } else {
//                            ll_no_data.setVisibility(View.VISIBLE);
//                        }
//
//                        dismissProgressDialog();
//                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

}
