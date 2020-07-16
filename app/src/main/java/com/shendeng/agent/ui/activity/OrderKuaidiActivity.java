package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
import com.shendeng.agent.dialog.TishiDialog;
import com.shendeng.agent.model.PingjiaModel;
import com.shendeng.agent.util.TimeUtils;
import com.shendeng.agent.util.Urls;
import com.shendeng.agent.util.Y;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderKuaidiActivity extends BaseActivity {

    @BindView(R.id.tv_yuan_money)
    TextView tv_yuan_money;
    @BindView(R.id.ed_new_money)
    EditText ed_new_money;
    @BindView(R.id.tv_ok)
    TextView tv_ok;
    private String shop_form_id;

    @Override
    public int getContentViewResId() {
        return R.layout.act_order_kuaidifei;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("修改快递费");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String form_money_go, String shop_form_id) {
        Intent intent = new Intent();
        intent.setClass(context, OrderKuaidiActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("form_money_go", form_money_go);
        intent.putExtra("shop_form_id", shop_form_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        tv_yuan_money.setText(getIntent().getStringExtra("form_money_go") + "元");
        shop_form_id = getIntent().getStringExtra("shop_form_id");

        ed_new_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                double money = Y.getDouble(s.toString());
                if (money > 0) {
                    tv_ok.setEnabled(true);
                } else {
                    tv_ok.setEnabled(false);
                }
            }
        });
    }

    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04314);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("shop_form_id", shop_form_id);
        map.put("form_money_go", ed_new_money.getText().toString());
        Gson gson = new Gson();
        OkGo.<AppResponse<PingjiaModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<PingjiaModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<PingjiaModel.DataBean>> response) {
                        Y.t("修改成功");
                        finish();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }
}
