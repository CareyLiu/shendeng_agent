package com.shendeng.agent.ui.fragment.mendian;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shendeng.agent.R;
import com.shendeng.agent.adapter.TuanGouDingDanListAdapter;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.ui.activity.tuangou.TuanGouDingDanDetails;
import com.shendeng.agent.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListFragment extends BaseFragment {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initLogic() {
        String str = getArguments().getString("title");
        List<String> mDatas = new ArrayList<>();
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        TuanGouDingDanListAdapter tuanGouDingDanListAdapter = new TuanGouDingDanListAdapter(R.layout.item_tuangou_dingdan, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(tuanGouDingDanListAdapter);

        smartRefreshLayout.setEnableRefresh(true);

        tuanGouDingDanListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        UIHelper.ToastMessage(getActivity(), "点击了");
                        TuanGouDingDanDetails.actionStart(getActivity());
                        break;
                }
            }
        });

    }


    @Override
    protected int getLayoutRes() {
        return R.layout.layout_order_list;
    }

    @Override
    protected void initView(View rootView) {

    }
}
