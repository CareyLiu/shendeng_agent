package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.app.PreferenceHelper;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppConfig;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.JiesuanModel;
import com.shendeng.agent.util.Urls;
import com.shendeng.agent.util.Y;
import com.shendeng.agent.wxapi.WXEntryActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetAlipayActivity extends BaseActivity {


    @BindView(R.id.et_account)
    EditText et_account;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.btn_save)
    Button btn_save;
    private String sms_id;
    private String sms_code;
    private String money_use;
    private String zuidiMoney;
    private String shouxufei;

    @Override
    public int getContentViewResId() {
        return R.layout.act_set_alipay;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("绑定支付宝");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SetAlipayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        sms_id = PreferenceHelper.getInstance(this).getString(AppConfig.SMS_ID, "");
        sms_code = PreferenceHelper.getInstance(this).getString(AppConfig.SMS_CODE, "");
        money_use = getIntent().getStringExtra("money_use");
        zuidiMoney = getIntent().getStringExtra("zuidiMoney");
        shouxufei = getIntent().getStringExtra("shouxufei");
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        String pay_num = et_account.getText().toString();
        String pay_name = et_name.getText().toString();

        if (TextUtils.isEmpty(pay_num)) {
            Y.t("请输入支付宝账号/手机号码");
            return;
        }

        if (TextUtils.isEmpty(pay_name)) {
            Y.t("请输入您的真实姓名");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("code", "04334");
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("pay_num", pay_num);//支付宝账号
        map.put("pay_name", pay_name);//支付宝真实姓名
        map.put("sms_code", sms_code);
        map.put("sms_id", sms_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<JiesuanModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<JiesuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<JiesuanModel.DataBean>> response) {
                        UserManager.getManager(SetAlipayActivity.this).setAlipay_number_check("1");
                        UserManager.getManager(SetAlipayActivity.this).setAlipay_number(pay_num);
                        UserManager.getManager(SetAlipayActivity.this).setAlipay_name(pay_name);
                        Y.t("设置成功");

                        TixianActivity.actionStart(
                                SetAlipayActivity.this,
                                "1",
                                money_use,
                                zuidiMoney,
                                shouxufei);

                        finish();
                    }
                });
    }
}
