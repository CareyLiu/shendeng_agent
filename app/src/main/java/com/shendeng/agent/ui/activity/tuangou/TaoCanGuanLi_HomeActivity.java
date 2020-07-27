package com.shendeng.agent.ui.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.adapter.TaoCanGuanLiHomeAdapter;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.model.JiesuanModel;
import com.shendeng.agent.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TaoCanGuanLi_HomeActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    TaoCanGuanLiHomeAdapter taoCanGuanLiHomeAdapter;
    List<JiesuanModel.DataBean> mDatas;
    @BindView(R.id.tv_chushouzhong)
    TextView tvChushouzhong;
    @BindView(R.id.tv_yixiajia)
    TextView tvYixiajia;
    @BindView(R.id.tv_xinjian_taocan)
    TextView tvXinjianTaocan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });

        srLSmart.setEnableLoadMore(false);
        mDatas = new ArrayList<>();
        JiesuanModel.DataBean jiesuanModel = new JiesuanModel.DataBean();


        mDatas.add(jiesuanModel);

        mDatas.add(jiesuanModel);

        mDatas.add(jiesuanModel);

        mDatas.add(jiesuanModel);

        mDatas.add(jiesuanModel);
        mDatas.add(jiesuanModel);

        mDatas.add(jiesuanModel);


        taoCanGuanLiHomeAdapter = new TaoCanGuanLiHomeAdapter(R.layout.item_taocan_guanli, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        rlvList.setLayoutManager(linearLayoutManager);

        rlvList.setAdapter(taoCanGuanLiHomeAdapter);


        tvChushouzhong.setText("出售中(" + mDatas.size() + ")");
        tvYixiajia.setText("已下架(" + mDatas.size() + ")");


        tvChushouzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvChushouzhong.setTextColor(mContext.getResources().getColor(R.color.FC0100));
                tvYixiajia.setTextColor(mContext.getResources().getColor(R.color.gray999999));
                tvChushouzhong.setText("出售中(" + mDatas.size() + ")");
            }
        });
        tvYixiajia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvYixiajia.setTextColor(mContext.getResources().getColor(R.color.FC0100));
                tvChushouzhong.setTextColor(mContext.getResources().getColor(R.color.gray999999));
                tvYixiajia.setText("已下架(" + mDatas.size() + ")");
            }
        });

        taoCanGuanLiHomeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:

                        //  AddTaoCanActivity.actionStart(mContext, "1");
                        TianJiaTaoCanActivity.actionStart(mContext, "1");
                        break;
                }
            }
        });


        View view = View.inflate(mContext, R.layout.taocan_guanli_emptyview, null);
        taoCanGuanLiHomeAdapter.setEmptyView(view);


        tvXinjianTaocan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     AddTaoCanActivity.actionStart(mContext, "0");

                TianJiaTaoCanActivity.actionStart(mContext, "0");
            }
        });

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tao_can_guan_li__home;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("套餐管理");
        tv_title.setTextColor(getResources().getColor(R.color.black));
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TaoCanGuanLi_HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
