package com.shendeng.agent.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.dialog.InputDialog;
import com.shendeng.agent.model.ChandiModel;
import com.shendeng.agent.model.LeimuModel;
import com.shendeng.agent.model.ShangpinDetailsModel;
import com.shendeng.agent.util.Urls;
import com.shendeng.agent.util.Y;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShangpinAddActivity extends BaseActivity {

    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.ed_title_name)
    TextView ed_title_name;
    @BindView(R.id.tv_leimu)
    TextView tv_leimu;
    @BindView(R.id.ll_leimu)
    LinearLayout ll_leimu;
    @BindView(R.id.iv_swich)
    ImageView iv_swich;
    @BindView(R.id.ll_anzhuangfuwu)
    LinearLayout ll_anzhuangfuwu;
    @BindView(R.id.tv_chandi)
    TextView tv_chandi;
    @BindView(R.id.ll_chandi)
    LinearLayout ll_chandi;
    @BindView(R.id.bt_ok)
    TextView bt_ok;
    @BindView(R.id.ll_anzhuangfei)
    LinearLayout ll_anzhuangfei;
    @BindView(R.id.tv_anzhuangfei)
    TextView tv_anzhuangfei;
    @BindView(R.id.tv_yunfei)
    TextView tv_yunfei;
    @BindView(R.id.ll_yunfei)
    LinearLayout ll_yunfei;

    private List<ChandiModel.DataBean> chandiModels;
    private List<LeimuModel.DataBean> leimuModels;
    private OptionsPickerView<Object> leimuPicker;
    private OptionsPickerView<Object> chandiPicker;
    private String item_id_one;
    private String item_id_one_name;
    private String item_id_two;
    private String item_id_two_name;
    private String item_id_three;
    private String item_id_three_name;
    private String province_name_pro;
    private String province_id_pro;
    private String city_name_pro;
    private String city_id_pro;
    private String enter_type;//录入类型：1.商城普通商品 2.拼购商品 3.团购商品套餐4.非可录入商品类型
    private String wares_name;
    private String wares_money_go;
    private String is_installable;//是否安装 1.安装 2.不安装
    private String install_money;
    private ShangpinDetailsModel.DataBean detailsModel;
    private InputDialog dialog;

    @Override
    public int getContentViewResId() {
        return R.layout.act_shangpin_add;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("添加商品");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ShangpinAddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        enter_type = "1";
        is_installable = "2";
        Window win = this.getWindow();
        win.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    private void getChandi() {
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_00005);
        map.put("key", Urls.KEY);
        map.put("type_id", "province_city_all");//物流公司
        Gson gson = new Gson();
        OkGo.<AppResponse<ChandiModel.DataBean>>post(Urls.MSG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChandiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChandiModel.DataBean>> response) {
                        chandiModels = response.body().data;
                        initChandi();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    private void getLeimu() {
        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04193);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<LeimuModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<LeimuModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<LeimuModel.DataBean>> response) {
                        leimuModels = response.body().data;
                        initLeimu();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }


    private void showSelectLeimu() {
        if (leimuPicker == null) {
            showProgressDialog();
            getLeimu();
        } else {
            leimuPicker.show();
        }
    }

    private void initLeimu() {
        if (leimuModels.size() > 0) {
            List<Object> names1 = new ArrayList<>();
            List<List<Object>> names2 = new ArrayList<>();
            List<List<List<Object>>> names3 = new ArrayList<>();

            for (int i = 0; i < leimuModels.size(); i++) {
                LeimuModel.DataBean bean = leimuModels.get(i);
                names1.add(bean.getItem_name());
                List<LeimuModel.DataBean.NextLevelBeanX> next2 = bean.getNext_level();
                List<Object> names2Beans = new ArrayList<>();
                List<List<Object>> names3Beans = new ArrayList<>();
                for (int j = 0; j < next2.size(); j++) {
                    LeimuModel.DataBean.NextLevelBeanX nextLevelBeanX = next2.get(j);
                    names2Beans.add(nextLevelBeanX.getItem_name());
                    List<LeimuModel.DataBean.NextLevelBeanX.NextLevelBean> next3 = nextLevelBeanX.getNext_level();

                    List<Object> names3BeansZi = new ArrayList<>();
                    for (int k = 0; k < next3.size(); k++) {
                        names3BeansZi.add(next3.get(k).getItem_name());
                    }
                    names3Beans.add(names3BeansZi);

                }
                names2.add(names2Beans);
                names3.add(names3Beans);
            }

            //条件选择器
            leimuPicker = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    if (leimuModels != null && leimuModels.size() > 0) {
                        LeimuModel.DataBean dataBean = leimuModels.get(options1);
                        String is_install = dataBean.getIs_install();
                        item_id_one = dataBean.getItem_id();
                        item_id_one_name = dataBean.getItem_name();
                        List<LeimuModel.DataBean.NextLevelBeanX> next_level = dataBean.getNext_level();
                        if (next_level != null && next_level.size() > 0) {
                            LeimuModel.DataBean.NextLevelBeanX nextLevelBeanX = next_level.get(option2);
                            item_id_two = nextLevelBeanX.getItem_id();
                            item_id_two_name = nextLevelBeanX.getItem_name();
                            List<LeimuModel.DataBean.NextLevelBeanX.NextLevelBean> next_level1 = nextLevelBeanX.getNext_level();
                            if (next_level1 != null && next_level1.size() > 0) {
                                LeimuModel.DataBean.NextLevelBeanX.NextLevelBean nextLevelBean = next_level1.get(options3);
                                item_id_three = nextLevelBean.getItem_id();
                                item_id_three_name = nextLevelBean.getItem_name();

                                tv_leimu.setText(item_id_one_name + "-" + item_id_two_name + "-" + item_id_three_name);
                            } else {
                                item_id_three = "";
                                item_id_three_name = "";
                                tv_leimu.setText(item_id_one_name + "-" + item_id_two_name);
                            }
                        } else {
                            item_id_two = "";
                            item_id_two_name = "";
                            tv_leimu.setText(item_id_one_name);
                        }
                    } else {
                        item_id_one = "";
                        item_id_one_name = "";
                        tv_leimu.setText("");
                    }
                }
            }).build();

            if (names2.size() > 0) {
                if (names3.size() > 0) {
                    leimuPicker.setPicker(names1, names2, names3);
                } else {
                    leimuPicker.setPicker(names1, names2);
                }
            } else {
                leimuPicker.setPicker(names1);
            }

            leimuPicker.show();
        }
    }

    private void showSelectChandi() {
        if (chandiPicker == null) {
            showProgressDialog();
            getChandi();
        } else {
            chandiPicker.show();
        }
    }

    private void initChandi() {
        List<Object> names1 = new ArrayList<>();
        List<List<Object>> names2 = new ArrayList<>();
        for (int i = 0; i < chandiModels.size(); i++) {
            ChandiModel.DataBean bean = chandiModels.get(i);
            names1.add(bean.getCode_name());

            List<ChandiModel.DataBean.CityBean> city = bean.getCity();
            List<Object> names2Beans = new ArrayList<>();
            for (int j = 0; j < city.size(); j++) {
                ChandiModel.DataBean.CityBean cityBean = city.get(j);
                names2Beans.add(cityBean.getCode_name());
            }
            names2.add(names2Beans);
        }

        //条件选择器
        chandiPicker = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                ChandiModel.DataBean dataBean = chandiModels.get(options1);
                ChandiModel.DataBean.CityBean cityBean = dataBean.getCity().get(option2);

                province_name_pro = dataBean.getCode_name();
                province_id_pro = dataBean.getCode_id();
                city_name_pro = cityBean.getCode_name();
                city_id_pro = cityBean.getCode_id();

                tv_chandi.setText(province_name_pro + "-" + city_name_pro);
            }
        }).build();
        chandiPicker.setPicker(names1, names2);
        chandiPicker.show();
    }


    @OnClick({R.id.ll_leimu, R.id.iv_swich, R.id.ll_chandi, R.id.bt_ok, R.id.ll_anzhuangfei, R.id.ll_yunfei, R.id.ed_title_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_leimu:
                showSelectLeimu();
                break;
            case R.id.iv_swich:
                swich();
                break;
            case R.id.ll_chandi:
                showSelectChandi();
                break;
            case R.id.bt_ok:
                addShangpin();
                break;
            case R.id.ll_anzhuangfei:
                clickAnzhuangfei();
                break;
            case R.id.ll_yunfei:
                clickYunfei();
                break;
            case R.id.ed_title_name:
                clickEd();
                break;
        }
    }

    private void clickAnzhuangfei() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                tv_anzhuangfei.setText(dialog.getTextContent());
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请设置安装费");
        dialog.setTextContent(tv_anzhuangfei.getText().toString());
        dialog.show();
    }

    private void clickYunfei() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                tv_yunfei.setText(dialog.getTextContent());
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });

        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请设置运费");
        dialog.setTextContent(tv_yunfei.getText().toString());
        dialog.show();
    }

    private void clickEd() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                ed_title_name.setText(dialog.getTextContent());
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setTextInput(InputType.TYPE_CLASS_TEXT);
        dialog.setTextTitle("请输入商品标题");
        dialog.setTextContent(ed_title_name.getText().toString());
        dialog.show();
    }

    private void swich() {
        if (is_installable.equals("1")) {
            is_installable = "2";
            iv_swich.setImageResource(R.mipmap.swich_off);
            ll_anzhuangfei.setVisibility(View.GONE);
        } else {
            is_installable = "1";
            iv_swich.setImageResource(R.mipmap.swich_on);
            ll_anzhuangfei.setVisibility(View.VISIBLE);
        }
    }

    private void addShangpin() {
        wares_name = ed_title_name.getText().toString();
        wares_money_go = tv_yunfei.getText().toString();
        install_money = tv_anzhuangfei.getText().toString();
        String textLeimu = tv_leimu.getText().toString();
        String textChandi = tv_chandi.getText().toString();

        if (TextUtils.isEmpty(wares_name)) {
            Y.t("请输入商品标题");
            return;
        }

        if (TextUtils.isEmpty(textLeimu)) {
            Y.t("请选择类目");
            return;
        }

        if (is_installable.equals("1")) {
            if (TextUtils.isEmpty(install_money)) {
                Y.t("请输入安装费用");
                return;
            }
        }

        if (TextUtils.isEmpty(wares_money_go)) {
            Y.t("请输入运费");
            return;
        }

        if (TextUtils.isEmpty(textChandi)) {
            Y.t("请选择产地");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("code", Urls.code_04179);
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("enter_type", enter_type);
        map.put("wares_name", wares_name);
        map.put("item_id_one", item_id_one);
        map.put("item_id_one_name", item_id_one_name);
        map.put("item_id_two", item_id_two);
        map.put("item_id_two_name", item_id_two_name);
        map.put("item_id_three", item_id_three);
        map.put("item_id_three_name", item_id_three_name);
        map.put("wares_money_go", wares_money_go);
        map.put("province_id_pro", province_id_pro);
        map.put("province_name_pro", province_name_pro);
        map.put("city_id_pro", city_id_pro);
        map.put("city_name_pro", city_name_pro);
        map.put("is_installable", is_installable);
        map.put("install_money", install_money);

        Gson gson = new Gson();
        OkGo.<AppResponse<ShangpinDetailsModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ShangpinDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ShangpinDetailsModel.DataBean>> response) {
                        detailsModel = response.body().data.get(0);
                        ShangpinEditActivity.actionStart(mContext, detailsModel);
                        finish();
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
