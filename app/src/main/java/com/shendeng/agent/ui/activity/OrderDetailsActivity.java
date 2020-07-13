package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.OrderDetailsModel;
import com.shendeng.agent.model.OrderModel;
import com.shendeng.agent.ui.fragment.BottomDingDanFragment;
import com.shendeng.agent.util.Urls;
import com.shendeng.agent.util.Y;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.qiu_paixia)
    View qiu_paixia;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.qiu_fukuan)
    View qiu_fukuan;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.qiu_fahuo)
    View qiu_fahuo;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.qiu_chenggong)
    View qiu_chenggong;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.qiu_pingjia)
    View qiu_pingjia;
    @BindView(R.id.tv_paixia)
    TextView tv_paixia;
    @BindView(R.id.tv_fukuan)
    TextView tv_fukuan;
    @BindView(R.id.tv_fahuo)
    TextView tv_fahuo;
    @BindView(R.id.tv_chenggong)
    TextView tv_chenggong;
    @BindView(R.id.tv_pingjia)
    TextView tv_pingjia;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_adress)
    TextView tv_adress;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_wuliu)
    TextView tv_wuliu;
    @BindView(R.id.tv_title_msg)
    TextView tv_title_msg;
    @BindView(R.id.tv_taocan)
    TextView tv_taocan;
    @BindView(R.id.tv_lianxi)
    TextView tv_lianxi;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.tv_beizhu)
    TextView tv_beizhu;
    @BindView(R.id.tv_youhui)
    TextView tv_youhui;
    @BindView(R.id.tv_mai_name)
    TextView tv_mai_name;
    @BindView(R.id.tv_bianhao)
    TextView tv_bianhao;
    @BindView(R.id.tv_pay_type)
    TextView tv_pay_type;
    @BindView(R.id.tv_yunfei)
    TextView tv_yunfei;
    @BindView(R.id.tv_pay_time)
    TextView tv_pay_time;
    @BindView(R.id.bt1)
    TextView bt1;
    @BindView(R.id.bt2)
    TextView bt2;
    private String shop_form_id;
    private OrderDetailsModel.DataBean dataBean;
    private String pay_check_index;

    @Override
    public int getContentViewResId() {
        return R.layout.act_order_details;
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
        intent.setClass(context, OrderDetailsActivity.class);
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
        intent.setClass(context, OrderDetailsActivity.class);
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

        gerOrtherDetails();
    }

    private void gerOrtherDetails() {
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
                        dataBean = response.body().data.get(0);

                        List<String> pay_check_arr = dataBean.getPay_check_arr();
                        tv_paixia.setText(pay_check_arr.get(0));
                        tv_fukuan.setText(pay_check_arr.get(1));
                        tv_fahuo.setText(pay_check_arr.get(2));
                        tv_chenggong.setText(pay_check_arr.get(3));
                        tv_pingjia.setText(pay_check_arr.get(4));
                        pay_check_index = dataBean.getPay_check_index();
                        if (pay_check_index.equals("0")) {
                            bt1.setText("修改快递费");
                            bt1.setTextColor(Y.getColor(R.color.text_red));
                            bt2.setText("关闭此交易");
                            bt2.setTextColor(Y.getColor(R.color.text_color_3));
                        } else if (pay_check_index.equals("1")) {
                            bt1.setVisibility(View.INVISIBLE);
                            bt2.setText("去发货");
                            bt2.setTextColor(Y.getColor(R.color.text_red));

                            qiu_fukuan.setBackgroundResource(R.drawable.order_qiu_s);
                            line1.setBackgroundColor(Y.getColor(R.color.order_red));
                        } else if (pay_check_index.equals("2")) {
                            bt1.setVisibility(View.GONE);
                            bt2.setVisibility(View.GONE);

                            qiu_fukuan.setBackgroundResource(R.drawable.order_qiu_s);
                            qiu_fahuo.setBackgroundResource(R.drawable.order_qiu_s);
                            line1.setBackgroundColor(Y.getColor(R.color.order_red));
                            line2.setBackgroundColor(Y.getColor(R.color.order_red));
                        } else if (pay_check_index.equals("3")) {
                            bt1.setText("查看欠款去向");
                            bt1.setTextColor(Y.getColor(R.color.text_color_3));
                            bt2.setText("催评价");
                            bt2.setTextColor(Y.getColor(R.color.text_red));

                            qiu_fukuan.setBackgroundResource(R.drawable.order_qiu_s);
                            qiu_fahuo.setBackgroundResource(R.drawable.order_qiu_s);
                            qiu_chenggong.setBackgroundResource(R.drawable.order_qiu_s);
                            line1.setBackgroundColor(Y.getColor(R.color.order_red));
                            line2.setBackgroundColor(Y.getColor(R.color.order_red));
                            line3.setBackgroundColor(Y.getColor(R.color.order_red));
                        } else if (pay_check_index.equals("4")) {
                            bt1.setText("查看欠款去向");
                            bt1.setTextColor(Y.getColor(R.color.text_color_3));
                            bt2.setText("已评价");
                            bt2.setTextColor(Y.getColor(R.color.text_red));

                            qiu_fukuan.setBackgroundResource(R.drawable.order_qiu_s);
                            qiu_fahuo.setBackgroundResource(R.drawable.order_qiu_s);
                            qiu_chenggong.setBackgroundResource(R.drawable.order_qiu_s);
                            qiu_pingjia.setBackgroundResource(R.drawable.order_qiu_s);
                            line1.setBackgroundColor(Y.getColor(R.color.order_red));
                            line2.setBackgroundColor(Y.getColor(R.color.order_red));
                            line3.setBackgroundColor(Y.getColor(R.color.order_red));
                            line4.setBackgroundColor(Y.getColor(R.color.order_red));
                        }

                        Glide.with(OrderDetailsActivity.this).load(dataBean.getIndex_photo_url()).into(iv_img);
                        tv_money.setText("¥ " + dataBean.getPay_money());
                        tv_time.setText(dataBean.getCreate_time());
                        tv_title_msg.setText(dataBean.getShop_product_title());
                        tv_taocan.setText(dataBean.getProduct_title() + "        X" + dataBean.getPay_count());
                        tv_adress.setText(dataBean.getUser_addr_all());

                        tv_beizhu.setText("订单备注：" + dataBean.getShop_form_text());
                        tv_youhui.setText("优惠券：");
                        tv_mai_name.setText("买家昵称：" + dataBean.getUser_name());
                        tv_bianhao.setText("订单编号：" + dataBean.getForm_no());
                        tv_pay_type.setText("支付方式：" + dataBean.getPay_name());
                        tv_yunfei.setText("运费：" + dataBean.getForm_money_go());
                        tv_pay_time.setText("交易时间：" + dataBean.getCreate_time());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    @OnClick({R.id.tv_wuliu, R.id.tv_lianxi, R.id.bt1, R.id.bt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wuliu:
                break;
            case R.id.tv_lianxi:
                break;
            case R.id.bt1:
                clickBt1();
                break;
            case R.id.bt2:
                clickBt2();
                break;
        }
    }

    private void clickBt1() {
        if (pay_check_index.equals("0")) {//修改快递费
            Y.t("修改快递费");

        } else if (pay_check_index.equals("1")) {

        } else if (pay_check_index.equals("2")) {


        } else if (pay_check_index.equals("3")) {//查看欠款去向
            Y.t("查看欠款去向");
        } else if (pay_check_index.equals("4")) {//查看欠款去向
            Y.t("查看欠款去向");
        }
    }

    private void clickBt2() {
        if (pay_check_index.equals("0")) {//修改快递费
            Y.t("修改快递费");

        } else if (pay_check_index.equals("1")) {//去发货
            OrderFahuoActivity.actionStart(this);

        } else if (pay_check_index.equals("2")) {


        } else if (pay_check_index.equals("3")) {//催评价
            Y.t("催评价");
        } else if (pay_check_index.equals("4")) {// 查看评价
            Y.t("查看评价");
        }
    }
}
