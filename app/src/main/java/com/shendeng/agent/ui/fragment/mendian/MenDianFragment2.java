package com.shendeng.agent.ui.fragment.mendian;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shendeng.agent.R;
import com.shendeng.agent.adapter.TuanGouAccessAdapter;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.OrderDetailsModel;
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
    TextView tvZongpingshu;
    @BindView(R.id.tv_haoping)
    TextView tvHaoping;
    @BindView(R.id.view_haoping)
    LinearLayout viewHaoping;
    @BindView(R.id.ll_haoping)
    LinearLayout llHaoping;
    @BindView(R.id.tv_zhongchaping)
    TextView tvZhongchaping;
    @BindView(R.id.view_zhongchaping)
    View viewZhongchaping;
    @BindView(R.id.ll_zhongchaping)
    LinearLayout llZhongchaping;
    @BindView(R.id.tv_haopinglv)
    TextView tvHaopinglv;
    @BindView(R.id.tv_zhongchapinglv)
    TextView tvZhongchapinglv;
    @BindView(R.id.tv_quanbupingjia)
    TextView tvQuanbupingjia;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.choose_zhongchaping)
    TextView chooseZhongchaping;
    @BindView(R.id.choose_haoping)
    TextView chooseHaoping;
    @BindView(R.id.choose_quanbu)
    TextView chooseQuanbu;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    private TuanGouAccessAdapter tuanGouAccessAdapter;
    private String wares_id = "";//wares_id 这是id

    @Override
    protected void initLogic() {
        srLSmart.setEnableLoadMore(false);

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

        viewHaoping.removeAllViews();
        String strQuanBuPingLun = "100";
        String strHaoPing = "10";
        BigDecimal haoPingBigDecimal = new BigDecimal(strHaoPing);
        BigDecimal quanBuBigDecimal = new BigDecimal(strQuanBuPingLun);
        BigDecimal quanBuView = new BigDecimal("58");
        BigDecimal oneBig = quanBuView.multiply(haoPingBigDecimal);
        BigDecimal finalViewLength = oneBig.divide(quanBuBigDecimal);

        //   LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtil.dip2px(Float.valueOf("10000")), ScreenUtil.dip2px(5));

        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) viewHaoping.getLayoutParams();
        ScreenUtil.init(getActivity());
        linearParams.width = ScreenUtil.dip2px(Float.valueOf(finalViewLength.toString()));
        UIHelper.ToastMessage(getActivity(), "我的好评view长度" + finalViewLength.toString());
        viewHaoping.setLayoutParams(linearParams);

        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");

        tuanGouAccessAdapter = new TuanGouAccessAdapter(R.layout.item_access, strings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(tuanGouAccessAdapter);



    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_mendian_fragment2;
    }

    @Override
    protected void initView(View rootView) {

    }



}
