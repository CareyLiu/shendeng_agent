package com.shendeng.agent.ui.fragment;


import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.adapter.OrderAdapter;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.bean.Notice;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.dialog.BottomDialog;
import com.shendeng.agent.dialog.BottomDialogView;
import com.shendeng.agent.dialog.tishi.MyCarCaoZuoDialog_Success;
import com.shendeng.agent.model.OrderModel;
import com.shendeng.agent.ui.FeedBackActivity;
import com.shendeng.agent.ui.activity.DefaultX5WebViewActivity;
import com.shendeng.agent.ui.activity.LoginPwdActivity;
import com.shendeng.agent.ui.activity.OrderDetailsActivity;
import com.shendeng.agent.ui.activity.OrderFahuoActivity;
import com.shendeng.agent.ui.activity.OrderPingjiaActivity;
import com.shendeng.agent.ui.activity.OrderSaoyisaoActivity;
import com.shendeng.agent.ui.activity.OrderTuikuanActivity;
import com.shendeng.agent.ui.view.SelectTabView;
import com.shendeng.agent.util.Urls;
import com.shendeng.agent.util.Y;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.iv_saoyisao)
    ImageView iv_saoyisao;
    @BindView(R.id.ll_no_data)
    LinearLayout ll_no_data;

    private int shop_pay_check;
    private String form_id;
    private List<OrderModel.DataBean> data = new ArrayList<>();
    private OrderAdapter adapter;

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
    protected void initView(View view) {

    }

    @Override
    protected void initLogic() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        tab_all.setTitle("全部");
        tab_daifukuan.setTitle("待付款");
        tab_daifahuo.setTitle("待发货");
        tab_daishouhuo.setTitle("待收货");
        tab_xiaofei.setTitle("到店订单");
        tab_daipingjia.setTitle("待评价");
        tab_yipingjia.setTitle("已评价");
        tab_tuikuanshenqing.setTitle("退款申请");
        tab_tuikuanzhong.setTitle("退款中");
        tab_guanbi.setTitle("已关闭");

        initAdapter();
        initSM();

        selectTab(0);
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

        showProgressDialog("");
        getOrder(shop_pay_check);
    }

    private void initAdapter() {
        adapter = new OrderAdapter(R.layout.item_order_mian, data);
        rv_content.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_content.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_bt:
                        if (data != null && data.size() > 0) {
                            OrderModel.DataBean dataBean = data.get(position);
                            String shop_form_id = dataBean.getProduct().get(0).getShop_form_id();
                            String shop_pay_check = dataBean.getShop_pay_check();

                            if (shop_pay_check.equals("3")) {//去发货
                                OrderFahuoActivity.actionStart(getContext(), shop_form_id);
                            } else if (shop_pay_check.equals("4")) {//查看物流
                                String express_url = dataBean.getExpress_url();
                                if (TextUtils.isEmpty(express_url)) {
                                    Y.t("暂无物流详情");
                                } else {
                                    DefaultX5WebViewActivity.actionStart(getContext(), express_url);
                                }
                            } else if (shop_pay_check.equals("6")) {//去评价
                                OrderPingjiaActivity.actionStart(getContext(), shop_form_id);
                            } else if (shop_pay_check.equals("7")) {//查看详情
                                OrderPingjiaActivity.actionStart(getContext(), shop_form_id);
                            } else if (shop_pay_check.equals("8")) {//退款审核
                                showBottom(shop_form_id);
                            }
                        }
                        break;
                }
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (data != null && data.size() > 0) {
                    OrderModel.DataBean dataBean = data.get(position);
                    String shop_form_id = dataBean.getProduct().get(0).getShop_form_id();
                    String shop_pay_check = dataBean.getShop_pay_check();
                    if (shop_pay_check.equals("8") || shop_pay_check.equals("9") || shop_pay_check.equals("10") || shop_pay_check.equals("12")) {//退款
                        OrderTuikuanActivity.actionStart(getContext(), shop_form_id);
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("shop_form_id", shop_form_id);
                        OrderDetailsActivity.actionStart(getContext(), bundle);
                    }
                }
            }
        });
    }

    private void showBottom(String shop_form_id) {
        List<String> names = new ArrayList<>();
        names.add("同意退款");
        names.add("拒绝退款");
        final BottomDialog bottomDialog = new BottomDialog(getActivity());
        bottomDialog.setModles(names);
        bottomDialog.setClickListener(new BottomDialogView.ClickListener() {
            @Override
            public void onClickItem(int pos) {
                bottomDialog.dismiss();
                if (pos == 0) {
                    tuikuanshenhe("2", shop_form_id);
                } else {
                    tuikuanshenhe("6", shop_form_id);
                }
            }

            @Override
            public void onClickCancel(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.showBottom();
    }

    private void tuikuanshenhe(String refund_rate, String shop_form_id) {//refund_rate  审核结果：2同意6拒绝
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04315);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("shop_form_id", shop_form_id);
        map.put("refund_rate", refund_rate);
        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(Response<AppResponse> response) {
                        MyCarCaoZuoDialog_Success dialog = new MyCarCaoZuoDialog_Success(getActivity(), new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                            @Override
                            public void onDismiss() {
                                getOrder(shop_pay_check);
                            }
                        });
                        dialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        super.onError(response);
                        Y.tError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }

                    @Override
                    public void onStart(Request<AppResponse, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }
                });
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getOrder(shop_pay_check);
            }
        });


        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getLoad(shop_pay_check);
            }
        });
    }

    private void getOrder(int shop_pay_check) {
        form_id = "";
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04311);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("shop_pay_check", shop_pay_check + "");
        map.put("form_id", form_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<OrderModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<OrderModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<OrderModel.DataBean>> response) {
                        data = response.body().data;
                        adapter.setNewData(data);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                        if (data.size() > 0) {
                            form_id = BottomDingDanFragment.this.data.get(BottomDingDanFragment.this.data.size() - 1).getForm_id();
                            ll_no_data.setVisibility(View.GONE);
                        } else {
                            ll_no_data.setVisibility(View.VISIBLE);
                        }

                        dismissProgressDialog();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }


    private void getLoad(int shop_pay_check) {
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04311);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("shop_pay_check", shop_pay_check + "");
        map.put("form_id", form_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<OrderModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<OrderModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<OrderModel.DataBean>> response) {
                        List<OrderModel.DataBean> dataNew = response.body().data;
                        data.addAll(dataNew);
                        adapter.setNewData(BottomDingDanFragment.this.data);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                        if (data.size() > 0) {
                            form_id = BottomDingDanFragment.this.data.get(BottomDingDanFragment.this.data.size() - 1).getForm_id();
                            ll_no_data.setVisibility(View.GONE);
                        } else {
                            ll_no_data.setVisibility(View.VISIBLE);
                        }

                        smartRefreshLayout.finishLoadMore();
                    }
                });
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


    private void ewm() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) { // 在android 6.0之前会默认返回true
                    OrderSaoyisaoActivity.actionStartForResult(getActivity(), 100);
                } else {
                    Y.tLong("该应用需要赋予访问相机的权限，不开启将无法正常工作！");
                }
            }
        });
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


    @OnClick({R.id.iv_saoyisao, R.id.tab_all, R.id.tab_daifukuan, R.id.tab_daifahuo, R.id.tab_daishouhuo, R.id.tab_xiaofei, R.id.tab_daipingjia, R.id.tab_yipingjia, R.id.tab_tuikuanshenqing, R.id.tab_tuikuanzhong, R.id.tab_guanbi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_saoyisao:
                ewm();
                // FeedBackActivity.actionStart(getActivity());
                break;
            case R.id.tab_all:
                selectTab(0);
                break;
            case R.id.tab_daifukuan:
                selectTab(1);
                break;
            case R.id.tab_daifahuo:
                selectTab(3);
                break;
            case R.id.tab_daishouhuo:
                selectTab(4);
                break;
            case R.id.tab_xiaofei:
                selectTab(5);
                break;
            case R.id.tab_daipingjia:
                selectTab(6);
                break;
            case R.id.tab_yipingjia:
                selectTab(7);
                break;
            case R.id.tab_tuikuanshenqing:
                selectTab(8);
                break;
            case R.id.tab_tuikuanzhong:
                selectTab(9);
                break;
            case R.id.tab_guanbi:
                selectTab(10);
                break;
        }
    }
}
