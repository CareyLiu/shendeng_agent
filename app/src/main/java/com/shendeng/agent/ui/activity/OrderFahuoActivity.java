package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.model.WuliuModel;
import com.shendeng.agent.util.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderFahuoActivity extends BaseActivity {


    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_adress)
    TextView tv_adress;
    @BindView(R.id.tv_kuaidigongsi)
    TextView tv_kuaidigongsi;
    @BindView(R.id.ll_select_gongsi)
    LinearLayout ll_select_gongsi;
    @BindView(R.id.ll_saoyisao)
    LinearLayout ll_saoyisao;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_xl_kc)
    TextView tv_xl_kc;
    @BindView(R.id.tv_time)
    TextView tv_time;

    private List<WuliuModel.DataBean> wuliuModels = new ArrayList<>();
    private OptionsPickerView pickerSelct;
    private String express_name;
    private String express_code;
    private String express_id;

    @Override
    public int getContentViewResId() {
        return R.layout.act_order_fahuo;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("发货");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OrderFahuoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
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
        getWuliu();
    }

    private void getWuliu() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00005");
        map.put("key", Urls.KEY);
        map.put("type_id", "express");//物流公司
        Gson gson = new Gson();
        OkGo.<AppResponse<WuliuModel.DataBean>>post(Urls.MSG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<WuliuModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<WuliuModel.DataBean>> response) {
                        wuliuModels = response.body().data;
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });


    }

    @OnClick(R.id.ll_select_gongsi)
    public void onViewClicked() {
        showSelect();


    }

    private void showSelect() {

        if (pickerSelct == null) {
            List<String> names = new ArrayList<>();
            for (int i = 0; i < wuliuModels.size(); i++) {
                names.add(wuliuModels.get(i).getName());
            }

            //条件选择器
            pickerSelct = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    WuliuModel.DataBean dataBean = wuliuModels.get(options1);
                    express_name = dataBean.getName();
                    express_code = dataBean.getVal1();
                    express_id = dataBean.getId();
                    tv_kuaidigongsi.setText(express_name);
                }
            }).build();
            pickerSelct.setPicker(names);
        }


        pickerSelct.show();
    }
}
