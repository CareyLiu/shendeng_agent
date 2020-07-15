package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.dialog.TishiDialog;
import com.shendeng.agent.model.OrderDetailsModel;
import com.shendeng.agent.util.Urls;
import com.shendeng.agent.util.Y;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderTuikuanActivity extends BaseActivity {


    @BindView(R.id.tv_danhao_tuihuo)
    TextView tv_danhao_tuihuo;
    @BindView(R.id.tv_danhao_order)
    TextView tv_danhao_order;
    @BindView(R.id.tv_yuanyin)
    TextView tv_yuanyin;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.qiu1)
    View qiu1;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.qiu2)
    View qiu2;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.qiu3)
    View qiu3;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.qiu4)
    View qiu4;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.qiu5)
    View qiu5;
    @BindView(R.id.tv_daojishi)
    TextView tv_daojishi;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_pay_type)
    TextView tv_pay_type;
    @BindView(R.id.tv_pay_time)
    TextView tv_pay_time;
    @BindView(R.id.bt1)
    TextView bt1;
    @BindView(R.id.bt2)
    TextView bt2;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private String shop_form_id;

    @Override
    public int getContentViewResId() {
        return R.layout.act_order_tuikuan;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("订单详情");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OrderTuikuanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, OrderTuikuanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        shop_form_id = getIntent().getExtras().getString("shop_form_id");

        gerOrderTuikuan();

        initSM();
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                gerOrderTuikuan();
            }
        });
    }

    private void gerOrderTuikuan() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04313");
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("shop_form_id", shop_form_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<OrderDetailsModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<OrderDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<OrderDetailsModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    @OnClick({ R.id.bt1, R.id.bt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                clickBt1();
                break;
            case R.id.bt2:
                clickBt2();
                break;
        }
    }





    private void clickBt1() {
//        String title = pay_check_arr.get(Y.getInt(pay_check_index));
//        if (title.equals("已拍下")) {//修改快递费
//            OrderKuaidiActivity.actionStart(mContext, dataBean.getForm_money_go(), shop_form_id);
//        } else if (title.equals("已付款")) {
//
//        } else if (title.equals("已发货")) {
//
//        } else if (title.equals("交易成功")) {//查看欠款去向
//            Y.t("查看欠款去向");
//        } else if (title.equals("已评价")) {//查看欠款去向
//            Y.t("查看欠款去向");
//        }
    }

    private void clickBt2() {
//        String title = pay_check_arr.get(Y.getInt(pay_check_index));
//        if (title.equals("已拍下")) {//关闭此交易
//            Y.t("关闭此交易");
//        } else if (title.equals("已付款")) {//去发货
//            OrderFahuoActivity.actionStart(this);
//        } else if (title.equals("已发货")) {
//
//        } else if (title.equals("交易成功")) {//去评价
//            OrderPingjiaActivity.actionStart(mContext, shop_form_id);
//        } else if (title.equals("已评价")) {//查看评价
//            OrderPingjiaActivity.actionStart(mContext, shop_form_id);
//        }
    }
}
