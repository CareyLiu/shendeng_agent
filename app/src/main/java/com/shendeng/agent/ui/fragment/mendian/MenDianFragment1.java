package com.shendeng.agent.ui.fragment.mendian;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shendeng.agent.R;
import com.shendeng.agent.adapter.TaoCanGuanLiAdapter;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.model.JiesuanModel;
import com.shendeng.agent.ui.activity.tuangou.TaoCanGuanLi_HomeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MenDianFragment1 extends BaseFragment {
    @BindView(R.id.tv_mendian_tupian)
    TextView tvMendianTupian;
    @BindView(R.id.tv_mendianxinxi)
    TextView tvMendianxinxi;
    @BindView(R.id.tv_jichuxinxi)
    TextView tvJichuxinxi;
    @BindView(R.id.iv_back_bianji)
    ImageView ivBackBianji;
    @BindView(R.id.tv_huashu)
    TextView tvHuashu;
    @BindView(R.id.tv_kehufenlei)
    TextView tvKehufenlei;
    @BindView(R.id.tv_yingye_zhuagntai)
    TextView tvYingyeZhuagntai;
    @BindView(R.id.tv_yingyeshijian)
    TextView tvYingyeshijian;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.view_jichu_info)
    View viewJichuInfo;
    @BindView(R.id.tv_qitainfo)
    TextView tvQitainfo;
    @BindView(R.id.iv_back_bianji_1)
    ImageView ivBackBianji1;
    @BindView(R.id.tv_rongnarenshu)
    TextView tvRongnarenshu;
    @BindView(R.id.tv_mendian_mianji)
    TextView tvMendianMianji;
    @BindView(R.id.tv_baoxiangxinxi)
    TextView tvBaoxiangxinxi;
    @BindView(R.id.tv_mendiancaidan)
    TextView tvMendiancaidan;
    @BindView(R.id.tv_taocanguanli)
    TextView tvTaocanguanli;
    @BindView(R.id.iv_back_bianji_2)
    ImageView ivBackBianji2;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_tupian_caidan)
    TextView tvTupianCaidan;
    @BindView(R.id.iv_back_bianji_3)
    ImageView ivBackBianji3;
    @BindView(R.id.rlv_tupiancaidan)
    RecyclerView rlvTupiancaidan;
    @BindView(R.id.cl_mendiancaidan)
    ConstraintLayout clMendiancaidan;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;

    TaoCanGuanLiAdapter taoCanGuanLiAdapter;
    List<JiesuanModel.DataBean> mDatas;
    @BindView(R.id.taocan_bianji)
    TextView taocanBianji;

    @Override
    protected void initLogic() {
        srLSmart.setEnableLoadMore(false);


        //list


        mDatas = new ArrayList<>();
        JiesuanModel.DataBean jiesuanModel = new JiesuanModel.DataBean();


        mDatas.add(jiesuanModel);

        mDatas.add(jiesuanModel);

        mDatas.add(jiesuanModel);

        mDatas.add(jiesuanModel);

        mDatas.add(jiesuanModel);
        mDatas.add(jiesuanModel);

        mDatas.add(jiesuanModel);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);


        taoCanGuanLiAdapter = new TaoCanGuanLiAdapter(R.layout.layout_caidan_guanli, mDatas);
        rlvList.setAdapter(taoCanGuanLiAdapter);


        GridLayoutManager linearLayoutManager1 = new GridLayoutManager(getActivity(), 3);
        rlvTupiancaidan.setLayoutManager(linearLayoutManager1);


        taoCanGuanLiAdapter = new TaoCanGuanLiAdapter(R.layout.item_caidan_list, mDatas);
        rlvTupiancaidan.setAdapter(taoCanGuanLiAdapter);

        taocanBianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TaoCanGuanLiActivity.actionStart(getActivity());
                //AddTaoCanActivity.actionStart(getActivity(),"1");
                TaoCanGuanLi_HomeActivity.actionStart(getActivity());
            }
        });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_mendian_fragment1;
    }

    @Override
    protected void initView(View rootView) {

    }
}
