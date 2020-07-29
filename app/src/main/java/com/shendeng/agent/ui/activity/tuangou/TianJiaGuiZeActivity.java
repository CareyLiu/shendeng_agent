package com.shendeng.agent.ui.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;

import butterknife.BindView;

public class TianJiaGuiZeActivity extends BaseActivity {


    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.tv_shanchu)
    TextView tvShanchu;
    @BindView(R.id.tv_queding)
    TextView tvQueding;
    @BindView(R.id.ll_ercijinru)
    LinearLayout llErcijinru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getIntent().getStringExtra("leixing").equals("0")) {

            tvShanchu.setVisibility(View.GONE);
        } else {

        }

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tian_jia_gui_ze;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("添加规则");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context leixing 0新建进入 1 二次进入
     */
    public static void actionStart(Context context, String leixing) {
        Intent intent = new Intent(context, TianJiaGuiZeActivity.class);
        intent.putExtra("leixing", leixing);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
