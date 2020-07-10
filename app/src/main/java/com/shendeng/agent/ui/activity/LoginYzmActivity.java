package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.model.Message;
import com.shendeng.agent.util.TimeCount;
import com.shendeng.agent.util.Urls;
import com.shendeng.agent.util.Y;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginYzmActivity extends BaseActivity {


    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_code)
    EditText ed_code;
    @BindView(R.id.tv_yzm)
    TextView tv_yzm;
    @BindView(R.id.bt_ok)
    Button bt_ok;

    private TimeCount timeCount;
    private String smsId;//短信验证码id
    private String req_type;//登录场景:1.密码登陆2.手机验证码登陆

    @Override
    public int getContentViewResId() {
        return R.layout.act_login_yzm;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("手机验证");
        tv_title.setTextSize(17);
        tv_title.setTextColor(this.getResources().getColor(R.color.color_494949));
        tv_title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginYzmActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @OnClick({R.id.tv_yzm, R.id.bt_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_yzm:
                getCode();
                break;
            case R.id.bt_ok:
                requestData();
                break;
        }
    }


    /**
     * 获取短信验证码
     */
    private void getCode() {
        if (TextUtils.isEmpty(ed_phone.getText().toString())) {
            Y.t("请输入手机号");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("code", "00001");
        map.put("key", Urls.KEY);
        map.put("user_phone", ed_phone.getText().toString());
        map.put("mod_id", "0007");
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(Urls.MSG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        Y.t(response.body().msg);
                        timeCount.start();
                        smsId = response.body().data.get(0).getSms_id();
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
        Map<String, String> map = new HashMap<>();
        map.put("code", "0006");
        map.put("key", Urls.KEY);
        map.put("sms_id", smsId);
        map.put("sms_code", ed_phone.getText().toString());
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(Urls.MSG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        LoginPwdActivity.actionStart(LoginYzmActivity.this);
                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        Y.t(response.getException().getMessage());
                    }
                });
    }
}
