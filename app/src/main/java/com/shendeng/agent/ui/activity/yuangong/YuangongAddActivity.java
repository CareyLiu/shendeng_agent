package com.shendeng.agent.ui.activity.yuangong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.dialog.InputDialog;
import com.shendeng.agent.util.Y;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YuangongAddActivity extends BaseActivity {

    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.ll_name)
    LinearLayout ll_name;
    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.ll_state)
    LinearLayout ll_state;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.ll_num)
    LinearLayout ll_num;
    @BindView(R.id.tv_bumen)
    TextView tv_bumen;
    @BindView(R.id.ll_bumen)
    LinearLayout ll_bumen;

    private String inst_id;
    private String of_user_id;
    private String subsystem_id;
    private String phone;
    private String name;
    private String num;

    @Override
    public int getContentViewResId() {
        return R.layout.act_yuangong_add;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("添加新员工");
        tv_rightTitle.setText("下一步");
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setTextSize(17);
        tv_rightTitle.setTextColor(this.getResources().getColor(R.color.text_red));
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String of_user_id, String inst_id, String subsystem_id) {
        Intent intent = new Intent();
        intent.setClass(context, YuangongAddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("of_user_id", of_user_id);
        intent.putExtra("inst_id", inst_id);
        intent.putExtra("subsystem_id", subsystem_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        inst_id = getIntent().getStringExtra("inst_id");
        of_user_id = getIntent().getStringExtra("of_user_id");
        subsystem_id = getIntent().getStringExtra("subsystem_id");
    }

    @OnClick({R.id.ll_phone, R.id.ll_name, R.id.ll_state, R.id.ll_num, R.id.ll_bumen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_phone:
                clickPhone();
                break;
            case R.id.ll_name:
                clickName();
                break;
            case R.id.ll_state:
                clickState();
                break;
            case R.id.ll_num:
                clickNum();
                break;
            case R.id.ll_bumen:
                clickBumen();
                break;
        }
    }

    private void clickPhone() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                phone = dialog.getTextContent();
                if (TextUtils.isEmpty(phone)) {
                    Y.t("请输入员工手机号");
                } else {
                    tv_phone.setText(phone);
                    dialog.dismiss();
                }
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setDismissAfterClick(false);
        dialog.setTextInput(InputType.TYPE_CLASS_TEXT);
        dialog.setTextTitle("请输入员工手机号");
        dialog.setTextContent(tv_phone.getText().toString());
        dialog.show();
    }

    private void clickName() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                name = dialog.getTextContent();
                if (TextUtils.isEmpty(name)) {
                    Y.t("请输入员工姓名");
                } else {
                    tv_name.setText(name);
                    dialog.dismiss();
                }
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setDismissAfterClick(false);
        dialog.setTextInput(InputType.TYPE_CLASS_TEXT);
        dialog.setTextTitle("请输入员工姓名");
        dialog.setTextContent(tv_name.getText().toString());
        dialog.show();
    }

    private void clickState() {

    }

    private void clickNum() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                num = dialog.getTextContent();
                if (TextUtils.isEmpty(num)) {
                    Y.t("请输入员工编号");
                } else {
                    tv_num.setText(num);
                    dialog.dismiss();
                }
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setDismissAfterClick(false);
        dialog.setTextInput(InputType.TYPE_CLASS_TEXT);
        dialog.setTextTitle("请输入员工编号");
        dialog.setTextContent(tv_num.getText().toString());
        dialog.show();
    }

    private void clickBumen() {

    }
}
