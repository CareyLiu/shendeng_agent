package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.app.PreferenceHelper;
import com.shendeng.agent.config.AppCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginPwdActivity extends BaseActivity {


    @BindView(R.id.tv_pwd)
    TextView tv_pwd;
    @BindView(R.id.ed_pwd)
    EditText ed_pwd;
    @BindView(R.id.tv_pwd_two)
    TextView tv_pwd_two;
    @BindView(R.id.ed_pwd_two)
    EditText ed_pwd_two;
    @BindView(R.id.bt_ok)
    Button bt_ok;

    private String sms_id;
    private String sms_code;
    private String mod_id;

    @Override
    public int getContentViewResId() {
        return R.layout.act_login_pwd;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String mod_id) {
        Intent intent = new Intent();
        intent.setClass(context, LoginPwdActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("mod_id", mod_id);
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
        sms_id = PreferenceHelper.getInstance(this).getString(AppCode.SMS_ID, "");
        sms_code = PreferenceHelper.getInstance(this).getString(AppCode.SMS_CODE, "");
        mod_id = getIntent().getStringExtra("mod_id");
        if (mod_id.equals(AppCode.mod_login_pwd) || mod_id.equals(AppCode.mod_login_pwd_zhaohui)) {
            tv_title.setText("设置登录密码");
            ed_pwd.setHint("请输入登录密码");
            ed_pwd_two.setHint("请确认登录密码");
        } else if (mod_id.equals(AppCode.mod_zhifu_pwd)) {
            tv_title.setText("设置支付密码");
            ed_pwd.setHint("请输入支付密码");
            ed_pwd_two.setHint("请确认支付密码");
        }
    }

    @OnClick(R.id.bt_ok)
    public void onViewClicked() {

    }
}
