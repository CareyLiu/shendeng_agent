package com.shendeng.agent.ui.fragment.mendian;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.adapter.TuanGouAccessAdapter;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.tuangou.TuanPinglunModel;
import com.shendeng.agent.ui.view.sys.ScreenUtil;
import com.shendeng.agent.util.UIHelper;
import com.shendeng.agent.util.Urls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MenDianFragment2 extends BaseFragment {
    @BindView(R.id.tv_pingfen_number)
    TextView tvPingfenNumber;
    @BindView(R.id.ll_pingfen)
    LinearLayout llPingfen;
    @BindView(R.id.tv_zongpingshu)
    TextView tv_zongpingshu;
    @BindView(R.id.tv_haoping)
    TextView tv_haoping;
    @BindView(R.id.view_haoping)
    LinearLayout viewHaoping;
    @BindView(R.id.ll_haoping)
    LinearLayout llHaoping;
    @BindView(R.id.tv_zhongchaping)
    TextView tv_zhongchaping;
    @BindView(R.id.view_zhongchaping)
    LinearLayout viewZhongchaping;
    @BindView(R.id.ll_zhongchaping)
    LinearLayout llZhongchaping;
    @BindView(R.id.tv_haopinglv)
    TextView tv_haopinglv;
    @BindView(R.id.tv_zhongchapinglv)
    TextView tv_zhongchapinglv;
    @BindView(R.id.tv_quanbupingjia)
    TextView tvQuanbupingjia;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.choose_zhongchaping)
    TextView chooseZhongchaping;
    @BindView(R.id.choose_haoping)
    TextView chooseHaoping;
    @BindView(R.id.choose_quanbu)
    TextView chooseQuanbu;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    private String wares_id = "";//wares_id 这是id

    private TuanPinglunModel.DataBean pinglunModel;
    private List<TuanPinglunModel.DataBean.AssessListBean> assess_list = new ArrayList<>();
    private TuanGouAccessAdapter tuanGouAccessAdapter;

    @Override
    protected void initLogic() {

        chooseHaoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(getActivity(), "点击了好评");
                chooseHaoping.setBackgroundResource(R.drawable.bg_fe3332b_radius_12);
                chooseZhongchaping.setBackgroundResource(R.drawable.gray_broad_radius_12);
                chooseQuanbu.setBackgroundResource(R.drawable.gray_broad_radius_12);

            }
        });
        chooseZhongchaping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(getActivity(), "点击了中差评");
                chooseZhongchaping.setBackgroundResource(R.drawable.bg_fe3332b_radius_12);


                chooseHaoping.setBackgroundResource(R.drawable.gray_broad_radius_12);
                chooseQuanbu.setBackgroundResource(R.drawable.gray_broad_radius_12);

            }
        });

        chooseQuanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(getActivity(), "点击了全部");
                chooseQuanbu.setBackgroundResource(R.drawable.bg_fe3332b_radius_12);


                chooseHaoping.setBackgroundResource(R.drawable.gray_broad_radius_12);
                chooseZhongchaping.setBackgroundResource(R.drawable.gray_broad_radius_12);
            }
        });

        initAdapter();
        initSM();
        getNet();
    }

    private void initAdapter() {
        tuanGouAccessAdapter = new TuanGouAccessAdapter(R.layout.item_access, assess_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(tuanGouAccessAdapter);
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

    private int page_number;

    private void getNet() {
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04217);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<TuanPinglunModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanPinglunModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanPinglunModel.DataBean>> response) {
                        pinglunModel = response.body().data.get(0);
                        assess_list = pinglunModel.getAssess_list();

                        String total_assess_count = pinglunModel.getTotal_assess_count();
                        String high_assess_count = pinglunModel.getHigh_assess_count();
                        String low_assess_count = pinglunModel.getLow_assess_count();
                        tv_zongpingshu.setText("总评价数 " + total_assess_count);
                        tv_haoping.setText("好评 " + high_assess_count);
                            tv_zhongchaping.setText("中差评 " + low_assess_count);

                        setHaopinglv(high_assess_count, total_assess_count);
                        setZhongcha(low_assess_count, total_assess_count);

                        tv_haopinglv.setText(pinglunModel.getHigh_assess_percent());
                        tv_zhongchapinglv.setText(pinglunModel.getLow_assess_percent());

                        tuanGouAccessAdapter.setNewData(assess_list);
                        tuanGouAccessAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    private void setHaopinglv(String strHaoPing, String strQuanBuPingLun) {
        viewHaoping.removeAllViews();
        BigDecimal haoPingBigDecimal = new BigDecimal(strHaoPing);
        BigDecimal quanBuBigDecimal = new BigDecimal(strQuanBuPingLun);
        BigDecimal quanBuView = new BigDecimal("58");
        BigDecimal oneBig = quanBuView.multiply(haoPingBigDecimal);
        BigDecimal finalViewLength = oneBig.divide(quanBuBigDecimal);

        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) viewHaoping.getLayoutParams();
        ScreenUtil.init(getActivity());
        linearParams.width = ScreenUtil.dip2px(Float.valueOf(finalViewLength.toString()));
        viewHaoping.setLayoutParams(linearParams);
    }


    private void setZhongcha(String strHaoPing, String strQuanBuPingLun) {
        viewZhongchaping.removeAllViews();
        BigDecimal haoPingBigDecimal = new BigDecimal(strHaoPing);
        BigDecimal quanBuBigDecimal = new BigDecimal(strQuanBuPingLun);
        BigDecimal quanBuView = new BigDecimal("58");
        BigDecimal oneBig = quanBuView.multiply(haoPingBigDecimal);
        BigDecimal finalViewLength = oneBig.divide(quanBuBigDecimal);

        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) viewZhongchaping.getLayoutParams();
        ScreenUtil.init(getActivity());
        linearParams.width = ScreenUtil.dip2px(Float.valueOf(finalViewLength.toString()));
        viewZhongchaping.setLayoutParams(linearParams);
    }


    private void getLoad() {
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04217);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<TuanPinglunModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanPinglunModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanPinglunModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_mendian_fragment2;
    }

    @Override
    protected void initView(View rootView) {

    }


}
