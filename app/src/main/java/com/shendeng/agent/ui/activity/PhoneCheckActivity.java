package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.app.ConstanceValue;
import com.shendeng.agent.app.PreferenceHelper;
import com.shendeng.agent.bean.Notice;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppConfig;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.JiesuanModel;
import com.shendeng.agent.model.Message;
import com.shendeng.agent.util.TimeCount;
import com.shendeng.agent.util.Urls;
import com.shendeng.agent.util.Y;
import com.shendeng.agent.wxapi.WXEntryActivity;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PhoneCheckActivity extends BaseActivity {

    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.tv_yzm)
    TextView tvYzm;
    @BindView(R.id.bt_ok)
    Button btOk;
    @BindView(R.id.ed_pay_pwd)
    EditText ed_pay_pwd;
    @BindView(R.id.ll_pay_pwd)
    LinearLayout ll_pay_pwd;
    private String mod_id;
    private String weixinOrZhiFuBao;
    private String user_phone;
    private TimeCount timeCount;
    private String smsId;
    private IWXAPI api;
    private String sms_code;
    private String money_use;
    private String zuidiMoney;
    private String shouxufei;

    @Override
    public int getContentViewResId() {
        return R.layout.act_phone_check;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("手机验证");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PhoneCheckActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param weixinOrZhiFuBao 1支付宝 2微信 3微信解绑 4修改支付密码
     */
    public static void actionStart(Context context, String mod_id, String weixinOrZhiFuBao) {
        Intent intent = new Intent(context, PhoneCheckActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mod_id", mod_id);
        intent.putExtra("weixinOrZhiFuBao", weixinOrZhiFuBao);
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
        mod_id = getIntent().getStringExtra("mod_id");
        weixinOrZhiFuBao = getIntent().getStringExtra("weixinOrZhiFuBao");
        money_use = getIntent().getStringExtra("money_use");
        zuidiMoney = getIntent().getStringExtra("zuidiMoney");
        shouxufei = getIntent().getStringExtra("shouxufei");

        user_phone = UserManager.getManager(this).getUser_phone();
        tvPhone.setText(user_phone);
        timeCount = new TimeCount(60000, 1000, tvYzm);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.WX_SET_S) {
                    Y.t("微信绑定成功");
                    TixianActivity.actionStart(
                            PhoneCheckActivity.this,
                            "2",
                            money_use,
                            zuidiMoney,
                            shouxufei);
                    finish();
                } else if (message.type == ConstanceValue.WX_JIECHU_S) {
                    Y.t("微信解绑成功");
                    finish();
                } else if (message.type == ConstanceValue.WX_SET_F) {
                    Y.t((String) message.content);
                } else if (message.type == ConstanceValue.WX_JIECHU_F) {
                    Y.t((String) message.content);
                }
            }
        }));

        if (weixinOrZhiFuBao.equals("4")) {
            tv_title.setText("设置支付密码");
            btOk.setText("确定");
            ll_pay_pwd.setVisibility(View.VISIBLE);
        } else if (weixinOrZhiFuBao.equals("3")) {
            tv_title.setText("解绑微信");
        } else if (weixinOrZhiFuBao.equals("2")) {
            tv_title.setText("绑定微信");
        } else if (weixinOrZhiFuBao.equals("1")) {
            tv_title.setText("绑定支付宝");
        }
    }


    /**
     * 获取短信验证码
     */
    private void get_code() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00001");
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mod_id", mod_id);//微信支付宝0111, 0006修改登录密码
        Gson gson = new Gson();
        Y.e("接口数据是是呢 " + gson.toJson(map));
        OkGo.<AppResponse<Message.DataBean>>post(Urls.MSG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        Y.t("验证码获取成功");
                        timeCount.start();
                        if (response.body().data.size() > 0) {
                            smsId = response.body().data.get(0).getSms_id();
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        Y.t(response.getException().getMessage());
                        timeCount.cancel();
                        timeCount.onFinish();
                    }
                });
    }

    /**
     * 提交验证
     */
    private void requestData() {
        sms_code = edCode.getText().toString();
        if (TextUtils.isEmpty(sms_code)) {
            Y.t("请输入验证码");
            return;
        }

        smsId = "1213";
        if (TextUtils.isEmpty(smsId)) {
            Y.t("请发生验证码");
            return;
        }

        if (weixinOrZhiFuBao.equals("4")) {
            setPayPwd();
        } else {
            PreferenceHelper.getInstance(mContext).putString(AppConfig.SMS_ID, smsId);
            PreferenceHelper.getInstance(mContext).putString(AppConfig.SMS_CODE, sms_code);

            if (weixinOrZhiFuBao.equals("3")) {
                PreferenceHelper.getInstance(mContext).putString(AppConfig.WX_TYPE, "2");
                api = WXAPIFactory.createWXAPI(mContext, Y.getString(R.string.wx_app_id));
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                api.sendReq(req);
            } else if (weixinOrZhiFuBao.equals("2")) {
                PreferenceHelper.getInstance(mContext).putString(AppConfig.WX_TYPE, "1");
                api = WXAPIFactory.createWXAPI(mContext, Y.getString(R.string.wx_app_id));
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                api.sendReq(req);
            } else if (weixinOrZhiFuBao.equals("1")) {
                Intent intent = new Intent(PhoneCheckActivity.this, SetAlipayActivity.class);
                intent.putExtra("money_use", money_use);
                intent.putExtra("zuidiMoney", zuidiMoney);
                intent.putExtra("shouxufei", shouxufei);
                startActivity(intent);
            }
        }
    }

    private void setPayPwd() {
        String payPwd = ed_pay_pwd.getText().toString();
        if (TextUtils.isEmpty(payPwd)) {
            Y.t("请输入支付密码");
            return;
        }


        Map<String, String> map = new HashMap<>();
        map.put("code", "04337");
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("password", payPwd);
        map.put("sms_code", sms_code);
        map.put("sms_id", smsId);
        Gson gson = new Gson();
        OkGo.<AppResponse<JiesuanModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<JiesuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<JiesuanModel.DataBean>> response) {
                        UserManager.getManager(PhoneCheckActivity.this).setPay_pwd_check("1");
                        Y.t("设置成功");
                        finish();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                    }
                });
    }

    @OnClick({R.id.tv_yzm, R.id.bt_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_yzm:
                get_code();
                break;
            case R.id.bt_ok:
                requestData();
                break;
        }
    }
}
