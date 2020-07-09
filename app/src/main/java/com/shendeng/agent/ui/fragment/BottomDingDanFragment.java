package com.shendeng.agent.ui.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.shendeng.agent.R;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.bean.Notice;
import com.shendeng.agent.ui.view.SelectTabView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BottomDingDanFragment extends BaseFragment {
    public static final String TAG = "BottomDingDanFragment";
    @BindView(R.id.tab_all)
    SelectTabView tab_all;
    @BindView(R.id.tab_daifukuan)
    SelectTabView tab_daifukuan;
    @BindView(R.id.tab_daifahuo)
    SelectTabView tab_daifahuo;
    @BindView(R.id.tab_daishouhuo)
    SelectTabView tab_daishouhuo;
    @BindView(R.id.tab_xiaofei)
    SelectTabView tab_xiaofei;
    @BindView(R.id.tab_daipingjia)
    SelectTabView tab_daipingjia;
    @BindView(R.id.tab_yipingjia)
    SelectTabView tab_yipingjia;
    @BindView(R.id.tab_tuikuanshenqing)
    SelectTabView tab_tuikuanshenqing;
    @BindView(R.id.tab_tuikuanzhong)
    SelectTabView tab_tuikuanzhong;
    @BindView(R.id.tab_guanbi)
    SelectTabView tab_guanbi;
    @BindView(R.id.vpg_content)
    ViewPager vpg_content;

    private int shop_pay_check;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {

            }
        }));
    }


    public static BottomDingDanFragment newInstance() {
        Bundle args = new Bundle();
        BottomDingDanFragment fragment = new BottomDingDanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_mian_order;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }


    @Override
    protected void initView(View view) {

    }


    /**
     * shop_pay_check 状态：0.全部1.待付款3.待发货4.待收货5.到店消费6.待评价 7.已评价 8.退款申请 9.退款中 10.已关闭
     */
    private void selectTab(int shop_pay_check) {
        this.shop_pay_check = shop_pay_check;

        tab_all.setSelect(false);
        tab_daifukuan.setSelect(false);
        tab_daifahuo.setSelect(false);
        tab_daishouhuo.setSelect(false);
        tab_xiaofei.setSelect(false);
        tab_daipingjia.setSelect(false);
        tab_yipingjia.setSelect(false);
        tab_tuikuanshenqing.setSelect(false);
        tab_tuikuanzhong.setSelect(false);
        tab_guanbi.setSelect(false);
        switch (shop_pay_check) {
            case 0:
                tab_all.setSelect(true);
                break;
            case 1:
                tab_daifukuan.setSelect(true);
                break;
            case 3:
                tab_daifahuo.setSelect(true);
                break;
            case 4:
                tab_daishouhuo.setSelect(true);
                break;
            case 5:
                tab_xiaofei.setSelect(true);
                break;
            case 6:
                tab_daipingjia.setSelect(true);
                break;
            case 7:
                tab_yipingjia.setSelect(true);
                break;
            case 8:
                tab_tuikuanshenqing.setSelect(true);
                break;
            case 9:
                tab_tuikuanzhong.setSelect(true);
                break;
            case 10:
                tab_guanbi.setSelect(true);
                break;
        }

        getOrder(shop_pay_check);
    }

    private void getOrder(int shop_pay_check) {

    }

    @Override
    protected void initLogic() {


    }

    @Override
    protected void initToolBar(View rootView) {
        super.initToolBar(rootView);
        iv_rightTitle.setVisibility(View.VISIBLE);
        tv_title.setText("订单");
        tv_title.setTextSize(17);
        tv_title.setTextColor(this.getResources().getColor(R.color.color_494949));
        tv_title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .titleBar(toolbar)
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
        initS();
        return rootView;
    }

    private void initS() {
        tab_all.setTitle("全部");
        tab_daifukuan.setTitle("待付款");
        tab_daifahuo.setTitle("待发货");
        tab_daishouhuo.setTitle("待收货");
        tab_xiaofei.setTitle("到店消费");
        tab_daipingjia.setTitle("待评价");
        tab_yipingjia.setTitle("已评价");
        tab_tuikuanshenqing.setTitle("退款申请");
        tab_tuikuanzhong.setTitle("退款中");
        tab_guanbi.setTitle("已关闭");

        selectTab(0);
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
}
