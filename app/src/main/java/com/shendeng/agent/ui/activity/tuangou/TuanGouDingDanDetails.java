package com.shendeng.agent.ui.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;

import butterknife.BindView;

public class TuanGouDingDanDetails extends BaseActivity {

    @BindView(R.id.tv_dingdanzhuangtai)
    TextView tvDingdanzhuangtai;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.ll_taocan_neirong)
    LinearLayout llTaocanNeirong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        srLSmart.setEnableLoadMore(false);

        for (int i = 0; i < 5; i++) {
            View view = View.inflate(mContext, R.layout.item_taocan_neirong, null);
            llTaocanNeirong.addView(view);
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tuan_gou_ding_dan_details;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("订单详情");
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context leixing 0新建进入 1 二次进入
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TuanGouDingDanDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
