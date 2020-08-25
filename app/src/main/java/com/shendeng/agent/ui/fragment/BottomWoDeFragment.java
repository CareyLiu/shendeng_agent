package com.shendeng.agent.ui.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shendeng.agent.R;
import com.shendeng.agent.app.AppConfig;
import com.shendeng.agent.app.PreferenceHelper;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.bean.Notice;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.model.WodeModel;
import com.shendeng.agent.ui.HomeBasicTuanGouActivity;
import com.shendeng.agent.ui.activity.AboutActivity;
import com.shendeng.agent.ui.activity.SettingActivity;
import com.shendeng.agent.ui.activity.WodeQainbaoActivity;
import com.shendeng.agent.ui.activity.WodeTuihuoActivity;
import com.shendeng.agent.util.UIHelper;
import com.shendeng.agent.util.Urls;
import com.shendeng.agent.util.Y;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BottomWoDeFragment extends BaseFragment {
    public static final String TAG = "BottomWoDeFragment";
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_set)
    ImageView iv_set;
    @BindView(R.id.iv_head)
    CircleImageView iv_head;
    @BindView(R.id.tv_admin)
    TextView tv_admin;
    @BindView(R.id.iv_shenfen)
    ImageView iv_shenfen;
    @BindView(R.id.tv_guanzhu)
    TextView tv_guanzhu;
    @BindView(R.id.tv_shoucang)
    TextView tv_shoucang;
    @BindView(R.id.tv_kaquan)
    TextView tv_kaquan;
    @BindView(R.id.ll_qianbao)
    LinearLayout ll_qianbao;
    @BindView(R.id.ll_about)
    LinearLayout ll_about;
    @BindView(R.id.ll_dizhi)
    LinearLayout ll_dizhi;
    @BindView(R.id.ll_yuangong)
    LinearLayout ll_yuangong;
    @BindView(R.id.ll_qiehuan)
    LinearLayout ll_qiehuan;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.tv_change_moshi)
    TextView tvChangeMoshi;
    private WodeModel.DataBean userMain;
    private String inst_owner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {

            }
        }));

    }

    public static BottomWoDeFragment newInstance() {
        Bundle args = new Bundle();
        BottomWoDeFragment fragment = new BottomWoDeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_mian_mine;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initLogic() {
//        int roleNumber = Integer.parseInt(PreferenceHelper.getInstance(getActivity()).getString(AppConfig.ROLE_NUMBER, "0"));
//        if (roleNumber == 2) {//双角色时候展示切换按钮
//            ll_qiehuan.setVisibility(View.VISIBLE);
//        } else {
//            ll_qiehuan.setVisibility(View.GONE);
//        }
    }


    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .titleBar(toolbar)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initStart();
        return rootView;
    }

    public static int getStatusBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    private void initStart() {
        getNet();
        initSM();
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04331");
        map.put("key", Urls.KEY);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<WodeModel.DataBean>>post(Urls.WORKER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<WodeModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<WodeModel.DataBean>> response) {
                        userMain = response.body().data.get(0);
                        tv_title.setText(userMain.getInst_name());
                        inst_owner = userMain.getInst_owner();
                        if (inst_owner.equals("1")) {//员工管理
//                            ll_yuangong.setVisibility(View.VISIBLE);
                            ll_yuangong.setVisibility(View.GONE);
                            iv_shenfen.setImageResource(R.mipmap.mine_boss);
                        } else {
                            ll_yuangong.setVisibility(View.GONE);
                            iv_shenfen.setImageResource(R.mipmap.mine_yuangong);
                        }

                        tv_admin.setText(userMain.getUser_phone());
                        tv_guanzhu.setText(userMain.getShop_collection_count());
                        tv_shoucang.setText(userMain.getWares_collection_count());
                        tv_kaquan.setText("0");

                        Glide.with(getContext()).load(userMain.getWx_img_url())
                                .error(R.mipmap.mine_pic_touxiang_tb)
                                .into(iv_head);

                        UserManager.getManager(getActivity()).saveUserInfo(userMain);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        tvChangeMoshi.setText("点击切换到团购商家模式");
    }

    @OnClick({R.id.iv_set, R.id.ll_qianbao, R.id.ll_about, R.id.ll_dizhi, R.id.ll_yuangong, R.id.ll_qiehuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_qianbao:
                if (inst_owner.equals("1")) {
                    WodeQainbaoActivity.actionStart(getContext());
                } else {
                    Y.t("您没有打开钱包的权限哦");
                }
                break;
            case R.id.ll_about:
                AboutActivity.actionStart(getContext());
                break;
            case R.id.ll_dizhi:
                WodeTuihuoActivity.actionStart(getContext());
                break;
            case R.id.ll_yuangong:
                break;
            case R.id.ll_qiehuan:
                HomeBasicTuanGouActivity.actionStart(getActivity());
                break;
            case R.id.iv_set:
                SettingActivity.actionStart(getContext(), inst_owner);
                break;
        }
    }
}
