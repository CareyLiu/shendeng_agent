package com.shendeng.agent.ui.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.util.UIHelper;

import butterknife.BindView;

public class TianJiaTaoCanActivity extends BaseActivity {

    @BindView(R.id.tv_fenlei_2)
    TextView tvFenlei2;
    @BindView(R.id.iv_taocan_tupian)
    ImageView ivTaocanTupian;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_tup_num)
    TextView tvTupNum;
    @BindView(R.id.nested)
    NestedScrollView nested;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.ll_tianjia_leimu)
    LinearLayout llTianjiaLeimu;
    @BindView(R.id.ll_leimu)
    LinearLayout llLeimu;
    @BindView(R.id.ll_guize)
    LinearLayout llGuize;
    @BindView(R.id.ll_tianjia_guize)
    LinearLayout llTianjiaGuize;
    @BindView(R.id.rl_shoucijinru)
    RelativeLayout rlShoucijinru;
    @BindView(R.id.ll_ercijinru)
    LinearLayout llErcijinru;
    @BindView(R.id.tv_fangrucangku)
    TextView tvFangrucangku;
    @BindView(R.id.tv_shangjiaxiaoshou)
    TextView tvShangjiaxiaoshou;
    @BindView(R.id.constrain_tupian)
    ConstraintLayout constrainTupian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        srLSmart.setEnableLoadMore(false);
        llTianjiaLeimu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AddTaoCanActivity.actionStart(mContext, "0");

                View view = View.inflate(mContext, R.layout.item_taocan_tiaomu, null);
                ConstraintLayout constraintLayout = view.findViewById(R.id.constrain);
                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AddTaoCanActivity.actionStart(mContext, "1");
                    }
                });
                llLeimu.addView(view);


                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        nested.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });

        llTianjiaGuize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(mContext, R.layout.item_taocan_guize, null);
                ConstraintLayout constraintLayout = view.findViewById(R.id.constrain);
                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TianJiaGuiZeActivity.actionStart(mContext, "0");
                        UIHelper.ToastMessage(mContext,"我点击了添加规则");

                    }
                });
                llGuize.addView(view);


                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        nested.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });


        String str = getIntent().getStringExtra("leixing");

        if (str.equals("0")) {
            rlShoucijinru.setVisibility(View.VISIBLE);
            llErcijinru.setVisibility(View.GONE);
        } else if (str.equals("1")) {
            llErcijinru.setVisibility(View.VISIBLE);
            rlShoucijinru.setVisibility(View.GONE);
        }


        rlShoucijinru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "点击了提交");
            }
        });
        tvFangrucangku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "点击了放入仓库");
            }
        });
        tvShangjiaxiaoshou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "点击了上架销售");
            }
        });
        constrainTupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenDianCaiDanActivity.actionStart(TianJiaTaoCanActivity.this);
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tianjia_taocan;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("添加套餐");
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
        Intent intent = new Intent(context, TianJiaTaoCanActivity.class);
        intent.putExtra("leixing", leixing);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
