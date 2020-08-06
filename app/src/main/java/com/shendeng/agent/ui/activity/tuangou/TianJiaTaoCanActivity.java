package com.shendeng.agent.ui.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.ShangpinDetailsModel;
import com.shendeng.agent.model.TaoCanDetailsModel;
import com.shendeng.agent.model.TaoCanListModel;
import com.shendeng.agent.ui.activity.ShangpinEditActivity;
import com.shendeng.agent.util.UIHelper;
import com.shendeng.agent.util.Urls;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    @BindView(R.id.ll_bianjineirong)
    LinearLayout llBianjineirong;
    @BindView(R.id.et_taocanmingcheng)
    EditText etTaocanmingcheng;
    @BindView(R.id.et_taocanyuanjia)
    EditText etTaocanyuanjia;
    @BindView(R.id.et_taocanxianjia)
    EditText etTaocanxianjia;
    @BindView(R.id.et_taocanjianjie)
    EditText etTaocanjianjie;
    private String taoCanMingCheng, taoCanYuanjia, taoCanXianJia, taoCanJianJie;//套餐名称，套餐原价，套餐现价，套餐简介
    private String taoCanId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        srLSmart.setEnableLoadMore(false);
        llTianjiaLeimu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTaoCanActivity.actionStart(mContext, "0",taoCanId);

//                View view = View.inflate(mContext, R.layout.item_taocan_tiaomu, null);
//                ConstraintLayout constraintLayout = view.findViewById(R.id.constrain);
//                constraintLayout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        AddTaoCanActivity.actionStart(mContext, "1");
//                    }
//                });
//                llLeimu.addView(view);
//
//
//                Handler handler = new Handler();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        nested.fullScroll(ScrollView.FOCUS_DOWN);
//                    }
//                });
            }
        });

        llTianjiaGuize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TianJiaGuiZeActivity.actionStart(mContext, "0", taoCanId);

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
                //UIHelper.ToastMessage(mContext, "点击了提交");
                addShangpin();
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

        if (getIntent().getStringExtra("bianjiOrTianJia").equals("0")) {//0编辑
            llBianjineirong.setVisibility(View.VISIBLE);
        } else {//1添加
            llBianjineirong.setVisibility(View.GONE);
        }

        taoCanId = getIntent().getStringExtra("taoCanId");

        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getDetailsNet();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!StringUtils.isEmpty(taoCanId)) {
            srLSmart.autoRefresh();
        }

    }

    private void getDetailsNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04203);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("wares_id", taoCanId);

        Gson gson = new Gson();
        OkGo.<AppResponse<TaoCanDetailsModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TaoCanDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TaoCanDetailsModel.DataBean>> response) {
                        srLSmart.finishRefresh();
                        etTaocanmingcheng.setText(response.body().data.get(0).getWares_name());
                        etTaocanyuanjia.setText(response.body().data.get(0).getShop_money_old());
                        etTaocanxianjia.setText(response.body().data.get(0).getShop_money_now());
                        etTaocanjianjie.setText(response.body().data.get(0).getShop_product_title());

                        llGuize.removeAllViews();
                        if (response.body().data.get(0).getRules_list().size() != 0) {
                            for (int i = 0; i < response.body().data.get(0).getRules_list().size(); i++) {
                                View view = View.inflate(mContext, R.layout.item_taocan_guize, null);
                                ConstraintLayout constraintLayout = view.findViewById(R.id.constrain);
                                TextView tvTaoCanMing = view.findViewById(R.id.tv_taocan_ming);

                                tvTaoCanMing.setText(response.body().data.get(0).getRules_list().get(i).getPrompt_text());
                                int finalI = i;
                                constraintLayout.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        TianJiaGuiZeActivity.actionStart(mContext, "1", taoCanId, response.body().data.get(0).getRules_list().get(finalI).getPrompt_detail_id(), response.body().data.get(0).getRules_list().get(finalI).getPrompt_text());

                                    }
                                });
                                llGuize.addView(view);


//                                Handler handler = new Handler();
//                                handler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        nested.fullScroll(ScrollView.FOCUS_DOWN);
//                                    }
//                                });
                            }

                        }

                        for (int i = 0;i<response.body().data.get(0).getTaocan_list().size();i++){
                            View view = View.inflate(mContext, R.layout.item_taocan_tiaomu, null);
                            ConstraintLayout constraintLayout = view.findViewById(R.id.constrain);
                            TextView tvTaoCanMing = view.findViewById(R.id.tv_taocan_ming);
                            TextView tvTaoCanJiaGe = view.findViewById(R.id.tv_jiage);
                            TextView tvNumber = view.findViewById(R.id.tv_number);

                            tvTaoCanMing .setText(response.body().data.get(0).getTaocan_list().get(i).getMenu_text());
                            constraintLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    UIHelper.ToastMessage(mContext, "去编辑套餐");

                                }
                            });
                            llGuize.addView(view);

//                            Handler handler = new Handler();
//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    nested.fullScroll(ScrollView.FOCUS_DOWN);
//                                }
//                            });
                        }




                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }

                    @Override
                    public void onStart(Request<AppResponse<TaoCanDetailsModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
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
     *                bianjiOrTianjia 0编辑  1添加
     */
    public static void actionStart(Context context, String leixing, String bianjiOrTianJia, String taoCanId) {
        Intent intent = new Intent(context, TianJiaTaoCanActivity.class);
        intent.putExtra("leixing", leixing);
        intent.putExtra("bianjiOrTianJia", bianjiOrTianJia);
        intent.putExtra("taoCanId", taoCanId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    private void addShangpin() {

        etTaocanyuanjia.setText("100");
        etTaocanxianjia.setText("100");
        etTaocanmingcheng.setText("重庆小面");
        etTaocanjianjie.setText("重庆小面");

        taoCanYuanjia = etTaocanyuanjia.getText().toString();
        taoCanXianJia = etTaocanxianjia.getText().toString();
        taoCanMingCheng = etTaocanmingcheng.getText().toString();
        taoCanJianJie = etTaocanjianjie.getText().toString();
        if (StringUtils.isEmpty(taoCanMingCheng)) {
            UIHelper.ToastMessage(mContext, "请填写您的套餐名称");
            return;
        }
        if (StringUtils.isEmpty(taoCanYuanjia)) {
            UIHelper.ToastMessage(mContext, "请填写您的套餐原价");
            return;
        }
        if (StringUtils.isEmpty(taoCanXianJia)) {
            UIHelper.ToastMessage(mContext, "请填写您的套餐现价");
            return;
        }

        if (StringUtils.isEmpty(taoCanJianJie)) {
            UIHelper.ToastMessage(mContext, "请填写您的套餐描述");
            return;
        }


        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04179);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("enter_type", "3");
        map.put("wares_name", taoCanMingCheng);
        map.put("shop_product_title", taoCanJianJie);
        map.put("shop_money_old", taoCanYuanjia);
        map.put("shop_money_now", taoCanXianJia);


        Gson gson = new Gson();
        OkGo.<AppResponse<ShangpinDetailsModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ShangpinDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ShangpinDetailsModel.DataBean>> response) {

                        TianJiaTaoCanActivity.actionStart(mContext, "", "", null);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }

                    @Override
                    public void onStart(Request<AppResponse<ShangpinDetailsModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }
                });
    }

}
